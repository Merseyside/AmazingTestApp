package com.upstream.basemvvmimpl.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.upstream.basemvvmimpl.presentation.model.BaseAdapterViewModel
import com.upstream.basemvvmimpl.presentation.view.BaseViewHolder

abstract class BasePagedAdapter<M, T : BaseAdapterViewModel<M>>(diffUtilCallback : DiffUtil.ItemCallback<M>)
    : PagedListAdapter<M, BaseViewHolder>(diffUtilCallback) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        val binding: ViewDataBinding = DataBindingUtil.inflate(layoutInflater, viewType, parent, false)

        return BaseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val obj = getItem(position)
        //obj.setAdapterListener(listener)
        holder.bind(getBindingVariable(), obj!!)
    }

    protected abstract fun getBindingVariable(): Int
}