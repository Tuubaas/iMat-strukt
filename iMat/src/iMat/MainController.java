package iMat;

import iMat.checkout.CheckoutController;
import iMat.header.HeaderController;
import iMat.login.LogInController;
import iMat.purchasedone.PurchaseDoneController;
import iMat.settings.SettingsPanelController;
import iMat.shop.ShopController;
import iMat.welcomepage.WelcomePageController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import se.chalmers.ait.dat215.project.Product;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;

public class MainController implements Initializable {

    //Variabler
    private boolean isLoggedIn = false;  //Alltid utloggad vid programstart.
    private static final BackendWrapper wrapper = BackendWrapper.getInstance();

    //Anchors
    @FXML
    public AnchorPane mainAnchor;
    @FXML
    public AnchorPane logInAnchor;
    @FXML
    public AnchorPane welcomePageAnchor;
    @FXML
    public AnchorPane headerAnchor;
    @FXML
    public AnchorPane shopAnchor;
    @FXML
    public AnchorPane checkoutAnchor;
    @FXML
    public AnchorPane purchaseDoneAnchor;
    @FXML
    public AnchorPane settingsAnchor;

    //Controllers
    @FXML
    private LogInController logInController;
    @FXML
    private ShopController shopController;
    @FXML
    private CheckoutController checkoutController;
    @FXML
    private WelcomePageController welcomePageController;
    @FXML
    private HeaderController headerController;
    @FXML
    private PurchaseDoneController purchaseDoneController;
    @FXML
    private SettingsPanelController settingsPanelController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        goToWelcomePage();

        shopController.injectMainController(this);
        checkoutController.injectMainController(this);
        welcomePageController.injectMainController(this);
        headerController.injectMainController(this);
        purchaseDoneController.injectMainController(this);
        logInController.injectMainController(this);
        settingsPanelController.injectMainController(this);

        mainAnchor.widthProperty().addListener((observable, oldValue, newValue) -> {
            int value = newValue.intValue();
            shopController.setWidth(value);
            headerController.setWidth(value);
            logInController.setWidth(value);
            welcomePageController.setWidth(value);
            purchaseDoneController.setWidth(value);
            settingsPanelController.setWidth(value);
            checkoutController.setWidth(value);
        });
        mainAnchor.heightProperty().addListener((observable, oldValue, newValue) -> {
            int value = newValue.intValue();
            shopController.setHeight(value);
            logInController.setHeight(value);
            welcomePageController.setHeight(value);
            purchaseDoneController.setHeight(value);
            settingsPanelController.setHeight(value);
            checkoutController.setHeight(value);
        });

        update();
    }

    private void hideAllComponents() {
        welcomePageAnchor.setVisible(false);
        shopAnchor.setVisible(false);
        checkoutAnchor.setVisible(false);
        purchaseDoneAnchor.setVisible(false);
        showHeader(false);
        showLogin(false);
        showSettings(false);
        resetAllMenuButtons();
        closeAllCategorys();
    }

    public void goToShop() {
        hideAllComponents();
        showHeader(true);
        headerController.setSearchBarVisible(true);
        shopAnchor.setVisible(true);
        shopAnchor.toFront();
    }

    public void goToWelcomePage() {
        hideAllComponents();
        welcomePageAnchor.toFront();
        welcomePageAnchor.setVisible(true);
    }

    public void goToPurchaseDone() {
        hideAllComponents();
        purchaseDoneAnchor.toFront();
        purchaseDoneAnchor.setVisible(true);
    }

    public void goToCheckout() {
        hideAllComponents();
        showHeader(true);
        checkoutController.doSetCartItems();
        checkoutController.setTotalLabel(wrapper.getShoppingCart().getTotal());
        checkoutAnchor.setVisible(true);
        checkoutAnchor.toFront();
        headerController.setSearchBarVisible(false);
    }

    public void showHeader(boolean flag) {
        if (flag) {
            headerAnchor.toFront();
            headerController.update();
        } else {
            headerAnchor.toBack();
        }
        headerAnchor.setVisible(flag);
    }

    public void showLogin(boolean flag) {
        if (flag)
            logInAnchor.toFront();
        else
            logInAnchor.toBack();

        logInAnchor.setVisible(flag);
    }

    public void showSettings(boolean flag) {
        if (flag)
            settingsAnchor.toFront();
        else
            settingsAnchor.toBack();

        settingsAnchor.setVisible(flag);
    }

    public boolean isLoggedIn() {
        return this.isLoggedIn;
    }

    public void setSearchBarVisible(boolean flag) {
        headerController.setSearchBarVisible(flag);
    }

    public void goToShopHome() {
        goToShop();
        shopController.goToShopHome();
        closeAllCategorys();
        resetAllMenuButtons();
    }

    public void showProducts(Set<Product> set) {
        shopController.showProducts(set);
        shopController.goToProductList();
    }

    /*
    Returnerar vår wrapper för backenden. Statisk.
     */
    public static BackendWrapper getBackendWrapper() {
        return wrapper;
    }

    /*
    Utför en sökning och visar upp resultaten
     */
    public void performSearch(String searchTerms) {
        shopController.showProducts(wrapper.search(searchTerms));
    }

    /*
     * Metoden som ska anropas när en inloggning har utförts och godkänts
     */
    public void login() {
        this.isLoggedIn = true;
        headerController.update();
        updateFavoritePane();
    }

    public void logout() {
        this.isLoggedIn = false;
        update();
    }

    /*
     * Uppdaterar sig själv och alla subcontrollers
     */
    public void update() {
        headerController.update();
        shopController.update();
        settingsPanelController.update();
    }

    public void goToPurchaseHistory() {
        goToShop();
        shopController.goToPurchaseHistory();
    }

    public void showFavorites() {
        shopController.showFavorites();
        shopController.goToProductList();
    }

    public void updateFavoritePane() {
        shopController.updateFavoritePane();
    }

    public PurchaseDoneController getPurchaseDoneController() {
        return this.purchaseDoneController;
    }

    public void updatePurchaseHistory() {
        shopController.updatePurchaseHistory();
    }

    public void closeAllCategorys() {
        shopController.closeAllCategorys();
    }

    public void resetAllMenuButtons() {
        shopController.resetAllMenuButtons();
    }
}