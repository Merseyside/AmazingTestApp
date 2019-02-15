package com.merseyside.amazingtestapp.presentation.view.activity.main_activity.model

import android.util.Log
import androidx.databinding.Bindable
import com.merseyside.amazingtestapp.domain.Record
import com.upstream.basemvvmimpl.presentation.model.BaseSortedAdapterViewModel

class RecordItemViewModel(private val record : Record) : BaseSortedAdapterViewModel<Record>() {

    private val TAG = javaClass.simpleName

    override fun isContentTheSame(obj: Record): Boolean {
        Log.d(TAG, "here")
        return false
    }

    override fun isItemsTheSame(obj: Record): Boolean {
        return false
    }

    override fun getItem(): Record {
        return record
    }

    override fun compareTo(other: Record): Int {
        return 0
    }

    @Bindable
    fun getTitle() : String {
        return record.title
    }

    @Bindable
    fun getBody() : String {
        return record.body
    }
}