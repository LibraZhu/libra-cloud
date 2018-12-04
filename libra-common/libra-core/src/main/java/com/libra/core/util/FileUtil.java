package com.libra.core.util;

import com.libra.core.exception.CoreExceptionEnum;
import com.libra.core.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author Libra
 * @date 2018/11/28
 * @description NIO方式读取文件
 */
public class FileUtil {
    private static Logger log = LoggerFactory.getLogger(FileUtil.class);

    public static final String FILE_DEFAULT_NAME = "default";

    public static final String FILE_DEFAULT_SUFFIX = "txt";

    public static byte[] toByteArray(String filename) {

        File f = new File(filename);
        if (!f.exists()) {
            log.error("文件未找到！" + filename);
            throw new ServiceException(CoreExceptionEnum.FILE_NOT_FOUND);
        }
        FileChannel channel = null;
        FileInputStream fs = null;
        try {
            fs = new FileInputStream(f);
            channel = fs.getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate((int) channel.size());
            while ((channel.read(byteBuffer)) > 0) {
                // do nothing
                // System.out.println("reading");
            }
            return byteBuffer.array();
        } catch (IOException e) {
            throw new ServiceException(CoreExceptionEnum.FILE_READING_ERROR);
        } finally {
            try {
                channel.close();
            } catch (IOException e) {
                throw new ServiceException(CoreExceptionEnum.FILE_READING_ERROR);
            }
            try {
                fs.close();
            } catch (IOException e) {
                throw new ServiceException(CoreExceptionEnum.FILE_READING_ERROR);
            }
        }
    }

    /**
     * 获取文件后缀名 不包含点
     */
    public static String getFileSuffix(String fileWholeName) {
        int lastIndexOf = fileWholeName.lastIndexOf(".");
        return fileWholeName.substring(lastIndexOf + 1);
    }

    /**
     * 获取文件名称(考虑文件名和后缀为空的情况，返回默认的文件名和后缀)
     */
    public static String getFileName(String fileName, String suffix) {
        if (EmptyUtil.isEmpty(fileName)) {
            if (EmptyUtil.isEmpty(suffix)) {
                return FILE_DEFAULT_NAME + "." + FILE_DEFAULT_SUFFIX;
            } else {
                return FILE_DEFAULT_NAME + "." + suffix;
            }
        } else {
            return fileName;
        }
    }
}
