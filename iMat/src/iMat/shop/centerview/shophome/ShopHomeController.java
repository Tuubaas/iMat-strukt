package iMat.shop.centerview.shophome;

import iMat.shop.centerview.CenterViewController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class ShopHomeController implements Initializable {

    private CenterViewController centerViewController;

    @FXML
    private AnchorPane mainAnchor;


    public void setHeight(int height) {
        mainAnchor.setPrefHeight(height);

    }

    public void setWidth(int width) {
        mainAnchor.setPrefWidth(width);

    }

    public void injectCenterViewController(CenterViewController cw){
        this.centerViewController = cw;
    }

    /**
     * Called to initialize a controller after its root element has been
     * completely processed.
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  <tt>null</tt> if the location is not known.
     * @param resources The resources used to localize the root object, or <tt>null</tt> if
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}