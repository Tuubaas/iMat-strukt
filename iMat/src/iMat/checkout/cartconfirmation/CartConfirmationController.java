package iMat.checkout.cartconfirmation;

import iMat.checkout.CheckoutController;
import iMat.checkout.deliveryinfo.DeliveryInfoController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class CartConfirmationController implements Initializable{

    private CheckoutController cc;

    private DeliveryInfoController dc;

    @FXML
    private Button cartConfirmationNextButton;
    @FXML
    private Button cartConfirmationBackButton;
    @FXML
    private AnchorPane cartConfirmationAnchor;
    @FXML
    private AnchorPane cartConfirmationScrollAnchor;
    @FXML
    private ScrollPane cartConfirmationScrollPane;

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
}
