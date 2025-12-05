package com.brovvnie.mvvm.api

import io.reactivex.rxjava3.core.Observable
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.PartMap
import retrofit2.http.QueryMap
import retrofit2.http.Url

interface ApiService {
    @GET
    fun get(@Url s: String?): Observable<ResponseBody>?

    @GET
    fun getQuery(
        @Url s: String?,
        @QueryMap queryMap: MutableMap<String?, Any?>?
    ): Observable<ResponseBody>?

    @GET
    fun getQueryHeader(
        @Url s: String?,
        @QueryMap queryMap: MutableMap<String?, String?>?,
        @HeaderMap headerMap: MutableMap<String?, String?>?
    ): Observable<ResponseBody>?

    @POST
    fun post(@Url url: String?): Observable<ResponseBody>?

    @POST
    fun postHeader(
        @Url s: String?,
        @HeaderMap headerMap: MutableMap<String?, String?>?
    ): Observable<ResponseBody>?

    @POST
    @FormUrlEncoded
    fun postForm(
        @Url s: String?,
        @FieldMap fieldMap: MutableMap<String?, Any?>?
    ): Observable<ResponseBody>?

    @POST
    @FormUrlEncoded
    fun postFormHeader(
        @Url s: String?,
        @FieldMap fieldMap: MutableMap<String?, String?>?,
        @HeaderMap headerMap: MutableMap<String?, String?>?
    ): Observable<ResponseBody>?

    @POST
    fun postJson(@Url url: String?, @Body body: RequestBody?): Observable<ResponseBody>?

    @POST
    fun postJsonHeader(
        @Url url: String?,
        @Body body: RequestBody?,
        @HeaderMap headerMap: MutableMap<String?, String?>?
    ): Observable<ResponseBody>?

    @POST
    @Multipart
    fun postFileHeader(
        @Url url: String?,
        @Part file: MultipartBody.Part?,
        @HeaderMap headerMap: MutableMap<String?, String?>?
    ): Observable<ResponseBody>?

    @POST
    @Multipart
    fun postPart(
        @Url url: String?,
        @PartMap partMap: MutableMap<String?, RequestBody?>?
    ): Observable<ResponseBody>?

    @PUT
    fun putJson(@Url url: String?, @Body body: RequestBody?): Observable<ResponseBody>?

    @DELETE
    fun delete(
        @Url url: String?,
        @QueryMap queryMap: MutableMap<String?, Any?>?
    ): Observable<ResponseBody>?
}
