package iMat.header;

import iMat.MainController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class HeaderController implements Initializable {

    private MainController mc;

    @FXML
    private AnchorPane mainAnchor;
    @FXML
    private AnchorPane headerAnchor;
    @FXML
    private AnchorPane searchPane;
    @FXML
    private TextField searchField;
    @FXML
    private Button searchButton;
    @FXML
    private AnchorPane logInAnchorPane;
    @FXML
    private Button logInButton;
    @FXML
    private AnchorPane accountAnchorPane;
    @FXML
    private MenuButton accountMenuButton;
    @FXML
    private Label iMatLabel;
    @FXML
    private Label eMailLabel;
    @FXML
    private Label loggedInAsLabel;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        iMatLabel.setOnMouseClicked(event -> mc.goToShopHome());
    }

    public void injectMainController(MainController mc) {
        this.mc = mc;
    }

    public void setWidth(int width) {
        mainAnchor.setPrefWidth(width);
        searchPane.layoutXProperty().set(width / 2 - searchPane.getPrefWidth() / 2);
        searchPane.setPrefWidth(width / 3);
    }

    public void updateState() {
        updateLoginState();
    }

    private void updateLoginState() {
        if (mc.isLoggedIn()) {
            //Gör inloggad-relaterade saker
            this.accountAnchorPane.toFront();
            accountAnchorPane.setVisible(true);
            logInAnchorPane.setVisible(false);
            eMailLabel.setText(MainController.getBackendWrapper().getCustomer().getEmail());
            loggedInAsLabel.setText(String.format("Inloggad som: %s %s", MainController.getBackendWrapper().getCustomer().getFirstName(),
                    MainController.getBackendWrapper().getCustomer().getLastName()));
        } else {
            //Visa inloggningspanelen
            this.logInAnchorPane.toFront();
            this.logInAnchorPane.setVisible(true);
        }
    }

    public void setSearchBarVisible(boolean flag) {
        this.searchPane.setVisible(flag);
        this.searchPane.setDisable(!flag);
    }

    @FXML
    public void onLoginButtonPressed() {
        mc.showLogin(true);
    }

    @FXML
    public void onSearchPerformed() {
        mc.performSearch(searchField.getText());
    }

    @FXML
    public void onLogOutClicked() {
        accountAnchorPane.setVisible(false);
        accountAnchorPane.toBack();
        mc.logout();
    }

}
