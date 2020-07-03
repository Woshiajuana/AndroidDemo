package com.owulia.makekotlin.adapter

import android.graphics.Bitmap
import com.squareup.picasso.Transformation


class BlurTransformation : Transformation {
    private var mRadius = 0

    constructor(radius: Int) {
        mRadius = radius
    }

    override fun transform(source: Bitmap): Bitmap? {
        if (mRadius <= 0) {
            return source
        }
        var bitmap: Bitmap
        try {
            bitmap = BlurTool.blur(source, mRadius)
        } catch (e: Exception) {
            bitmap = source
        }
        if (bitmap != source) {
            source.recycle()
        }
        return bitmap
    }

    override fun key(): String? {
        return "BlurTransformation(radius=$mRadius)"
    }
}