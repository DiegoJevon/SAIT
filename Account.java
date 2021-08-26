package OrientedObject;

/**
 * Account - Write a description here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
// Standard import for the Scanner class
import java.util.*;
public class Account {
    private String accNumber;
    private double balance;

    Account (){
        this.balance = 0;
        Random r = new Random();
        this.accNumber = createAccount ();
    }

    private String createAccount (){
        Random r = new Random();
        return this.accNumber = ""+(1000000+r.nextInt(999999));
    }

    Account (double initialBalance){
        this.balance = initialBalance;  
        Random r = new Random();
        this.accNumber = createAccount ();
    }
    
    public String getAccNumber(){
        return this.accNumber;
    }

    public double getBalance(){
        return this.balance;
    }

    public void  credit (double value){
        this.balance += value; 
    }

    public void  debit (double value){
        this.balance -= value; 
    }   

}
