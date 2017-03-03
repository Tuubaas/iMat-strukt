package iMat.checkout.paymentinfo;


import iMat.MainController;
import iMat.checkout.CheckoutController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class PaymentInfoController implements Initializable {

    private CheckoutController cc;

    private MainController mc;

    @FXML
    private AnchorPane paymentInfoAnchor;
    @FXML
    private ImageView paymentInfoCardBacksideTemplatePic;
    @FXML
    private ImageView paymentInfoCardTemplatePic;
    @FXML
    private ImageView paymentInfoVisaPic;
    @FXML
    private ImageView paymentInfoMasterCardPic;
    @FXML
    private RadioButton visaRadioButton;
    @FXML
    private RadioButton masterCardRadioButton;
    @FXML
    private ChoiceBox paymentInfoExpireMonth;
    @FXML
    private ChoiceBox paymentInfoExpireYear;
    @FXML
    private TextField paymentInfoCardHolder;
    @FXML
    private TextField paymentInfoCardNumber;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void injectCheckoutController(CheckoutController cc) {
        this.cc = cc;
    }

    @FXML
    public void onPaymentInfoNextButtonClicked() {
        cc.onPaymentInfoNextButtonClicked();
    }

    //public void
}
