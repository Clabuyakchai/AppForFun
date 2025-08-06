package com.kuki.testapp.ui.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.navigation3.ListDetailSceneStrategy
import androidx.compose.material3.adaptive.navigation3.rememberListDetailSceneStrategy
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entry
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSavedStateNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.navigation3.ui.rememberSceneSetupNavEntryDecorator
import com.kuki.contactdetail.api.ContactDetailsScreen
import com.kuki.contacts.api.ContactsScreen
import com.kuki.testapp.navigation.ContactDetailsScreenNavModel
import com.kuki.testapp.navigation.ContactsScreenNavModel

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun MainScreen() {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

        val backStack = rememberNavBackStack(ContactsScreenNavModel)
        val listDetailStrategy = rememberListDetailSceneStrategy<Any>()

        NavDisplay(
            modifier = Modifier.padding(innerPadding),
            backStack = backStack,
            entryDecorators = listOf(
                rememberSceneSetupNavEntryDecorator(), // order is important
                rememberSavedStateNavEntryDecorator(),
                rememberViewModelStoreNavEntryDecorator(),
            ),
            entryProvider = entryProvider {

                entry<ContactsScreenNavModel>(
                    metadata = ListDetailSceneStrategy.listPane(
                        detailPlaceholder = {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                            ) {
                                Text(
                                    text = "Here will be contact description",
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .align(Alignment.Center),
                                    style = TextStyle(
                                        fontSize = 20.sp,
                                        textAlign = TextAlign.Center
                                    )
                                )
                            }
                        }
                    )
                ) {
                    ContactsScreen(
                        modifier = Modifier.padding(
                            innerPadding
                        ),
                        onClick = { contactId ->
                            if (backStack.last() is ContactDetailsScreenNavModel) {
                                backStack[backStack.lastIndex] = ContactDetailsScreenNavModel(
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
                    metadata = ListDetailSceneStrategy.detailPane()
                ) { key ->
                    ContactDetailsScreen(
                        contactId = key.contactId,
                        modifier = Modifier.padding(innerPadding),
                        onBackClick = {
                            backStack.removeAt(backStack.lastIndex)
                        }
                    )
                }
            },
            sceneStrategy = listDetailStrategy
        )
    }
}