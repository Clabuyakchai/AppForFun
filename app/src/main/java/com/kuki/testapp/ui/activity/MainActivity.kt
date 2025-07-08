package com.kuki.testapp.ui.activity

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.fragment.app.FragmentActivity
import com.kuki.contacts.di.ContactsComponent
import com.kuki.contacts.presentation.ContactsScreen
import com.kuki.presentation.theme.TestAppTheme
import com.kuki.presentation.viewmodel.lazyViewModel
import com.kuki.testapp.App
import com.kuki.testapp.di.components.DaggerActivityComponent
import com.kuki.testapp.ui.viewmodel.MainViewModel
import javax.inject.Inject

class MainActivity : FragmentActivity() {

    @Inject
    lateinit var factory: MainViewModel.Factory
    private val viewModel: MainViewModel by lazyViewModel {
        factory.create()
    }

    @Inject
    lateinit var contactsComponent: ContactsComponent

    override fun onCreate(savedInstanceState: Bundle?) {

        val appComponent = (applicationContext as App).appComponent
        val component = DaggerActivityComponent.builder()
            .appProvideContract(appComponent)
            .build()
        component.inject(this)

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        lifecycle.addObserver(viewModel)

        setContent {
            TestAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ContactsScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}
