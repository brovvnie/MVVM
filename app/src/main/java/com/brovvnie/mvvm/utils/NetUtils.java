package com.brovvnie.mvvm.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.blankj.utilcode.util.ActivityUtils;
import com.brovvnie.mvvm.api.Api;
import com.brovvnie.mvvm.api.ApiService;

import java.io.File;
import java.net.Proxy;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;

/**
 * 网络请求工具类
 */
public class NetUtils {
    private static final String TAG = "NetUtils";
    private static AlertDialog alertDialog = null;
    private final ApiService apiService;
    private final OkHttpClient okHttpClient;

    private NetUtils() {
        okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .addInterceptor(new RequestHeaderInterceptor())
                .connectTimeout(60, TimeUnit.SECONDS)//连接超时时间
                .writeTimeout(60, TimeUnit.SECONDS)//写操作 超时时间
                .readTimeout(60, TimeUnit.SECONDS)//读操作超时时间
                .retryOnConnectionFailure(true)//错误重联
                .proxy(Proxy.NO_PROXY)//防抓包
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(Api.BASE_URL)
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
        apiService = retrofit.create(ApiService.class);
    }

    public static NetUtils getInstance() {
        return SingleHolder.UTIL;
    }

    public void loadPic(String url, Callback callback) {
        final Request request = new Request.Builder().url(url).get().build();   //默认为get请求
        Call call = okHttpClient.newCall(request);
        call.enqueue(callback);
    }

    public void get(String url, Class c, NetCallback callback) {
        doObservable(apiService.get(url), c, callback);
    }

    public void getQuery(String url, Class c, Map<String, Object> queryMap, NetCallback callback) {
        Observable<ResponseBody> observable = apiService.getQuery(url, queryMap);
        doObservable(observable, c, callback);
    }

    public void getQueryHeader(String url,
                               Class c,
                               Map<String, String> queryMap,
                               Map<String, String> headerMap,
                               NetCallback callback) {
        doObservable(apiService.getQueryHeader(url, queryMap, headerMap), c, callback);
    }

    public void post(String url, Class c, NetCallback callback) {
        doObservable(apiService.post(url), c, callback);
    }

    public void postForm(String url, Class c, Map<String, Object> fieldMap, NetCallback callback) {
        Observable<ResponseBody> observable = apiService.postForm(url, fieldMap);
        doObservable(observable, c, callback);
    }

    public void postFormHeader(String url,
                               Class c,
                               Map<String, String> fieldMap,
                               Map<String, String> headerMap,
                               NetCallback callback) {
        Observable<ResponseBody> observable = apiService.postFormHeader(url, fieldMap, headerMap);
        doObservable(observable, c, callback);
    }

    public void postFileHeader(String url, Class c, String filePath, Map<String, String> headerMap, NetCallback callback) {
        File file1 = new File(filePath);
        RequestBody body = RequestBody.create(MediaType.parse("multipart/form-data"), new File(filePath));
        MultipartBody.Part file = MultipartBody.Part.createFormData("file", file1.getName(), body);
        Log.i(TAG, "postFileHeader: " + headerMap);
        Observable<ResponseBody> observable = apiService.postFileHeader(url, file, headerMap);
        doObservable(observable, c, callback);
    }

    public void postJson(String url, Class c, String json, NetCallback callback) {
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), json);
        Observable<ResponseBody> observable = apiService.postJson(url, body);
        doObservable(observable, c, callback);
    }

    public void putJson(String url, Class c, String json, NetCallback callback) {
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), json);
        Observable<ResponseBody> observable = apiService.putJson(url, body);
        doObservable(observable, c, callback);
    }

    public void postHeader(String url, Class c, Map<String, String> headerMap, NetCallback callback) {
        Observable<ResponseBody> observable = apiService.postHeader(url, headerMap);
        doObservable(observable, c, callback);
    }

    public void postJsonHeader(String url, Class c, String json, Map<String, String> headerMap, NetCallback callback) {
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), json);
        Observable<ResponseBody> observable = apiService.postJsonHeader(url, body, headerMap);
        doObservable(observable, c, callback);
    }

    public void postPartForm(String url, Class c, Map<String, RequestBody> partMap, Map<String, String> fieldMap, NetCallback callback) {
        for (Map.Entry<String, String> entry : fieldMap.entrySet()) {
            String mapKey = entry.getKey();
            String mapValue = entry.getValue();
            Log.e(TAG, "postPartForm: " + mapKey);
            if (!TextUtils.isEmpty(mapValue)) {
                RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), mapValue);
                partMap.put(mapKey, requestBody);
            }

        }
        Observable<ResponseBody> observable = apiService.postPart(url, partMap);
        doObservable(observable, c, callback);
    }

    public void delete(String url, Class c, Map<String, Object> queryMap, NetCallback callback) {
        doObservable(apiService.delete(url, queryMap), c, callback);
    }

    //解析数据
    private void doObservable(Observable<ResponseBody> observable, Class c, NetCallback callback) {
        // 解决RxObserval的内存泄露
        Disposable disposable = observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(responseBody -> {
                    String json = responseBody.string();
                    try {
                        JSONObject jsonObject = JSON.parseObject(json);
                        if (jsonObject.getIntValue("errorCode") == 401) {
                            //token过期，退出登录
                            Activity activity = ActivityUtils.getTopActivity();
                            if (alertDialog == null) {
                                alertDialog = new AlertDialog.Builder(activity)
                                        .setTitle("温馨提示")
                                        .setMessage("您的登录已失效，是否重新登录？")
                                        .setPositiveButton("确定", (dialog, which) -> {
                                            // 确定按钮点击事件
                                            dialog.dismiss();
                                        })
                                        .setNegativeButton("取消", (dialog, which) -> {
                                            dialog.dismiss();
                                            alertDialog = null;
                                        })
                                        .create();
                                alertDialog.show();
                            }
                        } else {
                            //json串转为各个接口bean类
                            Object o = JSON.parseObject(json, c);
                            callback.netSuccess(o);
                        }
                    } catch (Exception e) {
                        Log.e(TAG, "doObservable: ", e);
                        callback.netError(e);
                    }
                }, callback::netError);
        callback.netDisposable(disposable);
    }

    public interface NetCallback {
        void netSuccess(Object o);

        void netError(Throwable t);

        void netDisposable(Disposable disposable);
    }

    private static class SingleHolder {
        private static final NetUtils UTIL = new NetUtils();
    }
}