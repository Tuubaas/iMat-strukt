package iMat.settings;

import iMat.BackendWrapper;
import iMat.MainController;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.control.*;
import javafx.scene.effect.ColorInput;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import se.chalmers.ait.dat215.project.CreditCard;
import se.chalmers.ait.dat215.project.Customer;
import se.chalmers.ait.dat215.project.User;

import java.net.URL;
import java.time.Year;
import java.util.ResourceBundle;

public class SettingsPanelController implements Initializable {

    private MainController mainController;
    private BackendWrapper wrapper = MainController.getBackendWrapper();

    @FXML
    private AnchorPane mainAnchor;

    @FXML
    private AnchorPane aboutPane;

    @FXML
    private AnchorPane paymentPane;

    @FXML
    private Button paymentActionButton;

    @FXML
    private TextField cardNumberField1;

    @FXML
    private TextField cardNumberField2;

    @FXML
    private TextField cardNumberField3;

    @FXML
    private TextField cardNumberField11;

    @FXML
    private TextField holdersNameField;

    @FXML
    private TextField securityCode;

    @FXML
    private ComboBox<String> cardType;

    @FXML
    private ComboBox<Integer> validMonth;

    @FXML
    private ComboBox<Integer> validYear;

    @FXML
    private Label cardNumberWarningLabel;

    @FXML
    private Label cardTypeWarningLabel;

    @FXML
    private Label holdersNameWarningLabel;

    @FXML
    private Label validityLabel;

    @FXML
    private Label verificationCodeWarningLabel;

    @FXML
    private AnchorPane personalPane;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField firstnameField;

    @FXML
    private Button personalActionButton;

    @FXML
    private TextField lastnameField;

    @FXML
    private TextField addressField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField phoneField;

    @FXML
    private TextField mobilePhoneField;

    @FXML
    private TextField postcodeField;

    @FXML
    private Label userNameWarningLabel;

    @FXML
    private Label passwordWarningLabel;

    @FXML
    private ImageView exitImage;

    @FXML
    private Button personalInfoButton;

    @FXML
    private Button paymentButton;

    @FXML
    private Button aboutButton;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initPaymentInfoFields();
        initPersonalInfoFields();
        personalInfoButton.fire();

        mainAnchor.getChildren().get(0).setOnMouseClicked(Event::consume);

        exitImage.setOnMouseClicked(event -> {
            performPaymentSaveAction();
            mainController.showSettings(false);
        });
        exitImage.setOnMouseEntered(event -> {
            Bounds bound = exitImage.getBoundsInLocal(); //getting co-ordinates
            exitImage.setEffect(new ColorInput(bound.getMinX(), bound.getMinY(),
                    bound.getWidth(), bound.getHeight(), Color.LIGHTBLUE));
        });
        exitImage.setOnMouseExited(event -> exitImage.setEffect(null));

