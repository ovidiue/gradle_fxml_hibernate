import javafx.css.PseudoClass;
import javafx.scene.control.Alert;
import javafx.scene.control.Control;
import javafx.scene.control.TextField;

/**
 * Created by Ovidiu on 05-May-18.
 */
public class HelperUi {

    public final static PseudoClass ERROR = PseudoClass.getPseudoClass("error");

    public static void buildAlert(Alert.AlertType type,
                                  String title,
                                  String header,
                                  String contentText) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(contentText);

        alert.showAndWait();
    }

    public static void displayErrorColors(PseudoClass pseudoClass, Control... n) {
        for (Control a : n) {
            ((TextField) a).textProperty().addListener((observer, oldValue, newValue) -> {
                if (newValue.trim().length() > 0)
                    a.pseudoClassStateChanged(pseudoClass, false);
            });
        }
    }
}
