package com.example.adminsaller.presentor.homeScreen

import com.example.adminsaller.data.model.SellerData
import kotlinx.coroutines.flow.StateFlow

interface HomeContract {
    interface ViewModel {
        val uiState: StateFlow<UIState>
        fun onEventDispatcher(intent: Intent)
    }

    interface Intent {
        object MoveToAddScreen : Intent
        object MoveToProductsScreen : Intent
        data class DeleteSeller(val sellerData: SellerData) : Intent
        data class EditSeller(val id: String, val name: String, val password: String) : Intent
    }

    data class UIState(
        val sellerList: List<SellerData> = emptyList(),
        var isLoading: Boolean = false
    )


}