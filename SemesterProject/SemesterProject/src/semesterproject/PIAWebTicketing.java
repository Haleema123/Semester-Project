package semesterproject;

import java.io.*;

public class PIAWebTicketing implements WebTicketing 
{
    private Passenger[][] bookingRecord;

    public PIAWebTicketing() 
    {
        bookingRecord = new Passenger[5][10]; //3 flights and 10 seats each
    }
    
    @Override
    public void FlightsSchedule()
    {
        System.out.println("\n\t\tFLIGHT SCHEDULE FOR TOMORROW");
        System.out.println("--------------------------------------------------------------");
        System.out.println("   From\t\t\tTo\t\t\tDeparture time");
        System.out.println("--------------------------------------------------------------");
        System.out.println("1: Islamabad(Pakistan)\tKarachi(Pakistan)\t09 : 15 AM\n");
        System.out.println("2: Karachi(Pakistan)\tIslamabad(Pakistan)\t02 : 00 PM\n");
        System.out.println("3: Lahore(Pakistan)\tBeijing(China)\t\t05 : 30 PM\n");
        System.out.println("4: Islamabad(Pakistan)\tDubai(UAE)\t\t05 : 45 PM\n");
        System.out.println("5: Islamabad(Pakistan)\tJeddah(Saudi Arabia)\t08 : 00 PM");
        System.out.println("--------------------------------------------------------------\n");
    }
    
    //Shows the seats available in each cabin
    public void availableSeats(int flight) throws Exception
    {
        File f = new File("Bookings.hax");
        FileInputStream fis;
        ObjectInputStream ois;
        if(f.exists())
        {
            fis = new FileInputStream("Bookings.hax");
            ois = new ObjectInputStream(fis);
            try
            {
                while(true)
                {
                    Passenger obj =(Passenger)ois.readObject();
                    int x = obj.getFlight();
                    int y = obj.getSeat();
                    bookingRecord[x-1][y-1] = obj;
                }    
            }
            catch(Exception E){}
            ois.close();
        }
        int economy = 0;
        int executiveEconomy = 0;
        int business = 0;
        for(int i=0; i<bookingRecord[flight-1].length; i++)
        {
            if(bookingRecord[flight-1][i] == null)
            {
                if(i>=0 && i<=3)
                    economy++;
                else if(i>=4 && i<=7)
                    executiveEconomy++;
                else
                    business++;
            }
        }
        System.out.println("\nAVAILABLE SEATS\n1: Economy: " + economy + "\n2: Executive Economy: " + executiveEconomy + "\n3: Business: " + business + "\n");
    }
    
    //returns true if the seat is available in users selected cabin
    public boolean CheckAvalability(int flight, int Cabin)
    {
        if(Cabin==1)
        {
            if (bookingRecord[flight-1][0]!=null && bookingRecord[flight-1][1]!=null &&bookingRecord[flight-1][2]!=null &&bookingRecord[flight-1][3]!=null)
            {
                return false;
            }
        }
        else if(Cabin==2)
        {
            if (bookingRecord[flight-1][4]!=null && bookingRecord[flight-1][5]!=null &&bookingRecord[flight-1][6]!=null &&bookingRecord[flight-1][7]!=null)
            {
                return false;
            }
        }
        else
        {
            if (bookingRecord[flight-1][8]!=null && bookingRecord[flight-1][9]!=null)
            {
                return false;
            }
        }
        return true;   
    }
    
    @Override
    public void bookSeat(String Name, String Surname, String Gender, long IdCardNum, int age, String Nationality, long phoneNum, String email, int flight, int cabin) throws Exception
    {
        File f = new File("Bookings.hax");
       
        FileOutputStream fos;
        ObjectOutputStream oos;
        if(!f.exists())
        {
            fos = new FileOutputStream("Bookings.hax");
            oos = new ObjectOutputStream(fos);
        }
        else
        {        
            fos = new FileOutputStream("Bookings.hax",true);
            oos = new ObjectOutputStream(fos)
            {
                @Override 
                public void writeStreamHeader() 
                {      
                }
            };
        }
        
        boolean booked = false;
        int seat = 0;
        for(int i=0; i<bookingRecord[flight-1].length; i++)
        {
            if(bookingRecord[flight-1][i] == null)
            {
                if(cabin == 1)
                {
                    if(i>=0 && i<=3)
                    {
                        seat = i + 1;
                        Passenger p = new Passenger(Name, Surname, Gender, IdCardNum, age, Nationality, phoneNum, email, flight, seat, cabin);
                        bookingRecord[flight-1][i] = p;
                        oos.writeObject(p);
                        reservationSummary(p);
                        booked = true;
                        break;
                    }
                }
                else if(cabin == 2)
                {
                    if(i>=4 && i<=7)
                    {
                        seat = i + 1;
                        Passenger p = new Passenger(Name, Surname, Gender, IdCardNum, age, Nationality, phoneNum, email, flight, seat, cabin);
                        bookingRecord[flight-1][i] = p;
                        oos.writeObject(p);
                        reservationSummary(p);
                        booked = true;
                        break;
                    }
                }
                else
                {
                    if(i>=8 && i<=9)
                    {
                        seat = i + 1;
                        Passenger p = new Passenger(Name, Surname, Gender, IdCardNum, age, Nationality, phoneNum, email, flight, seat, cabin);
                        bookingRecord[flight-1][i] = p;
                        oos.writeObject(p);
                        reservationSummary(p);
                        booked = true;
                        break;
                    }
                }
            }
        }
        
        if(!booked)
        {
            System.out.println("\nBooking failed!\n");
        }  
        oos.close();
    } 
    
