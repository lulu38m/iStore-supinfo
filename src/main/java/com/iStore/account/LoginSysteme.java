package com.iStore.account;

import org.mindrot.jbcrypt.BCrypt;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginSysteme {
    private List<User> userList;

    private static final String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
            + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

    private Pattern emailPattern;

    public LoginSysteme() {
        userList = new ArrayList<>();
        userList.add(new User("titi@blabla.com", BCrypt.hashpw("titi", BCrypt.gensalt()), "admin", "1", "titi"));
        emailPattern = Pattern.compile(regexPattern);
    }

    public boolean validateEmail(String email) {
        Matcher matcher = emailPattern.matcher(email);
        return matcher.matches();
    }

    public boolean loginUser(String email, String password) {
        if (!validateEmail(email)) {
            System.out.println("Invalid email format");
            return false;
        }

        for (User user : userList) {
            if (user.checkLogin(email, password)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        //test the login systeme and create account systeme
        LoginSysteme loginSysteme = new LoginSysteme();
        CreateAccSysteme createAccSysteme = new CreateAccSysteme(loginSysteme.userList);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your email: ");
        String email = scanner.nextLine();
        System.out.println("Enter your password: ");
        String password = scanner.nextLine();
        if (loginSysteme.loginUser(email, password)) {
            System.out.println("Login successful");
        } else {
            System.out.println("Login failed");
        }

        System.out.println("Enter your email: ");
        email = scanner.nextLine();
        System.out.println("Enter your password: ");
        password = scanner.nextLine();
        if (createAccSysteme.createUser(email, password)) {
            System.out.println("Account created successfully");
            //print the list of users
            for (User user : loginSysteme.userList) {
                System.out.println(user.email + " " + user.passwordHash + " " + user.role + " " + user.id + " " + user.pseudo);
            }
        } else {
            System.out.println("Account creation failed");
        }
    }
}
