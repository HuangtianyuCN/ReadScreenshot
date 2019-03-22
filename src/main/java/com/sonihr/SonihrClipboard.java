package com.sonihr;/*
@author 黄大宁Rhinos
@date 2019/3/21 - 20:55
**/

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

public class SonihrClipboard {
    public static void getClipboradUrl(String url){
        Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
        Transferable tText = new StringSelection(url);
        clip.setContents(tText, null);
    }

    public static File getImage() {
        Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
        Image image = null;
        try {
            image = SonihrClipboard.getImageFromClipboard();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //说出来你可能不信，word里面复制的文字默认是以imageFlavor存在的
        File file = new File("D:/sonihr_clip_tmp.jpg");
        BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = bufferedImage.createGraphics();
        graphics.drawImage(image, null, null);
        try {
            ImageIO.write((RenderedImage)bufferedImage, "png", file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;

    }

    /**
     * 从剪切板获得图片。
     */
    private static Image getImageFromClipboard() throws Exception {
        Clipboard sysc = Toolkit.getDefaultToolkit().getSystemClipboard();
        Transferable cc = sysc.getContents(null);
        if (cc == null)
            return null;
        else if (cc.isDataFlavorSupported(DataFlavor.imageFlavor))
            return (Image) cc.getTransferData(DataFlavor.imageFlavor);
        return null;
    }

}
