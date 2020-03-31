package com.iconmobile.sample.feature.products.presentation.products.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import coil.size.Scale
import coil.transform.RoundedCornersTransformation
import com.iconmobile.sample.feature.products.R
import com.iconmobile.sample.feature.products.domain.model.Product
import com.iconmobile.sample.library.base.presentation.delegate.observer
import com.iconmobile.sample.library.base.presentation.extension.setOnDebouncedClickListener
import kotlinx.android.synthetic.main.fragment_product_item.view.*

internal class ProductAdapter : RecyclerView.Adapter<ProductAdapter.MyViewHolder>() {

    var deleteItem: ((String) -> Unit)? = null

    var products: List<Product> by observer(listOf()) {
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder =
        MyViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_product_item, parent, false)
        )

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(products[position])
    }

    override fun getItemCount(): Int = products.size

    internal inner class MyViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {

        fun bind(product: Product) {
            itemView.productName.text = product.name
            itemView.productBrandName.text = product.brand
            itemView.productPrice.text =
                "${product.price} ${product.currency} "

            itemView.productImageView.load(product.imageURL) {
                crossfade(true)
                error(R.drawable.ic_image)
                transformations(RoundedCornersTransformation(0F))
                scale(Scale.FILL)
            }

            itemView.deleteIcon.setOnDebouncedClickListener {
                deleteItem?.invoke(product.id!!)
            }
        }
    }
}