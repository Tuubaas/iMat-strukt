package iMat.checkout.paymentinfo;


import iMat.BackendWrapper;
import iMat.MainController;
import iMat.checkout.CheckoutController;
import iMat.checkout.confirmation.ConfirmationController;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import se.chalmers.ait.dat215.project.CreditCard;
import se.chalmers.ait.dat215.project.Customer;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class PaymentInfoController implements Initializable {

    private CheckoutController cc;

    private MainController mc;
    private boolean cardIsVisible = false;

    BackendWrapper wrapper;

    private ConfirmationController conf;

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
    private TextField paymentInfoCardNumber1;
    @FXML
    private TextField paymentInfoCardNumber2;
    @FXML
    private TextField paymentInfoCardNumber3;
    @FXML
    private TextField paymentInfoCardNumber4;
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
    @FXML
    private Label warningLabel;
    @FXML
    private Button helpButton;
    @FXML
    private Rectangle cardNumber;
    @FXML
    private Rectangle cardMonth;
    @FXML
    private Rectangle cardYear;
    @FXML
    private Rectangle cardName;
    @FXML
    private Rectangle cardCvc;
    @FXML
    private Line lineOne;
    @FXML
    private Line lineTwo;
    @FXML
    private Line lineThree;
    @FXML
    private Line lineFour;
    @FXML
    private Line lineFive;
    @FXML
    private Label cardNumberLabel;
    @FXML
    private Label expiryDateLabel;
    @FXML
    private Label slashLabel;
    @FXML
    private Label cardHolderNameLabel;
    @FXML
    private Label cvcLabel;



    private ToggleGroup group = new ToggleGroup();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        visaRadioButton.setToggleGroup(group);
        mcRadioButton.setToggleGroup(group);
        paymentInfoCardTemplatePic.setImage(new Image(String.valueOf(getClass().getClassLoader().getResource("resources/kreditkort.png"))));
        paymentInfoCardBacksideTemplatePic.setImage(new Image(String.valueOf(getClass().getClassLoader().getResource("resources/kreditkortbaksida.png"))));
        visaLogoImage.setImage(new Image(String.valueOf(getClass().getClassLoader().getResource("resources/Visa_lgo.png"))));
        mcLogoImage.setImage(new Image(String.valueOf(getClass().getClassLoader().getResource("resources/MasterCard_Logo.svg.png"))));
        setChoiceBoxItems();
    }

    public void injectCheckoutController(CheckoutController cc) {
        this.cc = cc;
    }

    public void injectConfirmationController(ConfirmationController conf) {
        this.conf = conf;
    }

    @FXML
    public void onPaymentInfoNextButtonClicked() {
        if (paymentInfoCardHolder.getText().equals("") || paymentInfoCardNumber1.getText().equals("") || paymentInfoCardNumber2.getText().equals("") || paymentInfoCardNumber3.getText().equals("") || paymentInfoCardNumber4.getText().equals("") || paymentInfoCardCVC.getText().equals("") || paymentInfoExpireMonth.getValue() == "" || paymentInfoExpireYear.getValue() == null || group.getSelectedToggle() == null) {
            warningLabel.setStyle("-fx-text-fill: red");
            warningLabel.setText("Du måste fylla i alla fält");
        } else {
            cc.onPaymentInfoNextButtonClicked();
            conf.setConfirmationLabels();
        }
    }

    public void onPaymentInfoBackButtonClicked() {
        cc.onPaymentInfoBackButtonClicked();
    }

    public String getCardHolder() {
        return paymentInfoCardHolder.getText();
    }

    public String getCardNumber() {
        return paymentInfoCardNumber1.getText() + paymentInfoCardNumber2.getText() + paymentInfoCardNumber3.getText() + paymentInfoCardNumber4.getText();
    }

    public String getCardExpireMonth() {
        return paymentInfoExpireMonth.getValue().toString();
    }

    public String getCardExpireYear() {
        return paymentInfoExpireYear.getValue().toString();
    }

    public String getCardCVC() {
        return paymentInfoCardCVC.getText();
    }

    public void setPaymentInfo() {
        CreditCard creditCard = cc.getWrapper().getCreditCard();
        paymentInfoCardHolder.setText(creditCard.getHoldersName());
        paymentInfoCardNumber1.setText(creditCard.getCardNumber().substring(0,4));
        paymentInfoCardNumber2.setText(creditCard.getCardNumber().substring(4,8));
        paymentInfoCardNumber3.setText(creditCard.getCardNumber().substring(8,12));
        paymentInfoCardNumber4.setText(creditCard.getCardNumber().substring(12,16));
        paymentInfoCardCVC.setText("" + creditCard.getVerificationCode());
        paymentInfoExpireMonth.setValue(creditCard.getValidMonth());
        paymentInfoExpireYear.setValue(creditCard.getValidYear());
        if (creditCard.getCardType().equals("Visa")) {
            visaRadioButton.fire();
        } else {
            mcRadioButton.fire();
        }
    }

    private String setCardNumberFormat() {
        CreditCard creditCard = cc.getWrapper().getCreditCard();
        String credit = creditCard.getCardNumber();
        String newCredit = credit.substring(0, 4) + " " + credit.substring(4, 8) + " " + credit.substring(8, 12) + " " + credit.substring(12, 16);
        return newCredit;
    }

    /*public void updateCardNumberInput() {
        if (paymentInfoCardNumber.getText().length() == 15) {
            String credit = paymentInfoCardNumber.getText();
            paymentInfoCardNumber.setText(credit.substring(0, 4) + " " + credit.substring(4, 8) + " " + credit.substring(8, 12) + " " + credit.substring(12, 16));
        }
    }*/

    private void setChoiceBoxItems() {
        paymentInfoExpireMonth.setItems(FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12));
        paymentInfoExpireMonth.getSelectionModel().selectFirst();
        paymentInfoExpireYear.setItems(FXCollections.observableArrayList(17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27));
        paymentInfoExpireYear.getSelectionModel().selectFirst();
    }

    public void onHelpButtonClicked() {
        if (cardIsVisible) {
            cardNumber.setVisible(false);
            cardMonth.setVisible(false);
            cardYear.setVisible(false);
            cardName.setVisible(false);
            cardCvc.setVisible(false);
            lineOne.setVisible(false);
            lineTwo.setVisible(false);
            lineThree.setVisible(false);
            lineFour.setVisible(false);
            lineFive.setVisible(false);
            paymentInfoCardBacksideTemplatePic.setVisible(false);
            paymentInfoCardTemplatePic.setVisible(false);
            cardIsVisible = false;
        } else {
            cardNumber.setVisible(true);
            cardMonth.setVisible(true);
            cardYear.setVisible(true);
            cardName.setVisible(true);
            cardCvc.setVisible(true);
            lineOne.setVisible(true);
            lineTwo.setVisible(true);
            lineThree.setVisible(true);
            lineFour.setVisible(true);
            lineFive.setVisible(true);
            paymentInfoCardBacksideTemplatePic.setVisible(true);
            paymentInfoCardTemplatePic.setVisible(true);
            cardIsVisible = true;
        }

    }

    public void responsiveEnablingCard() {
        if (visaRadioButton.isSelected() || mcRadioButton.isSelected()) {
            cardNumberLabel.setVisible(true);
            expiryDateLabel.setVisible(true);
            slashLabel.setVisible(true);
            cardHolderNameLabel.setVisible(true);
            cvcLabel.setVisible(true);
            paymentInfoCardNumber1.setVisible(true);
            paymentInfoCardNumber2.setVisible(true);
            paymentInfoCardNumber3.setVisible(true);
            paymentInfoCardNumber4.setVisible(true);
            paymentInfoCardHolder.setVisible(true);
            paymentInfoExpireMonth.setVisible(true);
            paymentInfoExpireYear.setVisible(true);
            paymentInfoCardCVC.setVisible(true);
            helpButton.setVisible(true);
        }
    }
}

