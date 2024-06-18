package com.ohshell.headline.utils

import java.util.Calendar

object OhShellDateUtil {
    fun currentYear(): Int {
        return Calendar.getInstance().get(Calendar.YEAR)
    }
}