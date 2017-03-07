package iMat.checkout.confirmation;


import iMat.BackendWrapper;
import iMat.checkout.CheckoutController;
import iMat.checkout.deliveryinfo.DeliveryInfoController;
import iMat.checkout.paymentinfo.PaymentInfoController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import se.chalmers.ait.dat215.project.ShoppingCart;

import java.net.URL;
import java.util.ResourceBundle;

public class ConfirmationController implements Initializable{

    private CheckoutController cc;

    private DeliveryInfoController dic;

    private PaymentInfoController pic;

    @FXML
    private Label adressLabel;
    @FXML
    private Label deliveryTimeLabel;
    @FXML
    private Label cardNumberLabel;
    @FXML
    private Label totalPriceLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void injectCheckoutController(CheckoutController cc){
        this.cc = cc;
    }

    public void injectDeliveryInfoController(DeliveryInfoController dic) {
        this.dic = dic;
    }

    public void injectPaymentInfoController(PaymentInfoController pic) {
        this.pic = pic;
    }

    public void onConfirmationNextButtonClicked() {
        cc.onConfirmationNextButtonClicked();
    }

    public void onConfirmationBackButtonClicked(){
        cc.onConfirmationBackButtonClicked();
    }

public void setConfirmationLabels(){
    ShoppingCart shoppingCart = cc.getWrapper().getShoppingCart();
    adressLabel.setText(dic.getSelectedAdress());
    deliveryTimeLabel.setText(dic.getSelectedDate());
    cardNumberLabel.setText(pic.getCardNumber());
    totalPriceLabel.setText(shoppingCart.getTotal() + "kr");
}

}
