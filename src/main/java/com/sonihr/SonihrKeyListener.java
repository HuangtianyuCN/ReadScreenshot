package com.sonihr;/*
@author 黄大宁Rhinos
@date 2019/3/21 - 17:06
**/

import com.melloware.jintellitype.HotkeyListener;
import com.melloware.jintellitype.JIntellitype;

import java.io.File;

public class SonihrKeyListener {
    public static void main(String[] args) {
        //注册一个ctrl+alt+t的热键
        JIntellitype.getInstance().registerHotKey(100, JIntellitype.MOD_CONTROL+JIntellitype.MOD_ALT, (int)'T');
        //添加热键监听器
        JIntellitype.getInstance().addHotKeyListener(new HotkeyListener() {
            public void onHotKey(int i) {
                if(i==100){
                    //todo:获取当前剪切板中的图片
                    File file = SonihrClipboard.getImage();
                    String url =  SonihrPost.getUrl(file,"http://www.你的服务器域名.com/ScreenshotForMarkdown/upload.do");
                    SonihrClipboard.getClipboradUrl(url);
                }
            }
        });

        while(true){
            try {
                Thread.sleep(1000000000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
