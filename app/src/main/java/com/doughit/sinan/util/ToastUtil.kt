package com.doughit.sinan.util

import android.content.Context
import android.os.Looper
import android.widget.Toast
import com.doughit.sinan.SiNanApplication

/**
 * @Description 利用扩展函数扩展toast调用方式
 *
 * @Copyright DoughIt Studio - Powered By DoughIt
 * @author Jerry Zhang <https://github.com/doughit>
 * @date 2021-11-23 12:23
 */
object ToastUtil {
    /**
     * 分步设置显示内容，解决个别手机调用Toast会自动添加应用名问题
     */
    fun String.showToast(context: Context = SiNanApplication.mContext) {
        try {
            Toast.makeText(context, this, Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            // 解决在子线程中无法调用 Toast 的异常
            Looper.prepare()
            Toast.makeText(context, this, Toast.LENGTH_SHORT).show()
            Looper.loop()
        }
    }

    /**
     * 分步设置显示内容，解决个别手机调用Toast会自动添加应用名问题
     */
    fun String.showLongToast(context: Context = SiNanApplication.mContext) {
        try {
            Toast.makeText(context, this, Toast.LENGTH_LONG).show()
        } catch (e: Exception) {
            // 解决在子线程中无法调用 Toast 的异常
            Looper.prepare()
            Toast.makeText(context, this, Toast.LENGTH_LONG).show()
            Looper.loop()
        }
    }

    fun String.showToastInSubThread(
        text: String,
        context: Context = SiNanApplication.mContext
    ) {
        Thread {
            this.showToast(context)
        }.start()
    }
}