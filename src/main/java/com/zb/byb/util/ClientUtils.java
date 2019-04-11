package com.zb.byb.util;

import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.ksoap2.transport.HttpTransportSE;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ClientUtils {

    /**
     *
     * @param uri  服务地址
     * @param methodName 调用方法
     * @param sessionId sessionId
     * @param obj 参数对象
     * @return 结果对象
     * @throws IOException
     */
    public static CloseableHttpResponse Service(String uri,String methodName,String sessionId,Object obj) throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(uri);
        String jsonStr = JSONObject.fromObject(obj).toString();
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("sessionId", sessionId));
        nvps.add(new BasicNameValuePair("methodName", methodName));
        nvps.add(new BasicNameValuePair("data", jsonStr));
        httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
        CloseableHttpResponse response = httpclient.execute(httpPost);

        /*String msg = entity.toString();
        int statuscode = response.getStatusLine().getStatusCode();
        System.out.println(response);
        if (statuscode == 200) {
            System.out.println("sussess:" + msg);
        }*/
        return response;
    }
}
