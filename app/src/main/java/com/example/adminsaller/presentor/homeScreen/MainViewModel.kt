package com.example.adminsaller.presentor.homeScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.adminsaller.domain.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val direction: HomeDirection,
    private val repository: AppRepository
) : ViewModel(), HomeContract.ViewModel {
    override val uiState = MutableStateFlow(HomeContract.UIState())


    init {
        loadData()
    }

    override fun onEventDispatcher(intent: HomeContract.Intent) {
        when (intent) {

            HomeContract.Intent.MoveToAddScreen -> {
                viewModelScope.launch {
                    direction.moveToAddScreen()
                }
            }

            is HomeContract.Intent.DeleteSeller -> {
                repository.deleteSeller(intent.sellerData).onEach {
                    repository.getSellers()
                        .onCompletion { uiState.update { it.copy(isLoading = false) } }
                        .onStart { uiState.update { it.copy(isLoading = true) } }
                        .onEach {
                            it.onSuccess { ls ->
                                uiState.update { it.copy(ls) }
                            }
                        }.launchIn(viewModelScope)
                }.launchIn(viewModelScope)
            }
        }
    }

    private fun loadData() {
        repository.getSellers()
            .onCompletion { uiState.update { it.copy(isLoading = false) } }
            .onStart { uiState.update { it.copy(isLoading = true) } }
            .onEach {
                it.onSuccess { ls ->
                    uiState.update { it.copy(ls) }
                }
            }.launchIn(viewModelScope)
    }

}