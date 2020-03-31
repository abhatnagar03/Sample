package com.iconmobile.sample.feature.products.presentation.products

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.iconmobile.sample.feature.products.R
import com.iconmobile.sample.feature.products.domain.model.Product
import com.iconmobile.sample.feature.products.presentation.products.recyclerview.ProductAdapter
import com.iconmobile.sample.library.base.presentation.extension.observe
import com.iconmobile.sample.library.base.presentation.extension.setOnDebouncedClickListener
import com.iconmobile.sample.library.base.presentation.fragment.BaseContainerFragment
import com.pawegio.kandroid.toast
import com.pawegio.kandroid.visible
import kotlinx.android.synthetic.main.fragment_add_product.*
import org.kodein.di.generic.instance

internal class AddProductFragment : BaseContainerFragment() {

    private val viewModel: AddProductViewModel by instance()

    override val layoutResourceId = R.layout.fragment_add_product

    private val stateObserver = Observer<AddProductViewModel.ViewState> {
        if (it.isLoaded) {
            toast("Product added successfully")
            findNavController().popBackStack()
        }
        it.errorMessage?.let { toast(it) }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        saveButton.setOnDebouncedClickListener {
            viewModel.saveProduct(
                Product(
                    name = name.text.toString(),
                    brand = brandName.text.toString(),
                    description = description.text.toString(),
                    currency = "EUR",
                    price = null,
                    imageURL = null
                )
            )
        }
        viewLifecycleOwner.observe(viewModel.stateLiveData, stateObserver)
    }
}