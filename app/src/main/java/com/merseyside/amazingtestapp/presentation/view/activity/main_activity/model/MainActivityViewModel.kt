package com.merseyside.amazingtestapp.presentation.view.activity.main_activity.model

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.merseyside.amazingtestapp.domain.Record
import com.merseyside.amazingtestapp.domain.interactors.GetDataPageInteractor
import com.merseyside.amazingtestapp.presentation.base.BaseTestViewModel
import com.upstream.basemvvmimpl.domain.interactor.DefaultSingleObserver

class MainActivityViewModel(private val getDataPageUseCase : GetDataPageInteractor) : BaseTestViewModel() {

    private val TAG = javaClass.simpleName
    private val INITIAL_PAGES_COUNT = 2

    private var currentPageIndex = 0
    private var loadingPageIndex = 0

    private var isLoading = false

    private val records : MutableList<Record> = ArrayList()

    val recordsLiveData = MutableLiveData<List<Record>>()

    override fun dispose() {
        getDataPageUseCase.dispose()
    }

    override fun clearDisposables() {
        getDataPageUseCase.clear()
    }

    override fun updateLanguage(context: Context) {}

    fun restoreRecords() : List<Record> {
        if (recordsLiveData.value != null) {
            recordsLiveData.value = null
        }

        return records
    }

    fun loadNextPages() {
        if (!isLoading) {

            isLoading = true

            if (currentPageIndex == 0) {
                showProgress()

                loadingPageIndex = currentPageIndex + INITIAL_PAGES_COUNT

                getDataPageUseCase.execute(RecordsObserver(),
                        GetDataPageInteractor.Params(currentPageIndex + 1, loadingPageIndex))
            } else {
                loadingPageIndex = currentPageIndex + 1
                getDataPageUseCase.execute(RecordsObserver(), GetDataPageInteractor.Params(loadingPageIndex))
            }
        }
    }

    private inner class RecordsObserver : DefaultSingleObserver<List<Record>>() {

        override fun onSuccess(obj: List<Record>) {
            super.onSuccess(obj)

            hideProgress()

            currentPageIndex = loadingPageIndex

            Log.d(TAG, "size = ${obj.size}")

            recordsLiveData.value = obj
            records.addAll(obj)

            isLoading = false
        }

        override fun onError(throwable: Throwable) {
            super.onError(throwable)

            showErrorMsg(throwable.message ?: "Unknown error")
        }
    }

}