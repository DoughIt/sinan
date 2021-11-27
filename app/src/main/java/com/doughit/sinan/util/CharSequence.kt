package com.doughit.sinan.util

import android.content.Context
import android.os.Looper
import android.widget.Toast
import com.doughit.sinan.SiNanApplication

/**
 * 弹出Toast提示。
 *
 * @param duration 显示消息的时间  Either {@link #LENGTH_SHORT} or {@link #LENGTH_LONG}
 */
fun CharSequence.showToast(duration: Int = Toast.LENGTH_SHORT) {
    // 解决在子线程中无法调用 Toast 的异常
    Looper.prepare()
    Toast.makeText(SiNanApplication.mContext, this, duration).show()
    Looper.loop()
}

fun CharSequence.showToastInSubThread(duration: Int = Toast.LENGTH_SHORT) {
    Thread {
        this.showToast(duration)
    }.start()
}