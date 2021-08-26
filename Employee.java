package OrientedObject;


/**
 * Employee - Write a description here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
// Standard import for the Scanner class
import java.util.*;
public class Employee {
    String name ="";
    int age;
    String position="";
    double salaryPerHour;
    
    Employee(String _name, int _age, String _position, double _salaryPerHour){
        this.name = _name;
        this.age = _age;
        this.position = _position;
        this.salaryPerHour = _salaryPerHour;
    }
        
    public String getName(){
        return this.name;
    }
    
    public String getPosition(){
        return this.position;
    }
    
    public int getAge(){
        return this.age;
    }
    
    public double getSalary(){
        return this.salaryPerHour;
    }
    
    public void  setAge(int _age){
        if (_age <this.age){
            System.out.println("Is not possible set a a lower age!");
        }else{
            this.age = _age;
        }
    }
    
    public double getSalaryPerHour(){
        return this.salaryPerHour;
    }
    
    public void setSalaryPerHour(int _salaryPerHour){
        if (_salaryPerHour <this.salaryPerHour){
            System.out.println("Is not possible set a lower salary!");
        }else{
            this.salaryPerHour = _salaryPerHour;
        }
    }
    
    public double calculateSalary (int hours){
        return hours * this.salaryPerHour;
    }
    
    public String toString(){
        return this.name+" | "+this.age+"yo | "+this.position+" | $"+this.salaryPerHour+"/hour";
    }
}
