package com.jason.moneybag;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;
import java.io.IOException;
import java.util.Base64;

public class ImageUtil {

    // 从resources目录加载文件并进行Base64编码
    public static String getBase64FromResource(String fileName) throws IOException {
        ClassPathResource imgFile = new ClassPathResource(fileName);
        byte[] imageBytes = IOUtils.toByteArray(imgFile.getInputStream());
        return Base64.getEncoder().encodeToString(imageBytes);
    }
}

