package com.example

import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [33])
class MainActivityCrashTest {

    @Test
    fun testActivityLaunches() {
        Robolectric.buildActivity(MainActivity::class.java).setup()
    }
}
