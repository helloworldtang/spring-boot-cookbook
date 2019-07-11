package com.tangcheng.learning.awt;

import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

/**
 * @author Cay Horstmann
 * @version 1.33 2007-04-14
 */
@Slf4j
public class FontTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                FontFrame frame = new FontFrame();
                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                showJFrameToCenter(frame);
                frame.setVisible(true);
                String[] fontNames = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
                for (String fontName : fontNames) {
                    log.info("fontName:{}", fontName);
                }
            }
        });
    }

    /**
     * 通过坐标来居中
     *
     * @param frame
     */
    private static void showJFrameToCenter(FontFrame frame) {
        //方法一：
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        int x = (int) (toolkit.getScreenSize().getWidth() - frame.getWidth()) / 2;
        int y = (int) (toolkit.getScreenSize().getHeight() - frame.getHeight()) / 2;
        frame.setLocation(x, y);

        //方法二：
        //传入参数null 即可让JFrame 位于屏幕中央, 这个函数若传入一个Component ,则JFrame位于该组件的中央
        // frame.setLocationRelativeTo(null);
    }
}

/**
 * A frame with a text message component
 */
class FontFrame extends JFrame {
    public FontFrame() {
        setTitle("FontTest");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        // add component to frame

        FontComponent component = new FontComponent();
        add(component);
    }

    public static final int DEFAULT_WIDTH = 300;
    public static final int DEFAULT_HEIGHT = 200;
}

/**
 * A component that shows a centered message in a box.
 */
class FontComponent extends JComponent {

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        String message = "Hello, World!";

        Font f = new Font("Serif", Font.BOLD, 36);
        g2.setFont(f);

        // measure the size of the message

        FontRenderContext context = g2.getFontRenderContext();
        Rectangle2D bounds = f.getStringBounds(message, context);

        // set (x,y) = top left corner of text

        double x = (getWidth() - bounds.getWidth()) / 2;
        double y = (getHeight() - bounds.getHeight()) / 2;

        // add ascent to y to reach the baseline

        double ascent = -bounds.getY();
        double baseY = y + ascent;

        // draw the message

        g2.drawString(message, (int) x, (int) baseY);

        g2.setPaint(Color.LIGHT_GRAY);

        // draw the baseline

        g2.draw(new Line2D.Double(x, baseY, x + bounds.getWidth(), baseY));

        // draw the enclosing rectangle

        Rectangle2D rect = new Rectangle2D.Double(x, y, bounds.getWidth(), bounds.getHeight());
        g2.draw(rect);
    }
}
