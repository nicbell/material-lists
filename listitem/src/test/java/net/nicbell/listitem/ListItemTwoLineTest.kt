package net.nicbell.listitem

import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.annotation.DrawableRes
import com.android.resources.NightMode
import org.junit.Test

class ListItemTwoLineTest(nightMode: NightMode) : SnapshotTest(nightMode) {

    @Test
    fun listItem() {
        val view = paparazzi.inflate<View>(R.layout.test_two_line_layout)
        paparazzi.snapshot(view, "ListItem - Two lines")
    }

    @Test
    fun listItemProgrammatic() {
        val layout = LinearLayout(paparazzi.context).apply {
            orientation = LinearLayout.VERTICAL
        }

        layout.addView(
            listItem(
                headlineText = "$headlineText 1",
                supportingText = supportText,
            )
        )

        layout.addView(
            listItem(
                headlineText = "$headlineText 2",
                supportingText = supportText,
                trailingType = ListItem.ListItemTrailingType.Checkbox
            )
        )

        layout.addView(
            listItem(
                headlineText = "$headlineText 3",
                supportingText = supportText,
                leadIcon = R.drawable.ic_outline_person_24,
            )
        )

        paparazzi.snapshot(layout, "ListItem - Two lines - programmatic")
    }


    private fun listItem(
        headlineText: String,
        supportingText: String,
        @DrawableRes leadIcon: Int? = null,
        trailingType: ListItem.ListItemTrailingType = ListItem.ListItemTrailingType.None,
    ) = ListItem(context = paparazzi.context).apply {
        layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        headline.text = headlineText
        supportText.text = supportingText

        setSizeType(ListItem.ListItemSizeType.TwoLine)
        setTrailingType(trailingType)

        leadIcon?.let {
            setLeadingType(ListItem.ListItemLeadingType.Icon)
            setLeadingIcon(resources.getDrawable(leadIcon, null), null)
        }
    }

    companion object {
        private const val headlineText = "Headline"
        private const val supportText = "Support text"
    }
}