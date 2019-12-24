package ru.ghost.keyri;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class Setting {

    private String propFileName;
    private String portCOM;
    private String speedBOD;
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

    public void saveSettings() throws IOException {

        Properties saveProperties = new Properties();
        saveProperties.setProperty("COM", portCOM);
        saveProperties.setProperty("BOD", speedBOD);

        FileInputStream inp = new FileInputStream(propFileName);
        Properties inpProperties = new Properties();
        inpProperties.load(inp);
        inp.close();

        saveProperties.putAll(inpProperties);


        FileOutputStream out = new FileOutputStream(propFileSetting);
        saveProperties.store(out, "Settings save");
        out.close();
    }
    public Properties openSettings() throws IOException {
        FileInputStream inp = new FileInputStream(propFileSetting);
        Properties inpProperties = new Properties();
        inpProperties.load(inp);
        inp.close();
        return inpProperties;
    }
}
