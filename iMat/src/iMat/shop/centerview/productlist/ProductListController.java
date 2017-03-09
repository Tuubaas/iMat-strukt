package iMat.shop.centerview.productlist;

import iMat.MainController;
import iMat.shop.centerview.CenterViewController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import se.chalmers.ait.dat215.project.Product;

import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

public class ProductListController implements Initializable {

    private CenterViewController centerViewController;
    private Set<Product> currentProducts;
    private boolean isShowingFavorites = false;

    @FXML
    private AnchorPane mainAnchor;
    @FXML
    private ScrollPane scrollAnchor;
    @FXML
    private FlowPane flowPane;
    @FXML
    private Label label;
    @FXML
    private VBox noProductsPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        noProductsPane.setVisible(false);
    }

    public void setHeight(int height) {
        mainAnchor.setPrefHeight(height);
        scrollAnchor.setPrefViewportHeight(height);
        flowPane.setPrefHeight(height);
    }

    public void setWidth(int width) {
        mainAnchor.setPrefWidth(width);
        scrollAnchor.setPrefViewportWidth(width);
        flowPane.setPrefWidth(width - 15);
    }

    /*
    Tar bort produkter som visas nu och visar upp dem nya som kom i metodanropet.
     */
    public void setProducts(Set<Product> set) {
        this.currentProducts = set;
        flowPane.getChildren().clear();

        if (set == null) {
            System.err.println("[JÄTTEFEL] Försökte visa upp ett Set<Product> men den aktuella parametern var en " +
                    "nullpointer. Någonstans skickas en nullpointer in, fel av oss alltså.");
            flowPane.getChildren().add(label);
            return;
        }

        //Om inga produkter finns att visa, visa noProductsPane
        if (set.isEmpty()) {
            noProductsPane.setVisible(true);
            label.setText("Whoops, det verkar inte finnas några varor att visa!\nGör en ny sökning eller välj en kategori");
        } else {
            noProductsPane.setVisible(false);
        }

        for (Product p : set) {
            flowPane.getChildren().add(new ProductListItemController(p, this));
        }

        scrollAnchor.setVvalue(0);    //Resetar scrollen så den hamnar högst upp.
        isShowingFavorites = false;
    }

    public void injectCenterViewController(CenterViewController cw) {
        this.centerViewController = cw;
    }

    public CenterViewController getCenterViewController() {
        return centerViewController;
    }

    public void showFavorites() {
        flowPane.getChildren().clear();

        if (centerViewController.getShopController().getMainController().isLoggedIn()) {
            Set<Product> favorites = MainController.getBackendWrapper().getFavourites();
            currentProducts = favorites;

            //Om inga produkter finns att visa, visa noProductsPane
            if (favorites.isEmpty()) {
                noProductsPane.setVisible(true);
                label.setText("Just nu har du inga favoriter.\nDu kan favorisera en produkt genom att trycka på dess stjärna.\nSök eller välj en kategori för att hitta produkter.");
            } else {
                noProductsPane.setVisible(false);
            }

            for (Product p : favorites) {
                flowPane.getChildren().add(new ProductListItemController(p, this));
            }
        } else {
            noProductsPane.setVisible(true);
            label.setText("Du måste vara inloggad för att kunna använda dig av favoriter!\nLogga in eller registrera dig med knappen uppe till höger.");
        }

        scrollAnchor.setVvalue(0);    //Resetar scrollen så den hamnar högst upp.
        isShowingFavorites = true;
    }

    public void updateFavoritePane() {
        showFavorites();
    }

    public void update() {
        if (currentProducts == null) {
            setProducts(new HashSet<>());
            return;
        }

        setProducts(currentProducts);
    }

    public boolean isShowingFavorites(){
        return isShowingFavorites;
    }
}
