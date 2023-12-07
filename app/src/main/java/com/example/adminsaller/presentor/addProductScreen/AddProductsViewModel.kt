package com.example.adminsaller.presentor.addProductScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.adminsaller.data.remote.request.ProductRequest
import com.example.adminsaller.domain.repository.AppRepository
import com.example.adminsaller.presentor.AddScreen.SellerAddContract
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject


@HiltViewModel
class AddProductsViewModel @Inject constructor(
    private val direction: AddProductDirection,
    private val repository: AppRepository
) : ViewModel(), AddProductsContract.ViewModel {
    override fun onEventDispatcher(event: AddProductsContract.Event) = intent {
        when (event) {
            is AddProductsContract.Event.AddProduct -> {
                viewModelScope.launch {
                    repository.addProduct(
                        ProductRequest(
                            productID = event.productID,
                            productName = event.productName,
                            productCount = event.productCount,
                            productInitialPrice = event.productInitialPrice,
                            productSellingPrice = event.productSellingPrice,
                            productIsValid = event.productIsValid,
                            productComment = event.productComment
                        )
                    ).onStart { postSideEffect(AddProductsContract.SideEffect.ProgressState(true)) }
                        .onCompletion {
                            postSideEffect(
                                AddProductsContract.SideEffect.ProgressState(
                                    false
                                )
                            )
                        }
                        .onEach {
                            it.onSuccess {
                                postSideEffect(AddProductsContract.SideEffect.ProgressState(false))
                                postSideEffect(AddProductsContract.SideEffect.ShowToast("Product muvafaqqiyatli qo'shildi"))
                                direction.back()
                            }
                        }.collect()
                }

            }
        }


    }

    override val container = container<AddProductsContract.UiState, AddProductsContract.SideEffect>(
        AddProductsContract.UiState.InitialState
    )

}