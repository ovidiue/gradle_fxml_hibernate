import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Ovidiu on 06-May-18.
 */
public class Listing implements Initializable {
    @FXML
    TableView table;

    @FXML
    AnchorPane listing_stage;

    Stage stage;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void closeListing() {
        stage = (Stage) listing_stage.getScene().getWindow();
        stage.close();
    }
}
