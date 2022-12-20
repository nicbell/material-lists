package net.nicbell.materiallists

import android.content.Context
import android.text.TextUtils
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View

internal class MyLayoutInflaterFactory2 : LayoutInflater.Factory2 {

    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
        return if (TextUtils.equals(name, "MaterialLists.ListItem")) {
            ListItem(context, attrs)
        } else {
            null
        }
    }

    override fun onCreateView(
        parent: View?,
        name: String,
        context: Context,
        attrs: AttributeSet
    ): View? = onCreateView(name, context, attrs)
}