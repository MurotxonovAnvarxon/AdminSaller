package com.example.adminsaller.presentor.AddScreen

import org.orbitmvi.orbit.ContainerHost


interface SellerAddContract {
    interface ViewModel : ContainerHost<UiState, SideEffect> {
        fun onEventDispatcher(event: Event)
    }

    interface Event {
        data class AddUser(val sellerName: String, val password: String) : Event
    }

    interface UiState {
        object InitialState : UiState
    }

    interface SideEffect {
        data class ProgressState(val state: Boolean) : SideEffect
        data class ShowToast(val message: String) : SideEffect
    }
}