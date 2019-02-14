package com.merseyside.amazingtestapp.presentation.view.activity.main_activity.model

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.merseyside.amazingtestapp.domain.Record
import com.merseyside.amazingtestapp.domain.interactors.GetDataPageInteractor
import com.merseyside.amazingtestapp.presentation.base.BaseTestViewModel
import com.upstream.basemvvmimpl.domain.interactor.DefaultSingleObserver

class MainActivityViewModel(private val getDataPageUseCase : GetDataPageInteractor) : BaseTestViewModel() {

    private val TAG = javaClass.canonicalName
    private val INITIAL_PAGES_COUNT = 3

    private var currentPageIndex : Int = 0

    private val records : List<Record> = ArrayList()

    val recordsLiveData = MutableLiveData<List<Record>>()

    override fun dispose() {
        getDataPageUseCase.dispose()
    }

    override fun clearDisposables() {
        getDataPageUseCase.clear()
    }

    override fun updateLanguage(context: Context) {}

    fun restoreRecords() : List<Record> {
        if (!recordsLiveData.value?.isEmpty()!!) {
            recordsLiveData.value = null
        }

        return records
    }

    fun loadNextPages() {
        if (currentPageIndex == 0) {
            getDataPageUseCase.execute(RecordsObserver(),
                    GetDataPageInteractor.Params(currentPageIndex + 1, currentPageIndex + 1 + INITIAL_PAGES_COUNT))
        } else {
            getDataPageUseCase.execute(RecordsObserver(), GetDataPageInteractor.Params(currentPageIndex + 1))
        }
    }

    private inner class RecordsObserver : DefaultSingleObserver<List<Record>>() {

        override fun onSuccess(obj: List<Record>) {
            super.onSuccess(obj)

            currentPageIndex++

            Log.d(TAG, "size = ${obj.size}")

            recordsLiveData.value = obj
        }

        override fun onError(throwable: Throwable) {
            super.onError(throwable)

            showErrorMsg(throwable.message ?: "Unknown error")
        }
    }

}