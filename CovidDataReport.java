/**
 * Created By:
 * Dakota Chatt and Diego Jevon de Lima Maia
 *
 * Date: Jul/27/2021
 * Time: 19:07
 *
 * Project Name: Assignment2
 *
 * Program/Class Description:
 *
 * CovidData class and sample code for sifting through Covid Data file provided by Dave Leskiw downloaded thru D2L.
 *
 * User enters path and filename for COVID data, data from this file is then used in a variety of calculations to
 * provide reports on country specific daily new cases, and daily new vaccination rates.
 *
 * User can make multiple reports with different file names, or append multiple reports in the same file without having
 * to restart the program.
 *
 * Output report filename structure is limited to .txt files. Filename is error checked accordingly.
 *
 **/

import java.util.*;
import java.io.*;
import java.text.*;

public class CovidDataReport {

    static Scanner input = new Scanner(System.in); //globally available for all methods in this class

    public static void main(String[] args) throws IOException, ParseException {

        // Code below gathers all relevant (as per CovidData Class) and fills an ArrayList to be used later in program
        String covidFilename;
        String data;
        String[] answer;
        CovidData line;
        ArrayList<CovidData> list = new ArrayList<>();

        System.out.print("Enter Covid filename: ");
        covidFilename = input.nextLine();
        File fin = new File(covidFilename);

        Scanner fscanner = new Scanner(fin);
        //Consumes header information in csv file
        fscanner.nextLine();

        //Transfer all of the desired covid data from the .csv file into an ArrayList
        if (fin.exists()) {

            while (fscanner.hasNextLine()) {
                data = fscanner.nextLine();
                answer = data.split(",");

                String location = answer[2];
                String dateString = answer[3];

                Date d = new SimpleDateFormat("yyyy-mm-dd").parse(dateString);

                long totalVaccinations;
                long peopleVaccinated;
                long peopleFullyVaccinated;
                long newVaccinations;
                long newVaccinationsSmoothed;
                double totalVaccinationsPerHundred;
                double peopleVaccinatedPerHundred;
                double peopleFullyVaccinatedPerHundred;
                double newVaccinatedSmoothedPerMillion;
                long newCases;
                long population;

                if (answer.length > 44) {
                    if (!answer[5].equals(""))
                        newCases = (long) Double.parseDouble(answer[5]);
                    else newCases = 0;

                    if (!answer[34].equals(""))
                        totalVaccinations = (long) Double.parseDouble(answer[34]);
                    else totalVaccinations = 0;

                    if (!answer[35].equals(""))
                        peopleVaccinated = (long) Double.parseDouble(answer[35]);
                    else peopleVaccinated = 0;

                    if (!answer[36].equals(""))
                        peopleFullyVaccinated = (long) Double.parseDouble(answer[36]);
                    else peopleFullyVaccinated = 0;

                    if (!answer[37].equals(""))
                        newVaccinations = (long) Double.parseDouble(answer[37]);
                    else newVaccinations = 0;

                    if (!answer[38].equals(""))
                        newVaccinationsSmoothed = (long) Double.parseDouble(answer[38]);
                    else newVaccinationsSmoothed = 0;

                    if (!answer[39].equals(""))
                        totalVaccinationsPerHundred = Double.parseDouble(answer[39]);
                    else totalVaccinationsPerHundred = 0;

                    if (!answer[40].equals(""))
                        peopleVaccinatedPerHundred = Double.parseDouble(answer[40]);
                    else peopleVaccinatedPerHundred = 0;

                    if (!answer[41].equals(""))
                        peopleFullyVaccinatedPerHundred = Double.parseDouble(answer[41]);
                    else peopleFullyVaccinatedPerHundred = 0;

                    if (!answer[42].equals(""))
                        newVaccinatedSmoothedPerMillion = Double.parseDouble(answer[42]);
                    else newVaccinatedSmoothedPerMillion = 0;

                    if (!answer[44].equals(""))
                        population = (long) Double.parseDouble(answer[44]);
                    else population = 0;

                    line = new CovidData(location, d, population, newCases, totalVaccinations, peopleVaccinated,
                            peopleFullyVaccinated, newVaccinations, newVaccinationsSmoothed, totalVaccinationsPerHundred,
                            peopleVaccinatedPerHundred, peopleFullyVaccinatedPerHundred, newVaccinatedSmoothedPerMillion);

                    list.add(line);
                }
            }

            fscanner.close(); //ADDED THIS DOUBLE CHECK STILL WORKS!

            //Menu control and report creation
            String filename = "";

            //This layout allow for multiple filename changes if desired to put information
            // in multiple reports without having to restart program
            while(true) {

                clearConsole();
                mainMenu();

                int menuSelection;

                if(input.hasNextInt()) {

                    menuSelection = input.nextInt();

                    File reportFile = new File(filename);

                    switch(menuSelection) {
                        //User specifies filename and directory to store report(s), filename is checked for correct form
                        case 0:
                            input.nextLine();
                            System.out.printf("Enter report directory/filename: ");
                            filename = input.nextLine();

                            if(!filenameEntered(filename)) {
                                System.out.printf("Please enter a valid name of the format filename.txt%n");
                                System.out.printf("Press ENTER to continue...");
                                input.nextLine();
                                clearConsole();
                            }

                            break;

                        //Generates "New Case Report" for Country, file is appended if multiple reports on same filename
                        case 1:
                            if(filenameEntered(filename)) {
                                PrintWriter output = new PrintWriter(new FileOutputStream(reportFile, true));

                                System.out.print("Enter Country Name: ");
                                input.nextLine();
                                String country = input.nextLine().trim();
                                System.out.printf("Press ENTER to continue...");
                                input.nextLine();

                                DateFormat df = new SimpleDateFormat ("mm/dd/YYYY");
                                String outputDate = "";
                                double newCases = 0.0;
                                double newCasesPer1M = 0.0;

                                output.printf("New Case Report for %s%n", country);
                                output.printf("%15s%15s%15s%n", "Date", "New Cases", "New Cases/1M");

                                for(CovidData cd: list) {
                                    if(cd.getLocation().equals(country) && cd.getNewCases() > 0) {
                                        outputDate = df.format (cd.getDate());
                                        newCases = cd.getNewCases();
                                        newCasesPer1M = ( newCases / cd.getPopulation() ) * 1_000_000;
                                        output.printf("%15s%,15.0f%,15.4f%n", outputDate, newCases, newCasesPer1M);
                                    }
                                }

                                output.close();
                            } else {
                                reportFileNameErrorResponse();
                                clearConsole();
                            }
                            break;

                        //Generates "New Vaccinations Report" for Country, file is appended if multiple reports on same
                        // filename
                        case 2:
                            if(filenameEntered(filename)) {
                                PrintWriter output = new PrintWriter(new FileOutputStream(reportFile, true));

                                System.out.print("Enter Country Name: ");
                                input.nextLine();
                                String country = input.nextLine().trim();
                                System.out.printf("Press ENTER to continue...");
                                input.nextLine();

                                DateFormat df = new SimpleDateFormat ("mm/dd/YYYY");
                                String outputDate = "";
                                double newVacc = 0.0;
                                double newVaccPerHundred = 0.0;
                                double totalVacc = 0.0;
                                double totalVaccPerHundred = 0.0;

                                output.printf("New Vaccinations/Day Report for %s%n", country);
                                output.printf("%15s%15s%15s%15s%15s%n", "Date", "New Vacc", "New Vacc/100", "Ttl Vacc",
                                                "Ttl Vacc/100");

                                for(CovidData cd: list) {
                                    if(cd.getLocation().equals(country) && cd.getNewVaccinations() > 0) {
                                        outputDate = df.format (cd.getDate());
                                        newVacc = cd.getNewVaccinations();
                                        newVaccPerHundred = ( newVacc / cd.getPopulation() ) * 100;
                                        totalVacc = cd.getTotalVaccinations();
                                        totalVaccPerHundred = ( totalVacc / cd.getPopulation() ) * 100;
                                        output.printf("%15s%,15.0f%,15.4f%,15.0f%,15.4f%n", outputDate, newVacc,
                                                        newVaccPerHundred, totalVacc, totalVaccPerHundred);
                                    }
                                }

                                output.close();
                            } else {
                                reportFileNameErrorResponse();
                                clearConsole();
                            }
                            break;

                        case 9:
                            System.exit(0);
                            break;

                        default:
                            invalidMenuOptionErrorResponse();
                            break;
                    }
                } else {
                    invalidMenuOptionErrorResponse();
                    clearConsole();
                }
            }
        }
    }


