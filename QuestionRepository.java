package com.aec.aqs.repository;

import com.aec.aqs.model.Question;

import java.util.List;

public interface QuestionRepository {

    void addQuestion(Question question);

    List<Question> getAllQuestions();

    Question getQuestionById(int questionId);

    void updateQuestion(Question question);

    void deleteQuestion(int questionId);
}