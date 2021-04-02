package com.dkit.oopca5.core.johnloane;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.dkit.oopca5.core.DTO.Course;
import com.dkit.oopca5.core.DTO.Student;
import com.dkit.oopca5.core.DTO.Student_Courses;
import com.dkit.oopca5.server.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    Student s = new Student(11101001,"2021-04-01","apr1lF15r7");
    StudentDaoInterface studentDAO = new MySqlStudentDao();
    CourseDaoInterface courseDAO = new MySqlCourseDao();
    ChoiceDaoInterface choiceDAO = new MySqlChoiceDao();
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }
//Student tests
    @Test
    public void testRegisterStudent() throws DaoException {
        Boolean result = studentDAO.registerStudent(s);
        assert(result);
    }

    @Test
    public void testLogin() throws DaoException{
        Boolean result = studentDAO.login(s);
        assert(result);
    }

    @Test
    public void testCheckIfReg() throws DaoException {
        Boolean result = studentDAO.checkIfRegistered(s);
        assert (result);
    }

//Course tests
    @Test
    public void testFindAllCourses() throws DaoException{
        List<Course> testing = courseDAO.findAllCourses();
        assert(testing != null);
    }

    @Test
    public void testFindCourse() throws DaoException{
        Course result = courseDAO.findCourse("DK820");
        Course expResult = new Course("DK820",8,"BSc in Computing in Games Development","Dundalk IT");
        Boolean equal = result.equals(expResult);
        Assert.assertFalse(equal);
    }
//Choice Tests
    @Test
    public void testFindCourseForUser() throws DaoException{
        List<String> courses = choiceDAO.findCoursesForUser(22224444);
        for(int i = 0; i < courses.size();i++){
            System.out.println(courses.get(i));
        }
        assert(courses != null);
    }

    @Test
    public void testUpdateCoursesForUser() throws DaoException{
        List<String> courses = new ArrayList<>();
        courses.add("DK820");
        courses.add("DK821");
        courses.add("DK720");
        choiceDAO.updateCoursesForUser(22224444,courses);
        System.out.println(courses);
        List<String> test = choiceDAO.findCoursesForUser(22224444);
        System.out.println(test);
    }

}
