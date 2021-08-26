package OrientedObject;

/**
 * Game - Write a description here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
// Standard import for the Scanner class
import java.util.*;
public class Game {
    private boolean hasStarted = false;
    private int scoreHomeTeam = 0;
    private int scoreVisitingTeam = 0;
    private String status = "Not Started:" ;

    private String homeTeam = "";
    private String visitingTeam = "";

    public Game (String _homeTeam, String _visitingTeam){
        this.homeTeam = _homeTeam;
        this.visitingTeam = _visitingTeam;
    }

    public void startGame(){
        this.status = "First Half: ";
        hasStarted = true;
    }

    public void endGame(){
        this.status = "FINAL: ";
        hasStarted = false;
    }

    public boolean addScore(String team, int value){
        if (!hasStarted){
            return hasStarted;
        }else if (team.equals(homeTeam)){
            this.scoreHomeTeam += value;
            return hasStarted;
        }else if(team.equals(visitingTeam)){
            this.scoreVisitingTeam += value;
            return hasStarted;
        }

        return hasStarted;
    }

    public void endFirstHalf(){
        this.status = "First Half Ended: ";
        hasStarted = false;
    }

    public void endSecondHalf(){
        this.status = "Second Half Ended: ";
        hasStarted = false;
    }

    public void startSecondHalf(){
        this.status = "Second Half: ";
        hasStarted = true;
    }

    public void printStatus(){
        System.out.println(this.status + this.homeTeam+" ("+this.scoreHomeTeam + ") vs ("+this.scoreVisitingTeam + ") "+visitingTeam);
    }

    public String toString(){
        return this.status+ this.homeTeam+" ("+this.scoreHomeTeam + ") vs ("+this.scoreVisitingTeam + ") "+visitingTeam;
    }

    public String getLocation(){
        return this.homeTeam;
    }

    public int getScore(String team){
        int score=0;
        if (team.equals(homeTeam)) score = this.scoreHomeTeam;
        if (team.equals(visitingTeam)) score = this.scoreVisitingTeam;
        return score;
    }

}
