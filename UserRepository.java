package com.aec.aqs.repository;


import com.aec.aqs.model.User;
import java.util.List;

public interface UserRepository {

    void addUser(User user);

    List<User> getAllUsers();

    User getUserById(int userId);

    void updateUser(User user);

    void deleteUser(int userId);
}