import javafx.scene.control.Alert;

/**
 * Created by Ovidiu on 05-May-18.
 */
public class HelperUi {
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
}
