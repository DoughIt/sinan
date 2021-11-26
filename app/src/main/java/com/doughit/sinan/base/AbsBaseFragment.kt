package com.doughit.sinan.base

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions
import java.lang.reflect.ParameterizedType

/**
 * @Description Fragment基类
 *
 * @Copyright DoughIt Studio - Powered By DoughIt
 * @author Jerry Zhang <https://github.com/doughit>
 * @date 2021-11-23 12:29
 */
abstract class AbsBaseFragment<T : ViewBinding> : Fragment(), EasyPermissions.PermissionCallbacks {
    private var isViewPrepare = false
    private var hasLoadData = false

    protected lateinit var binding: T

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val clazz =
            (this.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<*>
        val method = clazz.getDeclaredMethod("inflate", LayoutInflater::class.java)

        binding = method.invoke(null, layoutInflater) as T
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isViewPrepare = true

        initView()
        initListener()
        loadDataIfPrepared()
    }

    open val mRetryClickListener = View.OnClickListener {
        loadData()
    }

    private fun loadDataIfPrepared() {
        if (isViewPrepare && !hasLoadData) {
            loadData()
            hasLoadData = true
        }
    }

    /**
     * 初始化 view，绑定 presenter
     */
    abstract fun initView()

    /**
     * 监听事件
     */
    abstract fun initListener()

    /**
     * 加载数据
     */
    abstract fun loadData()

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
        Log.i("权限", "获取成功的权限$perms")
    }

    /**
     * 当运行时权限申请失败时回调
     */
    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        //当用户点击拒绝并不再询问时调用
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            Toast.makeText(activity, "已拒绝权限：$perms\n并不再询问", Toast.LENGTH_SHORT).show()
            with(AppSettingsDialog.Builder(this)) {
                setRationale("此应用程序需要$perms 权限，否则无法正常使用，是否打开设置")
                setPositiveButton("是")
                setNegativeButton("否")
                build().show()
            }
        }
    }
}