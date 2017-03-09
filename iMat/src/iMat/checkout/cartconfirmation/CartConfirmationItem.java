package iMat.checkout.cartconfirmation;

import iMat.MainController;
import iMat.checkout.CheckoutController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.AnchorPane;
import se.chalmers.ait.dat215.project.Product;
import se.chalmers.ait.dat215.project.ShoppingItem;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

public class CartConfirmationItem extends AnchorPane implements Initializable{

    private final ShoppingItem item;
    private CheckoutController cc;
    private CartConfirmationController ccc;


    @FXML
    private AnchorPane cartConfirmationAnchor;
    @FXML
    private Label itemName;
    @FXML
    private Spinner<Integer> itemSpinner;
    @FXML
    private Label itemPrice;
    @FXML
    private Label totalPrice;
    @FXML
    private Button deleteButton;
    @FXML
    private Label itemUnit;

    public CartConfirmationItem(ShoppingItem item, CartConfirmationController ccc){
        this.item = item;
        this.ccc = ccc;
        System.out.println(item.getAmount());
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CartConfirmationItem.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        double price = item.getProduct().getPrice();
        price = Math.round(price * 100.0 )/100.0;
        String newPrice = String.format("%.2f",price);
        System.out.println(price);
        System.out.println(newPrice);


        itemName.setText(item.getProduct().getName());
        itemSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1,10000));
        itemSpinner.getValueFactory().setValue((int)item.getAmount());
        itemPrice.setText(String.format("%.2f",item.getProduct().getPrice())  + " kr");
                //new DecimalFormat("##.###").format(item.getProduct().getPrice()) + " " + item.getProduct().getUnit());
        totalPrice.setText(String.format("%.2f",item.getTotal()) + " kr");
                //new DecimalFormat("##.###").format(item.getTotal()) + " kr");
        itemUnit.setText(item.getProduct().getUnitSuffix());


        itemSpinner.valueProperty().addListener(new ChangeListener<Integer>() {
            @Override
            public void changed(ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue) {
                item.setAmount(newValue);
                totalPrice.setText(String.format("%.2f",item.getTotal()) + " kr");
                System.out.println(item.getAmount());
                System.out.println(newValue);
                ccc.getCC().getMc().update();
            }
        });

    }

    public void updateCartItems(){
        for (ShoppingItem item : MainController.getBackendWrapper().getShoppingCart().getItems()){
            itemName.setText(item.getProduct().getName());
            itemSpinner.getValueFactory().setValue((int)item.getAmount());
            itemPrice.setText(item.getProduct().getPrice() + " " + item.getProduct().getUnit());
            totalPrice.setText(item.getTotal() + " kr");
            itemUnit.setText(item.getProduct().getUnitSuffix());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        deleteButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                MainController.getBackendWrapper().getShoppingCart().removeItem(item);
                ccc.getCC().getMc().update();
                ccc.setCartProducts();
            }
        });
    }
}
