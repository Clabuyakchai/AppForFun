package com.kuki.utils.viewmodel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.savedstate.SavedStateRegistryOwner
import kotlin.reflect.KClass

class Factory<T : ViewModel>(
    savedStateRegistryOwner: SavedStateRegistryOwner,
    private val creator: (SavedStateHandle) -> T,
    defaultArgs: Bundle? = null
) : AbstractSavedStateViewModelFactory(owner = savedStateRegistryOwner, defaultArgs = defaultArgs) {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T = creator(handle) as T
}

inline fun <reified T : ViewModel> ComponentActivity.lazyViewModel(
    noinline creator: (SavedStateHandle) -> T
): Lazy<T> {
    return viewModels<T> { Factory(this, creator) }
}

@Composable
inline fun <reified T : ViewModel> viewModelCompose(
    noinline factory: (SavedStateHandle) -> T
): T = viewModel<T>(
    factory = object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: KClass<T>, extras: CreationExtras): T {
            return factory(extras.createSavedStateHandle()) as T
        }
    }
)