    //Displays the standard user menu
    public static void mainMenu() {
        System.out.printf("0) Set Report filename%n");
        System.out.printf("1) Report of Daily New Cases%n");
        System.out.printf("2) Daily Vaccination Report for a Specific Country%n");
        System.out.printf("9) Exit%n");
        System.out.printf("Enter Selection: ");
    }

    //Ensures the report output filename entered by the user is not empty and is in correct format
    public static boolean filenameEntered(String filename) {
        //By trimming, will be a null string if the filename is only spaces with no text
        //Also verifies that the user specified a .txt file for outputting the data
        if(filename.trim().equals("") || !filename.contains(".txt")) {
            return false;
        } else {
            return true;
        }
    }

    //Error response when user did not enter any filename (null/empty string) or a filename of the correct format
    public static void reportFileNameErrorResponse() {
        System.out.printf("You need to specify a correct report filename BEFORE requesting this option%n");
        System.out.printf("Press ENTER to continue...");
        input.nextLine();
        input.nextLine();
    }

    //Error response when user enters menu option that is not an integer, or an incorrect integer not on menu
    public static void invalidMenuOptionErrorResponse() {
        System.out.printf("Invalid option. Please enter one of the integers shown in the menu%n");
        System.out.printf("Press ENTER to continue...");
        input.nextLine();
        input.nextLine();
    }

    //Clears out console screen after every user/menu interaction
    public static void clearConsole() {
        System.out.printf("\f");
        System.out.flush();
    }
}