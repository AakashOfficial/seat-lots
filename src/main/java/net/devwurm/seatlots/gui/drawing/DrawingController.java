package net.devwurm.seatlots.gui.drawing;

import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import net.devwurm.seatlots.location.RoomList;
import net.devwurm.seatlots.lots.Lot;
import net.devwurm.seatlots.lots.LotDrawer;

import java.util.Optional;

/**
 * Controller for the drawing GUI
 */
public class DrawingController {
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
        if(roomListModel != null && e.getEventType().equals(KeyEvent.KEY_TYPED) && e.getCode().equals(KeyCode.ENTER)) {
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
}
