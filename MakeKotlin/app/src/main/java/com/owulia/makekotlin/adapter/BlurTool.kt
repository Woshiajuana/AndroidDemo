package com.owulia.makekotlin.adapter

import android.graphics.Bitmap
import org.apache.weex.utils.WXLogUtils
import java.util.concurrent.Executors

object BlurTool {

    interface OnBlurCompleteListener {
        /**
         * blur complete event.(Notice:in sub thread)
         *
         * @param bitmap the blurred bitmap
         */
        fun onBlurComplete(bitmap: Bitmap)
    }

    private val sExecutorService =
        Executors.newCachedThreadPool { r -> Thread(r, "wx_blur_thread") }

    private val TAG = "BlurTool"

    /**
     * radius in [0,10]
     */
    fun blur(originalImage: Bitmap, radius: Int): Bitmap {
        var radius = radius
        val start = System.currentTimeMillis()
        radius = Math.min(10, Math.max(0, radius)) //[0,10]
        if (radius == 0) {
            return originalImage
        }
        val width = originalImage.width
        val height = originalImage.height
        if (width <= 0 || height <= 0) {
            return originalImage
        }
        var sampling = calculateSampling(radius)
        val retryTimes = 3
        var sampledImage = Bitmap.createScaledBitmap(
            originalImage,
            (sampling * width).toInt(),
            (sampling * height).toInt(),
            true
        )
        for (i in 0 until retryTimes) {
            try {
                if (radius == 0) {
                    return originalImage
                }
                val s = calculateSampling(radius)
                if (s != sampling) {
                    sampling = s
                    sampledImage = Bitmap.createScaledBitmap(
                        originalImage,
                        (sampling * width).toInt(),
                        (sampling * height).toInt(),
                        true
                    )
                }
                val result = stackBlur(sampledImage, radius)!!
                WXLogUtils.d(
                    TAG,
                    "elapsed time on blurring image(radius:" + radius + ",sampling: " + sampling + "): " + (System.currentTimeMillis() - start) + "ms"
                )
                return result
            } catch (e: Exception) {
                WXLogUtils.e(
                    TAG,
                    "thrown exception when blurred image(times = " + i + ")," + e.message
                )
                radius -= 1
                radius = Math.max(0, radius)
            }
        }
        WXLogUtils.d(
            TAG,
            "elapsed time on blurring image(radius:" + radius + ",sampling: " + sampling + "): " + (System.currentTimeMillis() - start) + "ms"
        )
        return originalImage
    }

    private fun calculateSampling(radius: Int): Double {
        val sampling: Double
        sampling = if (radius <= 3) {
            1 / 2.toDouble()
        } else if (radius <= 8) {
            1 / 4.toDouble()
        } else {
            1 / 8.toDouble()
        }
        return sampling
    }

    fun asyncBlur(
        originalImage: Bitmap,
        radius: Int,
        listener: OnBlurCompleteListener?
    ) {
        sExecutorService.execute { listener?.onBlurComplete(blur(originalImage, radius)) }
    }

