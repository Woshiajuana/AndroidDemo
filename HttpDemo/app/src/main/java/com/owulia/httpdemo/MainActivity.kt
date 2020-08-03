package com.owulia.httpdemo

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.*
import java.lang.Exception
import java.lang.StringBuilder
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        thread {
//            Log.d("loadData:", "headerFields => ${Thread.currentThread().name}")
//            loadData("https://www.sunofbeach.net/content/content/moment/list/1153952789488054272/1")
//        }

        vButton.setOnClickListener{
//            val width = vImage.measuredWidth
//            val height = vImage.measuredHeight
//            thread {
//                loadImageData(width, height)
//            }
            asyncGet()
        }

    }

    fun asyncGet () {
        // 请求 api
        val url = "https://api.sunofbeach.net/shop/api/discovery/categories"
        // 创建 client
        val okHttpClient = OkHttpClient()
        // 创建请求内容
        val request = Request.Builder()
            .url(url)
            .get()
            .build()
        // 调用创建任务
        val call = okHttpClient.newCall(request)
        call.enqueue(object : Callback{
            override fun onFailure(call: Call, e: IOException) {
                Log.d("asyncGet:", "e => $e")
            }
            override fun onResponse(call: Call, response: Response) {
                Log.d("asyncGet:", "response => $response")
            }
        })
    }

    private fun loadImageData(with: Int, height: Int) {
        try {
            val url = URL("https://imgs.sunofbeaches.com/group1/M00/00/05/rBsADV2rEz-AIzSoAABi-6nfiqs456.png")
            val urlConnection = url.openConnection() as HttpURLConnection
            urlConnection.apply {
                requestMethod = "GET"
                connectTimeout = 1000
                connect()
            }
            val inputStream = urlConnection.inputStream
            val out = ByteArrayOutputStream()
            val buffer = ByteArray(1024)
            var len = 0;
            while (len.let {
                    len = inputStream.read(buffer)
                    len
                } > -1) {
                out.write(buffer, 0, len)
            }
            out.flush()
            out.close()
            val byteArray = out.toByteArray()
            val is1 = ByteArrayInputStream(byteArray)
            val is2 = ByteArrayInputStream(byteArray)
            val options = BitmapFactory.Options()
            options.inJustDecodeBounds = true // true 不载入内存中
            BitmapFactory.decodeStream(is1, null, options)
            Log.d("loadData:", "outWidth => ${options.outWidth}")
            Log.d("loadData:", "outHeight => ${options.outHeight}")
            var sampleSize = 1
            if (with < options.outWidth || height < options.outHeight) {
                val scaleX = options.outWidth / with
                val scaleY = options.outHeight / height
                sampleSize = if (scaleX > scaleY) scaleX else scaleY
            }
            options.inSampleSize = sampleSize
            options.inJustDecodeBounds = false
            val bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size, options)
            runOnUiThread {
                vImage.setImageBitmap(bitmap)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun loadData (baseUrl: String, params: Map<String, String>? = null) {
        try {
            val sb = StringBuilder(baseUrl)
            if (params != null && params.isNotEmpty()) {
                sb.append("?")
                params.forEach{
                    sb.append(it.key)
                    sb.append("=")
                    sb.append(it.value)
                }
            }
            val url = URL(sb.toString())
            val urlConnection = url.openConnection() as HttpURLConnection
            urlConnection.apply {
                requestMethod = "GET"
                connectTimeout = 1000
                setRequestProperty("accept", "*/*")
                setRequestProperty("connection", "keep-alive")
                setRequestProperty("Accept-Language", "zh-CN,zh")
            }
            // 开始链接
            urlConnection.connect()
            //把数据给到服务
//            val outputStream = urlConnection.getOutputStream();
//            outputStream.write(bytes);
//            outputStream.flush();


            val headerFields = urlConnection.headerFields
            Log.d("loadData:", "headerFields => ${headerFields}")
            val inputStream = urlConnection.inputStream
            val bufferedReader = BufferedReader(InputStreamReader(inputStream, "UTF-8"))
            var line: String? = ""
            while ((line.let {
                        line = bufferedReader.readLine()
                        line
                    }) != null) {
                Log.d("loadData:", "line => ${line}")
            }
            bufferedReader.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    fun postRequest () {



//        try {
//
//
//
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }

    }
}
