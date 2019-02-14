package com.merseyside.amazingtestapp.data.repository

import com.merseyside.amazingtestapp.data.entity.mapper.RecordDataMapper
import com.merseyside.amazingtestapp.data.repository.datastore.DataStoreFactory
import com.merseyside.amazingtestapp.domain.Record
import com.merseyside.amazingtestapp.domain.repository.DataRepository
import io.reactivex.Single
import java.lang.IllegalArgumentException
import javax.inject.Inject

class DataRepositoryImpl @Inject constructor(
        private val dataStoreFactory: DataStoreFactory,
        private val recordDataMapper: RecordDataMapper) : DataRepository {


    override fun loadPages(startPage: Int, endPage : Int): Single<List<Record>> {
        if (startPage <= endPage) {

            return dataStoreFactory.loadPages(startPage, endPage)
                    .map { responseList ->
                        return@map recordDataMapper.transform(responseList)
                    }
        } else {
            return Single.error(IllegalArgumentException("startPage must be less than endPage"))
        }
    }
}