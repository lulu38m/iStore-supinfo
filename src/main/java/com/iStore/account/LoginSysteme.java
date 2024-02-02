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
        userList.add(new User("titi@blabla.com", BCrypt.hashpw("titi", BCrypt.gensalt())));
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
        LoginSysteme loginSystem = new LoginSysteme();

        Scanner scanner = new Scanner(System.in);

        boolean loginSuccessful = false;

        while (!loginSuccessful) {
            System.out.println("Enter your email: ");
            String email = scanner.nextLine();

            if (!loginSystem.validateEmail(email)) {
                System.out.println("Invalid email format. Please enter a valid email.");
                continue;
            }

            System.out.println("Enter your password: ");
            String password = scanner.nextLine();

            loginSuccessful = loginSystem.loginUser(email, password);
            if (loginSuccessful) {
                System.out.println("Login successful");
            } else {
                System.out.println("Login failed, password or email incorrect");
            }
        }
    }
}
