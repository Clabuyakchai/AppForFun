package com.kuki.contactdetail.api

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.kuki.common.findDependencies
import com.kuki.contactdetail.internal.di.ContactDetailComponentHolder
import com.kuki.contactdetail.internal.di.DaggerContactDetailsComponent
import com.kuki.contactdetail.internal.presentation.ContactDetailsScreenPrivate

@Composable
fun ContactDetailsScreen(
    contactId: String,
    modifier: Modifier = Modifier
) {
    ContactDetailComponentHolder._contactsDetailHolder =
        DaggerContactDetailsComponent.builder().dependencies(findDependencies()).build()
    ContactDetailsScreenPrivate(contactId = contactId, modifier = modifier)
}