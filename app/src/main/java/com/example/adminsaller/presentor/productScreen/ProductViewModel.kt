package com.example.adminsaller.presentor.productScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.adminsaller.data.model.ProductsData
import com.example.adminsaller.data.repository.AppRepositoryImpl
import com.example.adminsaller.domain.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ProductViewModel @Inject constructor(
    private val direction: ProductDirection,
    private val repository: AppRepository
) : ViewModel(), ProductContract.ViewModel {
    override val uiState = MutableStateFlow(ProductContract.UIState())

    init {
        getAllProducts()
    }

    override fun onEventDispatchers(intent: ProductContract.Intent) {
        when (intent) {
            ProductContract.Intent.MoveToAddProductsScreen -> {
                viewModelScope.launch {
                    direction.moveToAddProductScreen()
                }
            }

            ProductContract.Intent.MoveToDraft->{
                viewModelScope.launch {
                    direction.moveToDraft()
                }
            }
            is ProductContract.Intent.DeleteProduct -> {

                viewModelScope.launch {
                    repository.deleteProduct(intent.productsData).onEach {
                        getAllProducts()
                    }.launchIn(viewModelScope)
                }
            }

            is ProductContract.Intent.EditProduct -> {
                viewModelScope.launch {
                    repository.editProducts(
                        ProductsData(
                            intent.productID,
                            intent.productName,
                            intent.productCount,
                            intent.productInitialPrice,
                            intent.productSellingPrice,
                            intent.productIsValid,
                            intent.productComment
                        )
                    ).launchIn(viewModelScope)
                }

            }
        }
    }


    fun getAllProducts() {
        repository.getAllProducts()
            .onCompletion { uiState.update { it.copy(isLoading = false) } }
            .onStart { uiState.update { it.copy(isLoading = true) } }
            .onEach { list ->
                uiState.update { it.copy(list) }
            }.launchIn(viewModelScope)
    }
}