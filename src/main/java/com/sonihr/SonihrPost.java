package com.sonihr;/*
@author 黄大宁Rhinos
@date 2019/3/21 - 17:07
**/


import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.junit.Test;

import java.io.File;

public class SonihrPost {
    public static String getUrl(File targetFile,String targetURL) {
        PostMethod filePost = new PostMethod(targetURL);
        try {
            // 通过以下方法可以模拟页面参数提交
            Part[] parts = { new FilePart("upload_file", targetFile) };
            filePost.setRequestEntity(new MultipartRequestEntity(parts,
                    filePost.getParams()));
            HttpClient client = new HttpClient();
            client.getHttpConnectionManager().getParams()
                    .setConnectionTimeout(5000);
            int status = client.executeMethod(filePost);
            if (status == HttpStatus.SC_OK) {
                String url =  filePost.getResponseBodyAsString();
                return url;
                // 上传成功
            } else {
                return "上传失败";
                // 上传失败
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            filePost.releaseConnection();
        }
        return null;
    }
}
