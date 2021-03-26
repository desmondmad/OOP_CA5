package com.dkit.oopca5.server;

import com.dkit.oopca5.core.DTO.Course;
import com.dkit.oopca5.server.DaoException;
import java.util.List;


public interface CourseDaoInterface {
    public List<Course> findAllCourses() throws DaoException;

    public Course findCourse(String courseId) throws DaoException;
}
