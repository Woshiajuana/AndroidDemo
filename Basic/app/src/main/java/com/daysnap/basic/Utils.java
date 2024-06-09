package com.daysnap.basic;

import android.content.Context;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
    // 根据手机的分辨率从 dp 的单位转成 px 像素
    // px = dip * dpi / 160
    public static int dip2px(Context context, float value) {
        // 获取当前手机的像素密度（1个dp对应几个px）
        float scale = context.getResources().getDisplayMetrics().density;
        // 四舍五入取整
        return (int) (value * scale + 0.5f);
    }


    // 格式化当前时间
    public static String getNowTime () {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        return sdf.format(new Date());
    }
}
