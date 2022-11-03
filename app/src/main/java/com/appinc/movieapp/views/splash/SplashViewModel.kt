package com.appinc.movieapp.views.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SplashViewModel : ViewModel() {

    private val _success = MutableLiveData<Boolean>()
    val success: LiveData<Boolean> get() = _success

    init {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                delay(2000)
            }
            this@SplashViewModel._success.postValue(true)
        }
    }
}
