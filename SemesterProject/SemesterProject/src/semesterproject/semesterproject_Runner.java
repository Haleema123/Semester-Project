package semesterproject;
import java.util.Scanner;
import java.io.*;
public class semesterproject_Runner 
{
    public static void main(String[] args)  throws Exception
    {
        Scanner input = new Scanner(System.in);
        
        PIAWebTicketing obj = new PIAWebTicketing();
        
        boolean operation = true;
        while(operation)
        {
            System.out.println("\n1: Flight Schedule\n2: Reservation\n3: My Booking\n4: Cancel Booking\n");
            System.out.print("Select an option: ");
            int option = input.nextInt();

            switch(option)
            {
                case 1:
                    obj.FlightsSchedule();
                    break;
                case 2:
                    obj.FlightsSchedule();
                    System.out.print("Select a flight: ");
                    int flight = input.nextInt();
                    obj.availableSeats(flight);
                    System.out.print("Press 1 to book seat: ");
                    int book = input.nextInt();
                    if(book == 1)
                    {
                        System.out.print("\nSelect a Cabin: ");
                        int Cabin = input.nextInt();
                        if(obj.CheckAvalability(flight, Cabin))
                        {
                            System.out.print("Enter your name: ");
                            String Name = input.next();
                            System.out.print("Enter your surname: ");
                            String Surname = input.next();
                            System.out.print("Enter your gender: ");
                            String Gender = input.next();
                            System.out.print("Enter your Id-card number: ");
                            long IdCardNum = input.nextLong();
                            System.out.print("Enter your age: ");
                            int age = input.nextInt();
                            System.out.print("Enter your nationality: ");
                            String Nationality = input.next();
                            System.out.print("Enter your phone number: ");
                            long phoneNum = input.nextLong();
                            System.out.print("Enter your email address: ");
                            String email = input.next();
                            obj.bookSeat(Name, Surname, Gender, IdCardNum, age, Nationality, phoneNum, email, flight, Cabin);
                        }
                        else
                        {
                            System.out.println("\nNo seat is available in your desired Cabin.\n");
                        }
                    }
                    break;
                case 3:
                    System.out.print("\nEnter your Id-card number: ");
                    long IdCardNum = input.nextLong();
                    obj.myBookings(IdCardNum);
                    break;
                case 4:
                    System.out.print("\nEnter your Id-card number: ");
                    long IdCardNumber = input.nextLong();
                    boolean exist = false;
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
                                Passenger p =(Passenger)ois.readObject();
                                if(IdCardNumber == p.getIdCardNum())
                                {
                                    System.out.println("\nName: " + p.getName() + "\nSurname: "+ p.getSurname()+"\nFlight: PK-30" + p.getFlight()+
                                    "\nSeat no: " + p.getSeat() + "\n");
                                    exist = true;
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
                    if(exist)
                    {
                        System.out.print("Are you sure you want to cancel your Booking\nPress 1 to cancel: ");
                        int cancel = input.nextInt();
                        if(cancel == 1)
                        {
                            obj.CancelBooking(IdCardNumber);
                        }
                        else
                        {
                            System.out.println("\nFlight not cancel.\nEnjoy Your Flight :)\n");
                        }
                    }
                    break;
                default:
                    break;
            }   
            System.out.print("Press 1 for more operations and 0 to exit: ");
            int x = input.nextInt();
            if(x == 0)
            {
                operation = false;
            }
        }
        System.out.println("\nThanks for visiting PIA.\n");
    }
}
