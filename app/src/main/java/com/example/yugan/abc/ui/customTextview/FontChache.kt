package com.example.yugan.abc.ui.customTextview

import android.content.Context
import android.graphics.Typeface

class FontChache {

    private lateinit var fontStyle:HashMap<String,Typeface>
    fun getTypeface(context: Context,fontName: String): Typeface? {

        fontStyle= HashMap()
        var typeface= this.fontStyle[fontName]
        if (typeface==null)
        {
            typeface=Typeface.createFromAsset(context.assets,fontName)
            this.fontStyle[fontName] = typeface
        }


        return typeface
    }
}