package iMat.shop.centerview.purchasehistory.purchasehistoryitem;

import iMat.MainController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import se.chalmers.ait.dat215.project.Order;
import se.chalmers.ait.dat215.project.ShoppingItem;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class PurchaseHistoryOrder extends AnchorPane implements Initializable {

    private final Order order;
    private List<PurchaseHistoryOrderItem> orderItems;
    private final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    @FXML
    private AnchorPane mainAnchor;

    @FXML
    private Label orderIDLabel;

    @FXML
    private Label totalItemsLabel;

    @FXML
    private Label totalPriceLabel;

    @FXML
    private Label dateLabel;

    @FXML
    private FlowPane flowPane;

    public PurchaseHistoryOrder(Order order){
        this.order = order;
        orderItems = new ArrayList<>();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "PurchaseHistoryOrder.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    private void setTotalPriceLabel(){
        Double total = 0d;
        for(ShoppingItem item : this.order.getItems()){
            total += item.getTotal();
        }

        totalPriceLabel.setText(total.toString() + " kr");
    }

    public void setHeight(int height){
        mainAnchor.setPrefHeight(height);
    }

    public void setWidth(int width){
        mainAnchor.setPrefWidth(width);

        for(PurchaseHistoryOrderItem item : orderItems){
            item.setWidth(width - 20);
        }
    }

    @FXML
    public void onUnMarkAllButtonClicked(){
        for(PurchaseHistoryOrderItem item : orderItems){
            item.setChecked(false);
        }
    }

    @FXML
    public void onMarkAllButtonClicked(){
        for(PurchaseHistoryOrderItem item : orderItems){
            item.setChecked(true);
        }
    }

    @FXML
    public void onAddMarkedButtonClicked(){
        for(PurchaseHistoryOrderItem item : orderItems){
            if(item.isChecked()){
                System.out.println("adding...");
                MainController.getBackendWrapper().getShoppingCart().addItem(item.getShoppingItem());
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for(ShoppingItem item : order.getItems()){
            orderItems.add(new PurchaseHistoryOrderItem(item));
        }

        flowPane.setPrefHeight(order.getItems().size() * 70 + 50);

        for(PurchaseHistoryOrderItem item : orderItems){
            flowPane.getChildren().add(item);
        }

        orderIDLabel.setText(String.valueOf(order.getOrderNumber()));
        totalItemsLabel.setText(String.valueOf(order.getItems().size()) + " stycken");
        dateLabel.setText(formatter.format(order.getDate()));
        setTotalPriceLabel();
    }
}
