package com.kuki.testapp.ui.activity

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entry
import androidx.navigation3.runtime.entryProvider
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
import com.kuki.testapp.navigation.TwoPaneScene
import com.kuki.testapp.navigation.TwoPaneStrategy
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
                    val twoPaneStrategy = remember { TwoPaneStrategy<Any>() }

                    NavDisplay(
                        backStack = backStack,
                        entryDecorators = listOf(
                            rememberSceneSetupNavEntryDecorator(), // order is important
                            rememberSavedStateNavEntryDecorator(),
                            rememberViewModelStoreNavEntryDecorator(),
                        ),
                        entryProvider = entryProvider {

                            entry<ContactsScreenNavModel>(
                                metadata = TwoPaneScene.twoListPane()
                            ) {
                                ContactsScreen(
                                    modifier = Modifier.padding(
                                        innerPadding
                                    ),
                                    onClick = { contactId ->
                                        val last = backStack.lastOrNull()
                                        if (last is ContactDetailsScreenNavModel) {
                                            backStack[backStack.lastIndex] =
                                                ContactDetailsScreenNavModel(
                                                    contactId
                                                )
                                        } else {
                                            backStack.add(
                                                ContactDetailsScreenNavModel(
                                                    contactId
                                                )
                                            )
                                        }
                                    }
                                )
                            }

                            entry<ContactDetailsScreenNavModel>(
                                metadata = TwoPaneScene.twoDetailPane()
                            ) { key ->
                                ContactDetailsScreen(
                                    contactId = key.contactId,
                                    modifier = Modifier.padding(innerPadding)
                                )
                            }
                        },
                        sceneStrategy = twoPaneStrategy
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
