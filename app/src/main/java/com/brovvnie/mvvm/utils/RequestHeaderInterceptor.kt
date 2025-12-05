package com.brovvnie.mvvm.utils

import okhttp3.Interceptor
import okhttp3.Response

/**
 * 请求头拦截器
 */
class RequestHeaderInterceptor : Interceptor {
    companion object {
        private const val TAG = "RequestHeader"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        request = request.newBuilder().addHeader("token", "token").build()
        return chain.proceed(request)
    }
}