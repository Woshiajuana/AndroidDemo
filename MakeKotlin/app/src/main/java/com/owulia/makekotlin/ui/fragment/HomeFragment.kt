package com.owulia.makekotlin.ui.fragment

import android.graphics.*
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.owulia.makekotlin.R
import com.owulia.makekotlin.base.BaseFragment
import com.owulia.makekotlin.widget.WowCarousel
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : BaseFragment() {

    companion object {
        val instant: HomeFragment by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) { HomeFragment() }
    }

    override val mNavBarLeftImg: Int = -1

    override val mNavBarTitle: Int = R.string.string_tab_bar_home

    override val mNavBarRightImg: Int = R.mipmap.ic_message

    override fun getContentViewResourceId(): Int = R.layout.fragment_home

    val mArrBanner = listOf(
        R.mipmap.text_banner_1,
        R.mipmap.text_banner_2,
        R.mipmap.text_banner_3,
        R.mipmap.text_banner_4
    )

    override fun initView() {
        super.initView()
        vNavBar?.setNavBarTitleLeftAlign()
        render(RenderState.SUCCESS)
        vCarousel.start(object : WowCarousel.CarouselPagerAdapter() {
            override fun getInstantiateItem(position: Int): View {
                return ImageView(context).apply {
                    scaleType = ImageView.ScaleType.CENTER_CROP
                    val bitmap = BitmapFactory.decodeResource(resources, mArrBanner[position])
                    val outBitmap = getRoundBitmapByShader(bitmap, width, height, 10, 0)
                    setImageResource(mArrBanner[position])
                }
            }

            override fun getSize(): Int = mArrBanner.size
        })
    }

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
