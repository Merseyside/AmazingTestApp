package com.merseyside.amazingtestapp.presentation.view.activity.main_activity.adapter

import com.merseyside.amazingtestapp.BR
import com.merseyside.amazingtestapp.R
import com.merseyside.amazingtestapp.domain.Record
import com.merseyside.amazingtestapp.presentation.view.activity.main_activity.model.RecordItemViewModel
import com.upstream.basemvvmimpl.presentation.adapter.BaseSortedAdapter
import javax.inject.Inject

class RecordsAdapter @Inject constructor() : BaseSortedAdapter<Record, RecordItemViewModel>() {

    override fun getLayoutIdForPosition(position: Int): Int {
        return R.layout.record_view
    }

    override fun createItemViewModel(obj: Record): RecordItemViewModel {
        return RecordItemViewModel(obj)
    }

    override fun getBindingVariable(): Int {
        return BR.obj
    }

}