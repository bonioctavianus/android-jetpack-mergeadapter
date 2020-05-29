package com.bonioctavianus.android.jetpack_mergeadapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.row_item_promo.view.*

class PromoAdapter(
    var mItemSelectedListener: ((promo: Promo) -> Unit)? = null
) : RecyclerView.Adapter<PromoViewHolder>() {

    private var mItems: List<Promo> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PromoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_item_promo, parent, false)

        return PromoViewHolder(view, mItemSelectedListener)
    }

    override fun getItemCount(): Int = mItems.size

    override fun onBindViewHolder(holder: PromoViewHolder, position: Int) {
        holder.bind(mItems[position])
    }

    fun submitList(items: List<Promo>) {
        mItems = items
        notifyDataSetChanged()
    }
}

class PromoViewHolder(
    private val view: View,
    private val mItemSelectedListener: ((promo: Promo) -> Unit)? = null
) : RecyclerView.ViewHolder(view) {

    fun bind(item: Promo) {
        view.text_item_promo_title.text = item.title

        view.setOnClickListener {
            mItemSelectedListener?.invoke(item)
        }
    }
}