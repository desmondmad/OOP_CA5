package com.dkit.oopca5.client;

/* The client package should contain all code and classes needed to run the Client
 */

/* The CAOClient offers students a menu and sends messages to the server using TCP Sockets
 */

import com.dkit.oopca5.core.CAOService;
import com.dkit.oopca5.core.DTO.Student;
import com.dkit.oopca5.server.*;

import java.util.Scanner;

public class CAOClient
{
    private static final int QUIT = 0;
    private static final int REGISTER = 1;
    private static final int LOGIN = 2;

    private static final int LOGOUT = 1;
    private static final int DISPLAY_COURSE = 2;
    private static final int DISPLAY_ALL_COURSES = 3;
    private static final int DISPLAY_CURRENT_CHOICES = 4;
    private static final int UPDATE_CURRENT_CHOICES = 5;

    Scanner keyboard = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("\nWelcome to the CAO Service");
        CAOClient client = new CAOClient();
        client.start();
    }

    private void start(){
        mainMenu();
    }

    private void mainMenu(){
        printStartMenu();
        int menuOption = getMenuOption();
        while(menuOption != QUIT){
            switch(menuOption){
                case REGISTER:
                    System.out.println("Enter register details");
                    register();
                    break;
                case LOGIN:
                    System.out.println("Enter Login details");
                    System.out.println("Please enter your caoNumber: ");
                    int caoNumber = keyboard.nextInt();
                    keyboard.nextLine();
                    System.out.println("Please enter your date of birth (yyyy-mm-dd): ");
                    String dateString = keyboard.nextLine();
                    System.out.println("Please enter your password: ");
                    String password = keyboard.nextLine();
                    StudentDaoInterface studentdao = new MySqlStudentDao();
                    Student s = new Student(caoNumber,dateString,password);
                    try {
                        if(studentdao.login(s)){
                            loggedInMenu(s);
                        }
                        break;
                    } catch (DaoException throwables) {
                        throwables.printStackTrace();
                    }
            }
            printStartMenu();
            menuOption = getMenuOption();
        }
        System.out.println("Quitting App");
    }

    private void loggedInMenu(Student s){
        printMainMenu();
        int menuOption = getMainMenuOption();
        while(menuOption != QUIT){
            switch(menuOption){
                case LOGOUT:
                    System.out.println("Enter register details");
                    register();
                    break;
                case DISPLAY_COURSE:
                    System.out.println("Enter Course ID");
                    String courseid = keyboard.nextLine();
                    break;
                case DISPLAY_ALL_COURSES:
                    break;
                case DISPLAY_CURRENT_CHOICES:
                    break;
                case UPDATE_CURRENT_CHOICES:
                    break;
            }
            printMainMenu();
            menuOption = getMainMenuOption();
        }
        System.out.println("Quitting App");
    }

    public static void printStartMenu(){
        System.out.println("------------------------------------------------");
        System.out.println("0. QUIT");
        System.out.println("1. REGISTER");
        System.out.println("2. LOGIN");
        System.out.println("------------------------------------------------");
    }

    public static void printMainMenu(){
        System.out.println("------------------------------------------------");
        System.out.println("0. QUIT");
        System.out.println("1. LOGOUT");
        System.out.println("2. DISPLAY_COURSE");
        System.out.println("3. DISPLAY_ALL_COURSE");
        System.out.println("4. DISPLAY_CURRENT_CHOICES");
        System.out.println("5. UPDATE_CURRENT_CHOICES");
        System.out.println("------------------------------------------------");
    }

    private int getMenuOption(){
        int option;
        System.out.println("Please enter option: ");
        option = keyboard.nextInt();
        while(option != 0 && option != 1 && option != 2)
        {
            System.out.println("Please enter a valid number: ");
            option = keyboard.nextInt();
        }
        return option;
    }

    private int getMainMenuOption(){
        int option;
        System.out.println("Please enter option: ");
        option = keyboard.nextInt();
        while(option != 0 && option != 1 && option != 2 && option != 3 && option != 4 && option != 5)
        {
            System.out.println("Please enter a valid number: ");
            option = keyboard.nextInt();
        }
        return option;
    }

    private void register(){
        System.out.println("Please enter caoNumber: ");
        int caoNumber = keyboard.nextInt();
        keyboard.nextLine();
        System.out.println("Please enter date of birth (yyyy-mm-dd): ");
        String dateString = keyboard.nextLine();
        System.out.println("Please enter a password: ");
        String password = keyboard.nextLine();

        String message = CAOService.REGISTER_COMMAND + CAOService.BREAKING_CHARACTER +
                caoNumber + CAOService.BREAKING_CHARACTER +
                dateString + CAOService.BREAKING_CHARACTER +
                password;
        System.out.println("Message ready to send to server: " + message);
//        StudentDaoInterface studentdao = new MySqlStudentDao();
//        if(studentdao.registerStudent())
    }
}
