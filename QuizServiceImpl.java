package com.aec.aqs.service;

import com.aec.aqs.model.Question;
import com.aec.aqs.repository.QuestionRepository;

import java.util.List;

public class QuizServiceImpl implements QuizService {

    private QuestionRepository repository;

    public QuizServiceImpl(
            QuestionRepository repository) {

        this.repository = repository;
    }

    @Override
    public void addQuestion(Question question) {

        char answer =
                Character.toUpperCase(
                        question.getCorrectOption());

        if (answer != 'A' &&
                answer != 'B' &&
                answer != 'C' &&
                answer != 'D') {

            System.out.println(
                    "Correct option must be A, B, C or D.");

            return;
        }

        question.setCorrectOption(answer);

        repository.addQuestion(question);
    }

    @Override
    public List<Question> getAllQuestions() {
        return repository.getAllQuestions();
    }

    @Override
    public Question getQuestionById(int id) {
        return repository.getQuestionById(id);
    }

    @Override
    public void updateQuestion(Question question) {
        repository.updateQuestion(question);
    }

    @Override
    public void deleteQuestion(int id) {
        repository.deleteQuestion(id);
    }

    @Override
    public int calculateScore(
            List<Question> questions,
            List<Character> answers) {

        int score = 0;

        for (int i = 0; i < questions.size(); i++) {

            char correct =
                    Character.toUpperCase(
                            questions.get(i)
                                    .getCorrectOption());

            char answer =
                    Character.toUpperCase(
                            answers.get(i));

            if (correct == answer) {
                score++;
            }
        }

        return score;
    }
}