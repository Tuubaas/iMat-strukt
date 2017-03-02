package iMat.shop.menu;

import iMat.BackendWrapper;
import iMat.MainController;
import iMat.shop.ShopController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;

import javax.swing.border.LineBorder;
import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {

    private ShopController sc;
    private BackendWrapper wrapper  = MainController.getBackendWrapper();;

    @FXML
    private AnchorPane mainAnchor;

    @FXML
    private Accordion accordion;

    @FXML
    private TitledPane category1;

    @FXML
    private Button berriesButton;

    @FXML
    private Button fruitButton;

    @FXML
    private Button lemonAndLimeButton;

    @FXML
    private Button exoticFruitsButton;

    @FXML
    private Button cabbageAndRootVegetablesButton;

    @FXML
    private Button freshHerbsButton;

    @FXML
    private Button otherVegetablesButton;

    @FXML
    private TitledPane category2;

    @FXML
    private Button cheeseButton;

    @FXML
    private Button milkButton;

    @FXML
    private Button eggsButton;

    @FXML
    private Button juiceButton;

    @FXML
    private Button yoghurtButton;

    @FXML
    private TitledPane category3;

    @FXML
    private Button meatButton;

    @FXML
    private Button fishButton;

    @FXML
    private Button birdButton;

    @FXML
    private TitledPane category4;

    @FXML
    private AnchorPane breadAnchor;

    @FXML
    private Button breadButton;

    @FXML
    private Button pastryButton;

    @FXML
    private TitledPane category5;

    @FXML
    private AnchorPane pastriesAnchor;

    @FXML
    private Button flourAndBakingButton;

    @FXML
    private Button nutsAndSeedsButton;

    @FXML
    private Button pastaAndRiceButton;

    @FXML
    private Button dryHerbsButton;

    @FXML
    private Button coffeeAndTeaButton;

    @FXML
    private TitledPane category6;

    @FXML
    private AnchorPane candyAnchor;

    @FXML
    private Button candyButton;

    @FXML
    private Button chipsButton;

    @FXML
    private Button snackNutsButton;

    @FXML
    private Button iceCreamButton;

    @FXML
    private TitledPane category7;

    @FXML
    private AnchorPane drinksAnchor;

    @FXML
    private Button sodaButton;

    @FXML
    private Button favoriteButton;

    @FXML
    private Button historyButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void injectShopController(ShopController sc) {
        this.sc = sc;
    }

    public void setHeight(int height) {
        mainAnchor.setPrefHeight(height);
    }

     /*
    Metoder för knappar som sitter högst upp
     */

    @FXML
    public void onFavoriteButtonClicked() {
        sc.showProducts(wrapper.getFavourites());
    }

    @FXML
    public void onHistoryButtonClicked() {
        sc.goToPurchaseHistory();
    }

    /*
    Metoder för när man trycker på vår accordion
    Gör en snabb check för att kolla om panelen stängdes eller öppnades
     */
    @FXML
    public void onFruitAndVegetablesTiledPaneClicked() {
        if (category1.isExpanded())
            sc.getMainController().showProducts(wrapper.getFruitsAndVegetablesList());
    }

    @FXML
    public void onMilkAndRefrigeratedTiledPaneClicked(){
        if(category2.isExpanded())
            sc.getMainController().showProducts(wrapper.getMilkAndRefrigeratedList());
    }

    @FXML
    public void onMeatFishBirdTiledPaneClicked(){
        if(category3.isExpanded())
            sc.getMainController().showProducts(wrapper.getMeatFishBirdList());
    }

    @FXML
    public void onBreadAndPastriesTiledPaneClicked(){
        if(category4.isExpanded())
            sc.getMainController().showProducts(wrapper.getBreadAndPastriesList());
    }

    @FXML
    public void onDryProductsTiledPaneClicked(){
        if(category5.isExpanded())
            sc.getMainController().showProducts(wrapper.getDryProductsList());
    }

    @FXML
    public void onCandyAndSnacksTiledPaneClicked(){
        if(category6.isExpanded())
            sc.getMainController().showProducts(wrapper.getCandyAndSnacksList());
    }

    @FXML
    public void onDrinksTiledPaneClicked (){
        if(category7.isExpanded())
            sc.getMainController().showProducts(wrapper.getDrinksList());
    }


    /*
    Metoder för knappar som sitter i frukt och grönt
     */

    @FXML
    public void onBerriesButtonClicked() {
        sc.getMainController().showProducts(wrapper.getBerriesList());
    }

    @FXML
    public void onFruitButtonClicked() {
        sc.getMainController().showProducts(wrapper.getFruitsList());
    }

    @FXML
    public void onLemonAndLimeButtonClicked() {
        sc.getMainController().showProducts(wrapper.getLemonAndLimeList());
    }

    @FXML
    public void onExoticFruitsButtonClicked() {
        sc.getMainController().showProducts(wrapper.getExoticFruitsList());
    }

    @FXML
    public void onCabbageAndRootVegetablesButtonClicked() {
        sc.getMainController().showProducts(wrapper.getCabbageAndRootVegetablesList());
    }

    @FXML
    public void onFreshHerbsButtonClicked() {
        sc.getMainController().showProducts(wrapper.getFreshHerbsList());
    }

    @FXML
    public void onOtherVegetablesButtonClicked() {
        sc.getMainController().showProducts(wrapper.getOtherVegetablesList());
    }

     /*
    Metoder för knappar som sitter i Mjölk och kylvaror
     */

    @FXML
    public void onCheeseButtonClicked() {
        sc.getMainController().showProducts(wrapper.getCheeseList());
    }

    @FXML
    public void onMilkButtonClicked() {
        sc.getMainController().showProducts(wrapper.getMilkList());
    }

    @FXML
    public void onEggsButtonClicked() {
        sc.getMainController().showProducts(wrapper.getEggsList());
    }

    @FXML
    public void onJuiceButtonClicked() {
        sc.getMainController().showProducts(wrapper.getJuiceList());
    }

    @FXML
    public void onYoghurtButtonClicked() {
        sc.getMainController().showProducts(wrapper.getYoghurtList());
    }

    /*
    Metoder för knappar som sitter i Kött, fisk och fågel.
     */

    @FXML
    public void onMeatButtonClicked() {
        sc.getMainController().showProducts(wrapper.getMeatList());
    }

    @FXML
    public void onFishButtonClicked() {
        sc.getMainController().showProducts(wrapper.getFishAndCrayfishList());
    }

    @FXML
    public void onBirdButtonClicked() {
        sc.getMainController().showProducts(wrapper.getBirdsList());
    }

    /*
    Metoder för knappar som sitter i Bröd och bakverk
     */

    @FXML
    public void onBreadButtonClicked() {
        sc.getMainController().showProducts(wrapper.getBreadList());
    }

    @FXML
    public void onPastryButtonClicked() {
        sc.getMainController().showProducts(wrapper.getPastryList());
    }

    /*
    Metoder för knappar som sitter i Torrvaror
     */

    @FXML
    public void onFlourAndBakingButtonClicked() {
        sc.getMainController().showProducts(wrapper.getFlourAndBakingList());
    }

    @FXML
    public void onNutsAndSeedsButtonClicked() {
        sc.getMainController().showProducts(wrapper.getNutsAndSeedsList());
    }

    @FXML
    public void onPastaAndRiceButtonClicked() {
        sc.getMainController().showProducts(wrapper.getPastaAndRiceList());
    }

    @FXML
    public void onDryHerbsButtonClicked() {
        sc.getMainController().showProducts(wrapper.getDryHerbsList());
    }

    @FXML
    public void onCoffeeAndTeaButtonClicked() {
        sc.getMainController().showProducts(wrapper.getCoffeeAndTeaList());
    }

    /*
    Metoder för knappar som sitter i Godis och snacks
     */

    @FXML
    public void onCandyButtonClicked() {
        //Inte lika kul som det låter :(
        sc.getMainController().showProducts(wrapper.getCandyList());
    }

    @FXML
    public void onChipsButtonClicked() {
        sc.getMainController().showProducts(wrapper.getChipsList());
    }

    @FXML
    public void onSnackNutsButtonClicked() {
        sc.getMainController().showProducts(wrapper.getSnackNutsList());
    }

    @FXML
    public void onIceCreamButtonClicked() {
        sc.getMainController().showProducts(wrapper.getIceCreamList());
    }

    /*
    Metoder för knappar som sitter i Dryck
     */

    @FXML
    public void onSodaButtonClicked() {
        sc.getMainController().showProducts(wrapper.getSodaList());
    }
}
