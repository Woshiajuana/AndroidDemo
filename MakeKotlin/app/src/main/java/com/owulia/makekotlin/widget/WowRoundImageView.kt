package com.owulia.makekotlin.widget

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView


class WowRoundImageView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : AppCompatImageView(context, attrs, defStyleAttr) {

    private val mPaint = Paint()

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {
//        super.onDraw(canvas)
        drawable?.let {
            val bitmap = getBitmapFromDrawable(drawable)
            val b = getRoundBitmapByShader(bitmap, width, height, 50, 0)
            val rectSrc = Rect(0, 0, b.width, b.height)
            val rectDest = Rect(0, 0, width, height)
            mPaint.reset()
            canvas!!.drawBitmap(b, rectSrc, rectDest, mPaint)
        }
    }

    /**
     * 把资源图片转换成 Bitmap
     * @param drawable [Drawable] 资源图片
     * @return bitmap
     * */
    private fun getBitmapFromDrawable (drawable: Drawable): Bitmap {
        val width = drawable.intrinsicWidth
        val height = drawable.intrinsicHeight
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        drawable.draw(canvas)
        return bitmap
    }

    /**
     *
     * */
    private fun getRoundBitmapByShader (bitmap: Bitmap, outWidth: Int, outHeight: Int, radius: Int, border: Int): Bitmap {
        val with = bitmap.width
        val height = bitmap.height
        val widthScale = outWidth * 1f / with
        val heightScale = outHeight * 1f / height
        val matrix = Matrix()
        matrix.setScale(widthScale, heightScale)
        //创建输出的bitmap
        val desBitmap = Bitmap.createBitmap(outWidth, outHeight, Bitmap.Config.ARGB_8888)
        //创建canvas并传入desBitmap，这样绘制的内容都会在desBitmap上
        val canvas = Canvas(desBitmap)
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        //创建着色器
        val bitmapShader =
            BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
        //给着色器配置matrix
        bitmapShader.setLocalMatrix(matrix)
        paint.shader = bitmapShader
        //创建矩形区域并且预留出border
        val rect = RectF(border.toFloat(), border.toFloat(), (outWidth - border).toFloat(), (outHeight - border).toFloat())
        //把传入的bitmap绘制到圆角矩形区域内
        canvas.drawRoundRect(rect, radius.toFloat(), radius.toFloat(), paint)
        if (border > 0) {
            //绘制boarder
            val boarderPaint = Paint(Paint.ANTI_ALIAS_FLAG)
            boarderPaint.color = Color.GREEN
            boarderPaint.style = Paint.Style.STROKE
            boarderPaint.strokeWidth = border.toFloat()
            canvas.drawRoundRect(rect, radius.toFloat(), radius.toFloat(), boarderPaint)
        }
        return desBitmap

    }
}