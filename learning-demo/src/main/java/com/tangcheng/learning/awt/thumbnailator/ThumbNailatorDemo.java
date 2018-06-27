package com.tangcheng.learning.awt.thumbnailator;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Position;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author: tangcheng
 * @description:
 * @since: Created in 2018/06/27 15:11
 */
public class ThumbNailatorDemo {
    public static void main(String[] args) throws IOException {
        //首先获取
        String result = "result.png";
        makeRoundedCorner("C:/Users/luojie/Desktop/0.jpg", result, "png", 170);

        //后续水印在背景图片的的x轴y轴的坐标
        Position ab = new Position() {
            @Override
            public Point calculate(int enclosingWidth, int enclosingHeight, int width, int height, int insetLeft,
                                   int insetRight, int insetTop, int insetBottom) {
                return new Point(89, 53);
            }
        };

        //把生成的圆形图片变换成宽高142x142的图片
        String result_142x142 = "result_142x142.png";
        Thumbnails.of(result).size(142, 142).toFile(
                result_142x142);

        //把生成的圆形图片，当水印贴到背景图中，ab为圆形图片应该到背景图的x轴y轴的坐标
        Thumbnails.of("C:/Users/luojie/Desktop/cmbg.png").size(1280, 1024).watermark(ab,
                ImageIO.read(new File(result_142x142)), 1f)
                .outputQuality(0.8f).toFile("C:/Users/luojie/Desktop/image_watermark_bottom_right.jpg");


        //给文字水印
        pressText("C:/Users/luojie/Desktop/image_watermark_bottom_right.jpg", "WEIXINYONGHU", "Comic Sans MS", Font.BOLD, 30, Color.BLACK, 275, 65, 1f);

    }


    /**
     * 添加文字水印
     *
     * @param targetImg 目标图片路径，如：C://myPictrue//1.jpg
     * @param pressText 水印文字， 如：中国证券网
     * @param fontName  字体名称，    如：宋体
     * @param fontStyle 字体样式，如：粗体和斜体(Font.BOLD|Font.ITALIC)
     * @param fontSize  字体大小，单位为像素
     * @param color     字体颜色
     * @param x         水印文字距离目标图片左侧的偏移量，如果x<0, 则在正中间
     * @param y         水印文字距离目标图片上侧的偏移量，如果y<0, 则在正中间
     * @param alpha     透明度(0.0 -- 1.0, 0.0为完全透明，1.0为完全不透明)
     */
    public static void pressText(String targetImg, String pressText, String fontName, int fontStyle, int fontSize, Color color, int x, int y, float alpha) throws IOException {
        try {
            File file = new File(targetImg);
            Image image = ImageIO.read(file);
            int width = image.getWidth(null);
            int height = image.getHeight(null);

            BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = bufferedImage.createGraphics();
            g.drawImage(image, 0, 0, width, height, null);
            g.setFont(new Font(fontName, fontStyle, fontSize));
            g.setColor(color);
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));

            int width_wi = fontSize * getTextLength(pressText);
            int height_wi = fontSize;

            int widthDiff = width - width_wi;
            int heightDiff = height - height_wi;
            if (x < 0) {
                x = widthDiff / 2;
            } else if (x > widthDiff) {
                x = widthDiff;
            }

            if (y < 0) {
                y = heightDiff / 2;
            } else if (y > heightDiff) {
                y = heightDiff;
            }
            g.drawString(pressText, x, y + height_wi);//水印文件
            g.dispose();
            ImageIO.write(bufferedImage, "JPEG", file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 计算文字像素长度
     *
     * @param text
     * @return
     */
    private static int getTextLength(String text) {
        int textLength = text.length();
        int length = textLength;
        for (int i = 0; i < textLength; i++) {
            int wordLength = String.valueOf(text.charAt(i)).getBytes().length;
            if (wordLength > 1) {
                length += (wordLength - 1);
            }
        }

        return length % 2 == 0 ? length / 2 : length / 2 + 1;
    }


    /*
     * 圆角处理
     * @param BufferedImage
     * @param cornerRadius
     * */
    public static String makeRoundedCorner(String srcImageFile, String result, String type, int cornerRadius) {
        try {
            BufferedImage image = ImageIO.read(new File(srcImageFile));
            int w = image.getWidth();
            int h = image.getHeight();
            BufferedImage output = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = output.createGraphics();

            output = g2.getDeviceConfiguration().createCompatibleImage(w, h, Transparency.TRANSLUCENT);
            g2.dispose();
            g2 = output.createGraphics();
            //这里绘画圆角矩形
//        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//        g2.fillRoundRect(0, 0,w, h, cornerRadius, cornerRadius);
//        g2.setComposite(AlphaComposite.SrcIn);

            //这里绘画原型图
            Ellipse2D.Double shape = new Ellipse2D.Double(0, 0, w, h);
            g2.setClip(shape);

            g2.drawImage(image, 0, 0, w, h, null);
            g2.dispose();
            ImageIO.write(output, type, new File(result));
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
