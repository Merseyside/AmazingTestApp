package com.merseyside.testapi

import android.content.Context
import android.util.Log
import com.merseyside.testapi.entity.response.RecordResponse
import io.reactivex.Single
import retrofit2.HttpException

class TestApi(context : Context) {

    val TAG : String? = javaClass.canonicalName

    private val creator : TestResponseCreator = TestResponseCreator(context)

    fun getNextDataPage(page : Int) : Single<List<RecordResponse>> {
        return creator.getNextDataPage(page)
                .doOnError { throwable ->
                    if (throwable is HttpException) {
                        Log.e(TAG, "code = ${throwable.code()} message = ${throwable.message()})")
                    }

                }
    }

}