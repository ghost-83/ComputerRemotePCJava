package ru.ghost.keyri;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
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
                FileChooser fileChooser = new FileChooser();
                fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Properties", "*.properties"));
                File file = fileChooser.showOpenDialog(openFile);
                if (file != null) {
                    leb_file.setText(file.getAbsolutePath());
                }
            }
        });

        btn_save.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {

                Setting saveSetting = new Setting();

                if (leb_file.getText() != "") {
                    saveSetting.setPropFileName(leb_file.getText());
                }
                if (txt_port.getText() != "") {
                    saveSetting.setPortCOM(txt_port.getText());
                }
                if (txt_bod.getText() != "") {
                    saveSetting.setSpeedBOD(txt_bod.getText());
                }

                if (saveSetting.saveSettings()) {
                    Stage stage = (Stage) btn_save.getScene().getWindow();
                    stage.close();
                }

            }
        });
    }
}

