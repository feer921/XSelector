package com.fee.xselector

import android.util.AttributeSet
import android.view.View

/**
 */
abstract class AbsViewDecorator(protected val mOtherViewDecorator: IViewDecorator?) : IViewDecorator {

    /**
     * 装饰 [theView]
     */
    override fun decorateView(theView: View, attrsOfView: AttributeSet?) {
        mOtherViewDecorator?.decorateView(theView, attrsOfView)
    }

    /**
     * 执行 装饰 [theView]
     */
    open fun doDecorateView(theView: View, attrsOfView: AttributeSet?) {
        mOtherViewDecorator?.decorateView(theView, attrsOfView)

    }
}