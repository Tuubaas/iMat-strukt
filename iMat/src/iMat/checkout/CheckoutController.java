package iMat.checkout;

import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;
import iMat.BackendWrapper;
import iMat.MainController;
import iMat.checkout.cartconfirmation.CartConfirmationController;
import iMat.checkout.cartconfirmation.CartConfirmationItem;
import iMat.checkout.confirmation.ConfirmationController;
import iMat.checkout.deliveryinfo.DeliveryInfoController;
import iMat.checkout.paymentinfo.PaymentInfoController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.SVGPath;

import java.net.URL;
import java.util.ResourceBundle;

public class CheckoutController implements Initializable {

    private MainController mc;

    private BackendWrapper wrapper;

    private DeliveryInfoController dic;

    private PaymentInfoController pic;

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
    @FXML
    private Button confirmCartButton;
    @FXML
    private Button deliveryInfoButton;
    @FXML
    private Button paymentInfoButton;
    @FXML
    private Button confirmPurchaseButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cartConfirmationController.injectCheckoutController(this);
        deliveryInfoController.injectCheckoutController(this);
        paymentInfoController.injectCheckoutController(this);
        confirmationController.injectCheckoutController(this);
        cartConfirmationController.injectDeliveryInfoController(this.deliveryInfoController);
        deliveryInfoController.injectPaymentInfoController(this.paymentInfoController);
        paymentInfoController.injectConfirmationController(this.confirmationController);
        confirmationController.injectPaymentInfoController(this.paymentInfoController);
        confirmationController.injectDeliveryInfoController(this.deliveryInfoController);
        startCheckout();
    }

    public void setWidth(double newValue){
        loggedInAnchor.setPrefWidth(newValue);
    }

    public void setHeight(double newValue){
        loggedInAnchor.setPrefHeight(newValue);
    }

    public void injectMainController(MainController mc){
        this.mc = mc;
        this.wrapper = MainController.getBackendWrapper();
    }

    public void startCheckout(){
        cartConfirmationAnchor.setVisible(true);
        cartConfirmationAnchor.toFront();
        confirmCartButton.setId("onNextButtonClicked");
    }

    public void onConfirmCartNextButtonClicked(){
        deliveryInfoAnchor.setVisible(true);
        deliveryInfoAnchor.toFront();
        confirmCartButton.setId("onButtonVisited");
        deliveryInfoButton.setId("onNextButtonClicked");
        deliveryInfoButton.setDisable(false);
    }

    public void onDeliveryInfoNextButtonClicked(){
        paymentInfoAnchor.setVisible(true);
        paymentInfoAnchor.toFront();
        deliveryInfoButton.setId("onButtonVisited");
        paymentInfoButton.setId("onNextButtonClicked");
        paymentInfoButton.setDisable(false);
    }

    public void onPaymentInfoNextButtonClicked(){
        confirmationAnchor.setVisible(true);
        confirmationAnchor.toFront();
        confirmPurchaseButton.setId("onNextButtonClicked");
        paymentInfoButton.setId("onButtonVisited");
        confirmPurchaseButton.setDisable(false);
    }

    public void onConfirmationNextButtonClicked(){
        mc.checkoutAnchor.setVisible(false);
        mc.goToPurchaseDone();
    }

    public void onCartConfirmationBackButtonClicked(){
        mc.goToShopHome();
    }

    public void onDeliveryInfoBackButtonClicked(){
        cartConfirmationAnchor.setVisible(true);
        cartConfirmationAnchor.toFront();
        confirmCartButton.setId("onNextButtonClicked");
        deliveryInfoButton.setId("onButtonVisited");
    }

    public void onPaymentInfoBackButtonClicked() {
        deliveryInfoAnchor.setVisible(true);
        deliveryInfoAnchor.toFront();
        paymentInfoButton.setId("onButtonVisited");
        deliveryInfoButton.setId("onNextButtonClicked");

    }

    public void onConfirmationBackButtonClicked() {
        paymentInfoAnchor.setVisible(true);
        paymentInfoAnchor.toFront();
        confirmPurchaseButton.setId("onButtonVisited");
        paymentInfoButton.setId("onNextButtonClicked");
    }

    public BackendWrapper getWrapper(){
        return this.wrapper;
    }

    public MainController getMc(){
        return this.mc;
    }

    public void doSetCartItems(){
        cartConfirmationController.setCartProducts();
    }

    public void setTotalLabel(double price){
        cartConfirmationController.setTotalLabel();
    }

}
