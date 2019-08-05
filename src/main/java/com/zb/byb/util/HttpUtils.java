package com.zb.byb.util;

import com.zb.byb.common.Func;
import com.zb.byb.config.WxCache;
import it.sauronsoftware.jave.AudioUtils;
import lombok.Cleanup;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Map;

/* *
 * @description http工具类
 * @author xieli
 * @date  11:49 2019/7/24
 * @param
 * @return
 **/
public class HttpUtils {

    /**
     * 通过request获取参数
     *
     * @param request
     * @param name
     * @return
     */
    public static String getCookieByName(HttpServletRequest request, String name) {
        if (Func.checkNullOrEmpty(name))
            return null;

        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (name.equals(cookie.getName()))
                    return cookie.getValue();
            }
        }
        return null;
    }

    public static String httpRequestToString(String url, Map<String, String> params) {
        try {
            if (Func.checkNullOrEmpty(url))
                return null;

            @Cleanup InputStream is = httpRequestToStream(url, params);
            @Cleanup BufferedReader in = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            StringBuffer buffer = new StringBuffer();
            String line = "";
            while ((line = in.readLine()) != null) {
                buffer.append(line);
            }
            return buffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static InputStream httpRequestToStream(String url, Map<String, String> params) {
        if (Func.checkNullOrEmpty(url))
            return null;

        InputStream is = null;
        try {
            String parameters = "";
            boolean hasParams = false;
            for (String key : params.keySet()) {
                String value = URLEncoder.encode(params.get(key), "UTF-8");
                parameters += key + "=" + value + "&";
                hasParams = true;
            }
            if (hasParams) {
                parameters = parameters.substring(0, parameters.length() - 1);
            }

            url += "?" + parameters;

            URL u = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) u.openConnection();
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Accept-Charset", "UTF-8");
            conn.setRequestProperty("contentType", "utf-8");
            conn.setConnectTimeout(50000);
            conn.setReadTimeout(50000);
            conn.setDoInput(true);
            // 设置请求方式，默认为GET
            conn.setRequestMethod("GET");

            is = conn.getInputStream();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return is;
    }

    public static File downloadWxFile(String mediaId) {
        String accessToken = WxCache.getInstance().getAccessToken().getToken();
        String downloadUrl = "https://api.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID";
        downloadUrl = downloadUrl.replaceAll("ACCESS_TOKEN", accessToken).replaceAll("MEDIA_ID", mediaId);
        File file = null;
        File file2 = null;
        try {
            // 统一资源
            URL url = new URL(downloadUrl);
            // 连接类的父类，抽象类
            URLConnection urlConnection = url.openConnection();
            // http的连接类
            HttpURLConnection httpURLConnection = (HttpURLConnection) urlConnection;
            // 设定请求的方法，默认是GET
            httpURLConnection.setRequestMethod("GET");
            // 设置字符编码
            httpURLConnection.setRequestProperty("Charset", "UTF-8");
            // 打开到此 URL 引用的资源的通信链接（如果尚未建立这样的连接）。
            httpURLConnection.connect();
            URLConnection con = url.openConnection();

            @Cleanup BufferedInputStream bin = new BufferedInputStream(httpURLConnection.getInputStream());
            String folder = System.getProperty("java.io.tmpdir");
            String path = folder + File.separatorChar + mediaId + ".amr";
            file = new File(path);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            @Cleanup OutputStream out = new FileOutputStream(file);
            int size = 0;
            int len = 0;
            byte[] buf = new byte[1024];
            while ((size = bin.read(buf)) != -1) {
                len += size;
                out.write(buf, 0, size);
            }
//            bin.close();
//            out.close();

            String path2 = folder + File.separator + mediaId + ".mp3";
            file2 = new File(path2);
            if (!file2.getParentFile().exists()) {
                file2.getParentFile().mkdirs();
            }
            AudioUtils.amrToMp3(file, file2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file2;
    }

    public static File downloadWxImg(String mediaId) {
        String accessToken = WxCache.getInstance().getAccessToken().getToken();
        String downloadUrl = "https://api.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID";
        downloadUrl = downloadUrl.replaceAll("ACCESS_TOKEN", accessToken).replaceAll("MEDIA_ID", mediaId);
        File file = null;
        File file2 = null;
        try {
            // 统一资源
            URL url = new URL(downloadUrl);
            // 连接类的父类，抽象类 http的连接类
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            // 设定请求的方法，默认是GET
            httpURLConnection.setRequestMethod("GET");
            // 设置字符编码
            httpURLConnection.setRequestProperty("Charset", "UTF-8");
            // 打开到此 URL 引用的资源的通信链接（如果尚未建立这样的连接）。
            httpURLConnection.connect();
            @Cleanup BufferedInputStream bin = new BufferedInputStream(httpURLConnection.getInputStream());
            String folder = System.getProperty("java.io.tmpdir");
            String path = folder + File.separatorChar + mediaId + ".jpg";
            file = new File(path);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            @Cleanup OutputStream out = new FileOutputStream(file);
            int size = 0;
            int len = 0;
            byte[] buf = new byte[1024];
            while ((size = bin.read(buf)) != -1) {
                len += size;
                out.write(buf, 0, size);
            }
//            bin.close();
//            out.close();
            return file;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
