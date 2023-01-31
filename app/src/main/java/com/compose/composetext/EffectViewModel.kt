package com.compose.composetext

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch


class EffectViewModel : ViewModel() {

    private val _sharedView = MutableLiveData<ScreenEvents?>(null)
    val sharedView: LiveData<ScreenEvents?> get() = _sharedView


    sealed class ScreenEvents {
        data class ShowSnackbar(val message: String) : ScreenEvents()
        data class Navigate(val route: String) : ScreenEvents()
    }
}