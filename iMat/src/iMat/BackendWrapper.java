package iMat;

import javafx.scene.image.Image;
import se.chalmers.ait.dat215.project.*;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class BackendWrapper {

    private static final IMatDataHandler datahandler = IMatDataHandler.getInstance();

    /*
    Singleton
     */
    private static BackendWrapper instance;

    public static BackendWrapper getInstance() {
        if (instance == null) {
            instance = new BackendWrapper();
        }
        return instance;
    }

    /*
    Creates all sub- and main categories.
     */
    private BackendWrapper() {
        System.out.println("[BackendWrapper] Initializing backend...");
        initSubCategories();
        initMainCategories();
        System.out.println("[BackendWrapper] Done!");
    }

    public void addItem(ShoppingItem item) {
        double amount = item.getAmount();

        for (ShoppingItem i : datahandler.getShoppingCart().getItems()) {
            if (i.getProduct().equals(item.getProduct())) {
                amount += i.getAmount();
                datahandler.getShoppingCart().removeItem(i.getProduct().getProductId());
                datahandler.getShoppingCart().addProduct(item.getProduct(), amount);
                return;
            }


        }

        datahandler.getShoppingCart().addItem(item);
    }

    /**
     * Ger tillbaka en produkts bild i sin originalstorlek.
     */
    public Image getFXImage(Product p) {
        return datahandler.getFXImage(p);
    }

    /**
     * Ger tillbaka en produkts bild i specifik storlek. preserveRatio gör att bilden håller sina proportioner.
     */
    public Image getFXImage(Product p, double width, double height, boolean preserveRatio) {
        return datahandler.getFXImage(p, width, height, preserveRatio);
    }

    /**
     * Ger tillbaka en produkts bild i specifik storlek.
     */
    public Image getFXImage(Product p, double width, double height) {
        return datahandler.getFXImage(p, width, height);
    }

    /**
     * Söker i databasen med hjälp av en sträng och ger tillbaka sökresultaten i ett Set<Product>
     */
    public Set<Product> search(String searchTerms) {
        List<Product> list = datahandler.findProducts(searchTerms);
        return new LinkedHashSet<>(list);
    }

    /**
     * Ger tillbaka alla favoriter
     */
    public Set<Product> getFavourites() {
        return new LinkedHashSet<>(datahandler.favorites());
    }

    /**
     * Kollar om en specifik produkt är en favorit
     */
    public boolean isFavorite(Product p) {
        return datahandler.isFavorite(p);
    }

    /**
     * Lägger till en specifik produkt som favorit
     */
    public void addFavorite(Product p) {
        datahandler.addFavorite(p);
    }

    /**
     * Tar bort en specifik produkt som favorit
     */
    public void removeFavorite(Product p) {
        datahandler.removeFavorite(p);
    }

    /**
     * Sparar all data. Ska kallas innan nedstängning av programmet.
     */
    public void shutDown() {
        datahandler.shutDown();
    }

    /**
     * Ger tillbaka User-objektet som finns i datahandlern.
     */
    public User getUser() {
        return datahandler.getUser();
    }

    /**
     * Ger tillbaka Customer-objektet som finns i datahandlern.
     */
    public Customer getCustomer() {
        return datahandler.getCustomer();
    }

    /**
     * Ger tillbaka ShoppingCarten som finns i datahandlern.
     */
    public ShoppingCart getShoppingCart() {
        return datahandler.getShoppingCart();
    }

    /**
     * Ger tillbaka Creditcard-objektet som finns i datahandlern.
     */
    public CreditCard getCreditCard() {
        return datahandler.getCreditCard();
    }

    /**
     * Ger tillbaka alla tidigare lagda ordrar
     */
    public List<Order> getOrders() {
        return datahandler.getOrders();
    }

    /**
     * Gör den nuvarande kundvagnen till en order med möjlighet att ta bort det som finns i kundvangen
     */
    public void placeOrder(boolean clearShoppingCart) {
        datahandler.placeOrder(clearShoppingCart);
    }


    /* Lists of subcategories for products (which category contains products with what
      product ID?)
     */
    private Set<Integer> berriesID = new LinkedHashSet<Integer>(Arrays.asList(
            15, //björnbär
            16, //blåbär
            17, //hallon
            18, //krusbär
            19, //röda vinbär
            20, //smultron
            21, //svarta vinbär
            130 //körsbär
    ));

    private Set<Integer> fruitsID = new LinkedHashSet<Integer>(Arrays.asList(
            22, //apelsin
            24, //clementin
            25, //grapefruit
            129, //aprikos
            131, //nektarin
            132, //persika
            133 //plommon
    ));

    private Set<Integer> lemonAndLimeID = new LinkedHashSet<Integer>(Arrays.asList(
            23, //citron
            26 //lime
    ));

    private Set<Integer> exoticFruitsID = new LinkedHashSet<Integer>(Arrays.asList(
            41, //ananas
            42, //fikon
            43, //Granatäpple
            44, //kiwi
            45, //mango
            46, //papaya
            47, //rambutan
            86, //Charentaismelon
            87, //galiamelon
            88, //honungsmelon
            89, //nätmelon
            90, //vattenmelon
            100 //kokosnöt
    ));

    private Set<Integer> cabbageAndRootVegetablesID = new LinkedHashSet<Integer>(Arrays.asList(
            63, //blomkål
            64, //broccoli
            65, //brysselkål
            66, //kinakål
            67, //romanesco
            68, //rödkål
            69, //vitkål
            70, //savoykål
            116, //potatis
            117, //potatis röd
            118, //potatis söt
            119, //potatispuré
            121, //kålrot
            122, //majrova
            123, //morot
            124, //palsternacka
            125, //pepparrot
            127, //rödbeta
            128 //rotselleri
    ));

    private Set<Integer> otherVegetablesID = new LinkedHashSet<Integer>(Arrays.asList(
            55, //Aubergine
            56, //avocado
            57, //gurka
            58, //oliver
            59, //Okra
            60, //paprika
            61, //pumpa
            62, //squash
            126, //rädisa
            1 //gröna ärter
    ));

    private Set<Integer> freshHerbsID = new LinkedHashSet<Integer>(Arrays.asList(
            141, //basilika
            142, //citronmeliss
            143, //dill
            144, //koriander
            145, //oregano
            146, //persilja
            147, //rosmarin
            148 //timjan
    ));

    private Set<Integer> cheeseID = new LinkedHashSet<Integer>(Arrays.asList(
            77, //Brie
            79, //färskost
            81, //mozzarella
            82 //västerbotten
    ));

    private Set<Integer> milkID = new LinkedHashSet<Integer>(Arrays.asList(
            80 //mjölk
    ));

    private Set<Integer> eggsID = new LinkedHashSet<Integer>(Arrays.asList(
            85 //ägg
    ));

    private Set<Integer> juiceID = new LinkedHashSet<Integer>(Arrays.asList(
            32, //apelsinjuice
            37 //fruktsoppa
    ));


    private Set<Integer> yoghurtID = new LinkedHashSet<Integer>(Arrays.asList(
            78, //filmjölk
            83, //yoghurt laktosfri
            84 //yoghurt turkisk
    ));

    private Set<Integer> meatID = new LinkedHashSet<Integer>(Arrays.asList(
            71, //grytbitar nöt
            72, //högrev
            76 //köttfärs nöt
    ));

    private Set<Integer> fishAndCrayfishID = new LinkedHashSet<Integer>(Arrays.asList(
            48, //fiskpinnar
            49, //kräftor
            50, //Lax
            51, //räkor
            52, //sej
            53, //sill
            54 //tonfisk

    ));

    private Set<Integer> birdsID = new LinkedHashSet<Integer>(Arrays.asList(
            73, //kycklingbröstfilé
            74, //kycklingklubber
            75 //kyckling hel
    ));

    private Set<Integer> breadID = new LinkedHashSet<Integer>(Arrays.asList(
            8, //bröd, grovt
            9, //bröd, vitt
            10, //Fralla
            11, //Knäckebröd
            12, //Toast
            13, //Tunnbröd
            14 //Vörtlimpa
    ));

    private Set<Integer> pastryID = new LinkedHashSet<Integer>(Arrays.asList(
            137, //kanelbullar
            138, //kex
            139 //kokosbollar
    ));

    private Set<Integer> flourAndBakingID = new LinkedHashSet<Integer>(Arrays.asList(
            27, //cacao
            91, //farinsocker
            92, //gramsmjöl (grahamsmjöl?)
            93, //rågmjöl
            94, //salt
            95, //socker
            96, //vetemjöl
            119 //potatispuré
    ));

    private Set<Integer> nutsAndSeedsID = new LinkedHashSet<Integer>(Arrays.asList(
            97, //cashewnöt
            98, //hasselnöt
            99, //jordnöt
            100, //kokosnöt
            101, //mandel
            102, //pistagenöt
            103, //valnöt
            104, //pumpakärnor
            105 //solrosfrön
    ));

    private Set<Integer> pastaAndRiceID = new LinkedHashSet<Integer>(Arrays.asList(
            106, //farfalle
            107, //fettuccine
            108, //lasagne
            110, //penne
            111, //rigatoni
            112, //spaghetti
            113, //basmatiris
            114, //fullkornsris
            115, //jasmin-ris
            120 //råris
    ));

    private Set<Integer> dryHerbsID = new LinkedHashSet<Integer>(Arrays.asList(
            94 //salt
    ));


    private Set<Integer> dryBeansID = new LinkedHashSet<Integer>(Arrays.asList(
            2, //kikärtor
            3, //linser bruna
            4, //linser gröna
            5, //linser röda
            6, //röda bönor
            7 //vita bönor

    ));

    private Set<Integer> coffeeAndTeaID = new LinkedHashSet<Integer>(Arrays.asList(
            28, //Earl Grey
            29, //Grönt te
            30, //kaffe
            31 //Rooibos
    ));

    private Set<Integer> candyID = new LinkedHashSet<Integer>(Arrays.asList(
            135, //choklad
            140 //praliner
    ));

    private Set<Integer> chipsID = new LinkedHashSet<Integer>(Arrays.asList(
            134 //chips
    ));

    private Set<Integer> snackNutsID = new LinkedHashSet<Integer>(Arrays.asList(
            97, //cashewnöt
            99, //jordnöt
            102, //pistagenöt
            104 //pumpakärnor
    ));

    private Set<Integer> iceCreamID = new LinkedHashSet<Integer>(Arrays.asList(
            136 //glass
    ));

    private Set<Integer> sodaID = new LinkedHashSet<Integer>(Arrays.asList(
            33, //cola burk
            34, //cola flaska
            35, //fanta burk
            36, //fanta flaska
            38, //7Up burk
            39, //7Up
            40 //Vatten
    ));


    /* Defines all subcategory lists
     */
    private Set<Product> berriesList = new LinkedHashSet<>();
    private Set<Product> fruitsList = new LinkedHashSet<>();
    private Set<Product> lemonAndLimeList = new LinkedHashSet<>();
    private Set<Product> exoticFruitsList = new LinkedHashSet<>();
    private Set<Product> cabbageAndRootVegetablesList = new LinkedHashSet<>();
    private Set<Product> otherVegetablesList = new LinkedHashSet<>();
    private Set<Product> freshHerbsList = new LinkedHashSet<>();
    private Set<Product> cheeseList = new LinkedHashSet<>();
    private Set<Product> milkList = new LinkedHashSet<>();
    private Set<Product> eggsList = new LinkedHashSet<>();
    private Set<Product> juiceList = new LinkedHashSet<>();
    private Set<Product> yoghurtList = new LinkedHashSet<>();
    private Set<Product> meatList = new LinkedHashSet<>();
    private Set<Product> fishAndCrayfishList = new LinkedHashSet<>();
    private Set<Product> birdsList = new LinkedHashSet<>();
    private Set<Product> breadList = new LinkedHashSet<>();
    private Set<Product> pastryList = new LinkedHashSet<>();
    private Set<Product> flourAndBakingList = new LinkedHashSet<>();
    private Set<Product> nutsAndSeedsList = new LinkedHashSet<>();
    private Set<Product> pastaAndRiceList = new LinkedHashSet<>();
    private Set<Product> dryHerbsList = new LinkedHashSet<>();
    private Set<Product> dryBeansList = new LinkedHashSet<>();
    private Set<Product> coffeeAndTeaList = new LinkedHashSet<>();
    private Set<Product> candyList = new LinkedHashSet<>();
    private Set<Product> chipsList = new LinkedHashSet<>();
    private Set<Product> snackNutsList = new LinkedHashSet<>();
    private Set<Product> iceCreamList = new LinkedHashSet<>();
    private Set<Product> sodaList = new LinkedHashSet<>();

    /*
    Defines all main category lists:
     */
    private Set<Product> fruitsAndVegetablesList = new LinkedHashSet<>();
    private Set<Product> milkAndRefrigeratedList = new LinkedHashSet<>();
    private Set<Product> meatFishBirdList = new LinkedHashSet<>();
    private Set<Product> breadAndPastriesList = new LinkedHashSet<>();
    private Set<Product> dryProductsList = new LinkedHashSet<>();
    private Set<Product> candyAndSnacksList = new LinkedHashSet<>();
    private Set<Product> drinksList = new LinkedHashSet<>();

    /*
    Initializes all sub-categories.
     */
    private void initSubCategories() {
        initBerriesCategory();
        initFruitsCategory();
        initLemonAndLimeCategory();
        initExoticFruitsCategory();
        initCabbageAndRootVegetablesCategory();
        initOtherVegetablesCategory();
        initFreshHerbsCategory();
        initCheeseCategory();
        initMilkCategoty();
        initEggsCategory();
        initJuiceCategory();
        initYoghurtCategory();
        initMeatCategory();
        initFishAndCrayfishCategory();
        initBirdsCategory();
        initBreadCategory();
        initPastryCategory();
        initFlourAndBakingCategory();
        initNutsAndSeedsCategory();
        initPastaAndRiceCategory();
        initDryHerbsCategory();
        initCoffeeAndTeaCategory();
        initCandyCategory();
        initChipsCategory();
        initSnackNutsCategory();
        initIceCreamCategory();
        initSodaCategory();
    }

    /*
    Initilaizes main categories.
     */
    private void initMainCategories() {
        initMainFruitsAndVegetables();
        initMainMilkAndRefrigerated();
        initMainMeatFishBird();
        initMainBreadAndPastries();
        initDryProducts();
        initCandyAndSnacks();
        initDrinks();
    }

    /* Adds all products to main categories.
     */
    private void initMainFruitsAndVegetables() {
        fruitsAndVegetablesList.addAll(berriesList);
        fruitsAndVegetablesList.addAll(fruitsList);
        fruitsAndVegetablesList.addAll(lemonAndLimeList);
        fruitsAndVegetablesList.addAll(exoticFruitsList);
        fruitsAndVegetablesList.addAll(cabbageAndRootVegetablesList);
        fruitsAndVegetablesList.addAll(otherVegetablesList);
        fruitsAndVegetablesList.addAll(freshHerbsList);
    }

    private void initMainMilkAndRefrigerated() {
        milkAndRefrigeratedList.addAll(cheeseList);
        milkAndRefrigeratedList.addAll(milkList);
        milkAndRefrigeratedList.addAll(eggsList);
        milkAndRefrigeratedList.addAll(juiceList);
        milkAndRefrigeratedList.addAll(yoghurtList);
    }

    private void initMainMeatFishBird() {
        meatFishBirdList.addAll(meatList);
        meatFishBirdList.addAll(fishAndCrayfishList);
        meatFishBirdList.addAll(birdsList);
    }

    private void initMainBreadAndPastries() {
        breadAndPastriesList.addAll(breadList);
        breadAndPastriesList.addAll(pastryList);
    }

    private void initDryProducts() {
        dryProductsList.addAll(flourAndBakingList);
        dryProductsList.addAll(nutsAndSeedsList);
        dryProductsList.addAll(pastaAndRiceList);
        dryProductsList.addAll(dryHerbsList);
        dryProductsList.addAll(coffeeAndTeaList);
    }

    private void initCandyAndSnacks() {
        candyAndSnacksList.addAll(candyList);
        candyAndSnacksList.addAll(chipsList);
        candyAndSnacksList.addAll(snackNutsList);
        candyAndSnacksList.addAll(iceCreamList);
    }

    private void initDrinks() {
        drinksList.addAll(sodaList);
    }


    /* Adds all products to sub-categories.
     */
    private void initBerriesCategory() {
        for (Integer i : berriesID) {
            berriesList.add(datahandler.getProduct(i));
        }
    }

    private void initFruitsCategory() {
        for (Integer i : fruitsID) {
            fruitsList.add(datahandler.getProduct(i));
        }
    }

    private void initLemonAndLimeCategory() {
        for (Integer i : lemonAndLimeID) {
            lemonAndLimeList.add(datahandler.getProduct(i));
        }
    }

    private void initExoticFruitsCategory() {
        for (Integer i : exoticFruitsID) {
            exoticFruitsList.add(datahandler.getProduct(i));
        }
    }

    private void initCabbageAndRootVegetablesCategory() {
        for (Integer i : cabbageAndRootVegetablesID) {
            cabbageAndRootVegetablesList.add(datahandler.getProduct(i));
        }
    }

    private void initOtherVegetablesCategory() {
        for (Integer i : otherVegetablesID) {
            otherVegetablesList.add(datahandler.getProduct(i));
        }
    }

    private void initFreshHerbsCategory() {
        for (Integer i : freshHerbsID) {
            freshHerbsList.add(datahandler.getProduct(i));
        }
    }

    private void initCheeseCategory() {
        for (Integer i : cheeseID) {
            cheeseList.add(datahandler.getProduct(i));
        }
    }

    private void initMilkCategoty() {
        for (Integer i : milkID) {
            milkList.add(datahandler.getProduct(i));
        }
    }

    private void initEggsCategory() {
        for (Integer i : eggsID) {
            eggsList.add(datahandler.getProduct(i));
        }
    }

    private void initJuiceCategory() {
        for (Integer i : juiceID) {
            juiceList.add(datahandler.getProduct(i));
        }
    }

    private void initYoghurtCategory() {
        for (Integer i : yoghurtID) {
            yoghurtList.add(datahandler.getProduct(i));
        }
    }

    private void initMeatCategory() {
        for (Integer i : meatID) {
            meatList.add(datahandler.getProduct(i));
        }
    }

    private void initFishAndCrayfishCategory() {
        for (Integer i : fishAndCrayfishID) {
            fishAndCrayfishList.add(datahandler.getProduct(i));
        }
    }

    private void initBirdsCategory() {
        for (Integer i : birdsID) {
            birdsList.add(datahandler.getProduct(i));
        }
    }

    private void initBreadCategory() {
        for (Integer i : breadID) {
            breadList.add(datahandler.getProduct(i));
        }
    }

    private void initPastryCategory() {
        for (Integer i : pastryID) {
            pastryList.add(datahandler.getProduct(i));
        }
    }

    private void initFlourAndBakingCategory() {
        for (Integer i : flourAndBakingID) {
            flourAndBakingList.add(datahandler.getProduct(i));
        }
    }

    private void initNutsAndSeedsCategory() {
        for (Integer i : nutsAndSeedsID) {
            nutsAndSeedsList.add(datahandler.getProduct(i));
        }
    }

    private void initPastaAndRiceCategory() {
        for (Integer i : pastaAndRiceID) {
            pastaAndRiceList.add(datahandler.getProduct(i));
        }
    }

    private void initDryHerbsCategory() {
        for (Integer i : dryHerbsID) {
            dryHerbsList.add(datahandler.getProduct(i));
        }
    }

    private void initCoffeeAndTeaCategory() {
        for (Integer i : coffeeAndTeaID) {
            coffeeAndTeaList.add(datahandler.getProduct(i));
        }
    }

    private void initCandyCategory() {
        for (Integer i : candyID) {
            candyList.add(datahandler.getProduct(i));
        }
    }

    private void initChipsCategory() {
        for (Integer i : chipsID) {
            chipsList.add(datahandler.getProduct(i));
        }
    }

    private void initSnackNutsCategory() {
        for (Integer i : snackNutsID) {
            snackNutsList.add(datahandler.getProduct(i));
        }
    }

    private void initIceCreamCategory() {
        for (Integer i : iceCreamID) {
            iceCreamList.add(datahandler.getProduct(i));
        }
    }

    private void initSodaCategory() {
        for (Integer i : sodaID) {
            sodaList.add(datahandler.getProduct(i));
        }

    }

    /* Getters for all sub-categories and main categories' lists.
     */
    public Set<Product> getBerriesList() {
        return berriesList;
    }

    public Set<Product> getFruitsList() {
        return fruitsList;
    }

    public Set<Product> getLemonAndLimeList() {
        return lemonAndLimeList;
    }

    public Set<Product> getExoticFruitsList() {
        return exoticFruitsList;
    }

    public Set<Product> getCabbageAndRootVegetablesList() {
        return cabbageAndRootVegetablesList;
    }

    public Set<Product> getOtherVegetablesList() {
        return otherVegetablesList;
    }

    public Set<Product> getFreshHerbsList() {
        return freshHerbsList;
    }

    public Set<Product> getCheeseList() {
        return cheeseList;
    }

    public Set<Product> getMilkList() {
        return milkList;
    }

    public Set<Product> getEggsList() {
        return eggsList;
    }

    public Set<Product> getJuiceList() {
        return juiceList;
    }

    public Set<Product> getYoghurtList() {
        return yoghurtList;
    }

    public Set<Product> getMeatList() {
        return meatList;
    }

    public Set<Product> getFishAndCrayfishList() {
        return fishAndCrayfishList;
    }

    public Set<Product> getBirdsList() {
        return birdsList;
    }

    public Set<Product> getBreadList() {
        return breadList;
    }

    public Set<Product> getPastryList() {
        return pastryList;
    }

    public Set<Product> getFlourAndBakingList() {
        return flourAndBakingList;
    }

    public Set<Product> getNutsAndSeedsList() {
        return nutsAndSeedsList;
    }

    public Set<Product> getPastaAndRiceList() {
        return pastaAndRiceList;
    }

    public Set<Product> getDryHerbsList() {
        return dryHerbsList;
    }

    public Set<Product> getDryBeansList() {
        return dryBeansList;
    }

    public Set<Product> getCoffeeAndTeaList() {
        return coffeeAndTeaList;
    }

    public Set<Product> getCandyList() {
        return candyList;
    }

    public Set<Product> getChipsList() {
        return chipsList;
    }

    public Set<Product> getSnackNutsList() {
        return snackNutsList;
    }

    public Set<Product> getIceCreamList() {
        return iceCreamList;
    }

    public Set<Product> getSodaList() {
        return sodaList;
    }

    public Set<Product> getFruitsAndVegetablesList() {
        return fruitsAndVegetablesList;
    }

    public Set<Product> getMilkAndRefrigeratedList() {
        return milkAndRefrigeratedList;
    }

    public Set<Product> getMeatFishBirdList() {
        return meatFishBirdList;
    }

    public Set<Product> getBreadAndPastriesList() {
        return breadAndPastriesList;
    }

    public Set<Product> getDryProductsList() {
        return dryProductsList;
    }

    public Set<Product> getCandyAndSnacksList() {
        return candyAndSnacksList;
    }

    public Set<Product> getDrinksList() {
        return drinksList;
    }

    public List<Product> getAllProducts() {
        return datahandler.getProducts();
    }

    public double getTotalPrice() {
        double price = 0.0;
        for (ShoppingItem item : getShoppingCart().getItems()) {
            if (item.getProduct().getUnitSuffix().equals("kg")) {
                for (int i = 0; i < item.getAmount(); i++) {
                    price = price + ((item.getProduct().getPrice()) / 10);
                }
            } else {
                for (int i = 0; i < item.getAmount(); i++) {
                    price = price + item.getProduct().getPrice();
                }
            }
        }
        return price;
    }

/*
    public boolean isValidName(String s) {
        boolean isValid = false;
        for (Character c : s.toCharArray()) {
            if (!c.isLetter() && !c.equals(" ") && !c.equals("-")) {
                return isValid;
            }
        }
        isValid = true;
        return isValid;
    }
/*
    public boolean isValidCvcCode(String s) {
        boolean isValid = false;
        for (Character c : s.toCharArray()) {
            if (!c.isDigit()) {
                return isValid;
            }

        }
    }

*/


}