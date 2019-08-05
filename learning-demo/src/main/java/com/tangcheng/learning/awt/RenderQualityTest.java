package com.tangcheng.learning.awt;

import com.tangcheng.learning.awt.gridbag.GBC;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * spring-boot-cookbook
 *
 * @author tangcheng
 * @date 6/22/2018 8:44 AM
 */
public class RenderQualityTest {

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new RenderQualityTestFrame();
                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }

}

class RenderQualityTestFrame extends JFrame {
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


        makeButtons(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF, RenderingHints.VALUE_ANTIALIAS_ON);
        makeButtons(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_OFF, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        makeButtons(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_OFF, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        makeButtons(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED, RenderingHints.VALUE_RENDER_QUALITY);
        makeButtons(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE, RenderingHints.VALUE_STROKE_NORMALIZE);
        canvas = new RenderQualityComponent();
        canvas.setRenderingHints(hints);

        add(canvas, BorderLayout.CENTER);
        add(buttonBox, BorderLayout.NORTH);
    }

    /**
     * Makes a set of buttons for a rendering hint key and values
     *
     * @param key the key name
     * @param v1  the name of the first value for the key
     * @param v2  the name of the second value for the key
     */
    private void makeButtons(RenderingHints.Key key, Object v1, Object v2) {
        try {
            JLabel label = new JLabel(key.toString());
            buttonBox.add(label, new GBC(0, r).setAnchor(GBC.WEST));
            ButtonGroup group = new ButtonGroup();
            JRadioButton b1 = new JRadioButton(v1.toString(), true);

            buttonBox.add(b1, new GBC(1, r).setAnchor(GBC.WEST));
            group.add(b1);
            b1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    hints.put(key, v1);
                    canvas.setRenderingHints(hints);
                }
            });
            JRadioButton b2 = new JRadioButton(v2.toString(), false);
            buttonBox.add(b2, new GBC(2, r).setAnchor(GBC.WEST));
            group.add(b2);
            b2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    hints.put(key, v2);
                    canvas.setRenderingHints(hints);
                }
            });
            hints.put(key, v1);
            r++;
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

class RenderQualityComponent extends JComponent {
    private BufferedImage image;
    private RenderingHints hints = new RenderingHints(null);

    public RenderQualityComponent() {
        try {
            URL url = new URL("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1530427218&di=33caecd860f4fce4d23dfc26ee20666b&imgtype=jpg&er=1&src=http%3A%2F%2Fpic.58pic.com%2F58pic%2F15%2F42%2F47%2F79h58PICG4y_1024.png");
            InputStream inputStream = url.openStream();
            image = ImageIO.read(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHints(hints);
        Ellipse2D.Double ellipse2D = new Ellipse2D.Double(50, 50, 50, 50);
        g2.draw(ellipse2D);
        g2.setFont(new Font("Serif", Font.ITALIC, 40));
        g2.drawString("Hello", 75, 50);

        g2.draw(new Line2D.Double(201, 11, 239, 49));
        Shape oldClip = g2.getClip();
        g2.clip(ellipse2D);
        g2.drawImage(image, 250, 10, 100, 100, null);
        g2.setClip(oldClip);
    }

    public void setRenderingHints(RenderingHints h) {
        hints = h;
        repaint();
    }
}

