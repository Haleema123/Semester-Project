package semesterproject;

import java.io.*;

public interface WebTicketing 
{
    public void FlightsSchedule();
    public void bookSeat(String Name, String Surname, String Gender, long IdCardNum, int age, String Nationality,
            long phoneNum, String email, int flight, int cabin)throws Exception;
    public void myBookings(long IdCardNum) throws Exception;
    public void CancelBooking(long IdCardNumber) throws Exception;
}
