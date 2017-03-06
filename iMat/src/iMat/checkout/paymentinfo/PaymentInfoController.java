package iMat.checkout.paymentinfo;


import iMat.BackendWrapper;
import iMat.MainController;
import iMat.checkout.CheckoutController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import se.chalmers.ait.dat215.project.CreditCard;
import se.chalmers.ait.dat215.project.Customer;

import java.net.URL;
import java.util.ResourceBundle;

public class PaymentInfoController implements Initializable {

    private CheckoutController cc;

    private MainController mc;

    BackendWrapper wrapper;

    @FXML
    private AnchorPane paymentInfoAnchor;
    @FXML
    private ImageView paymentInfoCardBacksideTemplatePic;
    @FXML
    private ImageView paymentInfoCardTemplatePic;
    @FXML
    private ChoiceBox paymentInfoExpireMonth;
    @FXML
    private ChoiceBox paymentInfoExpireYear;
    @FXML
    private TextField paymentInfoCardHolder;
    @FXML
    private TextField paymentInfoCardNumber;
    @FXML
    private TextField paymentInfoCardCVC;
    @FXML
    private RadioButton visaRadioButton;
    @FXML
    private RadioButton mcRadioButton;
    @FXML
    private ImageView visaLogoImage;
    @FXML
    private ImageView mcLogoImage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        paymentInfoCardTemplatePic.setImage(new Image(String.valueOf(getClass().getClassLoader().getResource("resources/kreditkort.png"))));
        paymentInfoCardBacksideTemplatePic.setImage(new Image(String.valueOf(getClass().getClassLoader().getResource("resources/kreditkortbaksida.png"))));
        visaLogoImage.setImage(new Image(String.valueOf(getClass().getClassLoader().getResource("resources/Visa_lgo.png"))));
        mcLogoImage.setImage(new Image(String.valueOf(getClass().getClassLoader().getResource("resources/MasterCard_Logo.svg.png"))));

    }

    public void injectCheckoutController(CheckoutController cc) {
        this.cc = cc;
    }

    @FXML
    public void onPaymentInfoNextButtonClicked() {
        cc.onPaymentInfoNextButtonClicked();
    }

    public void onPaymentInfoBackButtonClicked(){
        cc.onPaymentInfoBackButtonClicked();
    }

    public String getCardHolder(){
        return paymentInfoCardHolder.getText();
    }

    public String getCardNumber(){
        return paymentInfoCardNumber.getText();
    }

    public String getCardExpireMonth(){
        return paymentInfoExpireMonth.getValue().toString();
    }

    public String getCardExpireYear(){
        return paymentInfoExpireYear.getValue().toString();
    }

    public String getCardCVC(){
        return paymentInfoCardCVC.getText();
    }

    public void setPaymentInfo(){
        CreditCard creditCard = cc.getWrapper().getCreditCard();
        paymentInfoCardHolder.setText(creditCard.getHoldersName());
        paymentInfoCardNumber.setText(creditCard.getCardNumber());
        paymentInfoCardCVC.setText("" + creditCard.getVerificationCode());
        paymentInfoExpireMonth.setValue(creditCard.getValidMonth());
        paymentInfoExpireYear.setValue(creditCard.getValidYear());
    }

}
