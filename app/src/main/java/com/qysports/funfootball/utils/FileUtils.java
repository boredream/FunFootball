package com.qysports.funfootball.utils;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.boredream.bdcodehelper.utils.AppUtils;
import com.qysports.funfootball.constants.CommonConstants;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;

public class FileUtils {

    /**
     * 根据文件名获取文件对象
     *
     * @param filename 文件名
     * @return 文件不存在时返回null
     */
    public static File getFile(String filename) {
        File sdPath = AppUtils.getSDPath();
        if (sdPath == null) {
            // SD卡不可用时视为不存在
            return null;
        }

        File dir = new File(sdPath, CommonConstants.DIR_NAME);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        File file = new File(dir, filename);
        return file;
    }

    /**
     * 文件是否存在。 所有文件都存放在固定app目录中，根据文件名判断是否存在。
     *
     * @param filename 文件名
     * @return
     */
    public static boolean isExist(String filename) {
        File file = getFile(filename);
        if (file == null) {
            return false;
        } else {
            return file.exists();
        }
    }

    /**
     * 保存文件, 需要放在子线程中执行
     *
     * @param data
     * @param filename
     * @return 保存成功返回文件, 失败返回null
     */
    public static File saveFile(byte[] data, String filename) throws IOException {
        File sdPath = AppUtils.getSDPath();
        if (sdPath == null) {
            // SD卡不可用时也定义为IO异常
            throw new IOException("SD卡路径不存在");
        }

        File dir = new File(sdPath, CommonConstants.DIR_NAME);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        File file = new File(dir, filename);
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(data);
        fos.close();

        return file;
    }

    /**
     * 保存图片文件, 在保存文件的基础上增加一个广播通知手机相册更新
     */
    public static File saveImageFile(Context context, byte[] bytes, String filename) throws IOException {
        File file = FileUtils.saveFile(bytes, filename);
        if (file != null) {
            // 图片保存在文件保存的基础上要添加一个提醒相册更新图片的广播
            Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            Uri uri = Uri.fromFile(file);
            intent.setData(uri);
            context.sendBroadcast(intent);
        }
        return file;
    }

    /**
     * 转换文件大小
     *
     * @param fileBytesSize 文件字节数长度
     * @return 大小缩写
     */
    public static String formetFileSize(long fileBytesSize) {
        DecimalFormat df = new DecimalFormat("#.00");
        String fileSizeString;
        String wrongSize = "0B";
        if (fileBytesSize == 0) {
            return wrongSize;
        }
        if (fileBytesSize < 1024) {
            fileSizeString = df.format((double) fileBytesSize) + "B";
        } else if (fileBytesSize < 1048576) {
            fileSizeString = df.format((double) fileBytesSize / 1024) + "KB";
        } else if (fileBytesSize < 1073741824) {
            fileSizeString = df.format((double) fileBytesSize / 1048576) + "MB";
        } else {
            fileSizeString = df.format((double) fileBytesSize / 1073741824) + "GB";
        }
        return fileSizeString;
    }
}
