package net.devwurm.seatlots.gui.drawing;

import com.fasterxml.jackson.core.JsonParseException;
import javafx.scene.control.Alert;
import net.devwurm.seatlots.SeatLots;
import net.devwurm.seatlots.location.RoomList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;
import java.util.stream.Collectors;

/**
 * Class from loading backup State files
 */
public class BackupLoader {
    public static List<RoomList> loadStates() {
        Preferences store = Preferences.userNodeForPackage(SeatLots.class);

        try {
            return Arrays.asList(store.keys()).stream()
                    .filter(key -> key.contains("backup_"))
                    .map(key -> store.get(key, ""))
                    .map(json -> {
                        RoomList roomListModel;
                        try {
                            roomListModel = RoomList.fromJSON(json);
                        } catch (JsonParseException e) {
                            Alert alert = new Alert(Alert.AlertType.ERROR, "Fehlerhafte Backupdatei");
                            alert.showAndWait();
                            return Optional.empty();
                        } catch (IOException e) {
                            Alert alert = new Alert(Alert.AlertType.ERROR, "Fehler beim Verarbeiten der Backupdatei");
                            alert.showAndWait();
                            return Optional.empty();
                        }
                        return Optional.of(roomListModel);
                    })
                    .filter(Optional::isPresent)
                    .map(opRoomList -> (RoomList) opRoomList.get())
                    .collect(Collectors.toList());
        } catch (BackingStoreException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Fehler beim Lesen des Backup speichers");
            alert.showAndWait();
            return new ArrayList<>();
        }
    }
}
