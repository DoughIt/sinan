package com.doughit.sinan

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.doughit.sinan.util.GlobalUtil
import com.doughit.sinan.view.NoStatusFooter
import com.scwang.smart.refresh.header.MaterialHeader
import com.scwang.smart.refresh.layout.SmartRefreshLayout

class SiNanApplication : Application() {
    companion object{
        @SuppressLint("StaticFieldLeak")
        lateinit var mContext: Context
    }


    init {
        SmartRefreshLayout.setDefaultRefreshInitializer { context, layout ->
            layout.setEnableLoadMore(true)
            layout.setEnableLoadMoreWhenContentNotFull(true)
        }

        SmartRefreshLayout.setDefaultRefreshHeaderCreator { context, layout ->
            layout.setEnableHeaderTranslationContent(true)
            MaterialHeader(context).setColorSchemeResources(R.color.blue, R.color.blue, R.color.blue)
        }

        SmartRefreshLayout.setDefaultRefreshFooterCreator { context, layout ->
            layout.setEnableFooterFollowWhenNoMoreData(true)
            layout.setEnableFooterTranslationContent(true)
            layout.setFooterHeight(153f)
            layout.setFooterTriggerRate(0.6f)
            NoStatusFooter.REFRESH_FOOTER_NOTHING = GlobalUtil.getString(R.string.footer_not_more)
            NoStatusFooter(context).apply {
                setAccentColorId(R.color.colorTextPrimary)
                setTextTitleSize(16f)
            }
        }
    }
    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()
        mContext = applicationContext
    }
}