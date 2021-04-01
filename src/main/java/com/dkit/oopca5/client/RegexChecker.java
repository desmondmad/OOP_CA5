package com.dkit.oopca5.client;

/* This class should contain static methods to verify input in the application
 */

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexChecker
{
    public static boolean validateCNumber(Integer caoNumber){
        if (caoNumber < 10000000 || caoNumber > 99999999){
            return false;
        } else {
            return true;
        }
    }

    public static boolean validatePassword(String password){
        String regex = "[a-zA-Z0-9]{8}.*";
        if (password == null){
            return false;
        } else {
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(password);
            return m.matches();
        }
    }

    public static boolean validateDOB(String dob){
        String regex = "[0-9]{4}[/-][0-9]{2}[/-][0-9]{2}";
        if (dob == null){
            return false;
        }
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(dob);
        return m.matches();
    }
}
