package iMat.checkout.deliveryinfo;


import iMat.BackendWrapper;
import iMat.MainController;
import iMat.checkout.CheckoutController;
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
    private ChoiceBox deliveryInfoMonthChoice;
    @FXML
    private ChoiceBox deliveryInfoDayChoice;
    @FXML
    private ChoiceBox deliveryInfoTimeChoice;
    @FXML
    private RadioButton logInRadioButton;
    @FXML
    private RadioButton guestRadioButton;
    @FXML
    private TextField logInUsername;
    @FXML
    private TextField logInPassword;
    @FXML
    private Button logInButton;
    @FXML
    private Label logInWarning;


    private final ToggleGroup group = new ToggleGroup();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        logInRadioButton.setToggleGroup(group);
        guestRadioButton.setToggleGroup(group);
    }

    public void injectCheckoutController(CheckoutController cc){
        this.cc = cc;
    }

    public void injectMainController(MainController mc){
        this.mc = mc;
    }
    public void onDeliveryInfoNextButtonClicked(){
        cc.onDeliveryInfoNextButtonClicked();
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
            System.out.println("Login success");
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
    }

}

