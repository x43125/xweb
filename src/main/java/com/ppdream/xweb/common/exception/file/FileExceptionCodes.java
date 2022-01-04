package com.ppdream.xweb.common.exception.file;

/**
 * @Author: x43125
 * @Date: 22/01/04
 */
public enum FileExceptionCodes {

    /**
     * 文件读取错误
     */
    FILE_READ_EXCEPTION("600", "文件读取错误"),
    FILE_UPLOAD_FAILED("601", "文件上传失败");

    FileExceptionCodes(String code, String description) {
        this.code = code;
        this.description = description;
    }

    private String code;
    private String description;

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
