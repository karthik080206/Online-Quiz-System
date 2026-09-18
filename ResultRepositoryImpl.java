package com.aec.aqs.repository;

import com.aec.aqs.database.Database;
import com.aec.aqs.model.Result;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ResultRepositoryImpl
        implements ResultRepository {

    @Override
    public void saveResult(Result result) {

        String sql =
                "INSERT INTO results " +
                "(user_id, score, total_questions) " +
                "VALUES (?, ?, ?)";

        try (Connection con = Database.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, result.getUserId());
            ps.setInt(2, result.getScore());
            ps.setInt(3, result.getTotalQuestions());

            ps.executeUpdate();

            System.out.println(
                    "Result saved successfully!");

        } catch (SQLException e) {
            System.out.println("Error saving result: "
                    + e.getMessage());
        }
    }

    @Override
    public List<Result> getResultsByUser(int userId) {

        List<Result> list = new ArrayList<>();

        String sql =
                "SELECT * FROM results WHERE user_id=?";

        try (Connection con = Database.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                list.add(new Result(
                        rs.getInt("result_id"),
                        rs.getInt("user_id"),
                        rs.getInt("score"),
                        rs.getInt("total_questions")
                ));
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return list;
    }

    @Override
    public List<Result> getAllResults() {

        List<Result> list = new ArrayList<>();

        String sql = "SELECT * FROM results";

        try (Connection con = Database.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {

                list.add(new Result(
                        rs.getInt("result_id"),
                        rs.getInt("user_id"),
                        rs.getInt("score"),
                        rs.getInt("total_questions")
                ));
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return list;
    }
}