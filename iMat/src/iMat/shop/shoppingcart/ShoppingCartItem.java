package iMat.shop.shoppingcart;

import iMat.BackendWrapper;
import iMat.MainController;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.FillTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import se.chalmers.ait.dat215.project.ShoppingItem;

import java.io.IOException;

public class ShoppingCartItem extends Pane {

    private ShoppingItem item;
    private ShoppingCartController shoppingCartController;
    private BackendWrapper wrapper = MainController.getBackendWrapper();


    @FXML
    private Pane flashPane;
    @FXML
    private Pane mainAnchor;
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

        flashPane.setVisible(false);
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

        if (this.item.getProduct().getUnitSuffix().equals("kg")) {
            this.priceTotalLabel.setText(String.format("%.2f", item.getTotal() / 10) + " kr");
            this.itemUnit.setText("Hg");
        } else {
            this.itemUnit.setText(item.getProduct().getUnitSuffix());
            this.priceTotalLabel.setText(String.format("%.2f", item.getTotal()) + " kr");
        }
    }

    @FXML
    public void amountChanged(double newAmount) {
        item.setAmount(newAmount);
        updateItem(item);
        shoppingCartController.updateTotal();
    }

    @FXML
    public void onRemoveButtonClicked() {
        wrapper.getShoppingCart().removeItem(this.item);
        shoppingCartController.update();
    }

    public ShoppingItem getShoppingItem() {
        return item;
    }

    public void flashGreen() {
        flashPane.setVisible(true);
        flashPane.setOpacity(0);
        flashPane.setStyle("-fx-background-color: lightgreen");

        FadeTransition transition = new FadeTransition(Duration.millis(300), flashPane);
        transition.setFromValue(0);
        transition.setToValue(1);
        transition.setOnFinished(event -> {
            FadeTransition back = new FadeTransition(Duration.millis(300), flashPane);
            back.setFromValue(1);
            back.setToValue(0);
            back.setOnFinished(event1 -> flashPane.setVisible(false));
            back.play();
        });
        transition.play();
    }
}
