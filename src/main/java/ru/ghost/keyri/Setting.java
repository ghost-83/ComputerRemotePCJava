package ru.ghost.keyri;

import javafx.scene.control.Alert;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class Setting {

    private String propFileName = "";
    private String portCOM = "";
    private String speedBOD = "";
    private String propFileSetting = "src/main/resources/setting.properties";

    public void setPropFileName(String jsonFileName) {
        this.propFileName = jsonFileName;
    }

    public void setPortCOM(String portCOM) {
        this.portCOM = portCOM;
    }

    public void setSpeedBOD(String speedBOD) {
        this.speedBOD = speedBOD;
    }

    public boolean saveSettings() {

        Properties saveProperties = new Properties();
        boolean status = false;

        saveProperties.setProperty("COM", "COM" + portCOM);
        saveProperties.setProperty("BOD", speedBOD);

        FileInputStream inp = null;
        Properties inpProperties = new Properties();
        try {
            inp = new FileInputStream(propFileName);
            inpProperties.load(inp);
            inp.close();
            saveProperties.putAll(inpProperties);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);

            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("Error saving!");

            alert.showAndWait();
        }

        if (portCOM != "" || speedBOD != "" || propFileName != "") {
            FileOutputStream out = null;
            try {
                out = new FileOutputStream(propFileSetting);
                saveProperties.store(out, "Settings save");
                out.close();
                status =true;

                Alert alert = new Alert(Alert.AlertType.INFORMATION);

                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("File: " + propFileName + "\n" +
                        "COM POTR: " + portCOM + "\n" + "BOD: " + speedBOD);

                alert.showAndWait();
            } catch (IOException e) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);

                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("Error reading file!");

                alert.showAndWait();
                status = false;
            }

        }else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);

            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("Fill in all the settings!");

            alert.showAndWait();
        }
        return status;
    }
    public Properties openSettings() throws IOException {
        FileInputStream inp = new FileInputStream(propFileSetting);
        Properties inpProperties = new Properties();
        inpProperties.load(inp);
        inp.close();
        return inpProperties;
    }
}
