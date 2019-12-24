package ru.ghost.keyri;

import java.io.File;
import java.net.URL;
import java.nio.channels.FileChannel;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ControllerSetting {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btn_save;

    @FXML
    private Button btn_file;

    @FXML
    private Label leb_file;

    @FXML
    private TextField txt_port;

    @FXML
    private TextField txt_bod;

    @FXML
    void initialize() {
        btn_file.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                Stage openFile = new Stage();
                FileChooser fileChooset = new FileChooser();
                File file = fileChooset.showOpenDialog(openFile);
                leb_file.setText(file.getAbsolutePath());
            }
        });

        btn_save.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                SaveSetting saveSetting = new SaveSetting();
                saveSetting.setJsonFileName(leb_file.getText());
                saveSetting.setPortCOM(txt_port.getText());
                saveSetting.setSpeedBOD(txt_bod.getText());
            }
        });

    }
}

