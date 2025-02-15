package application;

import java.util.Scanner;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.*;
/**
 * This page displays a simple welcome message for the user.
 */
public class UserHomePage {
	Random generator = new Random();
	int val= generator.nextInt(1000);
	int max=10000;
	int min=0;
    private User user;
    private boolean preload = false; 
    private boolean firstVisit=true;

    public UserHomePage(User user) {
        this.user = user;
    }

    public void show(Stage primaryStage) {
        VBox layout = new VBox();
        layout.setStyle("-fx-alignment: center; -fx-padding: 20;");

        Label userLabel = new Label("Hello, " + user.getUserName() + "!");
        userLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        layout.getChildren().add(userLabel);

        Scene userScene = new Scene(layout, 800, 400);
        primaryStage.setScene(userScene);
        primaryStage.setTitle("User Page");
        primaryStage.show();

        new Thread(() -> handleUserInput(layout)).start();
    }

    private void handleUserInput(VBox layout) {
        Scanner scanner = new Scanner(System.in);

        boolean qAndAOn = true;
        
        while (qAndAOn) {
        	
        	if (firstVisit==true) {
           displayMenu();
        	firstVisit=false;}

            String option = scanner.nextLine();

            if (option.equals("1")) {
                // Asking a question
                System.out.println("Please enter your question:");
                String questionText = scanner.nextLine();

                System.out.println("Please enter a description for your question:");
                String description = scanner.nextLine();

                // Generate question ID using timestamp
               
                Question newQuestion = new Question(user.getUserName(), questionText, description, String.valueOf(val));
                val++;

                // Write question to file or database
                Questions.writeQuestion(newQuestion);

                // Update the UI thread to show the new question added
                Platform.runLater(() -> {
                    layout.getChildren().clear();
                    layout.getChildren().add(new Label("New question added: " + questionText));
                });

            } else if (option.equals("2")) {
                // View all questions
                if (!preload) {
                    Questions.loadQuestionsFromFile();
                    preload = true;
                }
                
                Questions.clear();
                Questions.loadQuestionsFromFile();
                // Update the UI with the list of questions
				
				  Platform.runLater(() -> { layout.getChildren().clear();
				  layout.getChildren().add(new Label("Displaying all questions...")); });
				 

                Questions.displayQuestions(); 

            } else if (option.equals("3")) {
				
				  System.out.println("Please enter the code of the question you wish to answer: "); 
				  
				  int qNum= scanner.nextInt(); 
				  scanner.nextLine();
				  System.out.println("Please enter the response of your answer: ");
				  String answerInfo=scanner.nextLine();
				  Answer newAnswer =  new Answer(qNum, user.getUserName(), answerInfo);
				 
				  Answers.writeAnswers(newAnswer); Platform.runLater(() -> {
				  layout.getChildren().clear(); layout.getChildren().add(new Label("New answer added: " + answerInfo));
				  
				
				  });
				 
                
            }else if (option.equals("x")) {
                qAndAOn = false;
                Platform.runLater(() -> layout.getChildren().add(new Label("Exiting Q/A System...")));
                System.out.println("Exiting Q/A System...");
            }

            // **Redisplay menu after each operation**
            displayMenu();
        }
        scanner.close();
    }

    private void displayMenu() {
        System.out.println("\n-----MAIN MENU-----");
        System.out.println("1) Ask Question");
        System.out.println("2) View all Questions");
        System.out.println("3) Answer Questions");
        System.out.println("p) Return to main menu");
        System.out.println("x) Exit Q/A System");
        System.out.print("Select an option: ");
    }
}
