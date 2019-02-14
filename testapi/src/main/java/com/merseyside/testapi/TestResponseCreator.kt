package com.merseyside.testapi

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.merseyside.testapi.entity.response.RecordResponse
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class TestResponseCreator(context: Context) {

    private val TAG = javaClass.canonicalName

    private var testRest : TestRestApi

    init {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient : OkHttpClient = OkHttpClient.Builder()
                .addInterceptor(logging)
                .connectTimeout(8, TimeUnit.SECONDS)
                .writeTimeout(8, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .build()

        val retrofit = Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(context.getString(R.string.base_url))
                .addConverterFactory(GsonConverterFactory.create(createConverterFactory()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

        testRest = retrofit.create(TestRestApi::class.java)
    }

    private fun createConverterFactory(): Gson {
        return GsonBuilder()
                .setLenient()
                .create()
    }

    fun getNextDataPage(page : Int) : Single<List<RecordResponse>> {

        return testRest.getPage(page)

    }
}