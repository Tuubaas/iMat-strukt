package iMat.checkout.deliveryinfo;


import iMat.BackendWrapper;
import iMat.MainController;
import iMat.checkout.CheckoutController;
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
import java.util.ResourceBundle;

public class DeliveryInfoController implements Initializable{

    private CheckoutController cc;

    private BackendWrapper wrapper;

    private MainController mc;

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
    private Label LoginWarningLabel;

    //Not logged in, top buttons
    @FXML
    private RadioButton logInRadioButton;
    @FXML
    private RadioButton guestRadioButton;

    //Not logged in, login fields
    @FXML
    private TextField logInUsername;
    @FXML
    private TextField logInPassword;
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


    private final ToggleGroup group = new ToggleGroup();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        logInRadioButton.setToggleGroup(group);
        guestRadioButton.setToggleGroup(group);
        guestTimeChoice.setItems(FXCollections.observableArrayList("Förmiddag, 8-12", "Eftermiddag, 13-17"));
        guestTimeChoice.getSelectionModel().selectFirst();
        deliveryInfoTimeChoice.setItems(FXCollections.observableArrayList("Förmiddag, 8-12", "Eftermiddag, 13-17"));
        deliveryInfoTimeChoice.getSelectionModel().selectFirst();
    }

    public void injectCheckoutController(CheckoutController cc){
        this.cc = cc;
    }

    public void injectMainController(MainController mc){
        this.mc = mc;
    }

    public void onDeliveryInfoNextButtonClicked(){
        if (cc.getMc().isLoggedIn()){
            if (deliveryInfoFirstName.getText().equals("") || deliveryInfoLastName.getText().equals("") || deliveryInfoAdress.getText().equals("")){

            }

        }
        cc.onDeliveryInfoNextButtonClicked();
        if (guestRegCheckBox.isSelected()){
            Customer customer = cc.getWrapper().getCustomer();
            User user = cc.getWrapper().getUser();
            customer.setFirstName(guestFirstName.getText());
            customer.setLastName(guestLastName.getText());
            customer.setAddress(guestAdress.getText());
            customer.setEmail(guestMail.getText());
            customer.setPostAddress(guestCity.getText());
            customer.setPostCode(guestPostCode.getText());
            customer.setMobilePhoneNumber(guestPhone.getText());
        /*user.setUserName(guestUserName.getText());
        user.setPassword(guestPassword.getText());*/
        }
        else {

        }
    }

    public void onDeliveryInfoBackButtonClicked(){
        cc.onDeliveryInfoBackButtonClicked();
    }

    public void setDeliveryInfo(){
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
        blankDeliveryInfo.setVisible(true);
        blankDeliveryInfo.toFront();
        guestUserName.setVisible(false);
        guestPassword.setVisible(false);
    }

    public void onGuestRegCheckBoxChecked(){
        if (guestRegCheckBox.isSelected()) {
            guestFirstName.setLayoutX(guestFirstName.getLayoutX() - 250);
            guestLastName.setLayoutX(guestLastName.getLayoutX() - 250);
            guestUserName.setVisible(true);
            guestPassword.setVisible(true);
        }
        else {
            guestUserName.setVisible(false);
            guestPassword.setVisible(false);
            guestFirstName.setLayoutX(guestFirstName.getLayoutX() + 250);
            guestLastName.setLayoutX(guestLastName.getLayoutX() + 250);

        }
    }

    public String getSelectedDate(){
        return guestDatePicker.getValue().toString();
    }

}

