package com.kuki.testapp.ui.activity

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.fragment.app.FragmentActivity
import androidx.navigation3.runtime.NavKey
import com.kuki.common.DependenciesMap
import com.kuki.common.HasDependencies
import com.kuki.presentation.theme.TestAppTheme
import com.kuki.presentation.viewmodel.lazyViewModel
import com.kuki.testapp.App
import com.kuki.testapp.di.components.DaggerActivityComponent
import com.kuki.testapp.ui.compose.MainScreen
import com.kuki.testapp.ui.viewmodel.MainViewModel
import kotlinx.serialization.Serializable
import javax.inject.Inject

class MainActivity : FragmentActivity(), HasDependencies {

    @Inject
    lateinit var factory: MainViewModel.Factory
    private val viewModel: MainViewModel by lazyViewModel {
        factory.create()
    }

    @Inject
    override lateinit var dependenciesMap: DependenciesMap

    override fun onCreate(savedInstanceState: Bundle?) {

        val appComponent = (applicationContext as App).appComponent
        val component = DaggerActivityComponent.builder()
            .appProvideContract(appComponent)
            .build()
        component.inject(this)

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            TestAppTheme {
                MainScreen()
            }
        }
    }
}

@Serializable
data object ContactsScreenNavModel : NavKey

@Serializable
data class ContactDetailsScreenNavModel(val contactId: String) : NavKey
