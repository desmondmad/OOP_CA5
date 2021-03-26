package com.dkit.oopca5.client;

/* The client package should contain all code and classes needed to run the Client
 */

/* The CAOClient offers students a menu and sends messages to the server using TCP Sockets
 */

import com.dkit.oopca5.core.CAOService;
import com.dkit.oopca5.server.MySqlStudentDao;
import com.dkit.oopca5.server.StudentDaoInterface;
import com.dkit.oopca5.server.MySqlCourseDao;
import com.dkit.oopca5.server.CourseDaoInterface;

import java.util.Scanner;

public class CAOClient
{
    private static final int QUIT = 0;
    private static final int REGISTER = 1;
    private static final int LOGIN = 2;
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

                    break;
            }
            printStartMenu();
            menuOption = getMenuOption();
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
}
