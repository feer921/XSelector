package com.fee.xselector

import android.util.AttributeSet
import android.view.View

/**
 * View 的装饰器接口
 */
interface IViewDecorator {
    /**
     * 装饰 [theView]
     */
    fun decorateView(theView: View, attrsOfView: AttributeSet?)
}