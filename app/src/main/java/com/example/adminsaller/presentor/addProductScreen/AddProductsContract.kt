package com.example.adminsaller.presentor.addProductScreen

import com.example.adminsaller.presentor.AddScreen.SellerAddContract
import org.orbitmvi.orbit.ContainerHost

interface AddProductsContract {

    interface ViewModel :
        ContainerHost<AddProductsContract.UiState, AddProductsContract.SideEffect> {
        fun onEventDispatcher(event: AddProductsContract.Event)
    }


    interface Event {

        data class AddProduct(
            val productID: String,
            val productName: String,
            val productCount: Int,
            val productInitialPrice: Int,
            val productSellingPrice: Int,
            val productIsValid: Boolean,
            val productComment: String
        ) : Event
    }

    interface UiState {
        object InitialState : UiState
    }

    interface SideEffect {
        data class ProgressState(val state: Boolean) : SideEffect
        data class ShowToast(val message: String) : SideEffect
    }
}