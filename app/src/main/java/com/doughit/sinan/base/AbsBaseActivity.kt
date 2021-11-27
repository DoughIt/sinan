package com.doughit.sinan.base

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.doughit.sinan.view.LoadingDialog
import com.gyf.immersionbar.ImmersionBar
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions
import java.lang.reflect.ParameterizedType

/**
 * @Description Activity基类
 *
 * @Copyright DoughIt Studio - Powered By DoughIt
 * @author Jerry Zhang <https://github.com/doughit>
 * @date 2021-11-23 12:28
 */
abstract class AbsBaseActivity<T : ViewBinding> : AppCompatActivity(),
    EasyPermissions.PermissionCallbacks {
    protected lateinit var binding: T
    protected var loadingDialog: LoadingDialog? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val clazz =
            (this.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<*>
        val method = clazz.getDeclaredMethod("inflate", LayoutInflater::class.java)

        binding = method.invoke(null, layoutInflater) as T
        setContentView(binding.root)
        initData()
        initView()
        initListener()
        start()
    }


    /**
     * 初始化数据
     */
    abstract fun initData()

    /**
     * 初始化View，绑定Presenter
     */
    abstract fun initView()

    /**
     * 监听事件
     */
    abstract fun initListener()


    /**
     * 开始请求
     */
    abstract fun start()

    /**
     * 申请运行时权限
     */
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    /**
     * 当运行时权限申请成功时调用
     */
    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {

    }

    /**
     * 当运行时权限申请失败时回调
     */
    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        //当用户点击拒绝并不再询问时调用
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            Toast.makeText(
                this, "已拒绝权限：${
                    StringBuilder().apply {
                        for (perm in perms) {
                            append(perm).append("\n")
                        }
                        replace(length - 2, length, "")
                    }
                }\n并不再询问", Toast.LENGTH_SHORT
            ).show()
            with(AppSettingsDialog.Builder(this)) {
                setRationale("此应用程序需要$perms 权限，否则无法正常使用，是否打开设置")
                setPositiveButton("是")
                setNegativeButton("否")
                build().show()
            }
        }
    }

    protected open fun showLoadingDialog(message: String?) {
        if (loadingDialog == null) {
            loadingDialog = LoadingDialog(this)
        }
        loadingDialog?.show(message, cancelable = true, canceledOnTouchOutside = false)
    }

    protected open fun dismissLoadingDialog() {
        if (loadingDialog != null) {
            loadingDialog?.dismiss()
        }
    }


    /**
     * 设置状态栏背景色
     */
    open fun setStatusBarBackground(@ColorRes statusBarColor: Int) {
        ImmersionBar.with(this).autoStatusBarDarkModeEnable(true, 0.2f).statusBarColor(statusBarColor).fitsSystemWindows(true).init()
    }

}