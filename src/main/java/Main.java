import javafx.application.Application;
import javafx.css.PseudoClass;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Ovidiu on 16-Apr-18.
 */
public class Main extends Application implements Initializable {

    @FXML
    TextField name;
    @FXML
    TextField surname;

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("views/main_screen.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root, 600, 400);
        scene.getStylesheets().add("css/main_css.css");

        primaryStage.setTitle("FXML Welcome");
        primaryStage.setScene(scene);

        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                System.exit(1);
            }
        });


        primaryStage.show();
    }

    public void test_click() {
        Contact c = getContactInfo();
        if (!validateContact(c)) {
            displayErrorIfNecessary(c);
            HelperUi.buildAlert(Alert.AlertType.WARNING,
                    "Warning",
                    "Empty fields",
                    "Please enter values for all fields");
            return;
        }
        HibernateHelper.save(c);
        cleanFieldsValues();
        System.out.println("Successfully inserted: " + c.name + " " + c.surname);
    }

    private Contact getContactInfo() {
        System.out.println(name.getText());
        System.out.println(surname.getText());
        return new Contact(name.getText(), surname.getText());
    }

    private void cleanFieldsValues() {
        name.setText("");
        surname.setText("");
    }

    private boolean validateContact(Contact c) {
        return (c.name.trim().length() != 0 && c.surname.trim().length() != 0);
    }

    private void displayErrorIfNecessary(Contact c) {
        if (!validateContact(c)) {
            name.pseudoClassStateChanged(HelperUi.ERROR, true);
            surname.pseudoClassStateChanged(HelperUi.ERROR, true);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        HelperUi.displayErrorColors(PseudoClass.getPseudoClass("error"), name, surname);
    }

    public void viewAllEntries() throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("views/listing.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root, 600, 400);
        scene.getStylesheets().add("css/main_css.css");

        Stage primaryStage = new Stage();
        primaryStage.setTitle("Listing entries");
        primaryStage.setScene(scene);

        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                System.exit(1);
            }
        });


        primaryStage.show();


    }
}
