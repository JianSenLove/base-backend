package com.jason.moneybag;

import java.io.IOException;

public enum DefaultImages {
    DEFAULT_AVATAR("default-avatar.jpg"),
    DEFAULT_BACKGROUND("default-background.jpg");

    private final String base64Image;

    DefaultImages(String fileName) {
        try {
            this.base64Image = ImageUtil.getBase64FromResource(fileName);
        } catch (IOException e) {
            throw new RuntimeException("加载图片出错: " + fileName, e);
        }
    }

    public String getBase64Image() {
        return base64Image;
    }
}