    private fun stackBlur(sentBitmap: Bitmap, radius: Int): Bitmap? {
        // Stack Blur Algorithm by Mario Klingemann <mario@quasimondo.com>
        val bitmap = sentBitmap.copy(sentBitmap.config, true)
        if (radius < 1) {
            return null
        }
        val w = bitmap.width
        val h = bitmap.height
        val pix = IntArray(w * h)
        bitmap.getPixels(pix, 0, w, 0, 0, w, h)
        val wm = w - 1
        val hm = h - 1
        val wh = w * h
        val div = radius + radius + 1
        val r = IntArray(wh)
        val g = IntArray(wh)
        val b = IntArray(wh)
        var rsum: Int
        var gsum: Int
        var bsum: Int
        var x: Int
        var y: Int
        var i: Int
        var p: Int
        var yp: Int
        var yi: Int
        var yw: Int
        val vmin = IntArray(Math.max(w, h))
        var divsum = div + 1 shr 1
        divsum *= divsum
        val dv = IntArray(256 * divsum)
        i = 0
        while (i < 256 * divsum) {
            dv[i] = i / divsum
            i++
        }
        yi = 0
        yw = yi
        val stack = Array(div) { IntArray(3) }
        var stackpointer: Int
        var stackstart: Int
        var sir: IntArray
        var rbs: Int
        val r1 = radius + 1
        var routsum: Int
        var goutsum: Int
        var boutsum: Int
        var rinsum: Int
        var ginsum: Int
        var binsum: Int
        y = 0
        while (y < h) {
            bsum = 0
            gsum = bsum
            rsum = gsum
            boutsum = rsum
            goutsum = boutsum
            routsum = goutsum
            binsum = routsum
            ginsum = binsum
            rinsum = ginsum
            i = -radius
            while (i <= radius) {
                p = pix[yi + Math.min(wm, Math.max(i, 0))]
                sir = stack[i + radius]
                sir[0] = p and 0xff0000 shr 16
                sir[1] = p and 0x00ff00 shr 8
                sir[2] = p and 0x0000ff
                rbs = r1 - Math.abs(i)
                rsum += sir[0] * rbs
                gsum += sir[1] * rbs
                bsum += sir[2] * rbs
                if (i > 0) {
                    rinsum += sir[0]
                    ginsum += sir[1]
                    binsum += sir[2]
                } else {
                    routsum += sir[0]
                    goutsum += sir[1]
                    boutsum += sir[2]
                }
                i++
            }
            stackpointer = radius
            x = 0
            while (x < w) {
                r[yi] = dv[rsum]
                g[yi] = dv[gsum]
                b[yi] = dv[bsum]
                rsum -= routsum
                gsum -= goutsum
                bsum -= boutsum
                stackstart = stackpointer - radius + div
                sir = stack[stackstart % div]
                routsum -= sir[0]
                goutsum -= sir[1]
                boutsum -= sir[2]
                if (y == 0) {
                    vmin[x] = Math.min(x + radius + 1, wm)
                }
                p = pix[yw + vmin[x]]
                sir[0] = p and 0xff0000 shr 16
                sir[1] = p and 0x00ff00 shr 8
                sir[2] = p and 0x0000ff
                rinsum += sir[0]
                ginsum += sir[1]
                binsum += sir[2]
                rsum += rinsum
                gsum += ginsum
                bsum += binsum
                stackpointer = (stackpointer + 1) % div
                sir = stack[stackpointer % div]
                routsum += sir[0]
                goutsum += sir[1]
                boutsum += sir[2]
                rinsum -= sir[0]
                ginsum -= sir[1]
                binsum -= sir[2]
                yi++
                x++
            }
            yw += w
            y++
        }
        x = 0
        while (x < w) {
            bsum = 0
            gsum = bsum
            rsum = gsum
            boutsum = rsum
            goutsum = boutsum
            routsum = goutsum
            binsum = routsum
            ginsum = binsum
            rinsum = ginsum
            yp = -radius * w
            i = -radius
            while (i <= radius) {
                yi = Math.max(0, yp) + x
                sir = stack[i + radius]
                sir[0] = r[yi]
                sir[1] = g[yi]
                sir[2] = b[yi]
                rbs = r1 - Math.abs(i)
                rsum += r[yi] * rbs
                gsum += g[yi] * rbs
                bsum += b[yi] * rbs
                if (i > 0) {
                    rinsum += sir[0]
                    ginsum += sir[1]
                    binsum += sir[2]
                } else {
                    routsum += sir[0]
                    goutsum += sir[1]
                    boutsum += sir[2]
                }
                if (i < hm) {
                    yp += w
                }
                i++
            }
            yi = x
            stackpointer = radius
            y = 0
            while (y < h) {

                // Preserve alpha channel: ( 0xff000000 & pix[yi] )
                pix[yi] =
                    -0x1000000 and pix[yi] or (dv[rsum] shl 16) or (dv[gsum] shl 8) or dv[bsum]
                rsum -= routsum
                gsum -= goutsum
                bsum -= boutsum
                stackstart = stackpointer - radius + div
                sir = stack[stackstart % div]
                routsum -= sir[0]
                goutsum -= sir[1]
                boutsum -= sir[2]
                if (x == 0) {
                    vmin[y] = Math.min(y + r1, hm) * w
                }
                p = x + vmin[y]
                sir[0] = r[p]
                sir[1] = g[p]
                sir[2] = b[p]
                rinsum += sir[0]
                ginsum += sir[1]
                binsum += sir[2]
                rsum += rinsum
                gsum += ginsum
                bsum += binsum
                stackpointer = (stackpointer + 1) % div
                sir = stack[stackpointer]
                routsum += sir[0]
                goutsum += sir[1]
                boutsum += sir[2]
                rinsum -= sir[0]
                ginsum -= sir[1]
                binsum -= sir[2]
                yi += w
                y++
            }
            x++
        }
        bitmap.setPixels(pix, 0, w, 0, 0, w, h)
        return bitmap
    }
}