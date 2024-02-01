package com.iStore.authentification;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LoginSysteme {
    private List<User> userList;

    public LoginSysteme() {
        userList = new ArrayList<>();
        userList.add(new User("titi", "titi"));
    }

    public boolean loginUser(String email, String password) {
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
            System.out.println("Enter your password: ");
            String password = scanner.nextLine();

            loginSuccessful = loginSystem.loginUser(email, password);
            if (loginSuccessful) {
                System.out.println("Login successful");
            } else {
                System.out.println("Login failed , password or email incorrect");
            }
        }
    }
}
