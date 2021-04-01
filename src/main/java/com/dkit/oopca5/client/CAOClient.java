package com.dkit.oopca5.client;

/* The client package should contain all code and classes needed to run the Client
 */

/* The CAOClient offers students a menu and sends messages to the server using TCP Sockets
 */

import com.dkit.oopca5.core.CAOService;
import com.dkit.oopca5.core.DTO.Student;
import com.dkit.oopca5.server.*;

import java.util.ArrayList;
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
                    login();
                    break;
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
                    System.out.println("Logging out");
                    logout();
                    break;
                case DISPLAY_COURSE:
                    System.out.println("Enter Course ID");
                    String courseid = keyboard.nextLine();
                    displayCourse(courseid);
                    break;
                case DISPLAY_ALL_COURSES:
                    displayAllCourses();
                    break;
                case DISPLAY_CURRENT_CHOICES:
                    displayCurrentChoices(s.getCaoNumber());
                    break;
                case UPDATE_CURRENT_CHOICES:
                    updateChoices(s.getCaoNumber());
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
    }

    private void login(){
        System.out.println("Please enter your caoNumber: ");
        int caoNumber = keyboard.nextInt();
        keyboard.nextLine();
        System.out.println("Please enter your date of birth (yyyy-mm-dd): ");
        String dateString = keyboard.nextLine();
        System.out.println("Please enter your password: ");
        String password = keyboard.nextLine();

        String message = CAOService.LOGIN + CAOService.BREAKING_CHARACTER +
                caoNumber + CAOService.BREAKING_CHARACTER +
                dateString + CAOService.BREAKING_CHARACTER +
                password;
        System.out.println("Message ready to send to server: " + message);
        Student s = new Student(caoNumber,dateString,password);
        loggedInMenu(s);
    }

    private void logout(){
        String message = CAOService.LOGOUT;
        System.out.println("Message ready to send to server: " + message);
    }

    private void displayCourse(String courseId){
        String message = CAOService.DISPLAY_COURSE + CAOService.BREAKING_CHARACTER + courseId;
        System.out.println("Message ready to send to server: " + message);
    }

    private void displayAllCourses(){
        String message = CAOService.DISPLAY_ALL;
        System.out.println("Message ready to send to server: " + message);
    }

    private void displayCurrentChoices(int caoNumber){
        String message = CAOService.DISPLAY_CURRENT + CAOService.BREAKING_CHARACTER + caoNumber;
        System.out.println("Message ready to send to server: " + message);
    }

    private void updateChoices(int caoNumber){
        ArrayList<String> update = new ArrayList<>();
        String courseID = null;
        boolean running = true;
        while(running){
            System.out.println("Please enter the courseID to add to your choices or 'done' to finish: ");
            courseID = keyboard.nextLine();
            if(courseID.matches("") || courseID.matches("done")){
                running = false;
            } else {
                update.add(courseID);
            }
        }

        String message = CAOService.UPDATE_CURRENT + CAOService.BREAKING_CHARACTER + caoNumber;
        System.out.println("Message ready to send to server: " + message);
    }
}