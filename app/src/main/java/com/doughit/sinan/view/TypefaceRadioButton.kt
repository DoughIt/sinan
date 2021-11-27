package com.doughit.sinan.view

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatRadioButton
import com.doughit.sinan.R

/**
 * @Description 带有自定义字体RadioButton。
 *
 * @Copyright DoughIt Studio - Powered By DoughIt
 * @author Jerry Zhang <https://github.com/doughit>
 * @date 2021-11-26 11:38
 */
class TypefaceRadioButton : AppCompatRadioButton {

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    @SuppressLint("CustomViewStyleable")
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        attrs?.let {
            val typedArray = context.obtainStyledAttributes(it, R.styleable.TypefaceTextView, 0, 0)
            val typefaceType = typedArray.getInt(R.styleable.TypefaceTextView_typeface, 0)
            typeface = TypefaceTextView.getTypeface(typefaceType)
            typedArray.recycle()
        }
    }
}