package blackjack.fxui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BlackjackApp extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
	Parent parent = FXMLLoader.load(getClass().getResource("StartPage.fxml"));

	primaryStage.setTitle("BlackJack");
	primaryStage.setScene(new Scene(parent));
	primaryStage.show();
	}

	public static void main(String[] args) {
	launch(BlackjackApp.class, args);
	}

	// private Parent rootNode;

	// public static void main(final String[] args) {
	// 	Application.launch(args);
	// }

	// @Override
	// public void init() throws Exception {
	// 	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("StartPage.fxml"));
	// 	rootNode = fxmlLoader.load();
	// }

	// @Override
	// public void start(Stage stage) throws Exception {
	// 	stage.setScene(new Scene(rootNode));
	// 	stage.show();
	// }
}