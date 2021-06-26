package semesterproject;

import java.io.Serializable;

public class Person implements Serializable
{
    private String Name;
    private String Surname;
    private String Gender;
    private long IdCardNum;
    private int age;
    private String Nationality;
    private long phoneNum;
    private String email;

    public Person(String Name, String Surname, String Gender, long IdCardNum, int age, String Nationality, long phoneNum, String email) 
    {
        this.Name = Name;
        this.Surname = Surname;
        this.Gender = Gender;
        this.IdCardNum = IdCardNum;
        this.age = age;
        this.Nationality = Nationality;
        this.phoneNum = phoneNum;
        this.email = email;
    }

    public long getIdCardNum()
    {
        return IdCardNum;
    }

    public String getName()
    {
        return Name;
    }

    public String getSurname()
    {
        return Surname;
    }
    
    

    @Override
    public String toString() 
    {
        return "Name: " + Name + "\nSurname: " + Surname + "\nGender: " + Gender + "\nIdCardNum: " + IdCardNum + "\nage: " + age+ "\nNationality: " + Nationality + "\nPhone number: " + phoneNum + "\nemail: " + email;
    }
}
