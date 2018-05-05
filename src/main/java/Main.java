import javafx.application.Application;
import javafx.css.PseudoClass;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * Created by Ovidiu on 16-Apr-18.
 */
public class Main extends Application {

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
        cleanFields();
        System.out.println("Successfully inserted: " + c.name + " " + c.surname);
    }

    protected Contact getContactInfo() {
        System.out.println(name.getText());
        System.out.println(surname.getText());
        return new Contact(name.getText(), surname.getText());
    }

    protected void cleanFields() {
        name.setText("");
        surname.setText("");
    }

    protected boolean validateContact(Contact c) {
        return (c.name.trim().length() != 0 && c.surname.trim().length() != 0);
    }

    private void displayErrorIfNecessary(Contact c) {
        PseudoClass error = PseudoClass.getPseudoClass("error");
       name.pseudoClassStateChanged(error, false);
       surname.pseudoClassStateChanged(error, false);
        if (!validateContact(c)) {
            name.pseudoClassStateChanged(error, true);
            surname.pseudoClassStateChanged(error, true);
        }
    }
}
