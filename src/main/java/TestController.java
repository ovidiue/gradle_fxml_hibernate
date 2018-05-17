import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import model.Contact;
import netscape.javascript.JSObject;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by Ovidiu on 17-May-18.
 */
public class TestController implements Initializable {
    @FXML
    BorderPane borderPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();

        webEngine.setOnAlert(event -> showAlert(event.getData()));

        final URL urlHello = getClass().getResource("html/index.html");
        webEngine.load(urlHello.toExternalForm());

        JSObject window = (JSObject)webEngine.executeScript("window");
        ObjectMapper mapper = new ObjectMapper();
        ArrayList<Contact> c = (ArrayList<Contact>) HibernateHelper.fetchAllContacts();

        try {
            window.setMember("contact", mapper.writeValueAsString(c));
        } catch (IOException e) {
            e.printStackTrace();
        }

        borderPane.setCenter(webView);

    }

    private void showAlert(String message) {
        Dialog<Void> alert = new Dialog<>();
        alert.getDialogPane().setContentText(message);
        alert.getDialogPane().getButtonTypes().add(ButtonType.OK);
        alert.showAndWait();
    }
}


