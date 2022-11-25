package com.example.demo.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.fileupload.FileItem;

public class UploadPic {

    private static String path = "c:\\upload";

    // 上傳圖片
    public static void savePic(FileItem item, String filename) throws IOException {
        // 創建文件上傳路
        File file = new File(path);

        // 判斷路徑是否存在
        if (!file.exists())
            file.mkdirs();

        // 獲取item中的上傳文件的輸入流
        InputStream in = item.getInputStream();
        // 創建一個文件輸出流
        FileOutputStream out = new FileOutputStream(path + "\\" + filename);
        // 創建一個緩衝區
        byte buffer[] = new byte[1024];
        // 判斷輸入流中的數據是否已經讀完的標識
        int len = 0;
        // 循環將輸入流讀入到緩衝區當中，(len=in.read(buffer))>0就表示in裡面還有數據
        while ((len = in.read(buffer)) > 0) {
            // 使用FileOutputStream輸出流將緩衝區的數據寫入到指定的目錄(savePath + "\\" + filename)當中
            out.write(buffer, 0, len);
        }
        in.close();
        // 關閉輸出流
        out.close();
        // 刪除處理文件上傳時生成的臨時文件
        item.delete();

    }

    public String getPath() {
        return path;
    }

}
