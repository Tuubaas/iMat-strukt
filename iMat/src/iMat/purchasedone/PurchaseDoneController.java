package iMat.purchasedone;

import iMat.BackendWrapper;
import iMat.MainController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import se.chalmers.ait.dat215.project.ShoppingCart;
import se.chalmers.ait.dat215.project.ShoppingItem;

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

    private String receipt = "iMat - Ditt kvitto!" + "\n";


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        receiptField.setEditable(false);
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

    public void writeReceipt(){
        for (ShoppingItem item : MainController.getBackendWrapper().getShoppingCart().getItems()){
            receipt = receipt + "\n" + item.getProduct().getName() + "       " + item.getAmount() + " á " + item.getProduct().getPrice() + "       " + item.getTotal() + "\n";
        }
        receiptField.setText(receipt);
    }
}
