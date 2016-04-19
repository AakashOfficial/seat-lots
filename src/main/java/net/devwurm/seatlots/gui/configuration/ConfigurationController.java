package net.devwurm.seatlots.gui.configuration;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import net.devwurm.seatlots.location.configuration.IllegalCapacityStringException;
import net.devwurm.seatlots.location.configuration.IllegalRoomStringException;
import net.devwurm.seatlots.location.configuration.RoomConfiguration;
import net.devwurm.seatlots.location.configuration.RoomListConfiguration;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller class for the configuration view
 */

public class ConfigurationController implements Initializable {
    @FXML
    BorderPane rootPane;

    @FXML
    private TextField nameField;

    @FXML
    private TableView<RoomConfiguration> roomsTable;
    @FXML
    private TableColumn<RoomConfiguration, Number> numberColumn;
    @FXML
    private TableColumn<RoomConfiguration, Number> capacitiesColumn;

    @FXML
    private TextField newRoomNumber;
    @FXML
    private TextField newRoomCapacity;

    private RoomListConfiguration configurationModel = new RoomListConfiguration("");

    private Stage rootStage;

    @FXML
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        numberColumn.setCellFactory(TextFieldTableCell.<RoomConfiguration, Number>forTableColumn(new ZeroOnFailureStringConverter()));
        capacitiesColumn.setCellFactory(TextFieldTableCell.<RoomConfiguration, Number>forTableColumn(new ZeroOnFailureStringConverter()));

        numberColumn.setCellValueFactory(cellData -> cellData.getValue().numberProperty());
        capacitiesColumn.setCellValueFactory(cellData -> cellData.getValue().capacityProperty());

        rootPane.sceneProperty().addListener((observable, oldValue, newValue) -> {
            rootStage = (Stage) newValue.getWindow();
            newValue.windowProperty().addListener((observable1, oldValue1, newValue1) -> {
                rootStage = (Stage) newValue1;
            });
        });

        setDataFromModel();
    }

    private void setDataFromModel() {
        nameField.textProperty().bindBidirectional(configurationModel.nameProperty());
        roomsTable.setItems(configurationModel.getConfiguration());
    }

    @FXML
    public void handleAddRoom() {
        try {
            configurationModel.addRoom(newRoomNumber.getText(), newRoomCapacity.getText());
        } catch (IllegalRoomStringException e) {
            newRoomNumber.setStyle("-fx-text-inner-color: #ff5242;");
            newRoomNumber.textProperty().addListener(new InnerColorReseter(newRoomNumber));

        } catch (IllegalCapacityStringException e) {
            newRoomCapacity.setStyle("-fx-text-inner-color: #ff5242;");
            newRoomNumber.textProperty().addListener(new InnerColorReseter(newRoomCapacity));
        }
    }

    @FXML
    public void handleSave() {
        ConfigurationSaver.saveConfiguration(configurationModel);
    }

    @FXML
    public void handleLoad() {
        ConfigurationLoader.loadConfiguration().ifPresent(configuration -> {
            configurationModel = configuration;
            setDataFromModel();
        });
    }

    public RoomListConfiguration getConfigurationModel() {
        return configurationModel;
    }

    public void setConfigurationModel(RoomListConfiguration configurationModel) {
        this.configurationModel = configurationModel;

        setDataFromModel();
    }

    @FXML
    public void handleStart() {
        DrawingStarter startHandler = new DrawingStarter(configurationModel, rootStage);
        startHandler.startDrawing();
    }

    private class InnerColorReseter implements ChangeListener<String> {

        TextField target;

        public InnerColorReseter(TextField target) {
            this.target = target;
        }

        @Override
        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            target.setStyle("-fx-text-inner-color: #000000;");
            target.textProperty().removeListener(this);
        }
    }

    private class ZeroOnFailureStringConverter extends StringConverter<Number> {
        @Override
        public Number fromString(String string) {
            try {
                return Integer.parseInt(string);
            } catch (NumberFormatException e) {
                return 0;
            }
        }

        @Override
        public String toString(Number object) {
            return object.toString();
        }
    }
}
