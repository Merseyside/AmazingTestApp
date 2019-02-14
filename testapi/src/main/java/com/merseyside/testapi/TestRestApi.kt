package com.merseyside.testapi

import com.merseyside.testapi.entity.response.RecordResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface TestRestApi {

    @Headers(
        "Accept: application/json",
        "User-Agent: TestApp")
    @GET("/posts")
    fun getPage(@Query("_page") page : Int) : Single<List<RecordResponse>>
}