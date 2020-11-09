package com.owulia.user.ui.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.view.View
import com.bigkoo.alertview.AlertView
import com.bigkoo.alertview.OnItemClickListener
import com.jph.takephoto.app.TakePhoto
import com.jph.takephoto.app.TakePhotoImpl
import com.jph.takephoto.compress.CompressConfig
import com.jph.takephoto.model.TResult
import com.jph.takephoto.permission.PermissionManager
import com.owulia.base.ext.onClick
import com.owulia.base.ui.activity.BaseMvpActivity
import com.owulia.base.utils.DateUtils
import com.owulia.user.R
import com.owulia.user.data.protocol.UserInfo
import com.owulia.user.injection.component.DaggerUserComponent
import com.owulia.user.injection.module.UserModule
import com.owulia.user.presenter.UserInfoPresenter
import com.owulia.user.presenter.view.UserInfoView
import kotlinx.android.synthetic.main.activity_user_info.*
import org.jetbrains.anko.toast
import java.io.File

class UserInfoActivity : BaseMvpActivity<UserInfoPresenter>(),
    UserInfoView,
    View.OnClickListener,
    TakePhoto.TakeResultListener
{

    private lateinit var mTakePhoto: TakePhoto
    private lateinit var mTempFile: File

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_info)

        mTakePhoto = TakePhotoImpl(this, this)
        mTakePhoto.onCreate(savedInstanceState)

        initView()

    }

    private fun initView() {
        mUserIconIv.onClick(this)
    }

    override fun onLoginResult(result: UserInfo) {
        toast("$result")
    }

    override fun injectComponent() {
        DaggerUserComponent
            .builder()
            .activityComponent(activityComponent)
            .userModule(UserModule())
            .build()
            .inject(this)
        mPresenter.mView = this
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.mUserIconIv -> {
                showAlertView()
            }
        }
    }

    private fun showAlertView() {
        AlertView(
            "选择图片",
            "",
            "取消",
            null,
            arrayOf("拍照", "相册"),
            this,
            AlertView.Style.ActionSheet,
            OnItemClickListener { _, position ->
//                mTakePhoto.onEnableCompress(CompressConfig.ofDefaultConfig(), false)
                when(position) {
                    0 -> {
                        createTempFile()
                        mTakePhoto.onPickFromCapture(Uri.fromFile(mTempFile))
                    }
                    1 -> mTakePhoto.onPickFromGallery()
                }
            }
        ).show()
    }

    override fun takeSuccess(result: TResult?) {
        println("takeSuccess originalPath => ${result?.image?.originalPath}")
        println("takeSuccess compressPath => ${result?.image?.compressPath}")
    }

    override fun takeCancel() {
    }

    override fun takeFail(result: TResult?, msg: String?) {
        println("takeFail => $msg")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        mTakePhoto.onActivityResult(requestCode, resultCode, data)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        val type = PermissionManager.onRequestPermissionsResult(requestCode, permissions, grantResults)
//        PermissionManager.handlePermissionsResult(this, type, invokeParam,this);
    }



    private fun createTempFile () {
        val tempFileName = "${DateUtils.curTime}.png"
        if (Environment.MEDIA_MOUNTED == Environment.getExternalStorageState()) {
            mTempFile = File(Environment.getExternalStorageDirectory(), tempFileName)
        } else {
            mTempFile = File(filesDir, tempFileName)
        }
        if (!mTempFile.exists()){
            mTempFile.mkdirs();
        }
    }

}
