package com.doughit.sinan.base

import android.content.Context
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/**
 * @Description RecycleView通用Adapter类
 *
 * @Copyright DoughIt Studio - Powered By DoughIt
 * @author Jerry Zhang <https://github.com/doughit>
 * @date 2021-11-23 12:29
 */
abstract class CommonAdapter<T>(var mContext: Context, var mDataList: ArrayList<T>, private var mLayoutId: Int) :
    RecyclerView.Adapter<CommonAdapter.ViewHolder>() {

    private var mInflater: LayoutInflater? = null
    private var mItemClickListener: AdapterView.OnItemClickListener? = null
    private var mMultipleType: MultipleType<T>? = null

    init {
        mInflater = LayoutInflater.from(mContext)
    }

    constructor(context: Context, dataList: ArrayList<T>, multipleType: MultipleType<T>) : this(context, dataList, -1) {
        this.mMultipleType = multipleType
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        if (mMultipleType != null)
            mLayoutId = p1
        return with(mInflater?.inflate(mLayoutId, p0, false)) {
            ViewHolder(this!!)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return mMultipleType?.getLayoutId(mDataList[position], position) ?: super.getItemViewType(position)
    }

    override fun getItemCount(): Int = mDataList.size

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        bindData(p0, mDataList[p1], p1)
    }

    protected abstract fun bindData(holder: ViewHolder, data: T, position: Int)


    @Suppress("UNCHECKED_CAST")
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var mView: SparseArray<View>? = null

        init {
            mView = SparseArray()
        }

        fun <T : View> getView(viewId: Int): T {
            var view = mView?.get(viewId)
            if (view == null) {
                view = itemView.findViewById(viewId)
                mView?.put(viewId, view)
            }
            return view as T
        }

        fun <T : ViewGroup> getViewGroup(viewId: Int): T {
            var view = mView?.get(viewId)
            if (view == null) {
                view = itemView.findViewById(viewId)
                mView?.put(viewId, view)
            }
            return view as T
        }

        fun setText(viewId: Int, text: CharSequence): ViewHolder {
            getView<TextView>(viewId).text = text
            return this
        }

        fun setHintText(viewId: Int, text: CharSequence): ViewHolder {
            getView<TextView>(viewId).hint = text
            return this
        }

        fun setImageResource(viewId: Int, resId: Int): ViewHolder {
            getView<ImageView>(viewId).setImageResource(resId)
            return this
        }

        fun setImagePath(viewId: Int, imageLoader: HolderImageLoader): ViewHolder {
            imageLoader.loadImage(getView(viewId), imageLoader.path)
            return this
        }


        abstract class HolderImageLoader(val path: String?) {

            /**
             * 需要去复写这个方法加载图片
             *
             * @param iv
             * @param path
             */
            abstract fun loadImage(iv: ImageView, path: String?)
        }

        fun setViewVisibility(viewId: Int, visibility: Int): ViewHolder {
            getView<View>(viewId).visibility = visibility
            return this
        }

        fun setOnItemClickListener(listener: View.OnClickListener) {
            itemView.setOnClickListener(listener)
        }

    }

    interface MultipleType<in T> {
        fun getLayoutId(item: T, position: Int): Int
    }
}