package com.iconmobile.sample.feature.products.presentation.products

import androidx.lifecycle.viewModelScope
import com.iconmobile.sample.feature.products.domain.model.Product
import com.iconmobile.sample.feature.products.domain.usecase.DeleteProductUseCase
import com.iconmobile.sample.feature.products.domain.usecase.LoadProductListUseCase
import com.iconmobile.sample.library.base.domain.DomainResultWrapper
import com.iconmobile.sample.library.base.presentation.NetworkState
import com.iconmobile.sample.library.base.presentation.Status
import com.iconmobile.sample.library.base.presentation.viewmodel.BaseAction
import com.iconmobile.sample.library.base.presentation.viewmodel.BaseViewModel
import com.iconmobile.sample.library.base.presentation.viewmodel.BaseViewState
import kotlinx.coroutines.launch

internal class ProductViewModel(
    private val getProductUseCase: LoadProductListUseCase,
    private val deleteProductUseCase: DeleteProductUseCase
) : BaseViewModel<ProductViewModel.ViewState, ProductViewModel.Action>(ViewState()) {

    fun loadProducts() {
        loadData()
    }

    override fun onLoadData() {
        getProductList()
    }

    override fun onReduceState(viewAction: Action): ViewState {
        val action = viewAction as Action.ProductsRequestState

        return when (action.networkState.status) {
            Status.SUCCESS -> state.copy(
                isLoading = false,
                isError = false,
                isNetworkError = false,
                errorMessage = null,
                products = action.productModel!!
            )
            Status.RUNNING -> state.copy(
                isLoading = true,
                isError = false,
                isNetworkError = false,
                errorMessage = null,
                products = arrayListOf()
            )
            Status.FAILED -> state.copy(
                isLoading = false,
                isError = true,
                isNetworkError = false,
                errorMessage = action.networkState.msg,
                products = arrayListOf()
            )
            Status.NETWORK_FAILED -> state.copy(
                isLoading = false,
                isError = false,
                isNetworkError = true,
                errorMessage = action.networkState.msg,
                products = arrayListOf()
            )
            else -> state.copy(
                isLoading = false,
                isError = false,
                isNetworkError = false,
                errorMessage = null,
                products = arrayListOf()
            )
        }
    }

    private fun getProductList() {
        sendAction(Action.ProductsRequestState(NetworkState.LOADING))
        viewModelScope.launch {
            getProductUseCase.execute()
                .also {
                    when (it) {
                        is DomainResultWrapper.NetworkError -> sendAction(
                            Action.ProductsRequestState(
                                NetworkState.networkError(it.message)
                            )
                        )
                        is DomainResultWrapper.GenericError -> sendAction(
                            Action.ProductsRequestState(
                                NetworkState.error(it.error?.message)
                            )
                        )
                        is DomainResultWrapper.Success -> {
                            if (it.value.isNotEmpty()) {
                                sendAction(
                                    Action.ProductsRequestState(
                                        NetworkState.LOADED,
                                        arrayListOf<Product>().apply { addAll(it.value.reversed()) }
                                    )
                                )
                            } else {
                                sendAction(
                                    Action.ProductsRequestState(NetworkState.error(null))
                                )
                            }
                        }
                    }

                }
        }
    }

    internal fun deleteProduct(id: String) {
        sendAction(Action.ProductsRequestState(NetworkState.LOADING, state.products))
        viewModelScope.launch {
            deleteProductUseCase.execute(id)
                .also {
                    when (it) {
                        is DomainResultWrapper.NetworkError -> sendAction(
                            Action.ProductsRequestState(
                                NetworkState.networkError(it.message)
                            )
                        )
                        is DomainResultWrapper.GenericError -> sendAction(
                            Action.ProductsRequestState(
                                NetworkState.error(it.error?.message)
                            )
                        )
                        is DomainResultWrapper.Success -> {
                            getProductList()
                        }
                    }

                }
        }
    }

    internal data class ViewState(
        val isLoading: Boolean = true,
        val isError: Boolean = false,
        val isNetworkError: Boolean = false,
        val errorMessage: String? = null,
        val products: ArrayList<Product> = arrayListOf()
    ) : BaseViewState

    internal sealed class Action : BaseAction {
        class ProductsRequestState(
            val networkState: NetworkState,
            val productModel: ArrayList<Product>? = arrayListOf()
        ) : Action()
    }
}
