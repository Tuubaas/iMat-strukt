package iMat.shop.centerview.purchasehistory;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class PurchaseHistoryController implements Initializable {

    @FXML
    private AnchorPane mainAnchor;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setHeight(int height){
        mainAnchor.setPrefHeight(height);
    }

    public void setWidth(int width){
        mainAnchor.setPrefWidth(width);
    }
}
