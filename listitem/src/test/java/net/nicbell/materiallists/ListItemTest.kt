package net.nicbell.materiallists

import android.view.View
import com.android.resources.NightMode
import org.junit.Test

class ListItemTest(nightMode: NightMode) : SnapshotTest(nightMode) {
    @Test
    fun `ListItem 1 line`() {
        val view = paparazzi.inflate<View>(R.layout.test_one_line_layout)
        paparazzi.snapshot(view)
    }

    @Test
    fun `ListItem 2 lines`() {
        val view = paparazzi.inflate<View>(R.layout.test_two_line_layout)
        paparazzi.snapshot(view)
    }

    @Test
    fun `List Item 3 lines`() {
        val view = paparazzi.inflate<View>(R.layout.test_three_line_layout)
        paparazzi.snapshot(view)
    }
}