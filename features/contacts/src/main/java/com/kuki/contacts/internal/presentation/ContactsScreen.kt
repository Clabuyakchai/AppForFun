package com.kuki.contacts.internal.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.kuki.contacts.internal.di.ContactsComponentHolder
import com.kuki.domain.entry.contact.ContactEntry
import com.kuki.ui.compose.Toolbar
import com.kuki.ui.theme.Pink80
import com.kuki.utils.compose.LocalComponentHolder
import com.kuki.utils.viewmodel.viewModelCompose


@Composable
internal fun ContactsScreenPrivate(
    modifier: Modifier = Modifier,
    componentHolder: ContactsComponentHolder = LocalComponentHolder.current as ContactsComponentHolder,
    viewModel: ContactsViewModel = viewModelCompose {
        componentHolder.contactsComponent.viewModelFactory().create()
    },
    onClick: (contactId: String) -> Unit
) {
    val state by viewModel.uiState.collectAsState()

    ContactsContent(items = state.items, modifier = modifier, onClick = onClick)
}

@Composable
private fun ContactsContent(
    items: List<ContactEntry>,
    modifier: Modifier = Modifier,
    onClick: (contactId: String) -> Unit
) {
    Column(
        modifier = modifier
            .padding(horizontal = 10.dp)
            .fillMaxSize()
    ) {
        Toolbar(
            text = "Contacts",
            modifier = Modifier
                .padding(vertical = 15.dp),
            onBackButtonClick = {}
        )

        ListItems(items = items, onClick = onClick)
    }
}

@Composable
private fun ListItems(
    items: List<ContactEntry>,
    modifier: Modifier = Modifier,
    onClick: (contactId: String) -> Unit
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(top = 20.dp, bottom = 10.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp)
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
private fun Item(model: ContactEntry, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clip(shape = RoundedCornerShape(10.dp))
            .background(
                color = Pink80.copy(alpha = 0.3f),
            )
            .clickable { onClick.invoke() }
            .padding(all = 10.dp),
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
private fun ContactsPreview() {
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