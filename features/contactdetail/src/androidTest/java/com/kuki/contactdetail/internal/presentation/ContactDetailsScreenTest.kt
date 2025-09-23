package com.kuki.contactdetail.internal.presentation

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.kuki.contactdetail.internal.presentation.model.ContactDetailUiState
import com.kuki.domain.entry.contact.ContactEntry
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import java.util.concurrent.atomic.AtomicBoolean

class ContactDetailsScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun test_ContactDetailsScreen() {
        composeTestRule.setContent {
            ContactDetailsContent(
                state = ContactDetailUiState(),
                onBackClick = {}
            )
        }

        composeTestRule
            .onNodeWithText("Details")
            .assertIsDisplayed()

    }

    @Test
    fun openContactDetailsScreen_andCompareUserData() {
        composeTestRule.setContent {
            ContactDetailsContent(
                state = ContactDetailUiState(
                    entry = ContactEntry(
                        id = "1",
                        name = "Вася",
                        surname = "Пупкин",
                        phoneNumber = "+375 44 123-34-56",
                        avatarUrl = ""
                    )
                ),
                onBackClick = {}
            )
        }

        composeTestRule.onNodeWithText("Details").assertIsDisplayed()
        composeTestRule.onNodeWithText(text = "Вася Пупкин").assertIsDisplayed()
        composeTestRule.onNodeWithText(text = "+375 44 123-34-56").assertIsDisplayed()
    }

    @Test
    fun pressBackButton() {
        val isBackClicked = AtomicBoolean(false)

        composeTestRule.setContent {
            ContactDetailsContent(
                state = ContactDetailUiState(),
                onBackClick = { isBackClicked.set(true) }
            )
        }

        composeTestRule
            .onNodeWithContentDescription("Back")
            .performClick()

        assertTrue("The back button was not clicked", isBackClicked.get())
    }
}