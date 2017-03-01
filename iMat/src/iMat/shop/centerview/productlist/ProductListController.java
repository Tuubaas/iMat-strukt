package iMat.shop.centerview.productlist;

import iMat.shop.centerview.CenterViewController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;
import se.chalmers.ait.dat215.project.Product;
import se.chalmers.ait.dat215.project.ShoppingItem;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;

public class ProductListController implements Initializable {

    private CenterViewController centerViewController;

    @FXML
    private ScrollPane mainAnchor;
    @FXML
    private FlowPane flowPane;
    @FXML
    private Label label;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setHeight(int height) {
        mainAnchor.setPrefHeight(height);
        mainAnchor.setPrefViewportHeight(height);
        flowPane.setPrefHeight(height);
    }

    public void setWidth(int width) {
        mainAnchor.setPrefWidth(width);
        mainAnchor.setPrefViewportWidth(width);
        flowPane.setPrefWidth(width - 15);
    }

    /*
    Tar bort produkter som visas nu och visar upp dem nya som kom i metodanropet.
     */
    public void setProducts(Set<Product> set) {
        flowPane.getChildren().clear();

        //Om inga produkter finns att visa, visa text.
        if(set == null || set.isEmpty()){
            flowPane.getChildren().add(label);
        }

        //Fail-safe. Bör inte behövs när programmet är färdigt.
        if(set == null)
            return;

        for (Product p : set) {
            flowPane.getChildren().add(new ProductListItemController(p, this));
        }

        mainAnchor.setVvalue(0);
    }

    public void injectCenterViewController(CenterViewController cw) {
        this.centerViewController = cw;
    }

    public void addToCart(ShoppingItem item){
        centerViewController.addToCart(item);
    }
}
