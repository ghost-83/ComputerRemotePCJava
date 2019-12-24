package ru.ghost.keyri;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ru.ghost.keyri.Setting;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btn_start;

    @FXML
    private Button btn_stop;

    @FXML
    private Label leb_key;

    @FXML
    private Button btn_setting;

    @FXML
    void initialize() {
        btn_setting.setOnAction(new EventHandler<ActionEvent>() {
            public void handle (ActionEvent event) {
                try {
                    btnSetting();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    void btnSetting() throws IOException {
        Stage setWindow = new Stage();
        Parent setWin = FXMLLoader.load(getClass().getResource("/fxml/setting.fxml"));
        setWindow.setTitle("Setting");
        setWindow.setScene(new Scene(setWin, 300, 120));
        setWindow.setResizable(false);//Отключаем кнопку "развернуть на весь экран"
        setWindow.initModality(Modality.APPLICATION_MODAL);
        setWindow.show();
    }

}
