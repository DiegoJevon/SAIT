package OrientedObject;

/**
 * Garage - Write a description here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
// Standard import for the Scanner class
import java.util.*;
public class Garage {

    Car [] positions;

    Garage (int size){
        this.positions = new Car [size];    
    }

    public int parkCar(Car car){
        for (int i = 0; i< this.positions.length;i++){
                if (this.positions[i] == null){
                    this.positions[i] = car;
                    return i;
                }           

        }
        return -1;
    }

    public void unparkCar(Car car){

        for (int i = 0; i< this.positions.length;i++){
            if(this.positions[i].equals(car)){
                this.positions[i] = null;
                return;
            }
        }
    }
    
    public int availableSpots(){
        int spots = 0;
        
        for (int i = 0; i< this.positions.length;i++){
        if (this.positions[i] == null){
            spots++;
        }
        
        }
        return spots;
    }
    
    public String toString(){
        
        String resultStrings="";
        
        for (int i=0; i< this.positions.length; i++){
            
            resultStrings += i+ ": ";
            
            if (this.positions[i]==null){
                resultStrings += "FREE \n";                
                
            }else{
                resultStrings += this.positions[i] + "\n";
            }
        }
        
        return resultStrings;
    }
    
}
