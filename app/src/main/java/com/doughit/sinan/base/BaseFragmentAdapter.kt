package com.doughit.sinan.base

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter


class BaseFragmentAdapter(
    fm: FragmentManager,
    fragmentList: List<Fragment>? = ArrayList(),
    private var mTitles: List<String>? = null
) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    private var mFragmentList: List<Fragment>? = ArrayList()

    // 刷新
    init {
        if (mFragmentList != null) {
            val ft = fm.beginTransaction()
            mFragmentList?.forEach { ft.remove(it) }
            ft.commitAllowingStateLoss()
            fm.executePendingTransactions()
        }
        mFragmentList = fragmentList
        notifyDataSetChanged()
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return if (null != mTitles) mTitles!![position] else ""
    }

    override fun getItem(p0: Int): Fragment {
        return mFragmentList!![p0]
    }

    override fun getCount(): Int {
        return mFragmentList!!.size
    }
}