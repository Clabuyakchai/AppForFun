package com.kuki.contactdetail.api

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.kuki.common.findDependencies
import com.kuki.contactdetail.internal.di.ContactDetailComponentHolder
import com.kuki.contactdetail.internal.presentation.ContactDetailsScreenPrivate

@Composable
fun ContactDetailsScreen(
    contactId: String,
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit
) {

    ContactDetailComponentHolder.getInstance(dependencies = findDependencies())
//    DisposableEffect(contactId) {
//        println("DisposableEffect contactId=$contactId")
//
//        onDispose {
//            println("DisposableEffect onDispose contactId=$contactId")
//            ContactDetailComponentHolder.destroy()
//        }
//    }

    ContactDetailsScreenPrivate(
        contactId = contactId,
        modifier = modifier,
        onBackClick = onBackClick
    )
}