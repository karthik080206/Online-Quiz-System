package com.aec.aqs.repository;

import com.aec.aqs.database.Database;
import com.aec.aqs.model.Question;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuestionRepositoryImpl
        implements QuestionRepository {

    @Override
    public void addQuestion(Question q) {

        String sql =
                "INSERT INTO questions " +
                "(question_text, option_a, option_b, " +
                "option_c, option_d, correct_option) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = Database.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, q.getQuestionText());
            ps.setString(2, q.getOptionA());
            ps.setString(3, q.getOptionB());
            ps.setString(4, q.getOptionC());
            ps.setString(5, q.getOptionD());
            ps.setString(6,
                    String.valueOf(q.getCorrectOption()));

            ps.executeUpdate();

            System.out.println(
                    "Question added successfully!");

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public List<Question> getAllQuestions() {

        List<Question> list = new ArrayList<>();

        String sql = "SELECT * FROM questions";

        try (Connection con = Database.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {

                Question q = new Question(
                        rs.getInt("question_id"),
                        rs.getString("question_text"),
                        rs.getString("option_a"),
                        rs.getString("option_b"),
                        rs.getString("option_c"),
                        rs.getString("option_d"),
                        rs.getString("correct_option")
                                .charAt(0)
                );

                list.add(q);
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return list;
    }

    @Override
    public Question getQuestionById(int questionId) {

        String sql =
                "SELECT * FROM questions WHERE question_id=?";

        try (Connection con = Database.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, questionId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                return new Question(
                        rs.getInt("question_id"),
                        rs.getString("question_text"),
                        rs.getString("option_a"),
                        rs.getString("option_b"),
                        rs.getString("option_c"),
                        rs.getString("option_d"),
                        rs.getString("correct_option")
                                .charAt(0)
                );
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return null;
    }

    @Override
    public void updateQuestion(Question q) {

        String sql =
                "UPDATE questions SET " +
                "question_text=?, option_a=?, option_b=?, " +
                "option_c=?, option_d=?, correct_option=? " +
                "WHERE question_id=?";

        try (Connection con = Database.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, q.getQuestionText());
            ps.setString(2, q.getOptionA());
            ps.setString(3, q.getOptionB());
            ps.setString(4, q.getOptionC());
            ps.setString(5, q.getOptionD());
            ps.setString(6,
                    String.valueOf(q.getCorrectOption()));
            ps.setInt(7, q.getQuestionId());

            int rows = ps.executeUpdate();

            if (rows > 0) {

                System.out.println(
                        "Question updated successfully!");

            } else {

                System.out.println(
                        "Question ID " + q.getQuestionId()
                                + " not found!");

                System.out.println("Cannot update.");
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public void deleteQuestion(int questionId) {

        String sql =
                "DELETE FROM questions WHERE question_id=?";

        try (Connection con = Database.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, questionId);

            int rows = ps.executeUpdate();

            if (rows > 0) {

                System.out.println(
                        "Question deleted successfully!");

            } else {

                System.out.println(
                        "Question ID " + questionId
                                + " not found!");

                System.out.println("Cannot delete.");
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}