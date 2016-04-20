package net.devwurm.seatlots.gui.restore;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import net.devwurm.seatlots.gui.configuration.ConfigurationStarter;
import net.devwurm.seatlots.gui.drawing.DrawingStarter;
import net.devwurm.seatlots.gui.drawing.BackupRemover;
import net.devwurm.seatlots.location.RoomList;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Controller class for restore view
 */
public class RestoreController implements Initializable {
    @FXML
    BorderPane rootPane;

    @FXML
    private TableView<RoomList> backupsTable;

    @FXML
    private TableColumn<RoomList, String> nameColumn;

    @FXML
    private TableColumn<RoomList, Number> capacityColumn;

    private ObservableList<RoomList> backupsModel = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        capacityColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getCapacity()));

        setDataFromModel();
    }

    private void setDataFromModel() {
        backupsTable.setItems(backupsModel);
    }

    public void setBackupsModel(List<RoomList> backupsModel) {
        this.backupsModel = FXCollections.observableArrayList(backupsModel);
        setDataFromModel();
    }

    public void handleRestore() {
        RoomList selectedElement = backupsTable.getSelectionModel().getSelectedItem();
        DrawingStarter starter = new DrawingStarter(selectedElement, (Stage) rootPane.getScene().getWindow());

        BackupRemover.removeBackup(selectedElement.getName());
        starter.startDrawing();
    }

    public void handleDiscard() {
        RoomList selectedElement = backupsTable.getSelectionModel().getSelectedItem();
        BackupRemover.removeBackup(selectedElement.getName());

        backupsModel.remove(selectedElement);
    }

    public void handleClose() {
        ConfigurationStarter starter = new ConfigurationStarter((Stage) rootPane.getScene().getWindow());
        starter.startConfiguration();
    }
}
