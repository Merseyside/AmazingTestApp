package com.merseyside.amazingtestapp.presentation.view.activity.main_activity.view

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.merseyside.amazingtestapp.BR
import com.merseyside.amazingtestapp.R
import com.merseyside.amazingtestapp.databinding.ActivityMainBinding
import com.merseyside.amazingtestapp.domain.Record
import com.merseyside.amazingtestapp.presentation.base.BaseTestActivity
import com.merseyside.amazingtestapp.presentation.di.components.DaggerMainActivityComponent
import com.merseyside.amazingtestapp.presentation.di.modules.MainActivityModule
import com.merseyside.amazingtestapp.presentation.view.activity.main_activity.adapter.RecordsAdapter
import com.merseyside.amazingtestapp.presentation.view.activity.main_activity.model.MainActivityViewModel

class MainActivity : BaseTestActivity<ActivityMainBinding, MainActivityViewModel>() {

    private val TAG = javaClass.simpleName

    private val recordsObserver = Observer<List<Record>> {
        Log.d(TAG, "onObserve")
    }

    private val adapter = RecordsAdapter()

    override fun setBindingVariable(): Int {
        return BR.viewModel
    }

    override fun setLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun performInjection() {
        DaggerMainActivityComponent.builder()
                .appComponent(appComponent)
                .mainActivityModule(getMainActivityModule())
                .build().inject(this)
    }

    private fun getMainActivityModule() : MainActivityModule {
        return MainActivityModule(this)
    }

    override fun clear() {}

    override fun loadingObserver(isLoading: Boolean) {}

    override fun onCreate(savedInstance: Bundle?) {
        super.onCreate(savedInstance)

        init()

        getRecords()
    }

    private fun init() {

        viewDataBinding.recordList.adapter = adapter
    }

    private fun getRecords() {
        val recordList = viewModel.restoreRecords()
        if (recordList.isEmpty()) {
            getPages()
        } else {
            addRecords(recordList)

        }
    }

    private fun getPages() {
        if (!viewModel.recordsLiveData.hasObservers()) {
            viewModel.recordsLiveData.observe(this, recordsObserver)
        }

        viewModel.loadNextPages()
    }

    private fun addRecords(recordList : List<Record>) {
        adapter.add(recordList)
    }
}
