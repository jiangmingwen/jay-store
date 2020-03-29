package site.jaymw.JStore.utils;

import com.google.gson.Gson;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 封装Http get/post
 */
public class HttpUtils {
    private static final Gson gson = new Gson();

    /**
     * GET方法
     *
     * @param url
     * @return
     */
    public static Map<String, Object> doGet(String url) {
        Map<String, Object> map = new HashMap<>();//返回的结果

        CloseableHttpClient httpClient = HttpClients.createDefault();
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(5000)//链接超时
                .setConnectionRequestTimeout(5000)//请求超时
                .setSocketTimeout(5000)
                .setRedirectsEnabled(true)//允许自动重定向
                .build();
        HttpGet httpGet = new HttpGet(url);
        httpGet.setConfig(requestConfig);
        try {
            HttpResponse httpResponse = httpClient.execute(httpGet);
            if (httpResponse.getStatusLine().getStatusCode() == 200) {//请求成功
                String jsonResult = EntityUtils.toString(httpResponse.getEntity());
                map = gson.fromJson(jsonResult, map.getClass());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return map;
    }

    public static Map<String, Object> doGet(String url, int timeout) {
        Map<String, Object> map = new HashMap<>();//返回的结果

        CloseableHttpClient httpClient = HttpClients.createDefault();
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(timeout)//链接超时
                .setConnectionRequestTimeout(timeout)//请求超时
                .setSocketTimeout(timeout)
                .setRedirectsEnabled(true)//允许自动重定向
                .build();
        HttpGet httpGet = new HttpGet(url);
        httpGet.setConfig(requestConfig);
        try {
            HttpResponse httpResponse = httpClient.execute(httpGet);
            if (httpResponse.getStatusLine().getStatusCode() == 200) {//请求成功
                String jsonResult = EntityUtils.toString(httpResponse.getEntity());
                map = gson.fromJson(jsonResult, map.getClass());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return map;
    }

    /**
     * POST方法
     *
     * @param url
     * @return
     */
    public static String doPost(String url, String data, int timeout) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(timeout)//链接超时
                .setConnectionRequestTimeout(timeout)//请求超时
                .setSocketTimeout(timeout)
                .setRedirectsEnabled(true)//允许自动重定向
                .build();
        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(requestConfig);
        httpPost.addHeader("Content-Type", "application/json");
        if (data != null && data instanceof String) {//使用字符串传参
            StringEntity stringEntity = new StringEntity(data, "utf8");
            httpPost.setEntity(stringEntity);
        }
        try {
            HttpResponse httpResponse = httpClient.execute(httpPost);
            if (httpResponse.getStatusLine().getStatusCode() == 200) {//请求成功
                String result = EntityUtils.toString(httpResponse.getEntity());
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static String doPost(String url, String data) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(5000)//链接超时
                .setConnectionRequestTimeout(5000)//请求超时
                .setSocketTimeout(5000)
                .setRedirectsEnabled(true)//允许自动重定向
                .build();
        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(requestConfig);
        httpPost.addHeader("Content-Type", "application/json");
        if (data != null && data instanceof String) {//使用字符串传参
            StringEntity stringEntity = new StringEntity(data, "utf8");
            httpPost.setEntity(stringEntity);
        }
        try {
            HttpResponse httpResponse = httpClient.execute(httpPost);
            if (httpResponse.getStatusLine().getStatusCode() == 200) {//请求成功
                String result = EntityUtils.toString(httpResponse.getEntity());
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
