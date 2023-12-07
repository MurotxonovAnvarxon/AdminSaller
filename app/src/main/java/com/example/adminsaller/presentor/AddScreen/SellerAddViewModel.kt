package com.example.adminsaller.presentor.AddScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.adminsaller.data.remote.request.SellerRequest
import com.example.adminsaller.domain.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class SellerAddViewModel @Inject constructor(
    private val direction: SellerAddDirection,
    private val appRepository: AppRepository
) : SellerAddContract.ViewModel, ViewModel() {

    override fun onEventDispatcher(event: SellerAddContract.Event) = intent {
        when (event) {
            is SellerAddContract.Event.AddSeller -> {
                appRepository.addSeller(
                    SellerRequest(
                        id = event.id,
                        sellerName = event.sellerName,
                        password = event.password
                    )
                )

                    .onStart { postSideEffect(SellerAddContract.SideEffect.ProgressState(true)) }
                    .onCompletion { postSideEffect(SellerAddContract.SideEffect.ProgressState(false)) }
                    .onEach {
                        it.onSuccess {
                            postSideEffect(SellerAddContract.SideEffect.ProgressState(false))
                            postSideEffect(SellerAddContract.SideEffect.ShowToast("Seller muvaffaqiyatli qo'shildi!"))
                            direction.back()
                        }
                    }
                    .launchIn(viewModelScope)
            }
        }
    }

    override val container =
        container<SellerAddContract.UiState, SellerAddContract.SideEffect>(SellerAddContract.UiState.InitialState)
}