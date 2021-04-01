package com.dkit.oopca5.server;
import com.dkit.oopca5.core.DTO.Student_Courses;
import com.dkit.oopca5.server.DaoException;
import java.util.List;

public interface ChoiceDaoInterface {
    public List<String> findCoursesForUser(int caoNumber) throws DaoException;

    public void updateCoursesForUser(int caoNumber, List<String> courses) throws DaoException;
}
