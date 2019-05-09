package com.zb.byb.util;


import com.zb.byb.common.C;
import com.zb.byb.entity.FileEntry;
import org.apache.commons.codec.binary.Base64;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;

/**
 * 将图片转换为Base64<br>
 * 将base64编码字符串解码成img图片
 *
 * @创建时间 2015-06-01 15:50
 */
public class Image2Base64Util {

    public static void main(String[] args) throws IOException {
//        String imgFile = "C:\\Users\\pc2\\Desktop\\2.jpg";//待处理的图片
//        String imgbese = getImgStr(imgFile);
//        System.out.println(imgbese.length());
//        System.out.println(imgbese);
////
    }

    /**
     * 将图片转换成Base64编码
     *
     * @param imgFile 待处理图片
     * @return
     */
    public static String getImgStr(String imgFile) {
        //将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        InputStream in = null;
        byte[] data = null;
        //读取图片字节数组
        try {
            in = new FileInputStream(imgFile);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String(Base64.encodeBase64(data));
    }

    /**
     * 对字节数组字符串进行Base64解码并生成图片
     *
     * @param imgStr      图片数据
     * @param imgFilePath 保存图片全路径地址
     * @return
     */
    public static boolean generateImage(String imgStr, String imgFilePath) {
        if (imgStr == null) //图像数据为空
            return false;

        try {
            //Base64解码
            byte[] b = Base64.decodeBase64(imgStr);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {//调整异常数据
                    b[i] += 256;
                }
            }
            //生成jpeg图片
            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(b);
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static FileEntry subBase64(FileEntry fileEntry){
        String base64Str=fileEntry.getImgContent().split(",")[1];
         fileEntry.setImgContent(base64Str);
         return fileEntry;
    }

    /**
    * @Function: 将音频流转换为base64字符串
    * @Author: shaoys
    * @Date: Created in 14:41 2019/5/7
    **/
    public static String getBase64FromInputStream(InputStream in) {
        // 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        byte[] data = null;
        // 读取图片字节数组
        try {
            ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
            byte[] buff = new byte[100];
            int rc = 0;
            while ((rc = in.read(buff, 0, 100)) > 0) {
                swapStream.write(buff, 0, rc);
            }
            data = swapStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return new String(Base64.encodeBase64(data));
    }


    /**
     * 字符串加密
     */

    public static String getBase64Encoder(String str) throws IOException {
        String encode = new BASE64Encoder().encode(str.getBytes());
        return encode;
    }

    /**
     * 字符串解密
     */

    public static String getBase64Decoder(String str) throws IOException {
        String s = new String(new BASE64Decoder().decodeBuffer(str));
        return s;
    }

}