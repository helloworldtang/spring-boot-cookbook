package com.tangcheng.learning.awt;

import javax.swing.*;
import java.awt.*;

/**
 * spring-boot-cookbook
 *
 * @author tangcheng
 * @date 6/22/2018 8:46 AM
 */
public class RenderQualityTestFrame extends Frame {
    public static final int DEFAULT_WIDTH = 750;
    public static final int DEFAULT_HEIGHT = 300;
    private JPanel buttonBox;
    private RenderingHints hints;
    private int r;
    private RenderQualityComponent canvas;


    RenderQualityTestFrame() {
        setTitle("RenderQualityTestFrame");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        buttonBox = new JPanel();
        buttonBox.setLayout(new GridBagLayout());
        hints = new RenderingHints(null);

        makeButtons("KEY_ANTIALIASING", "VALUE_ANTIALIAS_OFF", "VALUE_ANTIALIAS_ON");
    }

    private void makeButtons(String key, String value1, String value2) {
        try {
            final RenderingHints.Key k = (RenderingHints.Key) RenderingHints.class.getField(key).get(null);
            final Object v1 = RenderingHints.class.getField(key).get(null);
            final Object v2 = RenderingHints.class.getField(key).get(null);
            JLabel label = new JLabel(key);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
