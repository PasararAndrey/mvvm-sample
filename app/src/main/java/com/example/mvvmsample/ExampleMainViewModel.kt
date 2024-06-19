package com.example.mvvmsample

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExampleMainViewModel @Inject constructor() : ViewModel() {
    private val _isSplashShouldBeRemoved = MutableStateFlow(false)
    val isSplashShouldBeRemoved: StateFlow<Boolean> = _isSplashShouldBeRemoved.asStateFlow()

    // ONLY FOR DEMONSTRATION
    init {
        viewModelScope.launch {
            @Suppress("MagicNumber")
            delay(3000)
            _isSplashShouldBeRemoved.emit(true)
        }
    }
}
