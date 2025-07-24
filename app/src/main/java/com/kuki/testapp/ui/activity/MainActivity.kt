package com.kuki.testapp.ui.activity

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSavedStateNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.navigation3.ui.rememberSceneSetupNavEntryDecorator
import com.kuki.common.DependenciesMap
import com.kuki.common.HasDependencies
import com.kuki.contactdetail.api.ContactDetailsScreen
import com.kuki.contacts.api.ContactsScreen
import com.kuki.presentation.theme.TestAppTheme
import com.kuki.presentation.viewmodel.lazyViewModel
import com.kuki.testapp.App
import com.kuki.testapp.di.components.DaggerActivityComponent
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
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    val backStack = rememberNavBackStack(ContactsScreenNavModel)

                    NavDisplay(
                        backStack = backStack,
                        entryDecorators = listOf(
                            rememberSavedStateNavEntryDecorator(),
                            rememberViewModelStoreNavEntryDecorator(),
                            rememberSceneSetupNavEntryDecorator()
                        ),
                        entryProvider = { key ->
                            when (key) {
                                is ContactsScreenNavModel -> NavEntry(key) {
                                    ContactsScreen(
                                        modifier = Modifier.padding(
                                            innerPadding
                                        ),
                                        onClick = { contactId ->
                                            backStack.add(
                                                ContactDetailsScreenNavModel(
                                                    contactId
                                                )
                                            )
                                        }
                                    )
                                }

                                is ContactDetailsScreenNavModel -> NavEntry(key) {
                                    ContactDetailsScreen(
                                        contactId = key.contactId,
                                        modifier = Modifier.padding(innerPadding)
                                    )
                                }

                                else -> throw IllegalStateException()
                            }
                        }
                    )


                }
            }
        }
    }
}

@Serializable
data object ContactsScreenNavModel : NavKey

@Serializable
data class ContactDetailsScreenNavModel(val contactId: String) : NavKey
