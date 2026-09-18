package com.aec.aqs.controller;

import com.aec.aqs.model.Question;
import com.aec.aqs.model.Result;
import com.aec.aqs.repository.ResultRepository;
import com.aec.aqs.service.QuizService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QuizController {

    private QuizService service;
    private ResultRepository resultRepository;
    private Scanner scanner;

    public QuizController(
            QuizService service,
            ResultRepository resultRepository,
            Scanner scanner) {

        this.service = service;
        this.resultRepository = resultRepository;
        this.scanner = scanner;
    }

    public void addQuestion() {

        scanner.nextLine();

        System.out.print("Enter Question: ");
        String question = scanner.nextLine();

        System.out.print("Enter Option A: ");
        String a = scanner.nextLine();

        System.out.print("Enter Option B: ");
        String b = scanner.nextLine();

        System.out.print("Enter Option C: ");
        String c = scanner.nextLine();

        System.out.print("Enter Option D: ");
        String d = scanner.nextLine();

        System.out.print("Enter Correct Option (A/B/C/D): ");
        char correct =
                Character.toUpperCase(
                        scanner.nextLine().charAt(0));

        Question q = new Question(
                question, a, b, c, d, correct);

        service.addQuestion(q);
    }

    public void viewQuestions() {

        List<Question> questions =
                service.getAllQuestions();

        if (questions.isEmpty()) {

            System.out.println(
                    "No questions found.");

            return;
        }

        for (Question q : questions) {

            System.out.println(
                    "\nQuestion ID: "
                            + q.getQuestionId());

            System.out.println(
                    "Question: "
                            + q.getQuestionText());

            System.out.println(
                    "A. " + q.getOptionA());

            System.out.println(
                    "B. " + q.getOptionB());

            System.out.println(
                    "C. " + q.getOptionC());

            System.out.println(
                    "D. " + q.getOptionD());
        }
    }

    public void searchQuestion() {

        System.out.print(
                "Enter Question ID: ");

        int id = scanner.nextInt();

        Question q =
                service.getQuestionById(id);

        if (q == null) {

            System.out.println(
                    "Question ID " + id
                            + " not found!");

        } else {

            System.out.println(q);
        }
    }

    public void updateQuestion() {

        System.out.print(
                "Enter Question ID: ");

        int id = scanner.nextInt();

        Question existing =
                service.getQuestionById(id);

        if (existing == null) {

            System.out.println(
                    "Question ID " + id
                            + " not found!");

            System.out.println(
                    "Cannot update.");

            return;
        }

        scanner.nextLine();

        System.out.print(
                "Enter New Question: ");

        String question =
                scanner.nextLine();

        System.out.print(
                "Enter New Option A: ");

        String a = scanner.nextLine();

        System.out.print(
                "Enter New Option B: ");

        String b = scanner.nextLine();

        System.out.print(
                "Enter New Option C: ");

        String c = scanner.nextLine();

        System.out.print(
                "Enter New Option D: ");

        String d = scanner.nextLine();

        System.out.print(
                "Enter Correct Option: ");

        char correct =
                Character.toUpperCase(
                        scanner.nextLine()
                                .charAt(0));

        Question q = new Question(
                id, question, a, b, c, d, correct);

        service.updateQuestion(q);
    }

    public void deleteQuestion() {

        System.out.print(
                "Enter Question ID: ");

        int id = scanner.nextInt();

        Question existing =
                service.getQuestionById(id);

        if (existing == null) {

            System.out.println(
                    "Question ID " + id
                            + " not found!");

            System.out.println(
                    "Cannot delete.");

            return;
        }

        service.deleteQuestion(id);
    }

    public void startQuiz(int userId) {

        List<Question> questions =
                service.getAllQuestions();

        if (questions.isEmpty()) {

            System.out.println(
                    "No questions available.");

            return;
        }

        List<Character> answers =
                new ArrayList<>();

        System.out.println(
                "\n================================");

        System.out.println(
                "         ONLINE QUIZ");

        System.out.println(
                "================================");

        int number = 1;

        for (Question q : questions) {

            System.out.println(
                    "\nQ" + number + ". "
                            + q.getQuestionText());

            System.out.println(
                    "A. " + q.getOptionA());

            System.out.println(
                    "B. " + q.getOptionB());

            System.out.println(
                    "C. " + q.getOptionC());

            System.out.println(
                    "D. " + q.getOptionD());

            char answer;

            while (true) {

                System.out.print(
                        "Enter Answer (A/B/C/D): ");

                answer =
                        Character.toUpperCase(
                                scanner.next()
                                        .charAt(0));

                if (answer == 'A' ||
                        answer == 'B' ||
                        answer == 'C' ||
                        answer == 'D') {

                    break;
                }

                System.out.println(
                        "Invalid option! "
                                + "Enter A, B, C or D.");
            }

            answers.add(answer);

            number++;
        }

        int score =
                service.calculateScore(
                        questions, answers);

        int total = questions.size();

        System.out.println(
                "\n================================");

        System.out.println(
                "          QUIZ RESULT");

        System.out.println(
                "================================");

        System.out.println(
                "Score: " + score + "/" + total);

        double percentage =
                ((double) score / total) * 100;

        System.out.printf(
                "Percentage: %.2f%%%n",
                percentage);

        if (percentage >= 80) {

            System.out.println(
                    "Grade: Excellent");

        } else if (percentage >= 60) {

            System.out.println(
                    "Grade: Good");

        } else if (percentage >= 40) {

            System.out.println(
                    "Grade: Average");

        } else {

            System.out.println(
                    "Grade: Need Improvement");
        }

        Result result =
                new Result(
                        userId,
                        score,
                        total);

        resultRepository.saveResult(result);
    }

    public void viewMyResults(int userId) {

        List<Result> results =
                resultRepository
                        .getResultsByUser(userId);

        if (results.isEmpty()) {

            System.out.println(
                    "No previous results found.");

            return;
        }

        System.out.println(
                "\n========== MY RESULTS ==========");

        for (Result result : results) {

            System.out.println(result);
        }
    }

    public void viewAllResults() {

        List<Result> results =
                resultRepository.getAllResults();

        if (results.isEmpty()) {

            System.out.println(
                    "No results found.");

            return;
        }

        System.out.println(
                "\n========== ALL RESULTS ==========");

        for (Result result : results) {

            System.out.println(result);
        }
    }
}