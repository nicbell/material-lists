package net.nicbell.materiallists

import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.view.ContextThemeWrapper
import androidx.appcompat.widget.AppCompatImageView
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

    @Test
    fun `ListItem programmatic`() {
        val layout = LinearLayout(paparazzi.context).apply {
            orientation = LinearLayout.VERTICAL
        }

        val listItem = ListItem(paparazzi.context).apply {
            layoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
            )
            headline.text = "Headline"

            addView(
                AppCompatImageView(
                    ContextThemeWrapper(context, R.style.MaterialLists_LeadingIcon), null, 0
                ).apply {
                    id = View.generateViewId()
                    setImageResource(R.drawable.ic_outline_person_24)
                },
                ListItem.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                ).apply {
                    isMaterialListLeadingContent = true
                    startToStart = R.id.guide_start
                    topToTop = R.id.guide_top
                    bottomToBottom = R.id.guide_bottom
                    endToStart = R.id.barrier_text_start
                    marginEnd = context.resources.getDimensionPixelSize(R.dimen.list_item_space_x4)
                }
            )
        }

        layout.addView(listItem)
        paparazzi.snapshot(layout)
    }
}