package ru.ghost.keyri;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

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

    }
}
