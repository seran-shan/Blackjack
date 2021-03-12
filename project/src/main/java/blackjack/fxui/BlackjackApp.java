package blackjack.fxui;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BlackjackApp extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("Main.fxml"));

        primaryStage.setTitle("BlackJack");
        primaryStage.setScene(new Scene(parent));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(BlackjackApp.class, args);
    }
}