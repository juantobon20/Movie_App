package com.appinc.movieapp.util

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<BINDING : ViewDataBinding, T>(
    var data: List<T> = emptyList()
) : RecyclerView.Adapter<BaseViewHolder<BINDING>>() {

    @get:LayoutRes
    abstract val layoutId: Int

    abstract fun bind(binding: BINDING, item: T)

    @SuppressLint("NotifyDataSetChanged")
    fun loadData(list: List<T>) {
        this.data = list.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<BINDING> {
        val binder = DataBindingUtil.inflate<BINDING>(
            LayoutInflater.from(parent.context),
            layoutId,
            parent,
            false
        )

        return BaseViewHolder(binder)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<BINDING>, position: Int) {
        bind(holder.binder, data[position])
    }

    override fun getItemCount(): Int = data.size
}

class BaseViewHolder<BINDING : ViewDataBinding>(val binder: BINDING) :
    RecyclerView.ViewHolder(binder.root)

interface IOnClickListener<T> {
    fun onClick(t: T)
}
