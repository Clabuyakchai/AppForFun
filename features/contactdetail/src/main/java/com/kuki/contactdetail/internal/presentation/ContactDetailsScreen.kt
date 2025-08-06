package com.kuki.contactdetail.internal.presentation

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.kuki.contactdetail.internal.di.ContactDetailComponentHolder
import com.kuki.contactdetail.internal.presentation.model.ContactDetailUiState
import com.kuki.domain.entry.contact.ContactEntry
import com.kuki.ui.compose.Toolbar
import com.kuki.ui.theme.TestAppTheme
import com.kuki.utils.viewmodel.viewModelCompose

@Composable
internal fun ContactDetailsScreenPrivate(
    contactId: String,
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    context: Context = LocalContext.current,
    viewModel: ContactDetailsViewModel = viewModelCompose {
        ContactDetailComponentHolder
            .getInstance()
            .viewModelFactory()
            .create(contactId)
    }
) {
    val state by viewModel.uiState.collectAsState()

    ContactDetailsContent(state = state, modifier = modifier, onBackClick = onBackClick)
}

@Composable
private fun ContactDetailsContent(
    state: ContactDetailUiState,
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Toolbar(
            text = "Details",
            modifier = Modifier
                .padding(vertical = 15.dp),
            onBackButtonClick = onBackClick
        )

        ProfileAvatar(
            url = state.entry.avatarUrl,
            modifier = Modifier
                .padding(top = 20.dp)
        )

        Title(text = "${state.entry.name} ${state.entry.surname}")
        Title(text = state.entry.phoneNumber)
    }
}

@Composable
private fun ProfileAvatar(url: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .size(100.dp)
    ) {
        AsyncImage(
            model = url,
            contentDescription = "Profile avatar",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .clip(CircleShape)
        )
    }
}

@Composable
private fun Title(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        style = TextStyle(
            fontSize = 20.sp,
            textAlign = TextAlign.Center
        ),
        modifier = modifier
            .padding(horizontal = 24.dp)
            .fillMaxWidth()
    )
}

@Composable
@Preview
private fun ContactDetailsPreview() {

    val contact = ContactEntry(
        id = "0",
        name = "Влад",
        surname = "Грибовский",
        phoneNumber = "+375 44 123-34-56",
        avatarUrl = ""
    )

    TestAppTheme {
        ContactDetailsContent(ContactDetailUiState(contact), onBackClick = {})
    }
}