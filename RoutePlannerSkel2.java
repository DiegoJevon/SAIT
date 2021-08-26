package Assignment_3;

/**
 * RoutePlanner
 * This program reads in a segments associated with a route to determine the length of the
 * route and the estimated time to complete the route.
 * 
 * The computer reads in a list of segments associated with a route and determine the length of the
 * route and it's duration.
 * 
 * @author Dave Leskiw
 * @version March 11, 2021
 */
// Standard import for the Scanner class
import java.util.*;
import java.io.*;

public class RoutePlannerSkel2 {
    public static void main (String [] args) throws IOException {
        // Create a Scanner object attached to the keyboard
        Scanner in = new Scanner (System.in);
        // create the route as a series of Segments
        ArrayList<Segment> route1 = new ArrayList<>();
        ArrayList<Segment> route2 = new ArrayList<>();
        String route1Filename;
        String route2Filename;
        int selection = 0;
        int segments1 = 0;
        int segments2 = 0;
        selection = displayMenu (in);
        while (selection != 9) {
            switch (selection) {

                case 1:
                    // clear any existing route
                    if (route1 != null) route1.clear();
                    // Load route from file
                    System.out.printf ("Enter the route filename: ");
                    route1Filename = in.nextLine();                    
                    segments1 = loadRoute(route1, route1Filename);
                    if (segments1 != 0) {
                        // successful
                        System.out.printf ("%d segments loaded%n", segments1);
                    }
                    else {
                        // can't open the file
                        System.out.printf ("Can't open file%n");
                    }
                    break;
                case 2:
                    if (segments1 != 0) {
                        // Analyse route
                        analyseRoute (route1);
                    }
                    else {
                        // can't open the file
                        System.out.printf ("File not founded. Please try to load the route from the file.%n");
                    } 
                    break;
                case 3:
                    // clear any existing route
                    if (route1 != null) route1.clear();
                    if (route2 != null) route2.clear();
                    // Load route from file
                    System.out.printf ("Enter the route filename: ");
                    route1Filename = in.nextLine();                    
                    segments1 = loadRoute(route1, route1Filename);
                    System.out.printf ("Enter the route filename: ");
                    route2Filename = in.nextLine();                    
                    segments2 = loadRoute(route2, route2Filename);
                    if (segments1 != 0 && segments2 != 0) {
                        // successful
                        System.out.printf ("%d segments loaded%n", segments1);
                        System.out.printf ("%d segments loaded%n", segments2);
                        analyse2Routes (route1,route2);

                    }
                    else {
                        // can't open the file
                        System.out.printf ("Can't open file%n");
                    }
                    break;
                default: 
                    System.out.printf("Please select a valiable option!%n");
                    break;
            }
            System.out.printf ("Press an Enter to continue...");
            in.nextLine();
            selection = displayMenu (in);
        }
    }

    /**
     * displayMenu
     * Displays the menu of user options and reads in a selection from the user.
     * @return the user selection as a number
     */
    public static int displayMenu (Scanner in) 
    {
        System.out.printf ("\f%n");
        System.out.printf ("Route Analyser%n");
        System.out.printf ("1) Load route from one file%n");
        System.out.printf ("2) Analyse one route%n");
        System.out.printf ("3) Load and compare for two routes%n");
        System.out.printf ("9) Exit%n");
        System.out.printf ("Enter your selection: ");

        int selection = in.nextInt();
        in.nextLine(); // discard \n
        return selection;
    }

