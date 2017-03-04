package iMat.shop;

import iMat.MainController;
import iMat.shop.centerview.CenterViewController;
import iMat.shop.menu.MenuController;
import iMat.shop.shoppingcart.ShoppingCartController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import se.chalmers.ait.dat215.project.Product;
import se.chalmers.ait.dat215.project.ShoppingItem;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;

public class ShopController implements Initializable {

    private MainController mc;

    @FXML
    private AnchorPane mainAnchor;
    @FXML
    private AnchorPane cartAnchorPane;
    @FXML
    private AnchorPane menuAnchorPane;
    @FXML
    private AnchorPane centerViewAnchorPane;


    //Controllers
    @FXML
    private MenuController menuController;
    @FXML
    private ShoppingCartController shoppingCartController;
    @FXML
    private CenterViewController centerViewController;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        menuController.injectShopController(this);
        shoppingCartController.injectShopController(this);
        centerViewController.injectShopContoller(this);

        mainAnchor.heightProperty().addListener((observable, oldValue, newValue) -> {
            int value = newValue.intValue();
            shoppingCartController.setHeight((int) (value - 120));
            menuController.setHeight((int) (value - 120));
            centerViewController.setHeight((int) (value - 120));
        });

        mainAnchor.widthProperty().addListener((observable, oldValue, newValue) -> {
            int value = newValue.intValue();
            centerViewController.setWidth(value - 240*2);
        });
    }

    public void injectMainController(MainController mc) {
        this.mc = mc;
    }

    public void setWidth(int width) {
        mainAnchor.setPrefWidth(width);
    }

    public void setHeight(int height) {
        mainAnchor.setPrefHeight(height);
    }

    public MainController getMainController() {
        return mc;
    }

    public void goToShopHome() {
        centerViewController.goToShopHome();
    }

    public void showProducts(Set<Product> set){
        centerViewController.showProducts(set);
    }

    public void goToProductList(){
        centerViewController.goToProductList();
    }

    public void goToPurchaseHistory(){
        centerViewController.goToPurchaseHistory();
    }

    /**
     * Uppdaterar sig själv och alla subcontrollers.
     */
    public void update(){
        shoppingCartController.update();
    }
}