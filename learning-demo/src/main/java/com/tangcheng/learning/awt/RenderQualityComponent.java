package com.tangcheng.learning.awt;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * spring-boot-cookbook
 *
 * @author tangcheng
 * @date 6/22/2018 8:50 AM
 */
public class RenderQualityComponent extends JComponent {
    private BufferedImage image;
    private RenderingHints hints = new RenderingHints(null);

    public RenderQualityComponent() {
        try {
            image = ImageIO.read(new File(""));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHints(hints);
        g2.draw(new Ellipse2D.Double(10, 10, 60, 50));
        g2.setFont(new Font("Serif", Font.ITALIC, 40));
        g2.drawString("Hello", 75, 50);

        g2.draw(new Line2D.Double(201, 11, 239, 49));
        g2.drawImage(image, 250, 10, 100, 100, null);
    }

    public void setRenderingHints(RenderingHints h) {
        hints = h;
        repaint();
    }
}
