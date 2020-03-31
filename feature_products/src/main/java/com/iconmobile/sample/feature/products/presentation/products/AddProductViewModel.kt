package com.iconmobile.sample.feature.products.presentation.products

import androidx.lifecycle.viewModelScope
import com.iconmobile.sample.feature.products.domain.model.Product
import com.iconmobile.sample.feature.products.domain.usecase.SaveProductUseCase
import com.iconmobile.sample.library.base.domain.DomainResultWrapper
import com.iconmobile.sample.library.base.presentation.NetworkState
import com.iconmobile.sample.library.base.presentation.Status
import com.iconmobile.sample.library.base.presentation.viewmodel.BaseAction
import com.iconmobile.sample.library.base.presentation.viewmodel.BaseViewModel
import com.iconmobile.sample.library.base.presentation.viewmodel.BaseViewState
import kotlinx.coroutines.launch

internal class AddProductViewModel(
    private val saveProductUseCase: SaveProductUseCase
) : BaseViewModel<AddProductViewModel.ViewState, AddProductViewModel.Action>(ViewState()) {

    override fun onReduceState(viewAction: Action): ViewState {

        when (viewAction) {
            is Action.SaveProductRequestState -> {
                return when (viewAction.networkState.status) {
                    Status.SUCCESS -> state.copy(
                        errorMessage = null,
                        isLoaded = true
                    )
                    Status.FAILED -> state.copy(
                        errorMessage = viewAction.networkState.msg,
                        isLoaded = false
                    )
                    Status.NETWORK_FAILED -> state.copy(
                        errorMessage = viewAction.networkState.msg,
                        isLoaded = false
                    )
                    else -> state.copy(
                        errorMessage = null,
                        isLoaded = false
                    )
                }
            }
        }
    }

    fun saveProduct(product: Product) {
        viewModelScope.launch {
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
                                Action.SaveProductRequestState(NetworkState.LOADED)
                            )
                        }
                    }

                }
        }
    }

    internal data class ViewState(
        val errorMessage: String? = null,
        val isLoaded: Boolean = false
    ) : BaseViewState

    internal sealed class Action : BaseAction {

        class SaveProductRequestState(
            val networkState: NetworkState
        ) : Action()
    }
}
