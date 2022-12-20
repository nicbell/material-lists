package net.nicbell.materiallists

import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout

var View.constraintVerticalBias: Float
    get() {
        val layout = this.layoutParams as ConstraintLayout.LayoutParams
        return layout.verticalBias
    }
    set(value) {
        val layout = this.layoutParams as ConstraintLayout.LayoutParams
        layout.verticalBias = value
        this.layoutParams = layout
    }