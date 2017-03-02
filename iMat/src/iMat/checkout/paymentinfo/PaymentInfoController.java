package iMat.checkout.paymentinfo;


import iMat.checkout.CheckoutController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class PaymentInfoController implements Initializable {

    CheckoutController cc;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void injectCheckoutController(CheckoutController cc) {
        this.cc = cc;
    }

    @FXML
    public void onPaymentInfoNextButtonClicked() {
    }
}
