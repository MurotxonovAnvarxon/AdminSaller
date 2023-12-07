package com.example.adminsaller.presentor.productScreen

import com.example.adminsaller.data.model.ProductsData
import kotlinx.coroutines.flow.StateFlow

interface ProductContract {

    interface ViewModel {
        val uiState: StateFlow<UIState>
        fun onEventDispatchers(intent: Intent)

    }

    interface Intent {
        object MoveToHomeScreen : Intent
        object MoveToAddProductsScreen : Intent
        data class DeleteProduct(val productsData: ProductsData) : Intent

        data class EditProduct(
            val id: String,
            val productName: String,
            val productCount: Int,
            val productInitialPrice: Int,
            val productSellingPrice: Int,
            val productIsValid: Boolean,
            val productComment: String
        ):Intent

    }

    data class UIState(
        val productList: List<ProductsData> = emptyList(),
        val isLoading: Boolean = false
    )


}