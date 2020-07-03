package com.owulia.makekotlin.adapter

import org.apache.weex.adapter.IWXJSExceptionAdapter
import org.apache.weex.common.WXJSExceptionInfo
import org.apache.weex.utils.WXLogUtils

class JSExceptionAdapter : IWXJSExceptionAdapter {
    override fun onJSException(wxjsExceptionInfo: WXJSExceptionInfo?) {
        if (wxjsExceptionInfo != null) {
            WXLogUtils.d(wxjsExceptionInfo.toString())
        }
    }
}