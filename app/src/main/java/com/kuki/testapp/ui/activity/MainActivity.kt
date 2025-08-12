package com.kuki.testapp.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.kuki.di.DependenciesMap
import com.kuki.di.HasDependencies
import com.kuki.testapp.App
import com.kuki.testapp.di.components.DaggerActivityComponent
import com.kuki.testapp.ui.compose.MainScreen
import com.kuki.testapp.ui.viewmodel.MainViewModel
import com.kuki.ui.theme.TestAppTheme
import com.kuki.utils.viewmodel.lazyViewModel
import javax.inject.Inject

class MainActivity : ComponentActivity(), HasDependencies {

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
