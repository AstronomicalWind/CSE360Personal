package application;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FlaggedContentPage {
    private final QuestionsManager questionsManager = QuestionsManager.getInstance();
    private final User currentUser;

    public FlaggedContentPage(User currentUser) {
        this.currentUser = currentUser;
    }

    public void show(Stage primaryStage) {
        VBox layout = new VBox(10);
        layout.setStyle("-fx-padding: 20; -fx-alignment: center;");

        Label label = new Label("Flagged Content for Review");
        ListView<String> flaggedList = new ListView<>();

        for (Question q : questionsManager.getQuestions()) {
            if (q.isFlagged()) {
                flaggedList.getItems().add("Q" + q.getId() + ": " + q.getTitle());
            }
        }

        Button deleteButton = new Button("Delete Selected");
        deleteButton.setOnAction(e -> {
            String selected = flaggedList.getSelectionModel().getSelectedItem();
            if (selected != null && selected.startsWith("Q")) {
                int id = Integer.parseInt(selected.substring(1, selected.indexOf(":")));
                questionsManager.deleteQuestion(id);
                flaggedList.getItems().remove(selected);
                showAlert("Deleted", "Flagged question removed.", Alert.AlertType.INFORMATION);
            }
        });

        Button back = new Button("Back");
        back.setOnAction(e -> new StaffHomePage(currentUser).show(primaryStage));

        layout.getChildren().addAll(label, flaggedList, deleteButton, back);
        primaryStage.setScene(new Scene(layout, 800, 400));
        primaryStage.setTitle("Flagged Questions");
        primaryStage.show();
    }

    private void showAlert(String title, String msg, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
