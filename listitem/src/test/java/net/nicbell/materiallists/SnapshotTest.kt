package net.nicbell.materiallists

import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.Paparazzi
import app.cash.paparazzi.detectEnvironment
import com.android.resources.NightMode
import org.junit.Rule
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@Suppress("UnnecessaryAbstractClass")
abstract class SnapshotTest {

    @get:Rule
    val paparazzi = Paparazzi(
        deviceConfig = DeviceConfig.PIXEL_4A.copy(
            nightMode = NightMode.NOTNIGHT,
            softButtons = false,
        ),
        theme = "Theme.Material3.DayNight.NoActionBar",
        maxPercentDifference = 0.01,
        environment = detectEnvironment().copy(compileSdkVersion = 34)
    )
}