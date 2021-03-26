package com.dkit.oopca5.core.DTO;

public class Course
{
    private String courseId;
    private int level;
    private String title;
    private String institution;

    public Course(String courseId, int level, String title, String institution)
    {
        this.courseId = courseId;
        this.level = level;
        this.title = title;
        this.institution = institution;
    }

    public Course()
    {
    }


    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    @Override
    public String toString()
    {
        return "Course{" + "courseID= " + courseId + ", Level = " + level + ", Title = " + title + ", Institution = " + institution +'}';
    }

}