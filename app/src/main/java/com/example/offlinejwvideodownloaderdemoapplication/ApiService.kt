package com.example.offlinejwvideodownloaderdemoapplication

import com.example.offlinejwvideodownloaderdemoapplication.model.JwPlayerApiData
import com.example.offlinejwvideodownloaderdemoapplication.model.JwPlayerUrlData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import retrofit2.http.Url

interface ApiService {

    @Headers("Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxOTgyOCIsInJvbGUiOiJVU0VSIiwiSXAtQWRkcmVzcyI6IjEyNy4wLjAuMSIsIlVzZXItQWdlbnQiOiJBbWF6b24gQ2xvdWRGcm9udCIsImlhdCI6MTY4MTkwODA3NCwicGxhdGZvcm0iOiJBUFAiLCJpc3N1ZXIiOiJhZGRhMjQ3LmNvbSIsImV4cCI6MTcxMzQ0NDA3NH0.loIziL387cv65RNnvm8NksrcxSY9bLUbtEeMoWcPOifxrRt5hwMwsWUKFouE28qJ2cikKLahOQ41UDnIANPLJg")
    @GET
    fun getJwPlayerUrl(
        @Url url: String?,
        @Query("course_id") cId: Int,
        @Query("media_id") mediaId: String?,
        @Query("offline") offline: Boolean?
    ): Call<JwPlayerUrlData>

    @GET
    fun getJwPlayerData(@Url url: String): Call<JwPlayerApiData>
}