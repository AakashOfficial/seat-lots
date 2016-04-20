package net.devwurm.seatlots.gui.restore;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import net.devwurm.seatlots.location.RoomList;

import java.io.IOException;
import java.util.List;

/**
 * Class for starting the configuration view
 */
public class RestoreStarter {
    private final Stage rootStage;
    private List<RoomList> backupsModel;

    public RestoreStarter(List<RoomList> backupsModel) {
        this.backupsModel = backupsModel;
        rootStage = new Stage();
    }

    public RestoreStarter(List<RoomList> backupsModel, Stage rootStage) {
        this.backupsModel = backupsModel;
        this.rootStage = rootStage;
    }

    public void startRestore() {
        FXMLLoader loader = new FXMLLoader();
        Parent root;

        try {
            root = loader.load(getClass().getClassLoader().getResource("net/devwurm/seatlots/gui/restore/restore.fxml").openStream());
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Backupansicht konnte nicht geladen werden!");
            alert.showAndWait();
            System.exit(1);
            return;
        }

        RestoreController controller = loader.getController();
        controller.setBackupsModel(backupsModel);

        Scene scene = new Scene(root);

        rootStage.setScene(scene);
        rootStage.setWidth(scene.getWidth());
        rootStage.setHeight(scene.getHeight());
        rootStage.show();
    }
}
