package com.kuki.contactdetail.api

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.kuki.contactdetail.internal.di.ContactDetailComponentHolder
import com.kuki.contactdetail.internal.presentation.ContactDetailsScreenPrivate
import com.kuki.di.findDependencies
import com.kuki.utils.compose.LocalComponentHolder


@Composable
fun ContactDetailsScreen(
    contactId: String,
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit
) {
    val context = LocalContext.current
    val component = remember { ContactDetailComponentHolder(findDependencies(context)) }

    CompositionLocalProvider(
        LocalComponentHolder provides component
    ) {
        ContactDetailsScreenPrivate(
            contactId = contactId,
            modifier = modifier,
            onBackClick = onBackClick
        )
    }
}