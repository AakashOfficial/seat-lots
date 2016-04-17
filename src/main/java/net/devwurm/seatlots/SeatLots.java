package net.devwurm.seatlots;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import net.devwurm.seatlots.gui.configuration.ConfigurationController;

import java.io.IOException;

/**
 * Main class
 */

public class SeatLots extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        FXMLLoader loader = new FXMLLoader();
        Parent root;

        try {
            root = loader.load(getClass().getClassLoader().getResource("net/devwurm/seatlots/gui/configuration/configuration.fxml").openStream());
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Konfigurationsansicht konnte nicht gestartet werden!");
            alert.showAndWait();
            System.exit(1);
            return;
        }

        ConfigurationController controller = loader.getController();
        controller.setRootStage(primaryStage);

        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
