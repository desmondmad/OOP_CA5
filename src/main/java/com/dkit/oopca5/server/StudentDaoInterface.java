package com.dkit.oopca5.server;

import com.dkit.oopca5.core.DTO.Student;
import com.dkit.oopca5.server.DaoException;
import java.util.List;


public interface StudentDaoInterface
{
    public boolean registerStudent(Student student) throws DaoException;

    public boolean login(Student student) throws DaoException;

    public boolean checkIfRegistered(Student student) throws DaoException;
}
