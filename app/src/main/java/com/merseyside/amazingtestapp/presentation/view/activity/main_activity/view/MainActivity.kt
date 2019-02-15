package com.merseyside.amazingtestapp.presentation.view.activity.main_activity.view

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.merseyside.amazingtestapp.BR
import com.merseyside.amazingtestapp.R
import com.merseyside.amazingtestapp.databinding.ActivityMainBinding
import com.merseyside.amazingtestapp.domain.Record
import com.merseyside.amazingtestapp.presentation.base.BaseTestActivity
import com.merseyside.amazingtestapp.presentation.di.components.DaggerMainActivityComponent
import com.merseyside.amazingtestapp.presentation.di.modules.MainActivityModule
import com.merseyside.amazingtestapp.presentation.view.activity.main_activity.adapter.RecordsAdapter
import com.merseyside.amazingtestapp.presentation.view.activity.main_activity.model.MainActivityViewModel
import com.merseyside.amazingtestapp.utils.RecyclerViewPositionHelper
import com.merseyside.amazingtestapp.utils.WrapContentLinearLayoutManager

class MainActivity : BaseTestActivity<ActivityMainBinding, MainActivityViewModel>() {

    private val TAG = javaClass.simpleName

    private lateinit var recyclerHelper : RecyclerViewPositionHelper

    private val recordsObserver = Observer<List<Record>> {
        if (it != null) {
            addRecords(it)

            addOnScrollListener()
        }
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

        viewDataBinding.recordList.layoutManager = WrapContentLinearLayoutManager(
                this,
                LinearLayoutManager.VERTICAL,
                false
        )

        viewDataBinding.recordList.adapter = adapter
        addOnScrollListener()

        recyclerHelper = RecyclerViewPositionHelper(viewDataBinding.recordList)
    }

    private fun addOnScrollListener() {
        viewDataBinding.recordList.addOnScrollListener(scrollListener)
    }

    private fun removeOnScrollListener() {
        viewDataBinding.recordList.removeOnScrollListener(scrollListener)
    }

    private val scrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            val recyclerHelper = RecyclerViewPositionHelper(viewDataBinding.recordList)
            val lastVisible = recyclerHelper.findLastVisibleItemPosition()

            if (lastVisible > adapter.itemCount - 5) {
                removeOnScrollListener()
                loadNextPages()
            }
        }
    }

    private fun getRecords() {
        val recordList = viewModel.restoreRecords()
        if (recordList.isEmpty()) {
            loadNextPages()
        } else {
            addRecords(recordList)
        }
    }

    private fun loadNextPages() {

        if (!viewModel.recordsLiveData.hasObservers()) {
            viewModel.recordsLiveData.observe(this, recordsObserver)
        }

        viewModel.loadNextPages()
    }

    private fun addRecords(recordList : List<Record>) {
        adapter.add(recordList)
    }
}
