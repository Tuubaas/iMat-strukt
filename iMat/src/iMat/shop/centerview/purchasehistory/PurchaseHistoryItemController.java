package iMat.shop.centerview.purchasehistory;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import se.chalmers.ait.dat215.project.Order;

import java.io.IOException;

public class PurchaseHistoryItemController extends AnchorPane {

    private final Order order;

    public PurchaseHistoryItemController(Order order){
        this.order = order;

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "PurchaseHistoryItem.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
