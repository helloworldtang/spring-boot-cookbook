package com.tangcheng.learning.awt;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 * spring-boot-cookbook
 *
 * @author tangcheng
 * @date 6/24/2018 10:52 PM
 */
public class DrawTest {

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                DrawImg f = new DrawImg();
            }
        });
    }
}

class DrawImg extends JFrame {
    DrawImg() {
        setLocationByPlatform(true);
        setTitle("draw test");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        getContentPane().add(new DrawComponent());
        pack();
        setVisible(true);
    }
}

class DrawComponent extends JComponent {
    private static final int DEFAULT_W = 600;
    private static final int DEFAULT_H = 600;

    DrawComponent() {
        setPreferredSize(new Dimension(DEFAULT_W, DEFAULT_H));
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        double centerX = DEFAULT_W / 2;
        double centerY = DEFAULT_H / 2;

        double conerX = centerX + 100;
        double conerY = centerY + 100;

        Rectangle2D rect = new Rectangle2D.Double();
        rect.setFrameFromCenter(centerX, centerY, conerX, conerY);

        Ellipse2D ellipse = new Ellipse2D.Double();
        ellipse.setFrame(rect);

        Ellipse2D circle = new Ellipse2D.Double();
        double radius = Point2D.distance(centerX, centerY, conerX, conerY);
        circle.setFrameFromCenter(centerX, centerY, centerX + radius, centerY + radius);

        Line2D line = new Line2D.Double(conerX, conerY, conerX - 200, conerY - 200);
        Line2D line2 = new Line2D.Double(conerX - 200, conerY, conerX, conerY - 200);


//        g2d.draw(rect);

        g2d.draw(ellipse);
        g2d.setColor(Color.BLUE);
        g2d.draw(circle);

//        g2d.draw(line);
//        g2d.draw(line2);
    }
}
