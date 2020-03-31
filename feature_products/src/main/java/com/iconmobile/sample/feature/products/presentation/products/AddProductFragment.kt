package com.iconmobile.sample.feature.products.presentation.products

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import coil.api.load
import coil.size.Scale
import coil.transform.RoundedCornersTransformation
import com.iconmobile.sample.feature.products.R
import com.iconmobile.sample.feature.products.domain.model.Product
import com.iconmobile.sample.library.base.presentation.delegate.observer
import com.iconmobile.sample.library.base.presentation.extension.observe
import com.iconmobile.sample.library.base.presentation.extension.setOnDebouncedClickListener
import com.iconmobile.sample.library.base.presentation.fragment.BaseContainerFragment
import com.pawegio.kandroid.toast
import kotlinx.android.synthetic.main.fragment_add_product.*
import org.kodein.di.generic.instance

internal class AddProductFragment : BaseContainerFragment() {

    private var product: Product? = null
    private val viewModel: AddProductViewModel by instance()

    override val layoutResourceId = R.layout.fragment_add_product

    private val currencyAdapter by lazy {
        ArrayAdapter(
            context!!,
            android.R.layout.simple_spinner_item,
            listOf("EUR", "GBP", "USD")
        )
    }

    // The image data is currently hard coded with test URLs
    // This can be automated by uploading image to server that returns a URI once upload is successful
    private val imageAdapter by lazy {
        ArrayAdapter(
            context!!,
            android.R.layout.simple_spinner_item,
            listOf(
                "https://homepages.cae.wisc.edu/~ece533/images/airplane.png",
                "https://homepages.cae.wisc.edu/~ece533/images/arctichare.png",
                "https://homepages.cae.wisc.edu/~ece533/images/baboon.png",
                "https://homepages.cae.wisc.edu/~ece533/images/boat.png",
                "https://homepages.cae.wisc.edu/~ece533/images/cat.png"
            )
        )
    }

    private val stateObserver = Observer<AddProductViewModel.ViewState> {
        if (it.isLoaded && it.product == null) {
            it.successMessage?.let { toast(it) }
            findNavController().popBackStack()
        } else {
            it.errorMessage?.let { toast(it) }
            it.product?.let { product ->
                this.product = product
                name.setText(product.name)
                brandName.setText(product.brand)
                description.setText(product.description)
                price.setText(product.price.toString())
                currencySpinner.setSelection(currencyAdapter.getPosition(product.currency))
                imageSpinner.setSelection(imageAdapter.getPosition(product.imageURL))
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        activity!!.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setCurrencyData()
        setImageData()

        saveButton.setOnDebouncedClickListener {
            viewModel.saveProduct(
                Product(
                    id = product?.id,
                    name = name.text.toString(),
                    brand = brandName.text.toString(),
                    description = description.text.toString(),
                    currency = currencySpinner.selectedItem.toString(),
                    price = with(price.text.toString()) {
                        if (isNotEmpty()) toDouble() else null
                    },
                    imageURL = imageSpinner.selectedItem.toString()
                )
            )
        }
        viewLifecycleOwner.observe(viewModel.stateLiveData, stateObserver)
    }

    private fun setCurrencyData() {

        currencyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        currencySpinner.adapter = currencyAdapter
    }

    private fun setImageData() {
        imageAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        imageSpinner.adapter = imageAdapter

        imageSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
                // do nothing
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                url = imageAdapter.getItem(position)
            }
        }
    }

    private var url by observer<String?>(null) {
        it?.let {
            selectedImage.load(
                it
            ) {
                crossfade(true)
                error(R.drawable.ic_image)
                transformations(RoundedCornersTransformation(0F))
                scale(Scale.FILL)
            }
        }
    }
}