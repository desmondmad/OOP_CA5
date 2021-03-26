package com.dkit.oopca5.core.DTO;

public class Student
{
    private int caoNumber;
    private String dob;
    private String password;

    public Student(int caoNumber, String dob, String password)
    {
        this.caoNumber = caoNumber;
        this.dob = dob;
        this.password = password;
    }

    public Student()
    {
    }


    public int getCaoNumber()
    {
        return caoNumber;
    }

    public void setCaoNumber(int caoNumber)
    {
        this.caoNumber = caoNumber;
    }

    public String getDob()
    {
        return dob;
    }

    public void setDob(String dob)
    {
        this.dob = dob;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    @Override
    public String toString()
    {
        return "Student{" + "caoNumber=" + caoNumber + ", Date of Birth =" + dob + ", password=" + password + '}';
    }

}