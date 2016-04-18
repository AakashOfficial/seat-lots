package net.devwurm.seatlots.gui.configuration;

import com.fasterxml.jackson.core.JsonParseException;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import net.devwurm.seatlots.location.configuration.RoomListConfiguration;

import java.io.*;
import java.util.Optional;

/**
 * Class for loading a configuration
 */
public class ConfigurationLoader {
    public static Optional<RoomListConfiguration> loadConfiguration() {
        File source = showOpenDialog();
        if (source == null) return Optional.empty();


        String configurationJSON = "";
        try (BufferedReader reader = (new BufferedReader(new FileReader(source)))) {
            String line;
            while ((line = reader.readLine()) != null) {
                configurationJSON += line;
            }
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Fehler beim Lesen der Konfigurationsdatei");
            alert.showAndWait();
            return Optional.empty();
        }

        RoomListConfiguration configuration;
        try {
            configuration = RoomListConfiguration.fromJSON(configurationJSON);
        } catch (JsonParseException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Fehlerhafte Konfigurationsdatei");
            alert.showAndWait();
            return Optional.empty();
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Fehler beim Verarbeiten der Konfigurationsdatei");
            alert.showAndWait();
            return Optional.empty();
        }

        return Optional.of(configuration);
    }

    private static File showOpenDialog() {
        Stage fileChooserStage = new Stage();
        FileChooser fileChooser = new FileChooser();

        fileChooser.setTitle("Konfiguration laden");
        fileChooser.setInitialDirectory(
                new File(System.getProperty("user.home"))
        );
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Alle Typen", "*.*"),
                new FileChooser.ExtensionFilter("Seat-Lots Konfiguration", "*.slc")
        );

        return fileChooser.showOpenDialog(fileChooserStage);
    }
}