    /** loadRoute
     * Loads a route (series of segments) for a text file attached to the Scanner object 
     * 'routeScanner'.
     * @param list an arrayList of segments
     * @param routeFilename path of the route file
     * @return numSegments returns the number of segments in the route, 0 if the file was not found
     */
    public static int loadRoute (ArrayList<Segment> route, String routeFilename) throws IOException {
        //declaring variables        
        int count = 0;
        String data;
        String[] routePoint;
        Segment line;
        Point start;
        Point end;
        double degreeLat;
        double degreeLong;
        double avgSpeed;

        //creating file reader        
        File fin = new File (routeFilename);
        //checking if the file exists
        if (!fin.exists()) return count; 
        //scanner to read from the file
        Scanner fscanner = new Scanner(fin);      

        //reading data from the file
        fscanner.nextLine();
        data = fscanner.nextLine();
        routePoint = data.split(",");

        //adding latitude and longtude into a variable
        degreeLat = Double.parseDouble(routePoint[0]);            
        degreeLong = Double.parseDouble(routePoint[1]);

        //creating the start point with Lat and Long
        start = new Point (degreeLat, degreeLong);
        //adding avg speed into a variable
        avgSpeed =  Double.parseDouble(routePoint[2]);

        while (fscanner.hasNextLine()) {
            data = fscanner.nextLine();
            routePoint = data.split(",");
            //the data must contain three information
            if (routePoint.length != 3) {
                System.out.println("File has incorrent data. Please check the file contents!");
                return count;               
            }            
            //reading latitude and longitude
            degreeLat = Double.parseDouble(routePoint[0]);            
            degreeLong = Double.parseDouble(routePoint[1]);
            //saving Lat and Long data in ArrayList
            end = new Point (degreeLat, degreeLong);            

            //adding from Point and AvgSpeed  into Segment ArrayList
            line = new Segment (start,end,avgSpeed);
            route.add(line);
            //setting up the avgsped for the next point
            avgSpeed =  Double.parseDouble(routePoint[2]);
            //bringng the point end to be the point start for the next route
            start = end;
            //adding count for the next segment
            count++;
        }
        return count;
    }

    /** analyseRoute
     * Given a route determine it's length and duration
     * @param route an arrayList of segments
     */
    public static void analyseRoute (ArrayList<Segment> route)  {
        //declaring variable
        double distance=0;
        double time=0;

        //calculating distante and time from Segment ArrayList
        for(int i = 0; i< route.size(); i++){
            distance += route.get(i).length();
            time += route.get(i).estimatedTime();
        }    

        //printing into terminal        
        System.out.printf("Route data%n");
        System.out.printf("%-15s%10s%n","Description","Value");
        System.out.printf("%-15s%10d%n","Segments",route.size());
        System.out.printf("%-15s%10.1f%n","KM",distance);
        System.out.printf("%-15s%10.0f%n","Minutes",time);
    }

    /** analyseRoute
     * Given a route determine it's length and duration
     * @param route an arrayList of segments
     */
    public static void analyse2Routes (ArrayList<Segment> route1,ArrayList<Segment> route2)  {
        //declaring variable
        double distanceRoute1=0;
        double timeRoute1=0;
        double distanceRoute2=0;
        double timeRoute2=0;

        //calculating distante and time from Segment ArrayList
        for(int i=0; i<route1.size(); i++){
            distanceRoute1 += route1.get(i).length();
            timeRoute1 += route1.get(i).estimatedTime();
        }

        for(int i=0; i<route2.size(); i++){
            distanceRoute2 += route2.get(i).length();
            timeRoute2 += route2.get(i).estimatedTime();
        }

        //printing route 1 into terminal        
        System.out.printf("Route1 data%n");
        System.out.printf("%-15s%10s%n","Description","Value");
        System.out.printf("%-15s%10d%n","Segments",route1.size());
        System.out.printf("%-15s%10.2f%n","KM",distanceRoute1);
        System.out.printf("%-15s%10.0f%n","Minutes",timeRoute1);
        //skip line  
        System.out.printf("%n");
        //printing route 1 into terminal   
        System.out.printf("Route2 data%n");
        System.out.printf("%-15s%10s%n","Description","Value");
        System.out.printf("%-15s%10d%n","Segments",route2.size());
        System.out.printf("%-15s%10.2f%n","KM",distanceRoute2);
        System.out.printf("%-15s%10.0f%n","Minutes",timeRoute2);
        
        //comparing distance between two routes
        if (distanceRoute1<=distanceRoute2){
            System.out.printf("The first route is shorter than the second for %.2f KM%n",distanceRoute1-distanceRoute2);
        }else{System.out.printf("The second route is shorter than the first for %.2f KM%n",distanceRoute2-distanceRoute1);}
        //comparing time between two routes
        if (timeRoute1<=timeRoute2){
            System.out.printf("The first route is faster than the second for %.2f minutes%n",timeRoute1-timeRoute2);
        }else{System.out.printf("The second route is faster than the first for %.2f minutes%n",timeRoute1-timeRoute2);}
    }
}
