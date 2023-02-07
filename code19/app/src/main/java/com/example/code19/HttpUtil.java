package com.example.code19;

import android.os.Build;

import java.net.HttpCookie;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

import okhttp3.Call;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author xilinhan
 * @description: 对okHTTP进行封装，与远程服务器进行通信
 * @date :2022/6/22 10:42
 */
public class HttpUtil {
    public static final String BASE_URL = "http://192.168.1.88:8888/auction/api/";
    private static Map<String, List<Cookie>> cookieStore = new HashMap<>();
    //创建线程池
    private static ExecutorService threadPool = Executors.newFixedThreadPool(30);
    private static OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .cookieJar(new CookieJar() {
                @Override
                public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
                    cookieStore.put(url.host(), cookies);
                }

                @Override
                public List<Cookie> loadForRequest(HttpUrl url) {
                    List<Cookie> cookies = cookieStore.get(url.host());
                    return cookies == null ? new ArrayList<>() : cookies;
                }
            }).build();


    //获取请求
    public static String getRequest(String url) throws ExecutionException, InterruptedException {
        FutureTask<String> task = new FutureTask<>(() -> {
            //创建请求对象
            Request request = new Request.Builder()
                    .url(url)
                    .build();
            //用来发送请求和接受请求的Call
            Call call = okHttpClient.newCall(request);
            Response response = call.execute();
            //如果服务器成功地返回响应
            if(response.isSuccessful() && response.body() != null){
                return response.body().string().trim();
            }else {
                return null;
            }
        });
        threadPool.submit(task);
        return task.get();
    }

    //发送请求
    public static String postRequest(String url, Map<String, String> rawParams) throws ExecutionException, InterruptedException {
        FutureTask<String> task = new FutureTask<>(() -> {
            //构建包含请求参数的表单体
            FormBody.Builder builder = new FormBody.Builder();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                rawParams.forEach(builder::add);
            }
            FormBody body = builder.build();
            //创建请求对象
            Request request = new Request.Builder()
                    .url(url)
                    .post(body)
                    .build();
            Call call = okHttpClient.newCall(request);
            //发送Post请求
            Response response = call.execute();
            //如果服务器成功地返回响应
            if(response.isSuccessful() && response.body() != null) {
                //返回服务器的响应字符串
                return response.body().string().trim();
            }else {
                return null;
            }
        });
        threadPool.submit(task);
        return task.get();
    }
}
