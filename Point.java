package Assignment_3;

/**
 * Point - This code is a class with the name Point and works to convert latitude and longtiude degrees in decimal. Doing that, is possible to consult he latitude and longtude and access 
 * those using getter methods. 
 * @author Dakota Chatt and Diego Maia 
 * @version August 09, 2021
 */
// Standard import for the Scanner class
import java.util.*;
public class Point {
    double degreeLat;
    double degreeLong;
    
    //reading latidude and longtide degrees
    public Point (double _degreeLat, double _degreeLong){
        this.degreeLat = _degreeLat;
        this.degreeLong = _degreeLong;
    }
    //getter method to return latitude
    public double getLat(){               
        return this.degreeLat * Math.PI / 180;       
    }
    //getter method to return longtide
    public double getLong(){        
        return this.degreeLong * Math.PI / 180;       
    }
    //toString method returnig latitude and longtude
    public String toString(){
        return getLat()+","+this.getLong();        
    }
}