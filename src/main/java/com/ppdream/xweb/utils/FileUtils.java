package com.ppdream.xweb.utils;

import com.ppdream.xweb.common.exception.file.FileException;
import com.ppdream.xweb.common.exception.file.FileExceptionCodes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 文件处理工具类
 * @Author: x43125
 * @Date: 22/01/04
 */
public class FileUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileUtils.class);

    public static List<String> readFile2List(String filePath) {
        LOGGER.info("读取文件:" + filePath);
        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            List<String> blogList = new ArrayList<>();
            String tempStr;
            while ((tempStr = reader.readLine()) != null) {
                blogList.add(tempStr);
            }
            reader.close();
            return blogList;
        } catch (IOException e) {
            LOGGER.error("读取文本失败：" + filePath);
            throw new FileException(FileExceptionCodes.FILE_READ_EXCEPTION.getCode(), new Object[]{});
        }
    }

    public static String readFile2Str(String filePath) {
        LOGGER.info("读取文件:" + filePath);
        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            StringBuilder sb = new StringBuilder();
            String tempStr;
            while ((tempStr = reader.readLine()) != null) {
                sb.append(tempStr);
            }
            reader.close();
            return sb.toString();
        } catch (IOException e) {
            LOGGER.error("读取文本失败：" + filePath);
            throw new FileException(FileExceptionCodes.FILE_READ_EXCEPTION.getCode(), new Object[]{});
        }
    }
}
