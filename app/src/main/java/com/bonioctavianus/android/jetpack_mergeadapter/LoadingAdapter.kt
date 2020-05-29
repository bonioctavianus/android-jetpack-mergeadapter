package com.bonioctavianus.android.jetpack_mergeadapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class LoadingAdapter : RecyclerView.Adapter<LoadingViewHolder>() {

    private var mLoading: Boolean = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LoadingViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_item_loading, parent, false)

        return LoadingViewHolder(view)
    }

    override fun getItemCount(): Int {
        return if (mLoading) 1 else 0
    }

    override fun onBindViewHolder(holder: LoadingViewHolder, position: Int) = Unit

    fun submitState(loading: Boolean) {
        mLoading = loading
        notifyDataSetChanged()
    }
}

class LoadingViewHolder(private val view: View) : RecyclerView.ViewHolder(view)