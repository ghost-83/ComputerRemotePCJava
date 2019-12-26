package ru.ghost.keyri;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;

import com.fazecast.jSerialComm.SerialPort;
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

    public static boolean status;

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
                btnSetting();
            }
        });
        btn_start.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                leb_key.setText("Start");
                status = true;
                MainIR startIR = new MainIR();
                startIR.start();
            }
        });
        btn_stop.setOnAction(event -> {
            status = false;
            leb_key.setText("Stop");
        });
    }

    void btnSetting() {
        Stage setWindow = new Stage();
        Parent setWin = null;
        try {
            setWin = FXMLLoader.load(getClass().getResource("/fxml/setting.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        setWindow.setTitle("Setting");
        setWindow.setScene(new Scene(setWin, 300, 120));
        setWindow.setResizable(false);//Отключаем кнопку "развернуть на весь экран"
        setWindow.initModality(Modality.APPLICATION_MODAL);
        setWindow.show();
    }

    public class MainIR extends Thread {
        @Override
        public void run() {
            Properties settingArray = null;
            Robot robotKey = null;
            BufferedInputStream inputStream = null;
            byte[] buffer = new byte[1024];
            String key = "";

            try {
                robotKey = new Robot();
            } catch (AWTException e) {
                e.printStackTrace();
            }

            try {
                settingArray = new Setting().openSettings();
            } catch (IOException e) {
                e.printStackTrace();
            }
            SerialPort serialPort = SerialPort.getCommPort(settingArray.getProperty("COM"));
            serialPort.setBaudRate(Integer.parseInt(settingArray.getProperty("BOD")));


            if (serialPort.openPort()){

                while (Controller.status) {

                    if(serialPort.bytesAvailable() != 0){
                        serialPort.readBytes(buffer, 1024);

                        try {
                            key = new String(buffer, "UTF-8");
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }

                        System.out.println(key);

                        robotKey.keyPress(KeyEvent.VK_SPACE);
                    }
                    

                    robotKey.delay(100);
                }
            }
            else {
                System.out.println("Что то пошло не так.");
            }
        }
    }
}
