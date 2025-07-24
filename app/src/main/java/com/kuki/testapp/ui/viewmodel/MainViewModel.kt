package com.kuki.testapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class MainViewModel @AssistedInject constructor(
) : ViewModel() {

    @AssistedFactory
    interface Factory {
        fun create(): MainViewModel
    }
}