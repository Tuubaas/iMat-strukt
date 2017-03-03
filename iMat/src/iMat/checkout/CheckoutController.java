package iMat.checkout;

import iMat.MainController;
import iMat.checkout.cartconfirmation.CartConfirmationController;
import iMat.checkout.confirmation.ConfirmationController;
import iMat.checkout.deliveryinfo.DeliveryInfoController;
import iMat.checkout.paymentinfo.PaymentInfoController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.SVGPath;

import java.net.URL;
import java.util.ResourceBundle;

public class CheckoutController implements Initializable {

    private MainController mc;

    //Controllers
    @FXML
    private CartConfirmationController cartConfirmationController;
    @FXML
    private ConfirmationController confirmationController;
    @FXML
    private DeliveryInfoController deliveryInfoController;
    @FXML
    private PaymentInfoController paymentInfoController;

    @FXML
    private AnchorPane loggedInAnchor;
    @FXML
    private AnchorPane loggedInCartConfirmationAnchor;
    @FXML
    private AnchorPane loggedInConfirmationAnchor;
    @FXML
    private AnchorPane loggedInDeliveryInfoAnchor;
    @FXML
    private AnchorPane loggedInPaymentInfoAnchor;
    @FXML
    private SVGPath arrow3;
    @FXML
    private SVGPath arrow4;
    @FXML
    private SVGPath arrow1;
    @FXML
    private SVGPath arrow2;
    @FXML
    private SVGPath arrow5;
    @FXML
    private Label arrowLabel41;
    @FXML
    private Label arrowLabel1;
    @FXML
    private Label arrowLabel2;
    @FXML
    private SVGPath arrow11;
    @FXML
    private Label arrowLabel5;
    @FXML
    private SVGPath arrow31;
    @FXML
    private Label arrowLabel3;
    @FXML
    private SVGPath arrow51;
    @FXML
    private Label arrowLabel4;
    @FXML
    private Label arrowLabel51;
    @FXML
    private Label arrowLabel11;
    @FXML
    private Label arrowLabel31;
    @FXML
    private SVGPath arrow41;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cartConfirmationController.injectCheckoutController(this);
    }


    public void injectMainController(MainController mc){
        this.mc = mc;
    }

    public void startCheckout(){
        loggedInCartConfirmationAnchor.setVisible(true);
        loggedInCartConfirmationAnchor.toFront();
    }

    public void onConfirmCartNextButtonClicked(){
        if (mc.isLoggedIn()){
            loggedInDeliveryInfoAnchor.setVisible(true);
            loggedInDeliveryInfoAnchor.toFront();
        }
        else {
            mc.logInAnchor.setVisible(true);
            mc.logInAnchor.toFront();
        }
    }

    public void onDeliveryInfoNextButtonClicked(){
        loggedInPaymentInfoAnchor.setVisible(true);
        loggedInPaymentInfoAnchor.toFront();
    }

    public void onPaymentInfoNextButtonClicked(){
        loggedInConfirmationAnchor.setVisible(true);
        loggedInConfirmationAnchor.toFront();
    }

    public void onConfirmationNextButtonClicked(){
        mc.purchaseDoneAnchor.setVisible(true);
        mc.purchaseDoneAnchor.toFront();
    }
}
