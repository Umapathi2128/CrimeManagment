package com.example.yugan.abc.ui.customTextview

import android.content.Context
import android.util.AttributeSet
import android.widget.TextView
import android.graphics.Typeface


class EatFoodyTextView : TextView {

    constructor(context: Context) : super(context) {

        applyCustomFont(context)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {

        applyCustomFont(context)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {

        applyCustomFont(context)
    }

    private fun applyCustomFont(context: Context) {
        val customFont = FontChache().getTypeface( context,"SourceSansPro-Regular.ttf")
        typeface = customFont
    }
}