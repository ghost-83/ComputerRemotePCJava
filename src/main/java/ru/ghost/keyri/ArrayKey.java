package ru.ghost.keyri;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

public class ArrayKey {

    private Map <String, Integer> arrayKey = new HashMap();
    Robot robotKey;

    public ArrayKey() {
        arrayKey.put("SPACE", KeyEvent.VK_SPACE);
        arrayKey.put("DOWN", KeyEvent.VK_DOWN);
        arrayKey.put("ENTER", KeyEvent.VK_ENTER);
        arrayKey.put("LEFT", KeyEvent.VK_LEFT);
        arrayKey.put("RIGHT", KeyEvent.VK_RIGHT);
        arrayKey.put("STOP", KeyEvent.VK_STOP);
        arrayKey.put("UP", KeyEvent.VK_UP);

        try {
            robotKey = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    public void setArrayKey(String key) {

        if(arrayKey.containsKey(key)){
            robotKey.keyPress(arrayKey.get(key));
        }

    }
}
