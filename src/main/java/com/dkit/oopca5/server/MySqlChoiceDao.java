package com.dkit.oopca5.server;
import com.dkit.oopca5.core.DTO.Course;
import com.dkit.oopca5.core.DTO.Student_Courses;
import com.dkit.oopca5.server.DaoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlChoiceDao extends MySqlDao implements ChoiceDaoInterface{
    @Override
    public List<String> findCoursesForUser(int caoNumber) throws DaoException{
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<String> courses = new ArrayList<>();

        try
        {
            //Get connection object using the methods in the super class (MySqlDao.java)...
            con = this.getConnection();

            String query = "SELECT courseid FROM student_courses WHERE caoNumber = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, caoNumber);

            //Using a PreparedStatement to execute SQL...
            rs = ps.executeQuery();
            while (rs.next())
            {
                String courseId = rs.getString("courseid");
                courses.add(courseId);
            }
        } catch (SQLException e)
        {
            throw new DaoException("findCoursesForUser() " + e.getMessage());
        } finally
        {
            try
            {
                if (rs != null)
                {
                    rs.close();
                }
                if (ps != null)
                {
                    ps.close();
                }
                if (con != null)
                {
                    freeConnection(con);
                }
            } catch (SQLException e)
            {
                throw new DaoException("findCoursesForUser() " + e.getMessage());
            }
        }
        return courses;     // may be empty
    }

    @Override
    public void updateCoursesForUser(int caoNumber, List<String> courses) throws DaoException{
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        courses = new ArrayList<>();
        int choicenum = 0;

        try
        {
            //Get connection object using the methods in the super class (MySqlDao.java)...
            con = this.getConnection();

            String query = "DELETE * FROM student_courses WHERE caoNumber = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, caoNumber);

            //Using a PreparedStatement to execute SQL...
            rs = ps.executeQuery();

            for(int i = 0; i < courses.size();i++){
                query = "INSERT INTO student_courses VALUES (?,?,?)";
                ps = con.prepareStatement(query);
                ps.setInt(1,caoNumber);
                ps.setString(2, courses.get(i));
                ps.setInt(3, choicenum++);
                rs = ps.executeQuery();
            }

        } catch (SQLException e)
        {
            throw new DaoException("updateCoursesForUser() " + e.getMessage());
        } finally
        {
            try
            {
                if (rs != null)
                {
                    rs.close();
                }
                if (ps != null)
                {
                    ps.close();
                }
                if (con != null)
                {
                    freeConnection(con);
                }
            } catch (SQLException e)
            {
                throw new DaoException("updateCoursesForUser() " + e.getMessage());
            }
        }
    }
}
