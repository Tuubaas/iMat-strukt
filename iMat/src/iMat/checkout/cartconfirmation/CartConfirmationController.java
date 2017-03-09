package iMat.checkout.cartconfirmation;

import iMat.MainController;
import iMat.checkout.CheckoutController;
import iMat.checkout.deliveryinfo.DeliveryInfoController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import se.chalmers.ait.dat215.project.ShoppingCart;
import se.chalmers.ait.dat215.project.ShoppingItem;

import java.net.URL;
import java.util.ResourceBundle;

public class CartConfirmationController implements Initializable{

    private CheckoutController cc;

    private DeliveryInfoController dc;

    private CartConfirmationItem cci;

    @FXML
    private Button cartConfirmationNextButton;
    @FXML
    private Button cartConfirmationBackButton;
    @FXML
    private AnchorPane cartConfirmationAnchor;
    @FXML
    private FlowPane cartConfirmationFlowPane;
    @FXML
    private ScrollPane cartConfirmationScrollPane;
    @FXML
    private Label totalLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void injectCheckoutController(CheckoutController cc){
        this.cc = cc;
    }

    public void injectDeliveryInfoController(DeliveryInfoController dc){
        this.dc = dc;
    }

    @FXML
    public void onConfirmCartNextButtonClicked() {
        cc.onConfirmCartNextButtonClicked();
        if (cc.getMc().isLoggedIn()){
            dc.loggedInAnchorToFront();
        }
        else {
            dc.notLoggedInAnchorToFront();
        }

    }

    public void onCartConfirmationBackButton(){
        cc.onCartConfirmationBackButtonClicked();
    }

    public void setCartProducts(){
        cartConfirmationFlowPane.getChildren().clear();

        for (ShoppingItem item : cc.getWrapper().getShoppingCart().getItems()){
            cartConfirmationFlowPane.getChildren().add(new CartConfirmationItem(item, this));
        }
    }

    public CheckoutController getCC(){
        return this.cc;
    }

    public void clearFlowPane(){
        cartConfirmationFlowPane.getChildren().clear();
    }

    public void setTotalLabel() {
        totalLabel.setText("Totalpris: " + String.format("%.2f",cc.getWrapper().getShoppingCart().getTotal()) + " kr");
    }
}
