package com.zb.byb.util;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicEditorPaneUI;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

/**
 * @author xieli
 * @date 2019/8/1 17:34
 * @description HtmlToImageUtil
 */
public class HtmlToImageUtil {

    public static final int Height = 3200;
    public static final int Width = 1024;

    /**
     * 画页面的方法
     *
     * @param g 画笔
     * @param panel 画板
     * @return
     */
    public static void paintPage(Graphics g, JTextPane panel) {
        Graphics2D g2 = (Graphics2D) g;
        g2.translate(0f, 0f);
        panel.paint(g2);
    }

    /**
     * html转换为png文件
     *
     * @param bgColor 图片的背景色
     * @param html html的文本信息
     * @param width 显示图片的text容器的宽度
     * @param height 显示图片的text容器的高度
     * @param eb 設置容器的边框
     * @return
     * @throws Exception
     */
    public static byte[] html2png(Color bgColor, String html, EmptyBorder eb, int width, int height) throws Exception {
        JTextPane tp = new JTextPane();
        if (eb == null) {
            eb = new EmptyBorder(0, 50, 0, 50);
        }
        if (bgColor != null) {
            tp.setBackground(bgColor);
        }
        tp.setBorder(eb);
        tp.setContentType("text/html");
        tp.setText(html);
        byte[] bytes = null;
        Dimension d = ((BasicEditorPaneUI) tp.getUI()).getPreferredSize(tp);
//        int height = d.height + 20;
//        int width = d.width + 15;
        tp.setSize(width, height);
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        g.setClip(0, 0, width, height);
        paintPage(g, tp);
        g.dispose();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "png", baos);
        baos.flush();
        bytes = baos.toByteArray();
        baos.close();
        return bytes;
    }

}
