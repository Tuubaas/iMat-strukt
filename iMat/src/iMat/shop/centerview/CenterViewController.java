package iMat.shop.centerview;

import iMat.shop.ShopController;
import iMat.shop.centerview.productlist.ProductListController;
import iMat.shop.centerview.purchasehistory.PurchaseHistoryController;
import iMat.shop.centerview.shophome.ShopHomeController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import se.chalmers.ait.dat215.project.Product;
import se.chalmers.ait.dat215.project.ShoppingItem;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;

public class CenterViewController implements Initializable {

    private ShopController shopController;

    @FXML
    private AnchorPane mainAnchor;

    @FXML
    private AnchorPane productListAnchor;

    @FXML
    private AnchorPane shopHomeAnchor;

    @FXML
    private AnchorPane purchaseHistoryAnchor;


    //Controllers
    @FXML
    private ShopHomeController shopHomeController;
    @FXML
    private PurchaseHistoryController purchaseHistoryController;
    @FXML
    private ProductListController productListController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        productListController.injectCenterViewController(this);
        shopHomeController.injectCenterViewController(this);
        purchaseHistoryController.injectCenterViewController(this);
    }

    public void showProducts(Set<Product> set){
        productListAnchor.toFront();
        productListAnchor.setVisible(true);
        productListController.setProducts(set);
    }

    public void injectShopContoller(ShopController sc) {
        this.shopController = sc;
    }

    public void setHeight(int height) {
        mainAnchor.setPrefHeight(height);

        productListAnchor.setPrefHeight(height);
        productListController.setHeight(height);

        shopHomeController.setHeight(height);
        shopHomeAnchor.setPrefHeight(height);

        purchaseHistoryAnchor.setPrefHeight(height);
        purchaseHistoryController.setHeight(height);
    }

    public void setWidth(int width) {
        mainAnchor.setPrefWidth(width);

        productListAnchor.setPrefWidth(width);
        productListController.setWidth(width);

        shopHomeController.setWidth(width);
        shopHomeAnchor.setPrefWidth(width);

        purchaseHistoryAnchor.setPrefWidth(width);
        purchaseHistoryController.setWidth(width);
    }

    public void goToShopHome() {
        hideAllComponents();
        shopHomeAnchor.setVisible(true);
        shopHomeAnchor.toFront();
    }

    public void goToProductList() {
        hideAllComponents();
        productListAnchor.setVisible(true);
        productListAnchor.toFront();
    }

    public void goToPurchaseHistory() {
        hideAllComponents();
        purchaseHistoryAnchor.toFront();
        purchaseHistoryAnchor.setVisible(true);
    }

    private void hideAllComponents() {
        productListAnchor.toBack();
        productListAnchor.setVisible(false);
        shopHomeAnchor.toBack();
        shopHomeAnchor.setVisible(false);
        purchaseHistoryAnchor.toBack();
        purchaseHistoryAnchor.setVisible(false);
    }

    public ShopController getShopController() {
        return shopController;
    }

    public void showFavorites(){
        productListAnchor.toFront();
        productListAnchor.setVisible(true);
        productListController.showFavorites();
    }

    public void updateFavoritePane(){
        productListController.updateFavoritePane();
    }

    public void updatePurchaseHistory(){
        purchaseHistoryController.update();
    }
}
