package iMat.shop.shoppingcart;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import se.chalmers.ait.dat215.project.ShoppingItem;

import java.io.IOException;

public class ShoppingCartItem extends Pane {

    private ShoppingItem item;
    private ShoppingCartController shoppingCartController;

    @FXML
    private Button removeButton;
    @FXML
    private Label productNameLabel;
    @FXML
    private TextField amountField;
    @FXML
    private Label priceTotalLabel;

    public ShoppingCartItem(ShoppingItem item, ShoppingCartController scc) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "ShoppingCartItem.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.shoppingCartController = scc;
        updateItem(item);

        amountField.textProperty().addListener((observable, oldValue, newValue) -> amountChanged(Double.parseDouble(newValue)));
    }

    public void updateItem(ShoppingItem item) {
        this.item = item;

        this.productNameLabel.setText(item.getProduct().getName());
        this.amountField.setText(String.valueOf(item.getAmount()));
        this.priceTotalLabel.setText(String.valueOf(item.getTotal()));
    }

    @FXML
    public void amountChanged(double newAmount) {
        item.setAmount(newAmount);
        updateItem(item);
    }

    @FXML
    public void onRemoveButtonClicked() {
        shoppingCartController.remove(this);
    }

    public ShoppingItem getShoppingItem() {
        return item;
    }
}
