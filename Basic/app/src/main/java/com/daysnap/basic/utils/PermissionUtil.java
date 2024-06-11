package com.daysnap.basic.utils;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class PermissionUtil {
    // 检查多个权限，返回 true 表示已完全
    public static boolean checkPermission(Activity act, String[] permissions, int requestCode) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int check = PackageManager.PERMISSION_GRANTED;
            for (String permission : permissions) {
                check = ContextCompat.checkSelfPermission(act, permission);
                if (check != PackageManager.PERMISSION_GRANTED) {
                    break;
                }
            }

            if (check != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(act, permissions, requestCode);
                return false;
            }
        }
        return true;
    }

    // 检查权限结果数组，返回 true 表示都已经获得授权
    // 返回 false 表示至少有一个未获得权限
    public static boolean checkGrant(int[] grantResults) {
        if (grantResults != null) {
            for (int grant: grantResults) {
                if (grant != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
