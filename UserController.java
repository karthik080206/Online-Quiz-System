package com.aec.aqs.controller;

import com.aec.aqs.model.User;
import com.aec.aqs.service.UserService;

import java.util.List;
import java.util.Scanner;

public class UserController {

    private UserService service;
    private Scanner scanner;

    public UserController(
            UserService service,
            Scanner scanner) {

        this.service = service;
        this.scanner = scanner;
    }

    public void addUser() {

        scanner.nextLine();

        System.out.print("Enter Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Email: ");
        String email = scanner.nextLine();

        service.addUser(new User(name, email));
    }

    public void viewUsers() {

        List<User> users =
                service.getAllUsers();

        if (users.isEmpty()) {

            System.out.println(
                    "No users found.");

            return;
        }

        System.out.println("\n========== USERS ==========");

        for (User user : users) {
            System.out.println(user);
        }
    }

    public void searchUser() {

        System.out.print("Enter User ID: ");
        int id = scanner.nextInt();

        User user = service.getUserById(id);

        if (user == null) {

            System.out.println(
                    "User ID " + id + " not found!");

        } else {

            System.out.println(user);
        }
    }

    public void updateUser() {

        System.out.print("Enter User ID: ");
        int id = scanner.nextInt();

        User existing =
                service.getUserById(id);

        if (existing == null) {

            System.out.println(
                    "User ID " + id + " not found!");

            System.out.println(
                    "Cannot update.");

            return;
        }

        scanner.nextLine();

        System.out.print("Enter New Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter New Email: ");
        String email = scanner.nextLine();

        service.updateUser(
                new User(id, name, email));
    }

    public void deleteUser() {

        System.out.print("Enter User ID: ");
        int id = scanner.nextInt();

        User existing =
                service.getUserById(id);

        if (existing == null) {

            System.out.println(
                    "User ID " + id + " not found!");

            System.out.println(
                    "Cannot delete.");

            return;
        }

        service.deleteUser(id);
    }
}