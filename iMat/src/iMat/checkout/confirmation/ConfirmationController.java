package iMat.checkout.confirmation;


import iMat.checkout.CheckoutController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class ConfirmationController implements Initializable{

    private CheckoutController cc;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void injectCheckoutController(CheckoutController cc){
        this.cc = cc;
    }

    public void onConfirmationNextButtonClicked() {
        cc.onConfirmationNextButtonClicked();
    }

    public void onConfirmationBackButtonClicked(){
        cc.onConfirmationBackButtonClicked();
    }



}
