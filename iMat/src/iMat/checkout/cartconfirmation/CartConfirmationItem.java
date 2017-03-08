package iMat.checkout.cartconfirmation;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.AnchorPane;
import se.chalmers.ait.dat215.project.Product;
import se.chalmers.ait.dat215.project.ShoppingItem;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CartConfirmationItem extends AnchorPane{

    private ShoppingItem item;
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


    public CartConfirmationItem(ShoppingItem item, CartConfirmationController ccc){
        this.item = item;
        this.ccc = ccc;

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                ".fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        itemName.setText(item.getProduct().getName());
        itemSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1,10000));
        itemSpinner.getValueFactory().setValue((int)item.getAmount());
        itemPrice.setText("" + item.getProduct().getPrice());
        totalPrice.setText("" + item.getAmount());

    }


}
