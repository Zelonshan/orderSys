package com.ntlg.ordersys.controller;

import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;

@Controller
@RequestMapping("file")
public class FileController {
    @RequestMapping("download")
    public ResponseEntity<byte[]> download(String filename) {
        // 设置编码
        try {
            String newFileName = new String(filename.getBytes("UTF-8"), "ISO-8859-1");
            // 文件所在路径
            String path = "C:\\JAVA\\javaworkspace\\imgs\\foodlist\\";
            // 文件下载的全路径
            String downPath = path + filename;
            // 生成下载的文件
            File file = new File(downPath);
            // 设置HttpHeaders,使得浏览器响应下载
            HttpHeaders headers = new HttpHeaders();
            // 给浏览器设置下载文件名
            headers.setContentDispositionFormData("attachment", newFileName);
            // 浏览器响应方式
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            // 把文件以二进制格式响应给浏览器
            ResponseEntity<byte[]> responseEntity = new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),
                    headers, HttpStatus.OK);
            return responseEntity;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping("download1")
    public ResponseEntity<byte[]> download1(String filename) {
        // 设置编码
        try {
            String newFileName = new String(filename.getBytes("UTF-8"), "ISO-8859-1");
            // 文件所在路径
            String path = "C:\\JAVA\\javaworkspace\\imgs\\wxadsimage\\";
            // 文件下载的全路径
            String downPath = path + filename;
            // 生成下载的文件
            File file = new File(downPath);
            // 设置HttpHeaders,使得浏览器响应下载
            HttpHeaders headers = new HttpHeaders();
            // 给浏览器设置下载文件名
            headers.setContentDispositionFormData("attachment", newFileName);
            // 浏览器响应方式
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            // 把文件以二进制格式响应给浏览器
            ResponseEntity<byte[]> responseEntity = new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),
                    headers, HttpStatus.OK);
            return responseEntity;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
