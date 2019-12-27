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
        arrayKey.put("PLAY/PAUSE", 179);
        arrayKey.put("VOLUME_UP", 175);
        arrayKey.put("VOLUME_DOWN", 174);
        arrayKey.put("VOLUME_MUTE", 173);
        arrayKey.put("NEXT_TRACK", 176);
        arrayKey.put("PREVIOUS_TRACK", 177);
        arrayKey.put("STOP_MEDIA", 178);
        arrayKey.put("SELECT_MEDIA", 181);

        try {
            robotKey = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    public void setArrayKey(String key) {

        if(arrayKey.containsKey(key)){
            robotKey.keyPress(arrayKey.get(key));
            System.out.println(key);
        }

    }
}
