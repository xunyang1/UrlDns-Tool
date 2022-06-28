package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(this.getClass().getResource("/sample.fxml"));
        primaryStage.setTitle("URLDNS 探测工具 coded by 浔阳江头夜送客");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
