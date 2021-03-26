package com.dkit.oopca5.core.DTO;

public class Student_Courses
{
    private int caoNumber;
    private String courseId;
    private int placement;

    public Student_Courses(int caoNumber, String courseId, int placement)
    {
        this.caoNumber = caoNumber;
        this.courseId = courseId;
        this.placement = placement;
    }

    public Student_Courses()
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

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }


    public int getPlacement()
    {
        return placement;
    }

    public void setPlacement(int placement) {
        this.placement = placement;
    }

    @Override
    public String toString()
    {
        return "Student_Courses{" + "caoNumber=" + caoNumber + ", Course_ID =" + courseId + ", placement=" + placement+ '}';
    }

}
