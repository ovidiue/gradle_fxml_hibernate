import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
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
        TableColumn actionCol = new TableColumn<>("Delete");

        firstNameCol.setCellValueFactory(new PropertyValueFactory<Contact, String>("name"));
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("surname"));
        actionCol.setCellValueFactory(new PropertyValueFactory<>("DELETE"));

        table.getColumns().addAll(firstNameCol, lastNameCol, actionCol);


        HelperUi.applyClass(table, "css/loading_table.css", true);

        loader.setProgress(-1d);

        Thread th = new Thread(() -> {
            ObservableList<Contact> list = getUserList();
            for (Contact c : list)
                System.out.println(c.getName() + " " + c.getSurname());

            table.setItems(list);

            loader.setVisible(false);
            HelperUi.applyClass(table, "css/loading_table.css", false);

            Callback<TableColumn<Contact, String>, TableCell<Contact, String>> cellFactory
                    = new Callback<TableColumn<Contact, String>, TableCell<Contact, String>>() {
                @Override
                public TableCell call(final TableColumn<Contact, String> param) {
                    final TableCell<Contact, String> cell = new TableCell<Contact, String>() {

                        final Button btn = new Button("DELETE");

                        @Override
                        public void updateItem(String item, boolean empty) {
                            super.updateItem(item, empty);
                            if (empty) {
                                setGraphic(null);
                                setText(null);
                            } else {
                                btn.setOnAction(event -> {
                                    Contact person = getTableView().getItems().get(getIndex());
                                    System.out.println(person.getName()
                                            + "   " + person.getSurname());
                                });
                                setGraphic(btn);
                                setText(null);
                            }
                        }
                    };
                    return cell;
                }
            };
            actionCol.setCellFactory(cellFactory);
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
