package com.iconmobile.sample.feature.products.presentation.products

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.iconmobile.sample.feature.products.R
import com.iconmobile.sample.feature.products.domain.model.Product
import com.iconmobile.sample.feature.products.presentation.products.recyclerview.ProductAdapter
import com.iconmobile.sample.library.base.presentation.extension.observe
import com.iconmobile.sample.library.base.presentation.extension.setOnDebouncedClickListener
import com.iconmobile.sample.library.base.presentation.fragment.BaseContainerFragment
import com.pawegio.kandroid.toast
import com.pawegio.kandroid.visible
import kotlinx.android.synthetic.main.fragment_product.*
import org.kodein.di.generic.instance

internal class ProductListFragment : BaseContainerFragment() {

    private val viewModel: ProductViewModel by instance()
    private val productAdapter: ProductAdapter by instance()
    override val layoutResourceId = R.layout.fragment_product

    private var products = listOf<Product>()
    private val stateObserver = Observer<ProductViewModel.ViewState> {
        this.products = it.products
        productAdapter.products = it.products
        progressBar.visible = it.isLoading
        errorTextView.visible = it.isError
        noNetworkView.visible = it.isNetworkError
        retryBtn.visible = it.isNetworkError
        it.errorMessage?.let { toast(it) }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val context = checkNotNull(context)

        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager =
                LinearLayoutManager(context)
            adapter = productAdapter
        }

        retryBtn.setOnDebouncedClickListener {
            viewModel.loadProducts()
        }

        fab.setOnDebouncedClickListener {
            val navDirections =
                ProductListFragmentDirections.actionProductListToAddProduct()
            findNavController().navigate(navDirections)
        }
        viewLifecycleOwner.observe(viewModel.stateLiveData, stateObserver)
        viewModel.loadProducts()
    }
}