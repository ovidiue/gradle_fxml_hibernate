import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Created by Ovidiu on 16-Apr-18.
 */
public class Main extends Application {

    @FXML
    TextField name;
    @FXML
    TextField surname;

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("views/main_screen.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root, 600, 400);

        primaryStage.setTitle("FXML Welcome");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {

      Application.launch(args);

    }

    public void test_click() {
        Contact c = getContactInfo();
        HibernateHelper.save(c);
        cleanFields();
        System.out.println("Successfully inserted: "+c.name+" "+c.surname);
    }

    protected Contact getContactInfo() {
        System.out.println(name.getText());
        System.out.println(surname.getText());
        Contact c = new Contact(name.getText(), surname.getText());
        return c;
    }

    protected void cleanFields() {
        name.setText("");
        surname.setText("");
    }
}
