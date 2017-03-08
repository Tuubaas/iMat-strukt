package iMat.header;

import iMat.MainController;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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
    private ImageView logo;
    @FXML
    private Label loggedInAsLabel;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        logo.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                logo.setImage(new Image(String.valueOf(getClass().getClassLoader().getResource("resources/iMat-logoHOVER.png"))));
            }
        });

        logo.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                logo.setImage(new Image(String.valueOf(getClass().getClassLoader().getResource("resources/iMat-logo.png"))));
            }
        });
        logo.setOnMouseClicked(event -> mc.goToShopHome());

        accountAnchorPane.toBack();
        accountAnchorPane.setVisible(false);
    }

    public void injectMainController(MainController mc) {
        this.mc = mc;
    }

    public void setWidth(int width) {
        mainAnchor.setPrefWidth(width);
        searchPane.layoutXProperty().set(width / 2 - searchPane.getPrefWidth() / 2);
        searchPane.setPrefWidth(width / 3);
    }

    public void update() {
        updateLoginState();
    }

    private void updateLoginState() {
        if (mc.isLoggedIn()) {
            this.setLoggedinPane(true);
            loggedInAsLabel.setText(String.format("Inloggad som: %s %s", MainController.getBackendWrapper().getCustomer().getFirstName(),
                    MainController.getBackendWrapper().getCustomer().getLastName()));
        } else {
            this.setLoggedinPane(false);
        }
    }

    private void setLoggedinPane(boolean isLoggedin) {
        if (isLoggedin) {
            logInAnchorPane.toBack();
            logInAnchorPane.setVisible(false);
            accountAnchorPane.toFront();
            accountAnchorPane.setVisible(true);
        } else {
            accountAnchorPane.toBack();
            accountAnchorPane.setVisible(false);
            logInAnchorPane.toFront();
            logInAnchorPane.setVisible(true);
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

    @FXML
    public void onSettingsButtonClicked() {
        mc.showSettings(true);
    }

    @FXML
    public void onHistoryButtonClicked() {
        mc.goToPurchaseHistory();
    }
}
