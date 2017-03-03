package iMat.checkout.deliveryinfo;


import iMat.MainController;
import iMat.checkout.CheckoutController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class DeliveryInfoController implements Initializable{

    private CheckoutController cc;

    private MainController mc;

    @FXML
    private AnchorPane deliveryInfoAnchor;
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


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void injectCheckoutController(CheckoutController cc){
        this.cc = cc;
    }

    public void onDeliveryInfoNextButtonClicked(){
        cc.onDeliveryInfoNextButtonClicked();
    }

    public void onDeliveryInfoBackButtonClicked(){
        cc.onDeliveryInfoBackButtonClicked();
    }

}

