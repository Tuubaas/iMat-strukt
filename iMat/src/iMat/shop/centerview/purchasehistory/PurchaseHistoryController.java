package iMat.shop.centerview.purchasehistory;

import iMat.MainController;
import iMat.shop.centerview.purchasehistory.purchasehistoryitem.PurchaseHistoryOrder;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import se.chalmers.ait.dat215.project.Order;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

public class PurchaseHistoryController implements Initializable {

    private PurchaseHistoryOrder currentItem;

    @FXML
    private AnchorPane mainAnchor;

    @FXML
    private AnchorPane listViewPane;

    @FXML
    private TableView<OrderWrapper> tableView;

    @FXML
    private TableColumn<OrderWrapper, String> idColumn;

    @FXML
    private TableColumn<OrderWrapper, String> dateColumn;

    @FXML
    private AnchorPane orderItemPane;

    @FXML
    private Label tipLabel;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        MainController.getBackendWrapper().placeOrder(true);
        this.update();
        showNoOrder();

        tableView.setOnMouseClicked(event -> {
            if (tableView.getSelectionModel().getSelectedItem() == null) {
                return;
            }
            setOrderToShow(tableView.getSelectionModel().getSelectedItem().getOrder());
        });

        orderItemPane.widthProperty().addListener((observable, oldValue, newValue) -> {
            if (currentItem != null)
                currentItem.setWidth(newValue.intValue());
        });

        orderItemPane.heightProperty().addListener((observable, oldValue, newValue) -> {
            if (currentItem != null)
                currentItem.setHeight(newValue.intValue());
        });
    }

    public void setHeight(int height) {
        mainAnchor.setPrefHeight(height);
    }

    public void setWidth(int width) {
        mainAnchor.setPrefWidth(width);
    }

    private void update() {
        tableView.getItems().clear();

        for (Order order : MainController.getBackendWrapper().getOrders()) {
            tableView.getItems().add(new OrderWrapper(order));
        }
    }

    private void setOrderToShow(Order order) {
        currentItem = new PurchaseHistoryOrder(order);
        currentItem.setHeight((int)orderItemPane.getHeight());
        currentItem.setWidth((int)orderItemPane.getWidth());
        orderItemPane.getChildren().clear();
        orderItemPane.getChildren().add(currentItem);
        tipLabel.setVisible(false);
    }

    private void showNoOrder() {
        orderItemPane.getChildren().clear();
        currentItem = null;
        tipLabel.setVisible(true);
    }

    /**
     * Klass för att göra listan självgående.
     */
    public class OrderWrapper {
        private final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        private final Order order;
        private final SimpleStringProperty date;
        private final SimpleStringProperty id;

        OrderWrapper(Order order) {
            this.order = order;
            this.id = new SimpleStringProperty(String.valueOf(order.getOrderNumber()));
            this.date = new SimpleStringProperty(formatter.format(order.getDate()));
        }

        public Order getOrder() {
            return order;
        }

        public String getDate() {
            return date.get();
        }

        public SimpleStringProperty dateProperty() {
            return date;
        }

        public String getId() {
            return id.get();
        }

        public SimpleStringProperty idProperty() {
            return id;
        }
    }
}