    //shows the user his booking
    @Override
    public void myBookings(long IdCardNum) throws Exception
    {
        File f = new File("Bookings.hax");
        FileInputStream fis;
        ObjectInputStream ois;
        if(f.exists())
        {
            fis = new FileInputStream("Bookings.hax");
            ois = new ObjectInputStream(fis);
            try
            {
                while(true)
                {
                    Passenger obj =(Passenger)ois.readObject();
                    if(IdCardNum ==obj.getIdCardNum())
                    {
                        reservationSummary(obj);
                        break;
                    }
                }    
            }
            catch(Exception E)
            {
                System.out.println("\nNo Booking with this Id-Card Number.\n");
            }
            ois.close();
        }
        else
        {
            System.out.println("\nNo Booking with this Id-Card Number.\n");
        }
    }
    
    private void reservationSummary(Passenger p)
    {
        System.out.println("\n\tRESERVATION SUMMARY");
        System.out.println("------------------------------------");
        System.out.println("Status: Seat Booked");
        System.out.println("....................................");
        System.out.println("FLIGHT INFORMATION");
        System.out.println("Flight: PK-30" + p.getFlight());
        System.out.println("Seat no: " + p.getSeat());
        if(p.getCabin() == 1)
        {
            System.out.println("Cabin: Economy");
        }
        else if(p.getCabin() == 2)
        {
            System.out.println("Cabin: Executive Economy");
        }
        else
        {
            System.out.println("Cabin: Business");
        }
        System.out.println("....................................");
        if(p.getFlight() == 1)
        {
            System.out.println("DEPARTING");
            System.out.println("Airport: Islamabad Intl.");
            System.out.println("Time: 09 : 15 AM");
            System.out.println("....................................");
            System.out.println("ARRIVAL");
            System.out.println("Airport: Karachi - Jinnah Intl.");
            System.out.println("Time: 10 : 30 AM");
        }
        else if(p.getFlight() == 2)
        {
            System.out.println("DEPARTING");
            System.out.println("Airport: Karachi - Jinnah Intl.");
            System.out.println("Time: 02 : 00 PM");
            System.out.println("....................................");
            System.out.println("ARRIVAL");
            System.out.println("Airport: Islamabad Intl.");
            System.out.println("Time: 03 : 15 PM");
        }
        else if(p.getFlight() == 3)
        {
            System.out.println("DEPARTING");
            System.out.println("Airport: Lahore - Allam Iqbal Intl.");
            System.out.println("Time: 05 : 30 PM");
            System.out.println("....................................");
            System.out.println("ARRIVAL");
            System.out.println("Airport: Beijing - Capital Intl.");
            System.out.println("Time: 11 : 00 PM");
        }
        else if(p.getFlight() == 4)
        {
            System.out.println("DEPARTING");
            System.out.println("Airport: Islamabad Intl.");
            System.out.println("Time: 05 : 45 PM");
            System.out.println("....................................");
            System.out.println("ARRIVAL");
            System.out.println("Airport: Karachi - Jinnah Intl.");
            System.out.println("Time: 08 : 45 PM");
        }
        else
        {
            System.out.println("DEPARTING");
            System.out.println("Airport: Islamabad Intl.");
            System.out.println("Time: 08 : 00 PM");
            System.out.println("....................................");
            System.out.println("ARRIVAL");
            System.out.println("Airport: Jeddah.");
            System.out.println("Time: 12 : 45 AM");
        }
        System.out.println("....................................");
        System.out.println("PASSENGER INFORMATION");
        System.out.println(p);
        System.out.println("------------------------------------\n");
    }
    
    @Override
    public void CancelBooking(long IdCardNumber) throws Exception
    {
        FileInputStream fis = new FileInputStream("Bookings.hax");;
        ObjectInputStream ois = new ObjectInputStream(fis);
       
        while(true)
        {
            Passenger obj =(Passenger)ois.readObject();
            if(IdCardNumber ==obj.getIdCardNum())
            {
                int x = obj.getFlight();
                int y = obj.getSeat();
                bookingRecord[x-1][y-1] = null;
                break;
            }
        }    
        ois.close();
        
        FileOutputStream fos = new FileOutputStream("Bookings.hax");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        for(int i=0; i<bookingRecord.length; i++)
        {
            for(int j=0; j<bookingRecord[i].length; j++)
            {
                if(bookingRecord[i][j]!=null)
                {
                    oos.writeObject(bookingRecord[i][j]);
                }
            }
        }
        oos.close();
        System.out.println("\nBooking cancelled succesfully.\n");
    }
}
