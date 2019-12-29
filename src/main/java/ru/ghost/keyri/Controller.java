package ru.ghost.keyri;

import java.io.IOException;
import java.util.Properties;
import com.fazecast.jSerialComm.SerialPort;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Controller {

    public static String key = "";
    private  MainIR startIR;

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
        btn_setting.setOnAction(event -> btnSetting());
        btn_start.setOnAction(event -> {
            leb_key.setText("Start");
            startIR = new MainIR();
            startIR.start();

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (startIR.isAlive() ){
                btn_setting.setDisable(true);
                btn_start.setDisable(true);
                btn_stop.setDisable(false);
            }else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);

                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("Could not open port, check settings!");

                alert.showAndWait();
            }


        });
        btn_stop.setOnAction(event -> {
            startIR.shutdown();
            btn_start.setDisable(false);
            btn_setting.setDisable(false);
            btn_stop.setDisable(true);
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
        Properties settingArray = null;
        ArrayKey arrayKey = new ArrayKey();
        byte[] buffer = new byte[1024];
        private volatile boolean status = true;

        @Override
        public void run() {

            try {
                settingArray = new Setting().openSettings();
            } catch (IOException e) {
                e.printStackTrace();
            }
            SerialPort serialPort = SerialPort.getCommPort(settingArray.getProperty("COM"));
            serialPort.setBaudRate(Integer.parseInt(settingArray.getProperty("BOD")));


            if (serialPort.openPort()){

                while (status) {

                    if(serialPort.bytesAvailable() != 0){
                        serialPort.readBytes(buffer, 1024, 0);

                        key = (new String(buffer)).replaceAll("[^0-9]+", "");

                        Platform.runLater(new Runnable(){

                            @Override
                            public void run() {
                                leb_key.setText(key);
                            }
                        });

                        arrayKey.setArrayKey(settingArray.getProperty(key));
                    }

                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                serialPort.closePort();
                Platform.runLater(new Runnable(){

                    @Override
                    public void run() {
                        leb_key.setText("Stop");
                    }
                });
            }
        }
        public void shutdown() {
            status = false;
        }
    }
}
