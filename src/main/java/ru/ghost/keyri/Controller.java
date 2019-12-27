package ru.ghost.keyri;

import java.io.CharArrayReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Properties;
import com.fazecast.jSerialComm.SerialPort;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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

        });
        btn_stop.setOnAction(event -> {
            startIR.shutdown();
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
        private volatile boolean status;

        @Override
        public void run() {

            try {
                settingArray = new Setting().openSettings();
            } catch (IOException e) {
                e.printStackTrace();
            }
            SerialPort serialPort = SerialPort.getCommPort(settingArray.getProperty("COM"));
            serialPort.setBaudRate(Integer.parseInt(settingArray.getProperty("BOD")));
            status = true;

            if (serialPort.openPort()){

                while (status) {

                    if(serialPort.bytesAvailable() != 0){
                        serialPort.readBytes(buffer, 1024, 0);

                        key = new String(buffer).replaceAll("[^0-9]*", "");

                        key = new String(buffer);

                        Platform.runLater(new Runnable(){

                            @Override
                            public void run() {
                                leb_key.setText(key);
                            }
                        });

                        System.out.println(key);
                        System.out.println(settingArray);
                        System.out.println(key.matches("\\d"));
                        arrayKey.setArrayKey(settingArray.getProperty(key));
                    }

                    try {
                        Thread.sleep(50);
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
            else {
                System.out.println("Что то пошло не так.");
            }

        }
        public void shutdown() {
            status = false;
        }
    }
}
