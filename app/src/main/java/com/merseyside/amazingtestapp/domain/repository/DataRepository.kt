package com.merseyside.amazingtestapp.domain.repository

import com.merseyside.amazingtestapp.domain.Record
import io.reactivex.Single

interface DataRepository {

    fun loadPages(startPage : Int, endPage : Int) : Single<List<Record>>
}