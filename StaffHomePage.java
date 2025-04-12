package application;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class StaffHomePage {
    private final User currentUser;

    public StaffHomePage(User currentUser) {
        this.currentUser = currentUser;
    }

    public void show(Stage primaryStage) {
        VBox layout = new VBox(10);
        layout.setStyle("-fx-padding: 20; -fx-alignment: center;");

        Label title = new Label("Staff Dashboard - " + currentUser.getUserName());
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        Button manageQuestions = new Button("Manage Questions");
        manageQuestions.setOnAction(e -> new ManageQuestionsPage(currentUser).show(primaryStage));

        Button flaggedContent = new Button("View Flagged Content");
        flaggedContent.setOnAction(e -> new FlaggedContentPage(currentUser).show(primaryStage));

        Button reviewManager = new Button("Manage Reviews");
        reviewManager.setOnAction(e -> new ReviewManagerPage(currentUser).show(primaryStage));

        Button messages = new Button("Messages");
        messages.setOnAction(e -> new MessagesPage(currentUser).Show(primaryStage));

        Button logout = new Button("Log Out");
        logout.setOnAction(e -> new StartCSE360().start(primaryStage));

        layout.getChildren().addAll(title, manageQuestions, flaggedContent, reviewManager, messages, logout);
        primaryStage.setScene(new Scene(layout, 800, 400));
        primaryStage.setTitle("Staff Dashboard");
        primaryStage.show();
    }
}
