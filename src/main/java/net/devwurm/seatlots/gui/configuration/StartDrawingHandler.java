package net.devwurm.seatlots.gui.configuration;

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

public class StartDrawingHandler {
    private final RoomListConfiguration configurationModel;
    private final Stage rootStage;

    public StartDrawingHandler(RoomListConfiguration configurationModel) {
        this.configurationModel = configurationModel;
        rootStage = null;
    }

    public StartDrawingHandler(RoomListConfiguration configurationModel, Stage rootStage) {
        this.configurationModel = configurationModel;
        this.rootStage = rootStage;
    }

    @FXML
    public void startDrawing() {
        RoomListGenerator generator = new RoomListGenerator();
        RoomList roomList = generator.generateRoomList(configurationModel);

        Stage newStage = new Stage();
        newStage.setFullScreen(true);

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
        controller.setRoomListModel(roomList);

        Scene scene = new Scene(root);

        newStage.setScene(scene);
        newStage.show();

        if (rootStage != null) {
            rootStage.close();
        }
    }
}