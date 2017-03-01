package iMat.checkout.cartconfirmation;

import iMat.checkout.CheckoutController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class CartConfirmationController implements Initializable{

    CheckoutController cc;

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


}
