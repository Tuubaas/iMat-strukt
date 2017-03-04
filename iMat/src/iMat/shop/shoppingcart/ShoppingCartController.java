package iMat.shop.shoppingcart;

import iMat.BackendWrapper;
import iMat.MainController;
import iMat.shop.ShopController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import se.chalmers.ait.dat215.project.Product;
import se.chalmers.ait.dat215.project.ShoppingItem;

import java.net.URL;
import java.util.ResourceBundle;

public class ShoppingCartController implements Initializable {

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
    private VBox itemBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        itemBox.alignmentProperty().setValue(Pos.TOP_CENTER);
        update();
    }

    public void injectShopController(ShopController sc){
        this.sc = sc;
    }

    public void setHeight(int height){
        mainAnchor.setPrefHeight(height);
    }

    public void onGoToCheckoutButtonClicked(){
        sc.getMainController().goToCheckout();
    }

    public void update(){
        itemBox.getChildren().clear();

        for(ShoppingItem p : wrapper.getShoppingCart().getItems()){
            itemBox.getChildren().add(new ShoppingCartItem(p, this));
        }
    }
}