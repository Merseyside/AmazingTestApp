package com.merseyside.amazingtestapp.data.repository.datastore

import com.merseyside.testapi.entity.response.RecordResponse
import io.reactivex.Single

interface CloudDataStore {

    fun loadPage(page : Int) : Single<List<RecordResponse>>
}