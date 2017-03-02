package iMat;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class iMat extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
        primaryStage.setTitle("iMat");
        primaryStage.setScene(new Scene(root, 1280, 720));
        primaryStage.show();
        primaryStage.setMinWidth(800);
        primaryStage.setMinHeight(725);

        primaryStage.setOnCloseRequest(event -> MainController.getBackendWrapper().shutDown());
    }


    public static void main(String[] args) {
        launch(args);
    }
}
