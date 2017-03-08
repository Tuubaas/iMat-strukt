package iMat.shop.shoppingcart;

import iMat.BackendWrapper;
import iMat.MainController;
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
    private BackendWrapper wrapper = MainController.getBackendWrapper();

    @FXML
    private Button removeButton;
    @FXML
    private Label productNameLabel;
    @FXML
    private TextField amountField;
    @FXML
    private Label priceTotalLabel;
    @FXML
    private Label itemUnit;

    public ShoppingCartItem(ShoppingItem item, ShoppingCartController scc) {
        this.shoppingCartController = scc;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "ShoppingCartItem.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        updateItem(item);
        amountField.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                amountChanged(Double.parseDouble(newValue));
            } catch (NumberFormatException e) {
                amountChanged(Double.parseDouble(oldValue));
            }
        });
    }

    public void updateItem(ShoppingItem item) {
        this.item = item;

        this.productNameLabel.setText(item.getProduct().getName());
        this.amountField.setText(String.valueOf(item.getAmount()));
        this.priceTotalLabel.setText(String.valueOf(item.getTotal()) + " kr");
        this.itemUnit.setText(item.getProduct().getUnitSuffix());
    }

    @FXML
    public void amountChanged(double newAmount) {
        item.setAmount(newAmount);
        updateItem(item);
    }

    @FXML
    public void onRemoveButtonClicked() {
        wrapper.getShoppingCart().removeItem(this.item);
        shoppingCartController.update();
    }

    public ShoppingItem getShoppingItem() {
        return item;
    }
}
