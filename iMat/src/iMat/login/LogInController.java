package iMat.login;

import iMat.BackendWrapper;
import iMat.MainController;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.ColorInput;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import se.chalmers.ait.dat215.project.Customer;
import se.chalmers.ait.dat215.project.User;

import java.net.URL;
import java.util.ResourceBundle;

public class LogInController implements Initializable {

    private MainController mc;
    private BackendWrapper wrapper;

    @FXML
    private AnchorPane mainAnchor;
    @FXML
    private TextField loginUsername;
    @FXML
    private PasswordField loginPassword;
    @FXML
    private Button loginButton;
    @FXML
    private TextField regFirstName;
    @FXML
    private TextField regLastName;
    @FXML
    private TextField regEMail;
    @FXML
    private TextField regUserName;
    @FXML
    private PasswordField regPassword;
    @FXML
    private Button regButton;
    @FXML
    private ImageView exitImage;
    @FXML
    private Label loginWarning;
    @FXML
    private Label regWarning;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mainAnchor.toBack();

        /*
        Raden nedanför gör så att när man trycker på inloggningsfönstret så "förstörs" klick-eventet.
        Det gör att mainAnchor aldrig får reda på att man klickade, därav körs aldrig metoden onMainPaneClicked() som tar bort inloggningsfönstret.
        */
        mainAnchor.getChildren().get(0).setOnMouseClicked(Event::consume);


        exitImage.setOnMouseClicked(event -> mc.showLogin(false));
        exitImage.setOnMouseEntered(event -> {
            Bounds bound = exitImage.getBoundsInLocal(); //getting co-ordinates
            exitImage.setEffect(new ColorInput(bound.getMinX(), bound.getMinY(),
                    bound.getWidth(), bound.getHeight(), Color.LIGHTBLUE));
        });
        exitImage.setOnMouseExited(event -> exitImage.setEffect(null));
    }

    public void injectMainController(MainController mc) {
        this.mc = mc;
        this.wrapper = MainController.getBackendWrapper();
    }

    public void setHeight(int height) {
        mainAnchor.setPrefHeight(height);
        mainAnchor.getChildren().get(0).setLayoutY((height / 2) - 250);
    }

    public void setWidth(int width) {
        mainAnchor.setPrefWidth(width);
        mainAnchor.getChildren().get(0).setLayoutX((width / 2) - 250);
    }

    //Tar bort inloggningsrutan när man klickar på det gråa området.
    @FXML
    public void onMainPaneClicked() {
        mc.showLogin(false);
    }

    @FXML
    public void onLoginButtonClicked() {
        String name = loginUsername.getText();
        String pass = loginPassword.getText();
        User user = MainController.getBackendWrapper().getUser();

        if (user.getPassword().equals(pass) && user.getUserName().equals(name)) {
            System.out.println("Login success");
            mc.login();
            mc.showLogin(false);
        } else {
            loginWarning.setText("Fel användarnamn/lösenord!");
        }
    }

    /*
    Registrerar användaren i databasen och hjälper till med nästa steg för användaren (som är att logga in)
     */
    @FXML
    public void onRegButtonClicked() {
        String fName = regFirstName.getText();
        String lName = regLastName.getText();
        String eMail = regEMail.getText();
        String userName = regUserName.getText();
        String pass = regPassword.getText();

        if (fName.equals("") || lName.equals("") || eMail.equals("") || userName.equals("") || pass.equals("")) {
            regWarning.setText("Alla fält är inte ifyllda!");
            return;
        }

        Customer customer = wrapper.getCustomer();
        User user = wrapper.getUser();

        user.setPassword(pass);
        user.setUserName(userName);

        customer.setEmail(eMail);
        customer.setFirstName(fName);
        customer.setLastName(lName);

        regWarning.setStyle("-fx-text-fill: green");
        regWarning.setText("Registrerad! Du kan nu logga in.");

        regFirstName.clear();
        regLastName.clear();
        regEMail.clear();
        regUserName.clear();
        regPassword.clear();

        loginUsername.setText(userName);
    }


    /**
     * Nedanför är metoder som anropas när man trycker enter i dem olika textfälten som finns vid inloggning
     */
    @FXML
    public void onLoginPasswordFieldAction(){
        onLoginButtonClicked();
    }

    @FXML
    public void onLoginUsernameFieldAction(){
        loginPassword.requestFocus();
    }

    @FXML
    public void onRegFirstNameFieldAction(){
        regLastName.requestFocus();
    }

    @FXML
    public void onRegLastNameFieldAction (){
        regEMail.requestFocus();
    }

    @FXML
    public void onRegEmailFieldAction (){
        regUserName.requestFocus();
    }

    @FXML
    public void onRegUsernameFieldAction (){
        regPassword.requestFocus();
    }

    @FXML
    public void onRegPasswordFieldAction (){
        onRegButtonClicked();
    }
}
