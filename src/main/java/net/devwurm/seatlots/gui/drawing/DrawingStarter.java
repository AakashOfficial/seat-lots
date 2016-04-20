package net.devwurm.seatlots.gui.drawing;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import net.devwurm.seatlots.gui.drawing.DrawingController;
import net.devwurm.seatlots.location.RoomList;
import net.devwurm.seatlots.location.RoomListGenerator;
import net.devwurm.seatlots.location.configuration.RoomListConfiguration;

import java.io.IOException;

public class DrawingStarter {
    private RoomList roomListModel;
    private final Stage rootStage;

    public DrawingStarter(RoomList roomListModel) {
        this.roomListModel = roomListModel;
        rootStage = new Stage();
    }

    public DrawingStarter(RoomList roomListModel, Stage rootStage) {
        this.roomListModel = roomListModel;
        this.rootStage = rootStage;
    }

    public DrawingStarter(RoomListConfiguration configurationModel) {
        setUpRoomList(configurationModel);
        rootStage = null;
    }

    public DrawingStarter(RoomListConfiguration configurationModel, Stage rootStage) {
        setUpRoomList(configurationModel);
        this.rootStage = rootStage;
    }

    private void setUpRoomList(RoomListConfiguration configuration) {
        RoomListGenerator generator = new RoomListGenerator();
        roomListModel = generator.generateRoomList(configuration);
    }

    @FXML
    public void startDrawing() {
        FXMLLoader loader = new FXMLLoader();
        Parent root;

        try {
            root = loader.load(getClass().getClassLoader().getResource("net/devwurm/seatlots/gui/drawing/drawing.fxml").openStream());
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Los-Ansicht konnte nicht gestartet werden!");
            alert.showAndWait();
            System.exit(1);
            return;
        }

        DrawingController controller = loader.getController();
        controller.setRoomListModel(roomListModel);

        Scene scene = new Scene(root);

        rootStage.setScene(scene);
        rootStage.setFullScreen(true);
        rootStage.show();
    }
}