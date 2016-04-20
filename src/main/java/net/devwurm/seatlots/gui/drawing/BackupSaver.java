package net.devwurm.seatlots.gui.drawing;

import com.fasterxml.jackson.core.JsonProcessingException;
import javafx.scene.control.Alert;
import net.devwurm.seatlots.SeatLots;
import net.devwurm.seatlots.location.RoomList;

import java.util.prefs.Preferences;

/**
 * Class to save a RoomList state during drawing
 */
public class BackupSaver {
    public static void saveState(RoomList roomListModel) {
        Preferences store = Preferences.userNodeForPackage(SeatLots.class);

        String modelJSON;
        try {
            modelJSON = roomListModel.toJSON();
        } catch (JsonProcessingException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Fehler beim erzeugen des Backups");
            alert.showAndWait();
            return;
        }

        store.put("backup_" + roomListModel.getName(), modelJSON);
    }
}
