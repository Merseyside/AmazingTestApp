package com.merseyside.amazingtestapp.data.repository.datastore

import com.merseyside.amazingtestapp.data.exception.NoNetworkConnectionException
import com.merseyside.amazingtestapp.utils.Utils
import com.merseyside.testapi.entity.response.RecordResponse
import io.reactivex.Single
import io.reactivex.SingleSource
import javax.inject.Inject

class DataStoreFactory @Inject constructor(private val cloudDataStore: CloudDataStore) {

    fun loadPages(startPage : Int, endPage : Int) : Single<List<RecordResponse>> {

        if (Utils.isNetworkConnected()) {
            val singleList = ArrayList<SingleSource<List<RecordResponse>>>()

            for (i in startPage..endPage) {
                singleList.add(cloudDataStore.loadPage(i))
            }

            return Single.zip(singleList) { it ->
                val responses = it as List<List<RecordResponse>>
                val resultList = ArrayList<RecordResponse>()

                responses.forEach { response ->
                    resultList.addAll(response)
                }

                resultList
            }
        } else {
            return Single.error(NoNetworkConnectionException())
        }
    }
}