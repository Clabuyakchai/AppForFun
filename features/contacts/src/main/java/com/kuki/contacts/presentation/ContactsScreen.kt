package com.kuki.contacts.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.kuki.contacts.di.ContactsComponentHolder
import com.kuki.domain.entry.contact.ContactEntry
import com.kuki.presentation.viewmodel.viewModelCompose

@Composable
fun ContactsScreen(
    modifier: Modifier = Modifier,
    viewModel: ContactsViewModel = viewModelCompose {
        ContactsComponentHolder.contactsComponent.viewModelFactory().create()
    },
    onClick: (contactId: String) -> Unit
) {
    val state by viewModel.uiState.collectAsState()

    ContactsContent(items = state.items, modifier = modifier, onClick = onClick)
}

@Composable
fun ContactsContent(
    items: List<ContactEntry>,
    modifier: Modifier = Modifier,
    onClick: (contactId: String) -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        ListItems(items = items, onClick = onClick)
    }
}

@Composable
fun ListItems(
    items: List<ContactEntry>,
    modifier: Modifier = Modifier,
    onClick: (contactId: String) -> Unit
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
    ) {
        items(count = items.size) { index ->
            Item(
                model = items[index],
                onClick = { onClick.invoke(items[index].id) }
            )
        }
    }
}

@Composable
fun Item(model: ContactEntry, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable { onClick.invoke() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .padding(vertical = 5.dp, horizontal = 10.dp)
                .size(50.dp)
        ) {
            AsyncImage(
                model = model.avatarUrl,
                contentDescription = model.surname,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(CircleShape)
            )
        }

        Text(
            text = "${model.surname} ${model.name}",
            modifier = Modifier
                .padding(vertical = 10.dp)
                .fillMaxWidth()
                .wrapContentHeight(),
            style = TextStyle(
                fontSize = 20.sp, // TODO след шаг зайти через настройку темы,
                color = Color.Black,
                textAlign = TextAlign.Start
            )
        )
    }
}

@Composable
@Preview
fun ContactsPreview() {
    Surface {
        ContactsContent(
            items = listOf(
                ContactEntry(
                    id = "0",
                    name = "Влад",
                    surname = "Грибовский",
                    phoneNumber = "+375 44 123-34-56",
                    avatarUrl = ""
                )
            ),
            onClick = {}
        )
    }
}