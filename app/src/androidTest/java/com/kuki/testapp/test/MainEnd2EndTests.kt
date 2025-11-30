package com.kuki.testapp.test

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.kuki.testapp.TestApplication
import com.kuki.testapp.ui.activity.MainActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
class MainEnd2EndTests {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setUp() {
        val app = ApplicationProvider.getApplicationContext<TestApplication>()
        val testDispatcher = app.testAppComponent.dispatchersProvider().main()
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun appFlow_shouldShowContactsList_andNavigateToDetails() {
        composeTestRule.mainClock.advanceTimeBy(1000)
        composeTestRule
            .onNodeWithText("Contacts")
            .assertIsDisplayed()

        val contactNode = composeTestRule
            .onNodeWithText(text = "Миша", substring = true, ignoreCase = true)
        contactNode.assertIsDisplayed()

        contactNode.performClick()

        composeTestRule.mainClock.advanceTimeBy(1000)
        composeTestRule
            .onNodeWithText("Details")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText(text = "Миша", substring = true, ignoreCase = true)
            .assertIsDisplayed()
    }

//    @Test
//    fun shouldShowContactsList_scrollToContact_andNavigateToDetails() {
//        composeTestRule.mainClock.advanceTimeBy(1000)
//        composeTestRule
//            .onNodeWithText("Contacts")
//            .assertIsDisplayed()
//
//        val contactScroll = composeTestRule
//            .onNodeWithText("Попов Виктор")
//            .performScrollTo()
//            .assertIsDisplayed()
//
//        contactScroll.performClick()
//
//        composeTestRule.mainClock.advanceTimeBy(1000)
//
//        composeTestRule
//            .onNodeWithText("Details")
//            .assertIsDisplayed()
//
//        composeTestRule
//            .onNodeWithText("Виктор")
//            .assertIsDisplayed()
//    }
}
