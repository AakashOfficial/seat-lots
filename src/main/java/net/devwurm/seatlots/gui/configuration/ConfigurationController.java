package net.devwurm.seatlots.gui.configuration;

import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.StringConverter;
import javafx.util.converter.NumberStringConverter;
import net.devwurm.seatlots.location.configuration.IllegalCapacityStringException;
import net.devwurm.seatlots.location.configuration.IllegalRoomStringException;
import net.devwurm.seatlots.location.configuration.RoomConfiguration;
import net.devwurm.seatlots.location.configuration.RoomListConfiguration;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller class for the configuration view
 */

public class ConfigurationController implements Initializable {
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

    @FXML
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        numberColumn.setCellFactory(TextFieldTableCell.<RoomConfiguration, Number>forTableColumn(new ZeroOnFailureStringConverter()));
        numberColumn.setCellFactory(TextFieldTableCell.<RoomConfiguration, Number>forTableColumn(new ZeroOnFailureStringConverter()));

        numberColumn.setCellValueFactory(cellData -> cellData.getValue().numberProperty());
        capacitiesColumn.setCellValueFactory(cellData -> cellData.getValue().numberProperty());

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

    public void setConfigurationModel(RoomListConfiguration configurationModel) {
        this.configurationModel = configurationModel;

        setDataFromModel();
    }

    public RoomListConfiguration getConfigurationModel() {
        return configurationModel;
    }

    private class InnerColorReseter implements ChangeListener<String> {

        TextField target;

        public InnerColorReseter(TextField target) {
            this.target = target;
        }

        @Override
        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            target.setStyle("-fx-text-inner-color: #ffffff;");
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
