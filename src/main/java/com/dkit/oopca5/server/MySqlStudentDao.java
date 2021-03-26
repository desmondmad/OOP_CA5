package com.dkit.oopca5.server;

/** OOP 2021
 *
 * Data Access Object (DAO) for User table with MySQL-specific code
 * This 'concrete' class implements the 'UserDaoInterface'.
 *
 * The DAO will contain the SQL query code to interact with the database,
 * so, the code here is specific to a particular database (e.g. MySQL or Oracle etc...)
 * No SQL queries will be used in the Business logic layer of code, thus, it
 * will be independent of the database specifics.
 *
 */


import com.dkit.oopca5.core.DTO.Student;
import com.dkit.oopca5.server.DaoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class MySqlStudentDao extends MySqlDao implements StudentDaoInterface
{
    @Override
    public boolean registerStudent(Student student) throws DaoException
    {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean success = false;

        try
        {
            //Get connection object using the methods in the super class (MySqlDao.java)...
            con = this.getConnection();

            String query = "INSERT INTO student VALUES(?,?,?)";
            ps = con.prepareStatement(query);
            ps.setInt(1, student.getCaoNumber());
            ps.setString(2, student.getDob());
            ps.setString(3, student.getPassword());
            //Using a PreparedStatement to execute SQL...
            success = (ps.executeUpdate() == 1);
        } catch (SQLException e)
        {
            throw new DaoException("registerStudent() " + e.getMessage());
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
                throw new DaoException("registerStudent() " + e.getMessage());
            }
        }
        return success;
    }
    @Override
    public boolean login(Student student) throws DaoException
    {
        boolean success = false;

        if(checkIfRegistered(student)){
            success = true;
        }
        return success;
    }
    @Override
    public boolean checkIfRegistered(Student student) throws DaoException
    {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean registered = false;
        try
        {
            con = this.getConnection();

            String query = "SELECT * FROM student WHERE caoNumber = ? AND date_of_birth = ? AND password = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, student.getCaoNumber());
            ps.setString(2, student.getDob());
            ps.setString(3, student.getPassword());
            rs = ps.executeQuery();
            if (rs.next())
            {
                registered = true;
            }
        } catch (SQLException e)
        {
            throw new DaoException("checkIfRegistered() " + e.getMessage());
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
                throw new DaoException("checkIfRegistered() "  + e.getMessage());
            }
        }
        return registered;
    }

}

