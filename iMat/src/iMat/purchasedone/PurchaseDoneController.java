package iMat.purchasedone;

import iMat.BackendWrapper;
import iMat.MainController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.omg.IOP.ExceptionDetailMessage;
import se.chalmers.ait.dat215.project.ShoppingCart;
import se.chalmers.ait.dat215.project.ShoppingItem;

import java.net.URL;
import java.util.ResourceBundle;

public class PurchaseDoneController implements Initializable {

    // Graphical elements in the PurchaseDoneView
    @FXML
    private AnchorPane mainAnchor;
    @FXML
    private Button goToStoreButton;
    @FXML
    private Button printConfirmationButton;
    @FXML
    private AnchorPane confirmationAnchorPane;
    @FXML
    private TextArea receiptField;
    @FXML
    private ImageView purchaseDoneImage;
    @FXML
    private Label totalPrice;
    @FXML
    private TextArea receiptTextArea;

    private MainController mc;

    private String receipt = "iMat - Ditt kvitto!" + "\n";


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        purchaseDoneImage.setImage(new Image(String.valueOf(getClass().getClassLoader().getResource("resources/iMat-logo.png"))));
        receiptField.setEditable(false);
    }

    public void injectMainController(MainController mc) {
        this.mc = mc;
    }

    @FXML
    private void onGoToStoreButtonClicked() {
        mc.goToShop();
    }

    public void setHeight(int height) {
        mainAnchor.setPrefHeight(height);
    }

    public void setWidth(int width) {
        mainAnchor.setPrefWidth(width);
    }

    public void writeReceipt(){
        for (ShoppingItem item : MainController.getBackendWrapper().getShoppingCart().getItems()){
            if (item.getProduct().getUnitSuffix().equals("kg")){
                receipt = receipt + "\n" + item.getProduct().getName() + "          " + item.getAmount() + " " + item.getProduct().getUnitSuffix() + "  á  " + String.format("%.2f",item.getProduct().getPrice()/10) + " " + item.getProduct().getUnit() + "          " + String.format("%.2f",item.getTotal()/10) + "\n";
            }
            else {
                receipt = receipt + "\n" + item.getProduct().getName() + "          " + item.getAmount() + " " + item.getProduct().getUnitSuffix() + "  á  " + String.format("%.2f", item.getProduct().getPrice()) + " " + item.getProduct().getUnit() + "          " + String.format("%.2f", item.getTotal()) + "\n";
            }
        }
        receiptField.setText(receipt);
    }

    public void setTotalPrice(){
        totalPrice.setText(String.format("%.2f", MainController.getBackendWrapper().getTotalPrice()) + " kr");
    }

    public void printReceipt(){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("receipt.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Bekräftelse");
            stage.setScene(new Scene(root1));
            stage.show();
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }



        /*for (ShoppingItem item : MainController.getBackendWrapper().getShoppingCart().getItems()){
            if (item.getProduct().getUnitSuffix().equals("kg")){
                receipt = receipt + "\n" + item.getProduct().getName() + "          " + item.getAmount() + " " + item.getProduct().getUnitSuffix() + "  á  " + String.format("%.2f",item.getProduct().getPrice()/10) + " " + item.getProduct().getUnit() + "          " + String.format("%.2f",item.getTotal()/10) + "\n";
            }
            else {
                receipt = receipt + "\n" + item.getProduct().getName() + "          " + item.getAmount() + " " + item.getProduct().getUnitSuffix() + "  á  " + String.format("%.2f", item.getProduct().getPrice()) + " " + item.getProduct().getUnit() + "          " + String.format("%.2f", item.getTotal()) + "\n";
            }
        }
        receiptTextArea.setText(receipt + "\n" + "\n" + "Totalt: " + String.format("%.2f",MainController.getBackendWrapper().getTotalPrice()));
*/
    }
}
