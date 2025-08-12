package com.kuki.contacts.api

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.kuki.contacts.internal.di.ContactsComponentHolder
import com.kuki.contacts.internal.presentation.ContactsScreenPrivate
import com.kuki.di.findDependencies
import com.kuki.utils.compose.LocalComponentHolder

@Composable
fun ContactsScreen(
    modifier: Modifier = Modifier,
    onClick: (contactId: String) -> Unit
) {
    val context = LocalContext.current
    val component = remember { ContactsComponentHolder(findDependencies(context)) }

    CompositionLocalProvider(
        LocalComponentHolder provides component
    ) {
        ContactsScreenPrivate(
            modifier = modifier,
            onClick = onClick
        )
    }
}