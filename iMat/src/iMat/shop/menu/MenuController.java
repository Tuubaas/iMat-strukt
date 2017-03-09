package iMat.shop.menu;

import iMat.BackendWrapper;
import iMat.MainController;
import iMat.shop.ShopController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {

    private ShopController sc;
    private BackendWrapper wrapper = MainController.getBackendWrapper();
    private static final String subCategoryButtonStyleClassStandard =
            "-fx-background-color:white;";

    private static final String subCategoryButtonStyleClassClicked =
            "-fx-font-size: 14;" +
                    "-fx-alignment:baseline-left;" +
                    "-fx-pref-height:25;" +
                    "-fx-pref-width:250;" +
                    "-fx-padding: 0 0 0 30;" +
                    "-fx-background-color:#AAAAAA;";

    @FXML
    private AnchorPane mainAnchor;

    @FXML
    private Button favoriteButton;

    @FXML
    private ScrollPane titledPanesScrollPane;

    @FXML
    private AnchorPane titledPanesAnchor;

    @FXML
    private TitledPane category1;

    @FXML
    private AnchorPane category1Pane;

    @FXML
    private VBox titledPanesVbox;

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
    private AnchorPane category2Pane;

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
        resetAllSubCategoryButtons();
        favoriteButton.setId("favoriteButtonClicked");
        sc.getMainController().showFavorites();
    }

    /*
     * Jätteful kod - Räknar ut den totala höjden för alla TitledPanes.
     * Adderar sedan ihop det och sätter sedan deras gemensamma anchor till den höjden.
     * Görs för att dynamiskt kunna visa scrollbar:en när den behövs.
     */

    private void resizeTitledPanesAnchor() {
        //FOR IF IF IF - funkar
        double totalheight = 0;
        for (Node node : titledPanesVbox.getChildren()) {
            if (node instanceof TitledPane) {
                TitledPane pane = (TitledPane) node;

                totalheight += 45;  //Lägg till 25 pixlar för varje knapp
                if (pane.isExpanded()) {
                    if (pane.getContent() instanceof AnchorPane) {
                        totalheight += ((AnchorPane) pane.getContent()).getPrefHeight() + 20;
                    }
                }
            }
        }
        titledPanesAnchor.setPrefHeight(totalheight);
    }

    /*
    Metoder för när man trycker på vår accordion
    Gör en snabb check för att kolla om panelen stängdes eller öppnades
     */
    @FXML
    public void onFruitAndVegetablesTiledPaneClicked() {
        if (category1.isExpanded()) {
            sc.getMainController().showProducts(wrapper.getFruitsAndVegetablesList());
            resetAllSubCategoryButtons();
        }
        resizeTitledPanesAnchor();
    }

    @FXML
    public void onMilkAndRefrigeratedTiledPaneClicked() {
        if (category2.isExpanded()) {
            sc.getMainController().showProducts(wrapper.getMilkAndRefrigeratedList());
            resetAllSubCategoryButtons();
        }
        resizeTitledPanesAnchor();
    }

    @FXML
    public void onMeatFishBirdTiledPaneClicked() {

        if (category3.isExpanded()) {
            sc.getMainController().showProducts(wrapper.getMeatFishBirdList());
            resetAllSubCategoryButtons();
        }
        resizeTitledPanesAnchor();
    }

    @FXML
    public void onBreadAndPastriesTiledPaneClicked() {
        if (category4.isExpanded()) {
            sc.getMainController().showProducts(wrapper.getBreadAndPastriesList());
            resetAllSubCategoryButtons();
        }
        resizeTitledPanesAnchor();
    }

    @FXML
    public void onDryProductsTiledPaneClicked() {
        if (category5.isExpanded()) {
            sc.getMainController().showProducts(wrapper.getDryProductsList());
            resetAllSubCategoryButtons();
        }
        resizeTitledPanesAnchor();
    }

    @FXML
    public void onCandyAndSnacksTiledPaneClicked() {
        if (category6.isExpanded()) {
            sc.getMainController().showProducts(wrapper.getCandyAndSnacksList());
            resetAllSubCategoryButtons();
        }
        resizeTitledPanesAnchor();
    }

    @FXML
    public void onDrinksTiledPaneClicked() {
        if (category7.isExpanded()) {
            sc.getMainController().showProducts(wrapper.getDrinksList());
            resetAllSubCategoryButtons();
        }
        resizeTitledPanesAnchor();
    }


    /*
    Metoder för knappar som sitter i frukt och grönt
     */

    @FXML
    public void onBerriesButtonClicked() {
        resetAllSubCategoryButtons();
        berriesButton.setId("subCatButtonClicked");
        sc.getMainController().showProducts(wrapper.getBerriesList());
    }

    @FXML
    public void onFruitButtonClicked() {
        resetAllSubCategoryButtons();
        fruitButton.setId("subCatButtonClicked");
        sc.getMainController().showProducts(wrapper.getFruitsList());
    }

    @FXML
    public void onLemonAndLimeButtonClicked() {
        resetAllSubCategoryButtons();
        lemonAndLimeButton.setId("subCatButtonClicked");
        sc.getMainController().showProducts(wrapper.getLemonAndLimeList());
    }

    @FXML
    public void onExoticFruitsButtonClicked() {
        resetAllSubCategoryButtons();
        exoticFruitsButton.setId("subCatButtonClicked");
        sc.getMainController().showProducts(wrapper.getExoticFruitsList());
    }

    @FXML
    public void onCabbageAndRootVegetablesButtonClicked() {
        resetAllSubCategoryButtons();
        cabbageAndRootVegetablesButton.setId("subCatButtonClicked");
        sc.getMainController().showProducts(wrapper.getCabbageAndRootVegetablesList());
    }

    @FXML
    public void onFreshHerbsButtonClicked() {
        resetAllSubCategoryButtons();
        freshHerbsButton.setId("subCatButtonClicked");
        sc.getMainController().showProducts(wrapper.getFreshHerbsList());
    }

    @FXML
    public void onOtherVegetablesButtonClicked() {
        resetAllSubCategoryButtons();
        otherVegetablesButton.setId("subCatButtonClicked");
        sc.getMainController().showProducts(wrapper.getOtherVegetablesList());
    }

     /*
    Metoder för knappar som sitter i Mjölk och kylvaror
     */

    @FXML
    public void onCheeseButtonClicked() {
        resetAllSubCategoryButtons();
        cheeseButton.setId("subCatButtonClicked");
        sc.getMainController().showProducts(wrapper.getCheeseList());
    }

    @FXML
    public void onMilkButtonClicked() {
        resetAllSubCategoryButtons();
        milkButton.setId("subCatButtonClicked");
        sc.getMainController().showProducts(wrapper.getMilkList());
    }

    @FXML
    public void onEggsButtonClicked() {
        resetAllSubCategoryButtons();
        eggsButton.setId("subCatButtonClicked");
        sc.getMainController().showProducts(wrapper.getEggsList());
    }

    @FXML
    public void onJuiceButtonClicked() {
        resetAllSubCategoryButtons();
        juiceButton.setId("subCatButtonClicked");
        sc.getMainController().showProducts(wrapper.getJuiceList());
    }

    @FXML
    public void onYoghurtButtonClicked() {
        resetAllSubCategoryButtons();
        yoghurtButton.setId("subCatButtonClicked");
        sc.getMainController().showProducts(wrapper.getYoghurtList());
    }

    /*
    Metoder för knappar som sitter i Kött, fisk och fågel.
     */

    @FXML
    public void onMeatButtonClicked() {
        resetAllSubCategoryButtons();
        meatButton.setId("subCatButtonClicked");
        sc.getMainController().showProducts(wrapper.getMeatList());
    }

    @FXML
    public void onFishButtonClicked() {
        resetAllSubCategoryButtons();
        fishButton.setId("subCatButtonClicked");
        sc.getMainController().showProducts(wrapper.getFishAndCrayfishList());
    }

    @FXML
    public void onBirdButtonClicked() {
        resetAllSubCategoryButtons();
        birdButton.setId("subCatButtonClicked");
        sc.getMainController().showProducts(wrapper.getBirdsList());
    }

    /*
    Metoder för knappar som sitter i Bröd och bakverk
     */

    @FXML
    public void onBreadButtonClicked() {
        resetAllSubCategoryButtons();
        breadButton.setId("subCatButtonClicked");
        sc.getMainController().showProducts(wrapper.getBreadList());
    }

    @FXML
    public void onPastryButtonClicked() {
        resetAllSubCategoryButtons();
        pastryButton.setId("subCatButtonClicked");
        sc.getMainController().showProducts(wrapper.getPastryList());
    }

    /*
    Metoder för knappar som sitter i Torrvaror
     */

    @FXML
    public void onFlourAndBakingButtonClicked() {
        resetAllSubCategoryButtons();
        flourAndBakingButton.setId("subCatButtonClicked");
        sc.getMainController().showProducts(wrapper.getFlourAndBakingList());
    }

    @FXML
    public void onNutsAndSeedsButtonClicked() {
        resetAllSubCategoryButtons();
        nutsAndSeedsButton.setId("subCatButtonClicked");
        sc.getMainController().showProducts(wrapper.getNutsAndSeedsList());
    }

    @FXML
    public void onPastaAndRiceButtonClicked() {
        resetAllSubCategoryButtons();
        pastaAndRiceButton.setId("subCatButtonClicked");
        sc.getMainController().showProducts(wrapper.getPastaAndRiceList());
    }

    @FXML
    public void onDryHerbsButtonClicked() {
        resetAllSubCategoryButtons();
        dryHerbsButton.setId("subCatButtonClicked");
        sc.getMainController().showProducts(wrapper.getDryHerbsList());
    }

    @FXML
    public void onCoffeeAndTeaButtonClicked() {
        resetAllSubCategoryButtons();
        coffeeAndTeaButton.setId("subCatButtonClicked");
        sc.getMainController().showProducts(wrapper.getCoffeeAndTeaList());
    }

    /*
    Metoder för knappar som sitter i Godis och snacks
     */

    @FXML
    public void onCandyButtonClicked() {
        resetAllSubCategoryButtons();
        candyButton.setId("subCatButtonClicked");
        //Inte lika kul som det låter...
        sc.getMainController().showProducts(wrapper.getCandyList());
    }

    @FXML
    public void onChipsButtonClicked() {
        resetAllSubCategoryButtons();
        chipsButton.setId("subCatButtonClicked");
        sc.getMainController().showProducts(wrapper.getChipsList());
    }

    @FXML
    public void onSnackNutsButtonClicked() {
        resetAllSubCategoryButtons();
        snackNutsButton.setId("subCatButtonClicked");
        sc.getMainController().showProducts(wrapper.getSnackNutsList());
    }

    @FXML
    public void onIceCreamButtonClicked() {
        resetAllSubCategoryButtons();
        iceCreamButton.setId("subCatButtonClicked");
        sc.getMainController().showProducts(wrapper.getIceCreamList());
    }

    /*
    Metoder för knappar som sitter i Dryck
     */

    @FXML
    public void onSodaButtonClicked() {
        resetAllSubCategoryButtons();
        sodaButton.setId("subCatButtonClicked");
        sc.getMainController().showProducts(wrapper.getSodaList());
    }

    public void resetAllSubCategoryButtons() {
        berriesButton.setId("subCatButtonStandard");
        birdButton.setId("subCatButtonStandard");
        breadButton.setId("subCatButtonStandard");
        cabbageAndRootVegetablesButton.setId("subCatButtonStandard");
        candyButton.setId("subCatButtonStandard");
        cheeseButton.setId("subCatButtonStandard");
        chipsButton.setId("subCatButtonStandard");
        coffeeAndTeaButton.setId("subCatButtonStandard");
        dryHerbsButton.setId("subCatButtonStandard");
        eggsButton.setId("subCatButtonStandard");
        exoticFruitsButton.setId("subCatButtonStandard");
        fishButton.setId("subCatButtonStandard");
        flourAndBakingButton.setId("subCatButtonStandard");
        freshHerbsButton.setId("subCatButtonStandard");
        fruitButton.setId("subCatButtonStandard");
        iceCreamButton.setId("subCatButtonStandard");
        juiceButton.setId("subCatButtonStandard");
        meatButton.setId("subCatButtonStandard");
        lemonAndLimeButton.setId("subCatButtonStandard");
        milkButton.setId("subCatButtonStandard");
        nutsAndSeedsButton.setId("subCatButtonStandard");
        otherVegetablesButton.setId("subCatButtonStandard");
        pastaAndRiceButton.setId("subCatButtonStandard");
        pastryButton.setId("subCatButtonStandard");
        snackNutsButton.setId("subCatButtonStandard");
        sodaButton.setId("subCatButtonStandard");
        yoghurtButton.setId("subCatButtonStandard");

        favoriteButton.setId("favoriteButtonStandard");
    }

    public void closeAllCategorys() {
        category1.setExpanded(false);
        category2.setExpanded(false);
        category3.setExpanded(false);
        category4.setExpanded(false);
        category5.setExpanded(false);
        category6.setExpanded(false);
        category7.setExpanded(false);
    }
}
