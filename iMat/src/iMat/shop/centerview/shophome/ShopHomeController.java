package iMat.shop.centerview.shophome;

import iMat.shop.centerview.CenterViewController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class ShopHomeController implements Initializable {

    private CenterViewController centerViewController;

    @FXML
    private AnchorPane mainAnchor;
    @FXML
    private ImageView image;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void setHeight(int height) {
        mainAnchor.setPrefHeight(height);
        image.setFitHeight(height - 100);
    }

    public void setWidth(int width) {
        mainAnchor.setPrefWidth(width);
        image.setFitWidth(width - 100);
    }

    public void injectCenterViewController(CenterViewController cw){
        this.centerViewController = cw;
    }
}