        personalPane.toFront();
    }

    //Tar bort inloggningsrutan när man klickar på det gråa området.
    @FXML
    public void onMainPaneClicked() {
        performPaymentSaveAction();
        mainController.showSettings(false);
    }

    private void initPaymentInfoFields() {
        //Init comboboxes med värden
        cardType.getItems().addAll("Visa", "MasterCard");

        for (int i = 1; i <= 12; i++) {
            validMonth.getItems().add(i);
        }

        for (int i = Year.now().getValue(); i < Year.now().getValue() + 10; i++) {
            validYear.getItems().add(i);
        }

        //Hämta värden från databasen och sätter in på rätt ställen
        update();

        makePaymentEditable(false);
    }

    public void initPersonalInfoFields() {
        update();
        makePersonalInfoEditable(false);
    }

    @FXML
    public void onPersonalInfoActionButtonClicked() {
        if (personalActionButton.getText().equals("Ändra")) {
            System.out.println("[DEBUG] Enable editing for personal info...");
            performPersonalInfoEditAction();
        } else if (personalActionButton.getText().equals("Spara")) {
            System.out.println("[DEBUG] Saving personal info");
            performPersonalInfoSaveAction();
        }
    }

    private void performPersonalInfoSaveAction() {
        User user = wrapper.getUser();
        Customer customer = wrapper.getCustomer();

        boolean noErrors = true;

        if (usernameField.getText().isEmpty()) {
            userNameWarningLabel.setVisible(true);
            noErrors = false;
        } else {
            userNameWarningLabel.setVisible(false);
        }

        if (passwordField.getText().isEmpty()) {
            passwordWarningLabel.setVisible(true);
            noErrors = false;
        } else {
            passwordWarningLabel.setVisible(false);
        }

        //Gör inget mer om angiven information inte klarade testen ovanför
        if(!noErrors)
            return;

        user.setUserName(usernameField.getText());
        user.setPassword(passwordField.getText());
        customer.setFirstName(firstnameField.getText());
        customer.setLastName(lastnameField.getText());
        customer.setAddress(addressField.getText());
        customer.setPostCode(postcodeField.getText());
        customer.setEmail(emailField.getText());
        customer.setPhoneNumber(phoneField.getText());
        customer.setMobilePhoneNumber(mobilePhoneField.getText());

        makePersonalInfoEditable(false);
        personalActionButton.setText("Ändra");
        mainController.update();
    }

    private void performPersonalInfoEditAction() {
        makePersonalInfoEditable(true);
        personalActionButton.setText("Spara");
    }

    private void makePersonalInfoEditable(boolean flag) {
        flag = !flag;
        usernameField.setDisable(flag);
        passwordField.setDisable(flag);
        firstnameField.setDisable(flag);
        lastnameField.setDisable(flag);
        addressField.setDisable(flag);
        postcodeField.setDisable(flag);
        emailField.setDisable(flag);
        phoneField.setDisable(flag);
        mobilePhoneField.setDisable(flag);
    }

    public void injectMainController(MainController mc) {
        this.mainController = mc;
    }

    public void setHeight(int height) {
        mainAnchor.setPrefHeight(height);
        mainAnchor.getChildren().get(0).setLayoutY((height / 2) - 250);
    }

    public void setWidth(int width) {
        mainAnchor.setPrefWidth(width);
        mainAnchor.getChildren().get(0).setLayoutX((width / 2) - 250);
    }

    @FXML
    public void onPersonalInfoButtonClicked() {
        update();
        personalPane.toFront();
        resetAllButtons();
        personalInfoButton.setId("onButtonClicked");
    }

    @FXML
    public void onPaymentInfoButtonClicked() {
        update();
        paymentPane.toFront();
        resetAllButtons();
        paymentButton.setId("onButtonClicked");
    }

    @FXML
    public void onAboutButtonClicked() {
        update();
        aboutPane.toFront();
        resetAllButtons();
        aboutButton.setId("onButtonClicked");
    }

    @FXML
    public void onPaymentActionButtonClicked() {
        if (paymentActionButton.getText().equals("Ändra")) {
            performPaymentEditAction();
        } else if (paymentActionButton.getText().equals("Spara")) {
            performPaymentSaveAction();
        }
    }

    private void performPaymentEditAction() {
        makePaymentEditable(true);
        paymentActionButton.setText("Spara");
    }

    private void makePaymentEditable(boolean flag) {
        flag = !flag;
        cardNumberField1.setDisable(flag);
        cardNumberField2.setDisable(flag);
        cardNumberField3.setDisable(flag);
        cardNumberField11.setDisable(flag);
        holdersNameField.setDisable(flag);
        cardType.setDisable(flag);
        validMonth.setDisable(flag);
        validYear.setDisable(flag);
        securityCode.setDisable(flag);
    }

    private void performPaymentSaveAction() {
        CreditCard card = wrapper.getCreditCard();

        boolean noErrors = true;

        //Kolla alla fält och sätt ut varninglabels där dem ska vara
        if (!isLegitCardNumber()) {
            cardNumberWarningLabel.setVisible(true);
            noErrors = false;
        } else {
            cardNumberWarningLabel.setVisible(false);
        }

        if (cardType.getValue().isEmpty()) {
            cardTypeWarningLabel.setVisible(true);
            noErrors = false;
        } else {
            cardTypeWarningLabel.setVisible(false);
        }

        if (holdersNameField.getText().isEmpty()) {
            holdersNameWarningLabel.setVisible(true);
            noErrors = false;
        } else {
            holdersNameWarningLabel.setVisible(false);
        }

        if (validMonth.getSelectionModel().isEmpty() || validYear.getSelectionModel().isEmpty()) {
            validityLabel.setVisible(true);
            noErrors = false;
        } else {
            validityLabel.setVisible(false);
        }

        if (!isLegitVerificationCode()) {
            verificationCodeWarningLabel.setVisible(true);
            noErrors = false;
        } else {
            verificationCodeWarningLabel.setVisible(false);
        }

        if (!noErrors)
            return;


        //Klarade testet ovan. Spara datan i fältet i databasen
        card.setCardNumber(cardNumberField1.getText() + cardNumberField2.getText() + cardNumberField3.getText() + cardNumberField11.getText());
        card.setHoldersName(holdersNameField.getText());
        card.setCardType(cardType.getValue());
        card.setValidMonth(validMonth.getValue());
        card.setValidYear(validYear.getValue());
        card.setVerificationCode(Integer.parseInt(securityCode.getText()));


        makePaymentEditable(false);
        paymentActionButton.setText("Ändra");
        mainController.update();
    }

    private boolean isLegitCardNumber() {
        if (!(cardNumberField1.getText().length() == 4) && !(cardNumberField2.getText().length() == 4) && !(cardNumberField3.getText().length() == 4) && !(cardNumberField11.getText().length() == 4))  {
            return false;
        }
        if (!cardNumberField1.getText().matches("[0-9]+") && cardNumberField2.getText().matches("[0-9]+") && cardNumberField3.getText().matches("[0-9]+") && cardNumberField11.getText().matches("[0-9]+")) {
            return false;
        }
        return true;
    }

    private boolean isLegitVerificationCode() {
        //Returnerar TRUE om koden innehåller endast siffror och är längre än 0
        return securityCode.getText().matches("[0-9]+") && securityCode.getText().length() > 0;
    }

    public void update() {
        CreditCard card = wrapper.getCreditCard();
        User user = wrapper.getUser();
        Customer customer = wrapper.getCustomer();

        //Customerinfo
        usernameField.setText(user.getUserName());
        passwordField.setText(user.getPassword());
        firstnameField.setText(customer.getFirstName());
        lastnameField.setText(customer.getLastName());
        addressField.setText(customer.getAddress());
        postcodeField.setText(customer.getPostCode());
        emailField.setText(customer.getEmail());
        phoneField.setText(customer.getPhoneNumber());
        mobilePhoneField.setText(customer.getMobilePhoneNumber());

        //Paymentinfo
        if (card.getCardNumber().length() == 16) {
            cardNumberField1.setText(card.getCardNumber().substring(0, 4));
            cardNumberField2.setText(card.getCardNumber().substring(4, 8));
            cardNumberField3.setText(card.getCardNumber().substring(8, 12));
            cardNumberField11.setText(card.getCardNumber().substring(12, 16));
        }
        holdersNameField.setText(card.getHoldersName());
        cardType.setValue(card.getCardType());
        validMonth.setValue(card.getValidMonth());
        validYear.setValue(card.getValidYear());
        securityCode.setText(String.valueOf(card.getVerificationCode()));
    }

    private void resetAllButtons(){
        paymentButton.setId("buttonNotClicked");
        personalInfoButton.setId("buttonNotClicked");
        aboutButton.setId("buttonNotClicked");
    }
}