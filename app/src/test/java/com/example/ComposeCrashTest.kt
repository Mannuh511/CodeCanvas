package com.example

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.core.app.ActivityScenario
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

@RunWith(AndroidJUnit4::class)
@Config(sdk = [33])
@GraphicsMode(GraphicsMode.Mode.NATIVE)
class ComposeCrashTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testApp() {
        ActivityScenario.launch(MainActivity::class.java).use {
            composeTestRule.waitForIdle()
        }
    }
}
