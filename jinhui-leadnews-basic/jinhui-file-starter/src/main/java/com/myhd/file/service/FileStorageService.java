package com.myhd.file.service;

import java.io.InputStream;

/**
 * Description: 文件上传服务
 * @author jinhui-huang
 * @Date 2024/1/17
 * */
public interface FileStorageService {


    /**
     *  上传图片文件
     * @param prefix  文件前缀
     * @param filename  文件名
     * @param inputStream 文件流
     * @return  文件全路径
     */
    public String uploadImgFile(String prefix, String filename,InputStream inputStream);

    /**
     *  上传html文件
     * @param prefix  文件前缀
     * @param filename   文件名
     * @param inputStream  文件流
     * @return  文件全路径
     */
    public String uploadHtmlFile(String prefix, String filename,InputStream inputStream);

    /**
     *  上传文件
     * @param prefix  文件前缀
     * @param filename   文件名
     * @param inputStream  文件流
     * @param contentType 文件类型
     * @return  文件全路径
     */
    public String uploadFile(String prefix, String filename,InputStream inputStream, String contentType);

    /**
     * 删除文件
     * @param pathUrl  文件全路径
     */
    public void delete(String pathUrl);

    /**
     * 下载文件
     * @param pathUrl  文件全路径
     * @return
     *
     */
    public byte[]  downLoadFile(String pathUrl);

}
