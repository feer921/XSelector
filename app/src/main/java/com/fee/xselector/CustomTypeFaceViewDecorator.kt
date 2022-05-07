package com.fee.xselector

import android.util.AttributeSet
import android.view.View
import android.widget.EditText

/**
 * 对 View进行 自定义字体的装饰器
 */
class CustomTypeFaceViewDecorator(otherViewDecorator: IViewDecorator? = null) :
    AbsViewDecorator(otherViewDecorator) {


    override fun doDecorateView(theView: View, attrsOfView: AttributeSet?) {
        //todo 设置自定义字体
        if (theView is EditText) {
            theView.setHint("我来装饰一下自定义字体")
        }
        mOtherViewDecorator?.decorateView(theView, attrsOfView)
    }
}