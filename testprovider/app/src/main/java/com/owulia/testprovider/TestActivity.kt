package com.owulia.testprovider

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_test.*

class TestActivity : AppCompatActivity() {

    companion object {
        const val PERMISSION_MEDIA_REQUEST_CODE = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)


        vButton.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                val redPermission = checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                if (redPermission == PackageManager.PERMISSION_GRANTED) {
                    // 有权限
                    getMediaInfo()
                } else {
                    // 没有权限
                    // 做个提示，用户点击了确定了之后再去请求调用权限
                    // 如果点击了不再提示，就不再获取了
                    // 如果不能使用则直接退出
                    requestPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                        PERMISSION_MEDIA_REQUEST_CODE
                    )
                }
            } else {
                getMediaInfo()
            }
        }
    }

    private fun getMediaInfo () {

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        println("onRequestPermissionsResult => requestCode = $requestCode")
        println("onRequestPermissionsResult => permissions = $permissions")
        println("onRequestPermissionsResult => grantResults = ${grantResults[0]}")
        if (requestCode == PERMISSION_MEDIA_REQUEST_CODE) {
            if (grantResults.size == 2 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                // 有权限
                getMediaInfo()
            } else {
                // 没权限
//                finish()
            }
        }
    }
}
