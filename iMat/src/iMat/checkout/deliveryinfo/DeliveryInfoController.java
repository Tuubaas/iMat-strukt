package iMat.checkout.deliveryinfo;


import iMat.BackendWrapper;
import iMat.MainController;
import iMat.checkout.CheckoutController;
import iMat.checkout.paymentinfo.PaymentInfoController;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import se.chalmers.ait.dat215.project.Customer;
import se.chalmers.ait.dat215.project.User;
import sun.plugin.javascript.navig.Anchor;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class DeliveryInfoController implements Initializable{

    private CheckoutController cc;

    private BackendWrapper wrapper;

    private MainController mc;

    private PaymentInfoController pc;

    //Anchors and Stacks
    @FXML
    private StackPane deliveryInfoStackPane;
    @FXML
    private AnchorPane loggedInAnchor;
    @FXML
    private AnchorPane notLoggedInAnchor;
    @FXML
    private StackPane notLoggedInStackPane;
    @FXML
    private AnchorPane logInDeliveryInfo;
    @FXML
    private AnchorPane guestDeliveryInfo;
    @FXML
    private AnchorPane blankDeliveryInfo;

    //Logged in fields
    @FXML
    private TextField deliveryInfoFirstName;
    @FXML
    private TextField deliveryInfoLastName;
    @FXML
    private TextField deliveryInfoPhone;
    @FXML
    private TextField deliveryInfoMail;
    @FXML
    private TextField deliveryInfoAdress;
    @FXML
    private TextField deliveryInfoPostCode;
    @FXML
    private TextField deliveryInfoCity;
    @FXML
    private DatePicker deliveryInfoDatePicker;
    @FXML
    private ChoiceBox deliveryInfoTimeChoice;
    @FXML
    private Label loginWarningLabel;

    //Not logged in, top buttons
    @FXML
    private RadioButton logInRadioButton;
    @FXML
    private RadioButton guestRadioButton;

    //Not logged in, login fields
    @FXML
    private TextField logInUsername;
    @FXML
    private PasswordField logInPassword;
    @FXML
    private Button logInButton;
    @FXML
    private Label logInWarning;

    //not logged in, guest fields
    @FXML
    private TextField guestAdress;
    @FXML
    private TextField guestCity;
    @FXML
    private TextField guestFirstName;
    @FXML
    private TextField guestMail;
    @FXML
    private TextField guestPostCode;
    @FXML
    private TextField guestPhone;
    @FXML
    private TextField guestLastName;
    @FXML
    private TextField guestUserName;
    @FXML
    private TextField guestPassword;
    @FXML
    private DatePicker guestDatePicker;
    @FXML
    private ChoiceBox guestTimeChoice;
    @FXML
    private Button guestNextButton;
    @FXML
    private Button guestBackButton;
    @FXML
    private CheckBox guestRegCheckBox;
    @FXML
    private Label guestWarningLabel;
    @FXML
    private Label usernameLabel;
    @FXML
    private Label passWordLabel;


    private final ToggleGroup group = new ToggleGroup();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        logInRadioButton.setToggleGroup(group);
        guestRadioButton.setToggleGroup(group);
        guestTimeChoice.setItems(FXCollections.observableArrayList("Förmiddag", "Eftermiddag"));
        guestTimeChoice.getSelectionModel().selectFirst();
        guestDatePicker.setValue(LocalDate.now());
        deliveryInfoTimeChoice.setItems(FXCollections.observableArrayList("Förmiddag", "Eftermiddag"));
        deliveryInfoTimeChoice.getSelectionModel().selectFirst();
        deliveryInfoDatePicker.setValue(LocalDate.now());
    }

    public void injectCheckoutController(CheckoutController cc){
        this.cc = cc;
    }

    public void injectMainController(MainController mc){
        this.mc = mc;
    }

    public void injectPaymentInfoController(PaymentInfoController pc){
        this.pc = pc;
    }

    public void onDeliveryInfoNextButtonClicked(){
        if (cc.getMc().isLoggedIn()){
            if (deliveryInfoFirstName.getText().equals("") || deliveryInfoLastName.getText().equals("") || deliveryInfoAdress.getText().equals("") || deliveryInfoMail.getText().equals("") || deliveryInfoPostCode.getText().equals("") || deliveryInfoPhone.getText().equals("") || deliveryInfoCity.getText().equals("") || deliveryInfoDatePicker.getValue() == null || deliveryInfoTimeChoice.getValue() == null){
                loginWarningLabel.setStyle("-fx-text-fill: red");
                loginWarningLabel.setText("Du måste fylla i alla fält!");
            }
            else {
                cc.onDeliveryInfoNextButtonClicked();
                pc.setPaymentInfo();
            }
        }
        else {
            if (guestFirstName.getText().equals("") || guestLastName.getText().equals("") || guestAdress.getText().equals("") || guestCity.getText().equals("") || guestMail.getText().equals("") || guestPhone.getText().equals("") || guestPostCode.getText().equals("") || guestDatePicker.getValue() ==null || guestTimeChoice.getValue() == null){
                guestWarningLabel.setStyle("-fx-text-fill: red");
                guestWarningLabel.setText("Du måste fylla i alla fält!");
            }
            else {
                if (guestRegCheckBox.isSelected()){
                    if (guestUserName.getText().equals("") || guestPassword.getText().equals("")){
                        guestWarningLabel.setStyle("-fx-text-fill: red");
                        guestWarningLabel.setText("Du måste fylla i alla fält!");
                    }
                    else {
                        Customer customer = cc.getWrapper().getCustomer();
                        User user = cc.getWrapper().getUser();
                        customer.setFirstName(guestFirstName.getText());
                        customer.setLastName(guestLastName.getText());
                        customer.setAddress(guestAdress.getText());
                        customer.setEmail(guestMail.getText());
                        customer.setPostAddress(guestCity.getText());
                        customer.setPostCode(guestPostCode.getText());
                        customer.setMobilePhoneNumber(guestPhone.getText());
                        user.setUserName(guestUserName.getText());
                        user.setPassword(guestPassword.getText());
                        cc.onDeliveryInfoNextButtonClicked();
                    }
                }
                else {
                    cc.onDeliveryInfoNextButtonClicked();
                }
            }
        }
    }

    public void onDeliveryInfoBackButtonClicked(){
        cc.onDeliveryInfoBackButtonClicked();
    }

    private void setDeliveryInfo(){
        Customer customer = cc.getWrapper().getCustomer();
        deliveryInfoFirstName.setText(customer.getFirstName());
        deliveryInfoLastName.setText(customer.getLastName());
        deliveryInfoAdress.setText(customer.getAddress());
        deliveryInfoCity.setText(customer.getPostAddress());
        deliveryInfoMail.setText(customer.getEmail());
        deliveryInfoPhone.setText(customer.getMobilePhoneNumber());
        deliveryInfoPostCode.setText(customer.getPostCode());
    }

    public void onLoginRadioButtonClicked(){
        logInDeliveryInfo.setVisible(true);
        logInDeliveryInfo.toFront();
    }

    public void onGuestRadioButtonClicked(){
        guestDeliveryInfo.setVisible(true);
        guestDeliveryInfo.toFront();
    }

    public void onLoginButtonClicked(){
        String name = logInUsername.getText();
        String pass = logInPassword.getText();
        User user = MainController.getBackendWrapper().getUser();

        if (user.getPassword().equals(pass) && user.getUserName().equals(name)) {
            cc.getMc().login();
            cc.getMc().showLogin(false);
            loggedInAnchor.setVisible(true);
            loggedInAnchor.toFront();
            setDeliveryInfo();
        } else {
            logInWarning.setStyle("-fx-text-fill: red");
            logInWarning.setText("Fel användarnamn/lösenord!");
        }
    }

    public void loggedInAnchorToFront(){
        loggedInAnchor.setVisible(true);
        loggedInAnchor.toFront();
        setDeliveryInfo();
    }

    public void notLoggedInAnchorToFront(){
        notLoggedInAnchor.setVisible(true);
        notLoggedInAnchor.toFront();
        guestRadioButton.fire();
        guestUserName.setVisible(false);
        guestPassword.setVisible(false);
        usernameLabel.setVisible(false);
        passWordLabel.setVisible(false);
    }

    public void onGuestRegCheckBoxChecked(){
        if (guestRegCheckBox.isSelected()) {
            guestUserName.setVisible(true);
            guestPassword.setVisible(true);
            usernameLabel.setVisible(true);
            passWordLabel.setVisible(true);
        }
        else {
            guestUserName.setVisible(false);
            guestPassword.setVisible(false);
            usernameLabel.setVisible(false);
            passWordLabel.setVisible(false);
        }
    }

    public String getSelectedDate() {
        if (cc.getMc().isLoggedIn()) {
            return deliveryInfoDatePicker.getValue().toString();
        }
        return guestDatePicker.getValue().toString();
    }

    public String getSelectedAdress(){
        if (cc.getMc().isLoggedIn()){
            return deliveryInfoAdress.getText() + " "+ deliveryInfoPostCode.getText() + ", " + deliveryInfoCity.getText();
        }
        return guestAdress.getText() + " " + guestPostCode + ", " + guestCity.getText();
    }

}

