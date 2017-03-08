package iMat.shop.centerview.productlist;

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
import java.util.ResourceBundle;
import java.util.Set;

public class ProductListController implements Initializable {

    private CenterViewController centerViewController;

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
            if(!centerViewController.getShopController().getMainController().isLoggedIn()){
                //a
            }
        } else {
            noProductsPane.setVisible(false);
        }

        for (Product p : set) {
            flowPane.getChildren().add(new ProductListItemController(p, this));
        }

        scrollAnchor.setVvalue(0);    //Resetar scrollen så den hamnar högst upp.
    }

    public void injectCenterViewController(CenterViewController cw) {
        this.centerViewController = cw;
    }

    public CenterViewController getCenterViewController() {
        return centerViewController;
    }
}
