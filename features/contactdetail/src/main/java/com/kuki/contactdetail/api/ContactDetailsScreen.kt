package com.kuki.contactdetail.api

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.kuki.common.findDependenciesTest
import com.kuki.contactdetail.internal.di.ContactDetailComponentHolder
import com.kuki.contactdetail.internal.presentation.ContactDetailsScreenPrivate

@Composable
fun ContactDetailsScreen(
    contactId: String,
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit
) {

    val context = LocalContext.current
//    var flag by remember { mutableStateOf(false) }

//    DisposableEffect(contactId) {
//        println("DisposableEffect contactId=$contactId")
    ContactDetailComponentHolder.getInstance(dependencies = findDependenciesTest(context))
//        flag = true
//
//        onDispose {
//            println("DisposableEffect onDispose contactId=$contactId")
//            ContactDetailComponentHolder.destroy()
//            flag = false
//        }
//    }

//    if (flag) {
    ContactDetailsScreenPrivate(
        contactId = contactId,
        modifier = modifier,
        onBackClick = onBackClick
    )
//    }

}