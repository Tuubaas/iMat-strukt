package iMat.shop.centerview.productlist;

import iMat.BackendWrapper;
import iMat.MainController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import se.chalmers.ait.dat215.project.Product;
import se.chalmers.ait.dat215.project.ShoppingItem;

import java.io.IOException;

public class ProductListItemController extends AnchorPane {

    private final Product product;
    private ProductListController productListController;
    private BackendWrapper wrapper = MainController.getBackendWrapper();

    @FXML
    private AnchorPane mainAnchor;
    @FXML
    private Label titleLabel;
    @FXML
    private ImageView image;
    @FXML
    private Label priceLabel;
    @FXML
    private Spinner<Integer> spinner;
    @FXML
    private Button addBtn;
    @FXML
    private Label unitLabel;
    @FXML
    private ImageView star;

    public ProductListItemController(Product p, ProductListController plc) {
        this.product = p;
        this.productListController = plc;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "ProductListItem.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        titleLabel.setText(product.getName());
        image.setImage(MainController.getBackendWrapper().getFXImage(p));
        priceLabel.setText(product.getPrice() + " " + product.getUnit());
        spinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10000));

        if (MainController.getBackendWrapper().isFavorite(product)) {
            star.setImage(new Image(String.valueOf(getClass().getClassLoader().getResource("resources/Star.png"))));
        }

        if (product.getUnitSuffix().equals("kg")) {
            unitLabel.setText("Antal Kg:");
        } else if (product.getUnitSuffix().equals("förp")) {
            unitLabel.setText("Antal förpackningar:");
        } else if (product.getUnitSuffix().equals("st")) {
            unitLabel.setText("Antal stycken:");
        }

        star.setOnMouseClicked(event -> onStarClicked());
    }

    @FXML
    public void onAddBtnClicked() {
        wrapper.getShoppingCart().addItem(new ShoppingItem(this.product, spinner.valueProperty().getValue()));
        productListController.getCenterViewController().getShopController().getMainController().update();
    }

    /*

     */
    @FXML
    public void onStarClicked() {
        System.out.println("Starclicked");
        if (!MainController.getBackendWrapper().isFavorite(this.product)) {
            MainController.getBackendWrapper().addFavorite(this.product);
            star.setImage(new Image((String.valueOf(getClass().getClassLoader().getResource(
                    "resources/Star.png")))));
        } else {
            MainController.getBackendWrapper().removeFavorite(this.product);
            star.setImage(new Image((String.valueOf(getClass().getClassLoader().getResource(
                    "resources/Greystar.png")))));
        }
    }

}
