package net.nicbell.listitem

import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.android.resources.NightMode
import org.junit.Test

class ListItemOneLineTest(nightMode: NightMode) : SnapshotTest(nightMode) {

    @Test
    fun listItem() {
        val view = paparazzi.inflate<View>( R.layout.test_one_line_layout)
        paparazzi.snapshot(view, "ListItem - One line")
    }

    @Test
    fun listItemProgrammatic() {
        val layout = LinearLayout(paparazzi.context).apply {
            orientation = LinearLayout.VERTICAL
        }

        layout.addView(
            listItem(
                headlineText = "Headline"
            ).apply {
                setTrailingType(ListItem.ListItemTrailingType.None)
            }
        )

        layout.addView(
            listItem(
                headlineText = "Headline 2"
            ).apply {
                setLeadingType(ListItem.ListItemLeadingType.Icon)
                setTrailingType(ListItem.ListItemTrailingType.Icon)
                setLeadingIcon(resources.getDrawable(R.drawable.ic_outline_person_24, null), null)
                setTrailingIcon(resources.getDrawable(R.drawable.ic_baseline_arrow_right_24, null), null)
            }
        )

        layout.addView(
            listItem(
                headlineText = "Headline 3"
            ).apply {
                setLeadingType(ListItem.ListItemLeadingType.Icon)
                setTrailingType(ListItem.ListItemTrailingType.Checkbox)
                setLeadingIcon(resources.getDrawable(R.drawable.ic_outline_person_24, null), null)
            }
        )

        paparazzi.snapshot(layout, "ListItem - One line - programmatic")
    }


    private fun listItem(headlineText: String) = ListItem(context = paparazzi.context).apply {
        layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT
        )
        headline.text = headlineText
    }
}