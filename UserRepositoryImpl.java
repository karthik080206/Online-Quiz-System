package com.aec.aqs.repository;

import com.aec.aqs.database.Database;
import com.aec.aqs.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {

    @Override
    public void addUser(User user) {

        String sql =
                "INSERT INTO users(name, email) VALUES (?, ?)";

        try (Connection con = Database.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("User added successfully!");
            }

        } catch (SQLException e) {
            System.out.println("Error adding user: "
                    + e.getMessage());
        }
    }

    @Override
    public List<User> getAllUsers() {

        List<User> users = new ArrayList<>();

        String sql = "SELECT * FROM users";

        try (Connection con = Database.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {

                User user = new User(
                        rs.getInt("user_id"),
                        rs.getString("name"),
                        rs.getString("email")
                );

                users.add(user);
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return users;
    }

    @Override
    public User getUserById(int userId) {

        String sql =
                "SELECT * FROM users WHERE user_id=?";

        try (Connection con = Database.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                return new User(
                        rs.getInt("user_id"),
                        rs.getString("name"),
                        rs.getString("email")
                );
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return null;
    }

    @Override
    public void updateUser(User user) {

        String sql =
                "UPDATE users SET name=?, email=? WHERE user_id=?";

        try (Connection con = Database.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setInt(3, user.getUserId());

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("User updated successfully!");
            } else {
                System.out.println("User ID "
                        + user.getUserId() + " not found!");
                System.out.println("Cannot update.");
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public void deleteUser(int userId) {

        String sql =
                "DELETE FROM users WHERE user_id=?";

        try (Connection con = Database.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("User deleted successfully!");
            } else {
                System.out.println("User ID "
                        + userId + " not found!");
                System.out.println("Cannot delete.");
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}