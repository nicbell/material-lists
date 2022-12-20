package net.nicbell.materiallists

import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.Paparazzi
import com.android.resources.NightMode
import org.junit.Rule
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@Suppress("UnnecessaryAbstractClass")
@RunWith(Parameterized::class)
abstract class SnapshotTest(nightMode: NightMode) {

    @get:Rule
    val paparazzi = Paparazzi(
        deviceConfig = DeviceConfig.PIXEL_4A.copy(
            nightMode = nightMode,
            softButtons = false,
        ),
        theme = "Theme.Material3.DayNight.NoActionBar",
        maxPercentDifference = 0.0
    )

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "Mode: {0}")
        fun data() = listOf(
            NightMode.NIGHT,
            NightMode.NOTNIGHT
        )
    }
}