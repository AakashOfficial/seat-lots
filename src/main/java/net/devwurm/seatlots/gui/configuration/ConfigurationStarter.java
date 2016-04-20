package net.devwurm.seatlots.gui.configuration;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Class for starting the configuration view
 */
public class ConfigurationStarter {
    private final Stage rootStage;

    public ConfigurationStarter() {
        rootStage = new Stage();
    }

    public ConfigurationStarter(Stage rootStage) {
        this.rootStage = rootStage;
    }

    public void startConfiguration() {
        FXMLLoader loader = new FXMLLoader();
        Parent root;

        try {
            root = loader.load(getClass().getClassLoader().getResource("net/devwurm/seatlots/gui/configuration/configuration.fxml").openStream());
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Konfigurationsansicht konnte nicht geladen werden!");
            alert.showAndWait();
            System.exit(1);
            return;
        }

        Scene scene = new Scene(root);

        rootStage.hide();
        rootStage.setScene(scene);
        rootStage.setWidth(scene.getWidth());
        rootStage.setHeight(scene.getHeight());
        rootStage.show();
    }
}
