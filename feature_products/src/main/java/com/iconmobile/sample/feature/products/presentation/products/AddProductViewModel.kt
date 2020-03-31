package com.iconmobile.sample.feature.products.presentation.products

import androidx.lifecycle.viewModelScope
import com.iconmobile.sample.feature.products.domain.model.Product
import com.iconmobile.sample.feature.products.domain.usecase.LoadProductUseCase
import com.iconmobile.sample.feature.products.domain.usecase.SaveProductUseCase
import com.iconmobile.sample.feature.products.domain.usecase.UpdateProductUseCase
import com.iconmobile.sample.library.base.domain.DomainResultWrapper
import com.iconmobile.sample.library.base.presentation.NetworkState
import com.iconmobile.sample.library.base.presentation.Status
import com.iconmobile.sample.library.base.presentation.viewmodel.BaseAction
import com.iconmobile.sample.library.base.presentation.viewmodel.BaseViewModel
import com.iconmobile.sample.library.base.presentation.viewmodel.BaseViewState
import kotlinx.coroutines.launch

internal class AddProductViewModel(
    private val saveProductUseCase: SaveProductUseCase,
    private val loadProductUseCase: LoadProductUseCase,
    private val updateProductUseCase: UpdateProductUseCase,
    private val args: AddProductFragmentArgs
) : BaseViewModel<AddProductViewModel.ViewState, AddProductViewModel.Action>(ViewState()) {

    init {
        loadProduct()
    }

    private fun loadProduct() {
        if (args.productId != "") {
            viewModelScope.launch {
                loadProductUseCase.execute(args.productId).also {
                    when (it) {
                        is DomainResultWrapper.NetworkError -> sendAction(
                            Action.SaveProductRequestState(
                                NetworkState.networkError(it.message)
                            )
                        )
                        is DomainResultWrapper.GenericError -> sendAction(
                            Action.SaveProductRequestState(
                                NetworkState.error(it.error?.message)
                            )
                        )
                        is DomainResultWrapper.Success -> {
                            sendAction(
                                Action.SaveProductRequestState(NetworkState.LOADED, it.value)
                            )
                        }
                    }
                }
            }
        }
    }

    override fun onReduceState(viewAction: Action): ViewState {
        when (viewAction) {
            is Action.SaveProductRequestState -> {
                return when (viewAction.networkState.status) {
                    Status.RUNNING -> state.copy(
                        isLoading = true,
                        errorMessage = null,
                        isLoaded = false,
                        product = null,
                        successMessage = null
                    )
                    Status.SUCCESS -> state.copy(
                        isLoading = false,
                        errorMessage = null,
                        isLoaded = true,
                        product = viewAction.productModel,
                        successMessage = viewAction.successMessage
                    )
                    Status.FAILED -> state.copy(
                        isLoading = false,
                        errorMessage = viewAction.networkState.msg,
                        isLoaded = false,
                        product = null,
                        successMessage = null
                    )
                    Status.NETWORK_FAILED -> state.copy(
                        isLoading = false,
                        errorMessage = viewAction.networkState.msg,
                        isLoaded = false,
                        product = null,
                        successMessage = null
                    )
                    else -> state.copy(
                        isLoading = false,
                        errorMessage = null,
                        isLoaded = false,
                        product = null,
                        successMessage = null
                    )
                }
            }
        }
    }

    fun saveProduct(product: Product) {
        viewModelScope.launch {
            if (args.productId != "") {
                updateProductUseCase.execute(product).also {
                    when (it) {
                        is DomainResultWrapper.NetworkError -> sendAction(
                            Action.SaveProductRequestState(
                                NetworkState.networkError(it.message)
                            )
                        )
                        is DomainResultWrapper.GenericError -> sendAction(
                            Action.SaveProductRequestState(
                                NetworkState.error(it.error?.message)
                            )
                        )
                        is DomainResultWrapper.Success -> {
                            sendAction(
                                Action.SaveProductRequestState(
                                    NetworkState.LOADED,
                                    successMessage = "Product updated successfully"
                                )
                            )
                        }
                    }
                }
            } else {
                saveProductUseCase.execute(product)
                    .also {
                        when (it) {
                            is DomainResultWrapper.NetworkError -> sendAction(
                                Action.SaveProductRequestState(
                                    NetworkState.networkError(it.message)
                                )
                            )
                            is DomainResultWrapper.GenericError -> sendAction(
                                Action.SaveProductRequestState(
                                    NetworkState.error(it.error?.message)
                                )
                            )
                            is DomainResultWrapper.Success -> {
                                sendAction(
                                    Action.SaveProductRequestState(
                                        NetworkState.LOADED,
                                        successMessage = "Product added successfully"
                                    )
                                )
                            }
                        }
                    }
            }
        }
    }

    internal data class ViewState(
        val isLoading: Boolean = false,
        val errorMessage: String? = null,
        val isLoaded: Boolean = false,
        val product: Product? = null,
        val successMessage: String? = null
    ) : BaseViewState

    internal sealed class Action : BaseAction {

        class SaveProductRequestState(
            val networkState: NetworkState,
            val productModel: Product? = null,
            val successMessage: String? = null
        ) : Action()
    }
}
