package net.devwurm.seatlots.gui.drawing;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import net.devwurm.seatlots.location.RoomList;
import net.devwurm.seatlots.lots.Lot;
import net.devwurm.seatlots.lots.LotDrawer;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Controller for the drawing GUI
 */
public class DrawingController implements Initializable {
    @FXML
    BorderPane rootPane;

    @FXML
    private Text nameText;

    @FXML
    private Text capacityText;

    @FXML
    private Text drawResultText;

    private RoomList roomListModel;

    private void setDataFromModel() {
        if(roomListModel != null) {
            nameText.setText(roomListModel.getName());

            capacityText.setText(roomListModel.getCapacity().toString());
        }
    }

    public void setRoomListModel(RoomList roomListModel) {
        this.roomListModel = roomListModel;
        setDataFromModel();
    }

    @FXML
    public void handleNextDraw(KeyEvent e) {
        if(roomListModel != null && e.getCode().equals(KeyCode.ENTER)) {
            Optional<Lot> opLot = (new LotDrawer(roomListModel)).drawLot();

            if(opLot.isPresent()) {
                Lot lot = opLot.get();
                drawResultText.setText(lot.getRoomNumber() + "/" + lot.getSeatNumber());
                setDataFromModel();
            } else {
                drawResultText.setText("Kein Los");
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        rootPane.sceneProperty().addListener(((observable, oldValue, newValue) -> {
            rootPane.requestFocus();
        }));
    }
}
