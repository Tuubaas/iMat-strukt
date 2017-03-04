package iMat.checkout;

import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;
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

    private DeliveryInfoController dic;

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
    private AnchorPane cartConfirmationAnchor;
    @FXML
    private AnchorPane confirmationAnchor;
    @FXML
    private AnchorPane deliveryInfoAnchor;
    @FXML
    private AnchorPane paymentInfoAnchor;
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
        deliveryInfoController.injectCheckoutController(this);
        paymentInfoController.injectCheckoutController(this);
        confirmationController.injectCheckoutController(this);
        startCheckout();
        System.out.println("Test commit");
    }

    public void injectMainController(MainController mc){
        this.mc = mc;
    }

    public void startCheckout(){
        cartConfirmationAnchor.setVisible(true);
        cartConfirmationAnchor.toFront();
    }

    public void onConfirmCartNextButtonClicked(){
            System.out.println("Inloggad");
            deliveryInfoAnchor.setVisible(true);
            deliveryInfoAnchor.toFront();
    }

    public void onDeliveryInfoNextButtonClicked(){
        System.out.println("Går från Delivery info");
        paymentInfoAnchor.setVisible(true);
        paymentInfoAnchor.toFront();
    }

    public void onPaymentInfoNextButtonClicked(){
        System.out.println("Går från Payment info");
        confirmationAnchor.setVisible(true);
        confirmationAnchor.toFront();
    }

    public void onConfirmationNextButtonClicked(){
        System.out.println("går från Confirmation");
        mc.purchaseDoneAnchor.setVisible(true);
        mc.purchaseDoneAnchor.toFront();
    }

    public void onCartConfirmationBackButtonClicked(){
        mc.mainAnchor.setVisible(true);
        mc.mainAnchor.toFront();
    }

    public void onDeliveryInfoBackButtonClicked(){
        cartConfirmationAnchor.setVisible(true);
        cartConfirmationAnchor.toFront();
    }

    public void onPaymentInfoBackButtonClicked(){
        deliveryInfoAnchor.setVisible(true);
        deliveryInfoAnchor.toFront();
    }

    public void onConfirmationBackButtonClicked(){
        paymentInfoAnchor.setVisible(true);
        paymentInfoAnchor.toFront();
    }

}
