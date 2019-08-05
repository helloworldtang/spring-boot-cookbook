package com.tangcheng.learning.awt;

import javax.swing.*;
import java.awt.*;

/**
 * @author: tangcheng
 * @description:
 * @since: Created in 2018/06/28 17:28
 */
public class StrokeTest {

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                StrokeJFrame strokeJFrame = new StrokeJFrame();
                strokeJFrame.setLocationRelativeTo(null);
                strokeJFrame.setVisible(true);
            }
        });
    }

}

class StrokeJFrame extends JFrame {
    private static final int DEFAULT_WIDTH = 450;
    private static final int DEFAULT_HEIGHT = 350;

    public StrokeJFrame() throws HeadlessException {
        setTitle("使用Stroke画边框示例");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        add(new StrokeJComponent());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

}

class StrokeJComponent extends JComponent {
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        //get是获取默认字体，然后通过deriveFont的方法将字体设置为斜体，并且大小设置为20号字体
        graphics2D.setFont(getFont().deriveFont(Font.ITALIC, 20f));
        graphics2D.setPaint(Color.MAGENTA);
//      Stroke描边决定着图形或文字的轮廓。
//      由 BasicStroke 定义的呈现属性描述了用画笔沿Shape 的轮廓绘制的某个标记的形状，以及应用在 Shape 路径线段的末端和连接处的装饰。
//      将原有线条的粗细放大为原来的三倍大小
        graphics2D.setStroke(new BasicStroke(3f));

        // Draw a circle.
        graphics2D.drawOval(100, 50, 200, 200);
        // Draw one eye.
        graphics2D.fillOval(155, 100, 10, 20);
        // Draw another eye.
        graphics2D.fillOval(230, 100, 10, 20);
        // Draw a smile.
        graphics2D.drawArc(150, 160, 100, 50, 180, 180);
        //Draw a word
        graphics2D.setPaint(Color.BLUE);
        graphics2D.drawString("Hi~~Happy!", 280, 50);
    }


}
