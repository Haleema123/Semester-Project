package semesterproject;

import java.io.Serializable;

public class Passenger extends Person implements Serializable
{
    private int flight;
    private int seat;
    private int cabin;
    
    public Passenger(String Name, String Surname, String Gender, long IdCardNum, int age, String Nationality, long phoneNum, String email, int flight,int seat,int cabin) 
    {
        super(Name, Surname, Gender, IdCardNum, age, Nationality, phoneNum, email);
        this.flight = flight;
        this.seat = seat;
        this.cabin = cabin;
    }

    public int getFlight() 
    {
        return flight;
    }

    public int getSeat() 
    {
        return seat;
    }

    public int getCabin() 
    {
        return cabin;
    }
    
    
}
