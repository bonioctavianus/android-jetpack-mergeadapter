package com.bonioctavianus.android.jetpack_mergeadapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.row_item_product.view.*

class ProductAdapter(
    var mItemSelectedListener: ((product: Product) -> Unit)? = null,
    var mItemDeletedListener: ((product: Product, index: Int) -> Unit)? = null
) : RecyclerView.Adapter<ProductViewHolder>() {

    var mItems: List<Product> = emptyList()
        private set

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_item_product, parent, false)

        return ProductViewHolder(view, mItemSelectedListener, mItemDeletedListener)
    }

    override fun getItemCount(): Int = mItems.size

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(mItems[position])
    }

    fun submitList(items: List<Product>) {
        mItems = items
        notifyDataSetChanged()
    }
}

class ProductViewHolder(
    private val view: View,
    private val mItemSelectedListener: ((product: Product) -> Unit)? = null,
    private val mItemDeletedListener: ((product: Product, index: Int) -> Unit)? = null
) : RecyclerView.ViewHolder(view) {

    fun bind(item: Product) {
        view.text_item_product_title.text = item.title
        view.text_item_product_description.text = item.description

        view.setOnClickListener {
            mItemSelectedListener?.invoke(item)
        }

        view.image_delete.setOnClickListener {
            mItemDeletedListener?.invoke(item, bindingAdapterPosition)
        }
    }
}