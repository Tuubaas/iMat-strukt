package iMat.checkout.deliveryinfo;


import iMat.checkout.CheckoutController;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class DeliveryInfoController implements Initializable{

    CheckoutController cc;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void injectCheckoutController(CheckoutController cc){
        this.cc = cc;
    }
}
