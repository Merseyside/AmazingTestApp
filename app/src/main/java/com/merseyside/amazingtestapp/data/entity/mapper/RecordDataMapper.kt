package com.merseyside.amazingtestapp.data.entity.mapper

import com.merseyside.amazingtestapp.domain.Record
import com.merseyside.testapi.entity.response.RecordResponse
import javax.inject.Inject

class RecordDataMapper @Inject constructor() {

    fun transform(recordResponseList : List<RecordResponse>) : List<Record> {

        val recordList = ArrayList<Record>()

        recordResponseList.forEach {
            val record = Record(it.title, it.body)

            recordList.add(record)
        }

        return recordList
    }
}