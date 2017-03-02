package iMat.welcomepage;

import iMat.MainController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class WelcomePageController implements Initializable {

    private MainController mc;


    @FXML
    private AnchorPane mainPane;

    @FXML
    private Button startButton;

    @FXML
    private Label welcomeText;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        startButton.setDefaultButton(true);
    }

    public void injectMainController(MainController mc) {
        this.mc = mc;
    }

    @FXML
    public void startButtonPressed() {
        mc.goToShopHome();
    }

    public void setHeight(int height) {
        mainPane.setPrefHeight(height);
    }

    public void setWidth(int width) {
        mainPane.setPrefWidth(width);
    }
}
