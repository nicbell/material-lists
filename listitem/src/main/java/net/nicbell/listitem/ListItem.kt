package net.nicbell.listitem

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.Guideline
import androidx.core.view.isVisible
import com.google.android.material.checkbox.MaterialCheckBox
import com.google.android.material.textview.MaterialTextView

/**
 * Material 3 list item implementation:
 * https://m3.material.io/components/lists/specs
 */
@Suppress("MemberVisibilityCanBePrivate")
class ListItem @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    val headline by lazy<MaterialTextView> { findViewById(R.id.txt_headline) }
    val supportText by lazy<MaterialTextView> { findViewById(R.id.txt_support) }
    val leadingIcon by lazy<AppCompatImageView> { findViewById(R.id.lead_icon) }
    val trailingIcon by lazy<AppCompatImageView> { findViewById(R.id.trailing_icon) }
    val trailingCheckbox by lazy<MaterialCheckBox> { findViewById(R.id.trailing_checkbox) }

    private val guideTop by lazy<Guideline> { findViewById(R.id.guide_top) }
    private val guideBottom by lazy<Guideline> { findViewById(R.id.guide_bottom) }

    init {
        inflate(context, R.layout.list_item, this)

        attrs?.let {
            val attributes = context.obtainStyledAttributes(it, R.styleable.ListItem)

            // Apply attributes
            with(attributes) {

                // Text
                headline.text = getString(R.styleable.ListItem_headline).orEmpty()
                supportText.text = getString(R.styleable.ListItem_supportText).orEmpty()

                getInt(R.styleable.ListItem_sizeType, ListItemSizeType.OneLine.ordinal).run {
                    setSizeType(ListItemSizeType.values()[this])
                }

                // Icons
                val leadIconDrawable = getDrawable(R.styleable.ListItem_leadingIcon)
                val leadIconTint = getColorStateList(R.styleable.ListItem_leadingIconTint)
                setLeadingIcon(leadIconDrawable, leadIconTint)

                val trailingIconDrawable = getDrawable(R.styleable.ListItem_trailingIcon)
                val trailingIconTint = getColorStateList(R.styleable.ListItem_trailingIconTint)
                setTrailingIcon(trailingIconDrawable, trailingIconTint)

                val leadingType = ListItemLeadingType.values()[getInt(
                    R.styleable.ListItem_leadingType, ListItemLeadingType.None.ordinal
                )]
                setLeadingType(leadingType)

                val trailingType = ListItemTrailingType.values()[getInt(
                    R.styleable.ListItem_trailingType, ListItemTrailingType.None.ordinal
                )]
                setTrailingType(trailingType)

                recycle()
            }
        }

        if (attrs == null) {
            setSizeType(ListItemSizeType.OneLine)
            setLeadingType(ListItemLeadingType.None)
            setTrailingType(ListItemTrailingType.None)
        }
    }

    fun setSizeType(sizeType: ListItemSizeType) {
        when (sizeType) {
            ListItemSizeType.OneLine -> {
                supportText.isVisible = false
                minHeight = resources.getDimensionPixelSize(R.dimen.list_item_min_height_one_line)

                leadingIcon.constraintVerticalBias = 0.5f
                trailingIcon.constraintVerticalBias = 0.5f
                trailingCheckbox.constraintVerticalBias = 0.5f
                headline.constraintVerticalBias = 0.5f

                guideTop.setGuidelineBegin(resources.getDimensionPixelSize(R.dimen.space_x2))
                guideBottom.setGuidelineEnd(resources.getDimensionPixelSize(R.dimen.space_x2))
            }
            ListItemSizeType.TwoLine -> {
                supportText.isVisible = true
                minHeight = resources.getDimensionPixelSize(R.dimen.list_item_min_height_two_line)

                leadingIcon.constraintVerticalBias = 0.5f
                trailingIcon.constraintVerticalBias = 0.5f
                trailingCheckbox.constraintVerticalBias = 0.5f
                headline.constraintVerticalBias = 0.5f

                guideTop.setGuidelineBegin(resources.getDimensionPixelSize(R.dimen.space_x2))
                guideBottom.setGuidelineEnd(resources.getDimensionPixelSize(R.dimen.space_x2))
            }
            ListItemSizeType.ThreeLine -> {
                supportText.isVisible = true
                minHeight = resources.getDimensionPixelSize(R.dimen.list_item_min_height_three_line)

                leadingIcon.constraintVerticalBias = 0f
                trailingIcon.constraintVerticalBias = 0f
                trailingCheckbox.constraintVerticalBias = 0f
                headline.constraintVerticalBias = 0f

                guideTop.setGuidelineBegin(resources.getDimensionPixelSize(R.dimen.space_x3))
                guideBottom.setGuidelineEnd(resources.getDimensionPixelSize(R.dimen.space_x3))
            }
        }
    }

    fun setLeadingIcon(drawable: Drawable?, tint: ColorStateList?) {
        leadingIcon.setImageDrawable(drawable)
        leadingIcon.isVisible = drawable != null
        tint?.run { leadingIcon.imageTintList = this }
    }

    fun setTrailingIcon(drawable: Drawable?, tint: ColorStateList?) {
        trailingIcon.setImageDrawable(drawable)
        trailingIcon.isVisible = drawable != null
        tint?.run { trailingIcon.imageTintList = this }
    }

    fun setTrailingType(trailingType: ListItemTrailingType) = when (trailingType) {
        ListItemTrailingType.None -> {
            trailingIcon.isVisible = false
            trailingCheckbox.isVisible = false
        }
        ListItemTrailingType.Icon -> {
            trailingIcon.isVisible = true
            trailingCheckbox.isVisible = false
        }
        ListItemTrailingType.Checkbox -> {
            trailingIcon.isVisible = false
            trailingCheckbox.isVisible = true
        }
        ListItemTrailingType.Meta -> {

        }
    }

    fun setLeadingType(leadingType: ListItemLeadingType) = when (leadingType) {
        ListItemLeadingType.None -> {
            leadingIcon.isVisible = false
        }
        ListItemLeadingType.Icon -> {
            leadingIcon.isVisible = true
        }
    }

    enum class ListItemSizeType {
        OneLine, TwoLine, ThreeLine
    }

    enum class ListItemTrailingType {
        None, Icon, Checkbox, Meta
    }

    enum class ListItemLeadingType {
        None, Icon
    }
}