package com.aec.aqs.service;

import com.aec.aqs.model.Question;

import java.util.List;

public interface QuizService {

    void addQuestion(Question question);

    List<Question> getAllQuestions();

    Question getQuestionById(int id);

    void updateQuestion(Question question);

    void deleteQuestion(int id);

    int calculateScore(
            List<Question> questions,
            List<Character> answers
    );
}