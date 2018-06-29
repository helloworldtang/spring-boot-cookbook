package com.tangcheng.learning.awt;

import net.coobird.thumbnailator.Thumbnails;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author: tangcheng
 * @description:
 * @since: Created in 2018/06/28 15:05
 */
public class DrawAGraphicDemo {


    /**
     * java2D绘图流程
     * 如果要绘制一个形状，可以按照如下步骤操作：
     * 1）获得一个Graphics2D类的对象，该类是Graphics类的子类。
     * public void paintComponent()
     * {
     * Graphics2D g2 = (Graphics2D) g;
     * }
     * 2)使用setRenderingHints方法来设置绘图提示，它提供了速度与绘图质量之间的一种平衡。
     * RenderingHits hints = ...;
     * g2.setRenderingHints(hints);
     * 3)使用setStroke方法来设置笔划，笔划用于绘制形状的边框。可以选择边框的粗细和线段的虚实。
     * Stroke stroke = ...;
     * g2.setStroke(stroke);
     * 4)使用setPaint方法来设置着色方法。着色方法用于填充诸如笔划路径或者形状内部等区域的颜色。可以创建单色的着色法，也可
     * <p>
     * 以用变化的色调进行着色，或者使用平铺的填充模式。
     * Paint paint = ...;
     * g2.setPaint(paint);
     * 5)使用clip方法来设置剪切区域。
     * Shape clip = ...;
     * g2.clip(clip);
     * 6)使用transform方法，设置一个从用户空间到设备空间的变换方式。如果使用变换方式比使用像素坐标更容易地定义在定制坐标系
     * <p>
     * 统中的形状，那么就可以使用变换方式。
     * AffineTransform transform = ...;
     * g2.transform(transform);
     * 7)使用setComposite方法设置一个组合规则，用来描述如何将新像素与现有的像素组合起来。
     * Composite composite = ...;
     * g2.setComposite(composite);
     * 8)建立一个形状，java2D API提供了用来组合各种形状的许多形状对象和方法。
     * Shape shape = ...;
     * 9)绘制或者填充该形状。如果要绘制该形状，那么它的边框就会用笔划画出来；如果要填充该形状，那么它的内部就会被着色。
     * g2.draw(shape);
     * 或
     * g2.fill(shape);
     * <p>
     * 在绘图流程中，需要以下这些操作步骤来绘制一个形状：
     * 1)用笔划画出形状的线条；
     * 2)对形状进行变换操作；
     * 3)对形状进行剪切。如果形状与剪切区域之间没有任何相交的地方，那么就停止本次操作；
     * 4)对剪切后的形状的剩余部分进行填充；
     * 5)把填充后的形状的像素与已有的像素进行组合
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        int x = 10;
        int y = 20;
        int width = 100;
        int height = 100;
        String bgUrl = "https://timgsa.baidu.com/timg?image&quality=80&size=b10000_10000&sec=1530177661&di=8fb177af83524ac9c698426b9c81f9de&src=http://img.taopic.com/uploads/allimg/140724/318763-140H40H52795.jpg";
        BufferedImage board = ImageIO.read(new URL(bgUrl).openStream());
        Graphics2D graphics2D = board.createGraphics();
        Stroke oldStroke = graphics2D.getStroke();
        Shape olcClip = graphics2D.getClip();


        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.setStroke(new BasicStroke(1f));
        graphics2D.setPaint(Color.WHITE);

        Ellipse2D.Double shape = new Ellipse2D.Double(x, y, width, height);
        graphics2D.clip(shape);
        graphics2D.setComposite(AlphaComposite.SrcAtop);
        String headImageUrl = "http://thirdwx.qlogo.cn/mmopen/ryf8Rib9UL1g4lVn2IxNDPRTSCBe0F60KbZWB7f7Ak1gNic852zADBDxrtTjz0b7E46MoVtr5NNqibSJ5Hia4K7rianetsddhqYSD/132";
        BufferedImage bufferedImage = Thumbnails.of(new URL(headImageUrl)).size(width, height).asBufferedImage();
        graphics2D.drawImage(bufferedImage, x, y, null);
        graphics2D.setClip(olcClip);
        graphics2D.setStroke(oldStroke);
        graphics2D.dispose();
        board.flush();
        ImageIO.write(board, "jpg", new File("result.jpg"));
    }

}
