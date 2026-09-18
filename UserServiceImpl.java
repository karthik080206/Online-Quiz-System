package com.aec.aqs.service;

import com.aec.aqs.model.User;
import com.aec.aqs.repository.UserRepository;

import java.util.List;

public class UserServiceImpl implements UserService {

    private UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public void addUser(User user) {

        if (user.getName() == null ||
                user.getName().trim().isEmpty()) {

            System.out.println("Name cannot be empty.");
            return;
        }

        if (user.getEmail() == null ||
                !user.getEmail().contains("@")) {

            System.out.println("Invalid email.");
            return;
        }

        repository.addUser(user);
    }

    @Override
    public List<User> getAllUsers() {
        return repository.getAllUsers();
    }

    @Override
    public User getUserById(int id) {
        return repository.getUserById(id);
    }

    @Override
    public void updateUser(User user) {
        repository.updateUser(user);
    }

    @Override
    public void deleteUser(int id) {
        repository.deleteUser(id);
    }
}