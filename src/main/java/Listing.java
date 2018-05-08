import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Contact;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Ovidiu on 06-May-18.
 */
public class Listing implements Initializable {
    @FXML
    TableView<Contact> table;

    @FXML
    ProgressIndicator loader;

    @FXML
    AnchorPane listing_stage;

    Stage stage;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TableColumn<Contact, String> firstNameCol = new TableColumn<>("First Name");
        TableColumn<Contact, String> lastNameCol = new TableColumn<>("Last Name");

        firstNameCol.setCellValueFactory(new PropertyValueFactory<Contact, String>("name"));
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("surname"));

        table.getColumns().addAll(firstNameCol, lastNameCol);

        loader.setProgress(-1d);

        Thread th = new Thread(new Runnable() {
            @Override
            public void run() {
                ObservableList<Contact> list = getUserList();
                for (Contact c : list)
                    System.out.println(c.getName() + " " + c.getSurname());

                table.setItems(list);

                loader.setVisible(false);
            }
        });

        th.start();
    }

    public void closeListing() {
        stage = (Stage) listing_stage.getScene().getWindow();
        stage.close();
    }

    private ObservableList<Contact> getUserList() {
        ObservableList<Contact> list = FXCollections.observableArrayList(HibernateHelper.fetchAllContacts());
        return list;
    }
}
