package net.devwurm.seatlots;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import net.devwurm.seatlots.gui.configuration.ConfigurationController;
import net.devwurm.seatlots.gui.configuration.ConfigurationStarter;
import net.devwurm.seatlots.gui.drawing.BackupLoader;
import net.devwurm.seatlots.gui.restore.RestoreStarter;
import net.devwurm.seatlots.location.RoomList;

import java.io.IOException;
import java.util.List;

/**
 * Main class
 */

public class SeatLots extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        List<RoomList> backups = BackupLoader.loadStates();

        if (backups.size() > 0) {
            RestoreStarter starter = new RestoreStarter(backups, primaryStage);
            starter.startRestore();
        } else {
            ConfigurationStarter starter = new ConfigurationStarter(primaryStage);
            starter.startConfiguration();
        }
    }
}
