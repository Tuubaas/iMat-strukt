package iMat.shop.shoppingcart;

import iMat.BackendWrapper;
import iMat.MainController;
import iMat.shop.ShopController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import se.chalmers.ait.dat215.project.CartEvent;
import se.chalmers.ait.dat215.project.ShoppingCartListener;
import se.chalmers.ait.dat215.project.ShoppingItem;

import java.net.URL;
import java.util.ResourceBundle;

public class ShoppingCartController implements Initializable, ShoppingCartListener {

    /*
    Denna klass speglar endast vad som finns i IMatDataHandler.getInstance().getShoppingCart();
    För att se till att den är uppdaterad bör update() i MainController kallas när det är lämpligt.
     */

    private ShopController sc;
    private BackendWrapper wrapper = MainController.getBackendWrapper();

    @FXML
    private AnchorPane mainAnchor;

    @FXML
    private Button goToCheckoutButton;

    @FXML
    private ScrollPane itemScrollPane;

    @FXML
    private VBox cartItemBox;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cartItemBox.setAlignment(Pos.TOP_RIGHT);
        cartItemBox.alignmentProperty().setValue(Pos.TOP_RIGHT);
        MainController.getBackendWrapper().getShoppingCart().addShoppingCartListener(this);
        update();
    }

    public void injectShopController(ShopController sc) {
        this.sc = sc;
    }

    public void setHeight(int height) {
        mainAnchor.setPrefHeight(height);
    }

    public void onGoToCheckoutButtonClicked() {
        sc.getMainController().goToCheckout();
    }

    public void update() {
        cartItemBox.getChildren().clear();

        if (wrapper.getShoppingCart().getItems().isEmpty()) {
            goToCheckoutButton.setDisable(true);
        } else {
            goToCheckoutButton.setDisable(false);
        }

        for (ShoppingItem p : wrapper.getShoppingCart().getItems()) {
            cartItemBox.getChildren().add(new ShoppingCartItem(p, this));
        }

        cartItemBox.setPrefHeight(cartItemBox.getChildren().size() * 50 + 100);
    }

    @Override
    public void shoppingCartChanged(CartEvent cartEvent) {
        update();
    }
}
