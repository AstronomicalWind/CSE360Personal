package application;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


import java.sql.SQLException;

import databasePart1.*;

/**
 * The SetupAdmin class handles the setup process for creating an administrator account.
 * This is intended to be used by the first user to initialize the system with admin credentials.
 */
public class AdminSetupPage {
	
    private final DatabaseHelper databaseHelper;

    public AdminSetupPage(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }

    public void show(Stage primaryStage) {
    	// Input fields for userName and password
        TextField userNameField = new TextField();
        userNameField.setPromptText("Enter Admin userName");
        userNameField.setMaxWidth(250);

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Enter Password");
        passwordField.setMaxWidth(250);

        Button setupButton = new Button("Setup");
        //Label for username error and make it red color
        Label labelErrorUsername = new Label();
        labelErrorUsername.setStyle("-fx-text-fill: red; -fx-font-size: 12px;");
        //Label for password error and make it red color
        Label labelErrorPassword= new Label();
        labelErrorPassword.setStyle("-fx-text-fill: red; -fx-font-size: 12px;");
        
        setupButton.setOnAction(a -> {
        	// Retrieve user input
            String userName = userNameField.getText();
            String password = passwordField.getText();
            //Uses the userName recognizer class to check if the name is valid or not stored to a variable
            String userNameVer=UserNameRecognizer.checkForValidUserName(userName);
            String passwordVer=PasswordEvaluator.evaluatePassword(password);
            //Prints out for debugging purposes
            System.out.println(userNameVer);
            
            
            //UserNameRecognizer.checkForValidUserName(userName);
			
			//If an empty string is not returned it means there is an error
			if (userNameVer!="") {
				//if there is an error label it on the red textbox
				labelErrorUsername.setText(userNameVer);
				//prevent submission of fields since the username is not acceptable
			   return; }
			//if no error for the username then don't display anything
			else {
            	labelErrorUsername.setText("");
            }
			//If empty string is not returned there is an issue with the password
			if (passwordVer!="") {
				//present the label notifying an error and the specific issue
				labelErrorPassword.setText("Password error: Needs "+passwordVer);
				//prevent submission since password is not acceptable
				return;
			}
			//if no error with the password then don't display anything
			else {
				labelErrorPassword.setText("");
			}
			
			 
            try {
            	// Create a new User object with admin role and register in the database
            	User user=new User(userName, password, "admin");
                databaseHelper.register(user);
                System.out.println("Administrator setup completed.");
                
                // Navigate to the Welcome Login Page
                new WelcomeLoginPage(databaseHelper).show(primaryStage,user);
            } catch (SQLException e) {
                System.err.println("Database error: " + e.getMessage());
                e.printStackTrace();
            }
        });
        //Add the new labels to to the VBox so they can be displayed
        VBox layout = new VBox(10, userNameField, passwordField, setupButton, labelErrorUsername, labelErrorPassword);
        layout.setStyle("-fx-padding: 20; -fx-alignment: center;");

        primaryStage.setScene(new Scene(layout, 800, 400));
        primaryStage.setTitle("Administrator Setup");
        primaryStage.show();
    }
}
