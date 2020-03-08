package com.haowan.game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Image2Char {
    public static void main(String[] args) throws IOException {
        String ascii = "$@B%8&WM#*oahkbdpqwmZO0QLCJUYXzcvunxrjft/\\|()1{}[]?-_+~<>i!lI;:,\\\"^`'.";
        String path = Image2Char.class.getResource("/").getPath();
        String fileName = "xincun.jpg";
        BufferedImage image = ImageIO.read(new File(path + fileName));
        int width = image.getWidth();
        int height = image.getHeight();
        StringBuilder sb = new StringBuilder();
        BufferedImage bufferedImage = new BufferedImage(width, height, image.getType());
        // 获取图像上下文
        int step = 2;
        Graphics g = createGraphics(bufferedImage, width, height, 5);
        for (int i = 0; i < height; i += step) {
            for (int j = 0; j < width; j += step) {
                int rgb = image.getRGB(j, i);
                int red = (rgb & 0xff0000) >> 16;
                int green = (rgb & 0xff00) >> 8;
                int blue = (rgb & 0xff);
                float gray = 0.299f * red + 0.578f * green + 0.114f * blue;
//                sb.append(ascii.charAt((int) (gray / 255 * ascii.length())));
                g.drawString(String.valueOf(ascii.charAt((int) (gray / 255 * ascii.length()))), j, i);
            }
//            sb.append("\n");
        }
        ImageIO.write(bufferedImage, "jpg", new FileOutputStream(fileName));
//        System.out.println(sb.toString());
    }

    private static Graphics createGraphics (BufferedImage image,int width, int height, int size){
        Graphics g = image.createGraphics();
        g.setColor(null); // 设置背景色
        g.fillRect(0, 0, width, height);// 绘制背景
        g.setColor(Color.BLACK); // 设置前景色
        g.setFont(new Font("微软雅黑", Font.PLAIN, size)); // 设置字体
        return g;
    }
}
