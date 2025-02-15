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
	//If the user chooses to ask a question they will enter the question itself along with a further description
            if (option.equals("1")) {
                // Asking a question
                System.out.println("Please enter your question:");
                String questionText = scanner.nextLine();

                System.out.println("Please enter a description for your question:");
                String description = scanner.nextLine();

                
               //Makes question with these parameters
                Question newQuestion = new Question(user.getUserName(), questionText, description, String.valueOf(val));
                val++;

                // It will be stored to the .txt file
                Questions.writeQuestion(newQuestion);

                //
                Platform.runLater(() -> {
                    layout.getChildren().clear();
                    layout.getChildren().add(new Label("New question added: " + questionText));
                });
	//If they want to view all the questions it will load the.txt file and output it as a Questions Array List
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
	//If they want to answer a question they will type in the number of the question they want to answer along with the answer. 
            } else if (option.equals("3")) {
				
				  System.out.println("Please enter the code of the question you wish to answer: "); 
				  
				  int qNum= scanner.nextInt(); 
				  scanner.nextLine();
				  System.out.println("Please enter the response of your answer: ");
				  String answerInfo=scanner.nextLine();
				  Answer newAnswer =  new Answer(qNum, user.getUserName(), answerInfo);
				 //It will write to the answer file .txt
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
