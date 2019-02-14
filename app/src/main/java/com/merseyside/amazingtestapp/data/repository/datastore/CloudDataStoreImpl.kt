package com.merseyside.amazingtestapp.data.repository.datastore

import com.merseyside.testapi.TestApi
import com.merseyside.testapi.entity.response.RecordResponse
import io.reactivex.Single
import javax.inject.Inject

class CloudDataStoreImpl @Inject constructor(private val testApi: TestApi): CloudDataStore {

    override fun getNextPageData(page: Int): Single<List<RecordResponse>> {
        return testApi.getNextDataPage(page)
    }
}