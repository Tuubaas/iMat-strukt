package iMat.shop.shoppingcart;

import iMat.shop.ShopController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import se.chalmers.ait.dat215.project.ShoppingItem;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by SaraLandfors on 2/22/17.
 */
public class ShoppingCartController implements Initializable {

    private ShopController sc;

    @FXML
    private AnchorPane mainAnchor;
    @FXML
    private Button goToCheckoutButton;
    @FXML
    private FlowPane flowPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void injectShopController(ShopController sc){
        this.sc = sc;
    }

    public void setHeight(int height){
        mainAnchor.setPrefHeight(height);
    }

    public void onGoToCheckoutButtonClicked(){
        sc.getMainController().goToCheckout();
        sc.getMainController().setSearchBarVisible(false);
    }

    public void remove(ShoppingCartItem item){
        flowPane.getChildren().remove(item);
    }

    public void add(ShoppingItem item){
        flowPane.getChildren().add(new ShoppingCartItem(item, this));
    }

    public void remove(ShoppingItem item){
        for(Node node : flowPane.getChildren()){
            if(node instanceof ShoppingCartItem){
                ShoppingCartItem cartItem = (ShoppingCartItem) node;
                if(cartItem.getShoppingItem().getProduct().equals(item.getProduct())){
                    flowPane.getChildren().remove(cartItem);
                }
            }
        }
    }
}
