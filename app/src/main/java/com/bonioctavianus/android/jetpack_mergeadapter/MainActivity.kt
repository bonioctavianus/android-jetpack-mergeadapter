package com.bonioctavianus.android.jetpack_mergeadapter

import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.MergeAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val mPromos = getPromos()
    private val mProducts = getProducts()
    private val mPromoAdapter = PromoAdapter()
    private val mProductAdapter = ProductAdapter()
    private val mLoadingAdapter = LoadingAdapter()
    private val mErrorAdapter = ErrorAdapter()
    private val mAdapter = MergeAdapter(
        mPromoAdapter,
        mProductAdapter,
        mLoadingAdapter,
        mErrorAdapter
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setPromoAdapterItem()
        setProductAdapterItem()
        setErrorAdapterListener()

        list_item.layoutManager = LinearLayoutManager(this)
        list_item.adapter = mAdapter

        // simulate network request for 3 seconds to show loading indicator
        mLoadingAdapter.submitState(loading = true)

        // then remove the loading indicator
        Handler().postDelayed({
            mLoadingAdapter.submitState(loading = false)

            // after that show an error indicator
            mErrorAdapter.submitState(hasError = true)
        }, 3000)
    }

    private fun setPromoAdapterItem() {
        mPromoAdapter.mItemSelectedListener = { promo ->
            showToast(promo.title)
        }

        mPromoAdapter.submitList(mPromos)
    }

    private fun setProductAdapterItem() {
        mProductAdapter.mItemSelectedListener = { product ->
            showToast(product.title)
        }

        mProductAdapter.mItemDeletedListener = { product, index ->
            removeProduct(product, index)
        }

        mProductAdapter.submitList(mProducts)
    }

    private fun setErrorAdapterListener() {
        mErrorAdapter.mRetrySelectedListener = {
            showToast("Retry Selected")
        }
    }

    private fun removeProduct(product: Product, index: Int) {
        val products = mProductAdapter.mItems.toMutableList()
        products.removeAt(index)
        mProductAdapter.notifyItemRemoved(index)

        showToast("${product.title} removed")
    }

    private fun getProducts(): List<Product> {
        return listOf(
            Product(
                title = "Product 1",
                description = "Description 1"
            ),
            Product(
                title = "Product 2",
                description = "Description 2"
            ),
            Product(
                title = "Product 3",
                description = "Description 3"
            )
        )
    }

    private fun getPromos(): List<Promo> {
        return listOf(
            Promo(title = "Promo 1"),
            Promo(title = "Promo 2")
        )
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
