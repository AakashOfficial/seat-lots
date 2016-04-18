package net.devwurm.seatlots.gui.configuration;

import com.fasterxml.jackson.core.JsonProcessingException;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import net.devwurm.seatlots.location.configuration.RoomListConfiguration;

import java.io.*;

/**
 * Class for loading a configuration
 */
public class ConfigurationSaver {
    public static void saveConfiguration(RoomListConfiguration configuration) {
        File source = showSaveDialog();
        if (source == null) return;

        String configurationJSON;
        try {
            configurationJSON = configuration.toJSON();
        } catch (JsonProcessingException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Fehler beim erzeugen der Konfigurationsdatei");
            alert.showAndWait();
            return;
        }

        try (BufferedWriter writer = (new BufferedWriter(new FileWriter(source)))) {
            writer.write(configurationJSON);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Fehler beim Schreiben der Konfigurationsdatei");
            alert.showAndWait();
        }
    }

    private static File showSaveDialog() {
        Stage fileChooserStage = new Stage();
        FileChooser fileChooser = new FileChooser();

        fileChooser.setTitle("Konfiguration speichern");
        fileChooser.setInitialDirectory(
                new File(System.getProperty("user.home"))
        );
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Seat-Lots Konfiguration", "*.slc"),
                new FileChooser.ExtensionFilter("Alle Typen", "*.*")
        );

        return fileChooser.showSaveDialog(fileChooserStage);
    }
}
