package com.doughit.sinan.ui

import android.annotation.SuppressLint
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import androidx.lifecycle.lifecycleScope
import com.doughit.sinan.base.AbsBaseActivity
import com.doughit.sinan.databinding.ActivitySplashBinding
import com.doughit.sinan.util.DataStoreUtil
import kotlinx.coroutines.*


/**
 * @Description 闪屏页面，应用程序首次启动入口。
 *
 * @Copyright DoughIt Studio - Powered By DoughIt
 * @author Jerry Zhang <https://github.com/doughit>
 * @date 2021-11-26 18:48
 */
@SuppressLint("CustomSplashScreen")
class SplashActivity : AbsBaseActivity<ActivitySplashBinding>() {
    private val splashDuration = 3 * 1000L

    private val alphaAnimation by lazy {
        AlphaAnimation(0.5f, 1.0f).apply {
            duration = splashDuration
            fillAfter = true
        }
    }

    private val scaleAnimation by lazy {
        ScaleAnimation(1f, 1.05f, 1f, 1.05f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f).apply {
            duration = splashDuration
            fillAfter = true
        }
    }

    /**
     * 初始化数据
     */
    override fun initData() {

    }

    /**
     * 初始化View，绑定Presenter
     */
    override fun initView() {
    }

    /**
     * 监听事件
     */
    override fun initListener() {
    }

    /**
     * 开始请求
     */
    override fun start() {
        binding.ivSlogan.startAnimation(alphaAnimation)
        binding.ivSplashPicture.startAnimation(scaleAnimation)
        lifecycleScope.launch {
            delay(splashDuration)
            MainActivity.actionStart(this@SplashActivity)
            finish()
        }
        isFirstEntryApp = false
    }


    companion object {

        /**
         * 是否首次进入APP应用
         */
        var isFirstEntryApp: Boolean
            get() = DataStoreUtil.readBooleanData("is_first_entry_app", true)
            set(value) {
                CoroutineScope(Dispatchers.IO).launch { DataStoreUtil.saveBooleanData("is_first_entry_app", value) }
            }
    }
}
