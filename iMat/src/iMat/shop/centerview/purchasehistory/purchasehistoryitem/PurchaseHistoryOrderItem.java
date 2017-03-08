package iMat.shop.centerview.purchasehistory.purchasehistoryitem;

import iMat.MainController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import se.chalmers.ait.dat215.project.ShoppingItem;
import sun.plugin.javascript.navig.Anchor;

import java.io.IOException;

public class PurchaseHistoryOrderItem extends AnchorPane {

    private final ShoppingItem item;

    @FXML
    private AnchorPane mainAnchor;

    @FXML
    private CheckBox checkBox;

    @FXML
    private ImageView image;

    @FXML
    private Label productNameLabel;

    @FXML
    private Label amountLabel;

    @FXML
    private Label costLabel;

    public PurchaseHistoryOrderItem(ShoppingItem item) {
        this.item = item;

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "PurchaseHistoryOrderItem.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        image.setImage(MainController.getBackendWrapper().getFXImage(item.getProduct()));
        productNameLabel.setText(item.getProduct().getName());
        amountLabel.setText(item.getAmount() + " " + item.getProduct().getUnitSuffix());
        costLabel.setText(item.getTotal() + " kr");
    }

    public boolean isChecked() {
        return checkBox.isSelected();
    }

    public ShoppingItem getShoppingItem() {
        return item;
    }

    public void setWidth(int width){
        mainAnchor.setPrefWidth(width);
    }

    public void setChecked(boolean checked){
        this.checkBox.setSelected(checked);
    }
}
