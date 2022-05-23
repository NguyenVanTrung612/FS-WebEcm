/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;
import java.util.regex.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
/**
 *
 * @author Trung
 */
public class Regex {
    public static void main (String[] args) {
        String email = "vantrung@gmail.com";
        System.out.println(valEmail(email));
    }
    
    
    public static boolean valEmail(String input) 
    {     
        String emailRegex =   "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
        Pattern emailPat = Pattern.compile(emailRegex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = emailPat.matcher(input);
        return matcher.find();
    }      
}
