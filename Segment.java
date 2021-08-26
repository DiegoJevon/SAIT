package Assignment_3;

/**
 * Segment - This class is responsible to read data from the Point class and the average speed from the user terminal interation. Additionally, the method length provides the distance
 * in kilometers and the method estimatedTime provides the estimated time in minutes, considerint the average speed and the distance. 
 * @author Dakota Chatt and Diego Maia 
 * @version August 09, 2021
 */
// Standard import for the Scanner class
import java.util.*;
public class Segment {
    double avgSpeed;
    Point start;
    Point end;
    //reading start point and end pontin (using Point class) and the average speed in km/h
    public Segment(Point _start, Point _end, double _avgSpeed){
        this.avgSpeed = _avgSpeed;
        this.start = _start;
        this.end = _end;
    }
    //method created to facilitate the length Method for the distance formula. This method return the latitude sine
    private double sinLat(){
        return Math.sin((end.getLat()-start.getLat())/2);
    }
    //method created to facilitate the length Method for the distance formula. This method return the longtide sine
    private double sinLong(){
        return Math.sin((end.getLong()-start.getLong())/2);
    }
    //method created to facilitate the length Method for the distance formula.  This method return the latitude cosine  
    private double cosLat(){
        return Math.cos(end.getLat())*Math.cos(start.getLat());
    }
    //returning the distance formula in kilometers
    public double length(){ 
        return 2 * 6371 * Math.sqrt((Math.pow(sinLat(),2)+(cosLat()*Math.pow(sinLong(),2))));  
    }
    //returning the estimated time in minutes
    public double estimatedTime(){        
        return length()/this.avgSpeed * 60;
    }
    
}