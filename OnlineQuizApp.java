package com.aec.aqs.main;

import com.aec.aqs.controller.QuizController;
import com.aec.aqs.controller.UserController;

import com.aec.aqs.repository.QuestionRepository;
import com.aec.aqs.repository.QuestionRepositoryImpl;
import com.aec.aqs.repository.ResultRepository;
import com.aec.aqs.repository.ResultRepositoryImpl;
import com.aec.aqs.repository.UserRepository;
import com.aec.aqs.repository.UserRepositoryImpl;

import com.aec.aqs.service.QuizService;
import com.aec.aqs.service.QuizServiceImpl;
import com.aec.aqs.service.UserService;
import com.aec.aqs.service.UserServiceImpl;

import java.util.Scanner;

public class OnlineQuizApp {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Repository objects

        UserRepository userRepository =
                new UserRepositoryImpl();

        QuestionRepository questionRepository =
                new QuestionRepositoryImpl();

        ResultRepository resultRepository =
                new ResultRepositoryImpl();

        // Service objects

        UserService userService =
                new UserServiceImpl(userRepository);

        QuizService quizService =
                new QuizServiceImpl(questionRepository);

        // Controller objects

        UserController userController =
                new UserController(
                        userService,
                        scanner);

        QuizController quizController =
                new QuizController(
                        quizService,
                        resultRepository,
                        scanner);

        while (true) {

            System.out.println(
                    "\n====================================");

            System.out.println(
                    "       ONLINE QUIZ SYSTEM");

            System.out.println(
                    "====================================");

            System.out.println(
                    "1. User Registration");

            System.out.println(
                    "2. View Users");

            System.out.println(
                    "3. Search User");

            System.out.println(
                    "4. Update User");

            System.out.println(
                    "5. Delete User");

            System.out.println(
                    "6. Add Question");

            System.out.println(
                    "7. View Questions");

            System.out.println(
                    "8. Search Question");

            System.out.println(
                    "9. Update Question");

            System.out.println(
                    "10. Delete Question");

            System.out.println(
                    "11. Start Quiz");

            System.out.println(
                    "12. View My Results");

            System.out.println(
                    "13. View All Results");

            System.out.println(
                    "14. Exit");

            System.out.println(
                    "====================================");

            System.out.print(
                    "Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {

                case 1:

                    userController.addUser();

                    break;

                case 2:

                    userController.viewUsers();

                    break;

                case 3:

                    userController.searchUser();

                    break;

                case 4:

                    userController.updateUser();

                    break;

                case 5:

                    userController.deleteUser();

                    break;

                case 6:

                    quizController.addQuestion();

                    break;

                case 7:

                    quizController.viewQuestions();

                    break;

                case 8:

                    quizController.searchQuestion();

                    break;

                case 9:

                    quizController.updateQuestion();

                    break;

                case 10:

                    quizController.deleteQuestion();

                    break;

                case 11:

                    System.out.print(
                            "Enter User ID: ");

                    int userId =
                            scanner.nextInt();

                    if (userService
                            .getUserById(userId)
                            == null) {

                        System.out.println(
                                "User ID " + userId
                                        + " not found!");

                        System.out.println(
                                "Please register first.");

                    } else {

                        quizController
                                .startQuiz(userId);
                    }

                    break;

                case 12:

                    System.out.print(
                            "Enter User ID: ");

                    int resultUserId =
                            scanner.nextInt();

                    quizController
                            .viewMyResults(
                                    resultUserId);

                    break;

                case 13:

                    quizController
                            .viewAllResults();

                    break;

                case 14:

                    System.out.println(
                            "\nThank you for using "
                                    + "Online Quiz System!");

                    scanner.close();

                    System.exit(0);

                    break;

                default:

                    System.out.println(
                            "Invalid choice! "
                                    + "Please try again.");
            }
        }
    }
}