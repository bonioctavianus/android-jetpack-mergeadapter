package com.bonioctavianus.android.jetpack_mergeadapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.row_item_error.view.*

class ErrorAdapter(
    var mRetrySelectedListener: (() -> Unit)? = null
) : RecyclerView.Adapter<ErrorViewHolder>() {

    private var mHasError: Boolean = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ErrorViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_item_error, parent, false)

        return ErrorViewHolder(view, mRetrySelectedListener)
    }

    override fun getItemCount(): Int {
        return if (mHasError) 1 else 0
    }

    override fun onBindViewHolder(holder: ErrorViewHolder, position: Int) {
        holder.bind()
    }

    fun submitState(hasError: Boolean) {
        mHasError = hasError
        notifyDataSetChanged()
    }
}

class ErrorViewHolder(
    private val view: View,
    private val mRetrySelectedListener: (() -> Unit)? = null
) : RecyclerView.ViewHolder(view) {

    fun bind() {
        view.button_retry.setOnClickListener {
            mRetrySelectedListener?.invoke()
        }
    }
}