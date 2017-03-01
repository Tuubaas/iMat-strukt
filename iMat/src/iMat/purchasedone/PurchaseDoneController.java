package iMat.purchasedone;

import iMat.MainController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class PurchaseDoneController implements Initializable {

    // Graphical elements in the PurchaseDoneView
    @FXML
    private AnchorPane mainAnchor;
    @FXML
    private Button goToStoreButton;
    @FXML
    private Button printConfirmationButton;
    @FXML
    private AnchorPane confirmationAnchorPane;
    @FXML
    private TextArea receiptField;

    private MainController mc;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void injectMainController(MainController mc) {
        this.mc = mc;
    }

    @FXML
    private void onGoToStoreButtonClicked() {
        mc.goToShop();
    }

    public void setHeight(int height) {
        mainAnchor.setPrefHeight(height);
    }

    public void setWidth(int width) {
        mainAnchor.setPrefWidth(width);
    }
}
