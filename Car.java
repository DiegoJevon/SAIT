package OrientedObject;


public class Car {
    public String model;
    public int year;
    public String manufacturer;
    private int lastRevision;
    private static int numberProduced;
    
    Car(String model, String manufacturer, int year){
        this.model = model;
        this.manufacturer = manufacturer;
        this.year = year;        
        this.lastRevision = year;
        numberProduced++;
    }
    
    public void honk(){
        System.out.print("Beep!");
    }
    
    public void setLastRevision(int year){
        if (year >= this.lastRevision) this.lastRevision = year;
    }
    
    public int getLastRevision(){
        return this.lastRevision;
    }
    
    public String toString(){
        return "The model "+this.model +" was produced in "+ this.year;
    }
}


