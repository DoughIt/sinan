package com.doughit.sinan.view

import android.app.Dialog
import android.content.Context
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.annotation.StringRes
import com.doughit.sinan.R
import com.doughit.sinan.databinding.DialogLoadingBinding

/**
 * @Description 自定义加载中界面
 *
 * @Copyright DoughIt Studio - Powered By DoughIt
 * @author Jerry Zhang <https://github.com/doughit>
 * @date 2021-11-23 12:30
 */
class LoadingDialog(mContext: Context) : Dialog(mContext, R.style.LoadingDialogTheme) {
    private val mBinding = DialogLoadingBinding.inflate(layoutInflater)
    private var animation: Animation? = null

    init {
        setContentView(mBinding.root)
        animation = AnimationUtils.loadAnimation(context, R.anim.loading_dialog)
    }

    fun show(hintText: String?, cancelable: Boolean, canceledOnTouchOutside: Boolean) {
        setCancelable(cancelable)
        setCanceledOnTouchOutside(canceledOnTouchOutside)
        mBinding.tvHint.text = hintText
        mBinding.ivLoading.startAnimation(animation)
        show()
    }

    fun show(@StringRes hintTextRes: Int, cancelable: Boolean, canceledOnTouchOutside: Boolean) {
        setCancelable(cancelable)
        setCanceledOnTouchOutside(canceledOnTouchOutside)
        mBinding.tvHint.setText(hintTextRes)
        mBinding.ivLoading.startAnimation(animation)
        show()
    }

    override fun cancel() {
        super.cancel()
        animation!!.cancel()
        mBinding.ivLoading.clearAnimation()
    }

    override fun dismiss() {
        super.dismiss()
        animation!!.cancel()
        mBinding.ivLoading.clearAnimation()
    }

}