package com.example.demo.utils;

import java.util.UUID;

public class UploadUtils {
    /**
     * 截取真實文件名
     *
     * @param fileName
     * @return
     */
    public static String subFileName(String fileName) {
        // 查找最後一個 \出現位置
        int index = fileName.lastIndexOf("\\");
        if (index == -1) {
            return fileName;
        }
        return fileName.substring(index + 1);
    }

    // 獲得隨機UUID文件名
    public static String generateRandonFileName(String fileName) {
        // 獲得擴展名
        String ext = fileName.substring(fileName.lastIndexOf("."));
        return UUID.randomUUID().toString() + ext;
    }

    // 獲得hashcode生成二級目錄
    public static String generateRandomDir(String uuidFileName) {
        int hashCode = uuidFileName.hashCode();
        // 一級目錄
        int d1 = hashCode & 0xf;
        // 二級目錄
        int d2 = (hashCode >> 4) & 0xf;
        return "/" + d1 + "/" + d2;
    }
}
