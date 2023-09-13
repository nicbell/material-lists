package net.nicbell.materiallists

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import androidx.constraintlayout.widget.Barrier
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.Guideline
import androidx.core.view.isVisible
import com.google.android.material.textview.MaterialTextView

/**
 * Material 3 list item implementation:
 * https://m3.material.io/components/lists/specs
 */
@Suppress("MemberVisibilityCanBePrivate")
open class ListItem @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private lateinit var sizeType: ListItemSizeType

    val headline by lazy<MaterialTextView> { findViewById(R.id.txt_headline) }
    val supportText by lazy<MaterialTextView> { findViewById(R.id.txt_support) }

    private val guideTop by lazy<Guideline> { findViewById(R.id.guide_top) }
    private val guideBottom by lazy<Guideline> { findViewById(R.id.guide_bottom) }

    private val barrierTextEnd by lazy { findViewById<Barrier>(R.id.barrier_text_end) }
    private val barrierTextStart by lazy { findViewById<Barrier>(R.id.barrier_text_start) }

    init {
        @Suppress("LeakingThis") // This is normal in custom view groups that are non-final.
        inflate(context, R.layout.list_item, this)

        attrs?.let {
            val attributes = context.obtainStyledAttributes(it, R.styleable.ListItem)

            // Apply attributes
            with(attributes) {
                headline.text = getString(R.styleable.ListItem_headline).orEmpty()
                supportText.text = getString(R.styleable.ListItem_supportText).orEmpty()

                getInt(R.styleable.ListItem_sizeType, ListItemSizeType.OneLine.ordinal).run {
                    setSizeType(ListItemSizeType.values()[this])
                }

                recycle()
            }
        }

        if (attrs == null) {
            setSizeType(ListItemSizeType.OneLine)
        }
    }

    fun setSizeType(sizeType: ListItemSizeType) {
        this.sizeType = sizeType

        when (sizeType) {
            ListItemSizeType.OneLine -> {
                supportText.isVisible = false
                minHeight = resources.getDimensionPixelSize(R.dimen.list_item_min_height_one_line)

                headline.constraintVerticalBias = CENTERED_VERTICAL_BIAS

                guideTop.setGuidelineBegin(resources.getDimensionPixelSize(R.dimen.list_item_space_x2))
                guideBottom.setGuidelineEnd(resources.getDimensionPixelSize(R.dimen.list_item_space_x2))
            }
            ListItemSizeType.TwoLine -> {
                supportText.isVisible = true
                minHeight = resources.getDimensionPixelSize(R.dimen.list_item_min_height_two_line)

                headline.constraintVerticalBias = CENTERED_VERTICAL_BIAS

                guideTop.setGuidelineBegin(resources.getDimensionPixelSize(R.dimen.list_item_space_x2))
                guideBottom.setGuidelineEnd(resources.getDimensionPixelSize(R.dimen.list_item_space_x2))
            }
            ListItemSizeType.ThreeLine -> {
                supportText.isVisible = true
                minHeight = resources.getDimensionPixelSize(R.dimen.list_item_min_height_three_line)

                headline.constraintVerticalBias = TOP_VERTICAL_BIAS

                guideTop.setGuidelineBegin(resources.getDimensionPixelSize(R.dimen.list_item_space_x3))
                guideBottom.setGuidelineEnd(resources.getDimensionPixelSize(R.dimen.list_item_space_x3))
            }
        }
    }

    override fun addView(child: View?, params: ViewGroup.LayoutParams?) {
        if (child != null && params is LayoutParams) {
            if (params.isMaterialListLeadingContent || params.isMaterialListTrailingContent) {
                if (child.id == View.NO_ID) child.id = View.generateViewId()
                verticallyAlign(params)
            }

            when {
                params.isMaterialListLeadingContent -> {
                    barrierTextStart.referencedIds = barrierTextStart.referencedIds + child.id
                }
                params.isMaterialListTrailingContent -> {
                    barrierTextEnd.referencedIds = barrierTextEnd.referencedIds + child.id
                }
            }
        }
        super.addView(child, params)
    }

    private fun verticallyAlign(params: ConstraintLayout.LayoutParams) {
        params.verticalBias = when (sizeType) {
            ListItemSizeType.OneLine,
            ListItemSizeType.TwoLine -> CENTERED_VERTICAL_BIAS
            ListItemSizeType.ThreeLine -> TOP_VERTICAL_BIAS
        }
    }

    override fun checkLayoutParams(params: ViewGroup.LayoutParams?) =
        params is LayoutParams

    override fun generateDefaultLayoutParams() =
        LayoutParams(WRAP_CONTENT, WRAP_CONTENT)

    override fun generateLayoutParams(attrs: AttributeSet?) =
        LayoutParams(context, attrs)

    class LayoutParams : ConstraintLayout.LayoutParams {
        var isMaterialListLeadingContent = false
        var isMaterialListTrailingContent = false

        constructor(width: Int, height: Int) : super(width, height)

        constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
            val styledAttrs = context.obtainStyledAttributes(attrs, R.styleable.ListItem_Layout)

            with(styledAttrs) {
                isMaterialListLeadingContent =
                    getBoolean(R.styleable.ListItem_Layout_isMaterialListLeadingContent, false)
                isMaterialListTrailingContent =
                    getBoolean(R.styleable.ListItem_Layout_isMaterialListTrailingContent, false)
            }
            styledAttrs.recycle()
        }
    }

    enum class ListItemSizeType {
        OneLine, TwoLine, ThreeLine
    }

    companion object {
        private const val CENTERED_VERTICAL_BIAS = 0.5f
        private const val TOP_VERTICAL_BIAS = 0f
    }
}