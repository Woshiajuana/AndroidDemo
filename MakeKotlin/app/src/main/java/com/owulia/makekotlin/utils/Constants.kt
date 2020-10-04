package com.owulia.makekotlin.utils

class Constants {

    companion object {

        // 接口 code
        const val CODE_SUCCESS = 0

        const val TENANT_ID = "0"

        const val ENCRYPT_KEY = "shrewshrewshrews"

        /**
         * 请求接口地址
         * */
        const val BASE_URL = "https://gw.jfpays.com/"

        // 注册账号检查服务
        const val DO_CHECK_ACCOUNT = "upcs/user/before/loginNo/check"

        // 登录接口
        const val DO_USER_LOGIN = "auth/oauth/token"

        // 用户注册
        const val DO_USER_REGISTER = "upcs/user/before/regist"

        // 用户忘记密码
        const val DO_USER_RESET_PASSWORD = "upcs/user/before/modify/password"

        // 发送短信
        const val DO_SEND_SMS = "upcs/sms/before/send"

        // 用户信息
        const val REQ_USER_INFO = "upcs/user/info/query"


        /**
         * 账号 key
         * */
        const val KEY_ACCOUNT = "key_account"

        /**
         * webview 参数配置 key
         * */
        const val KEY_WEB_VIEW_OPTION_MODEL = "key_web_view_option_model"

        /**
         * 用户信息 key
         * */
        const val JSON_CACHE_KEY_USER = "json_cache_key_user"

        /**
         * json  缓存 总 key
         * */
        const val JSON_CACHE_KEY_COMMON = "json_cache_key_common"

        /**
         * 是否第一次进入 app
         * */
        const val JSON_CACHE_KEY_FIRST_OPEN = "json_cache_key_first_open"

        /**
         * 引导页 fragment 参数 key
         * */
        const val KEY_GUIDE_PAGE_MODEL = "key_guide_page_model"

    }
}