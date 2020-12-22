package com.fee.thexselector

import android.graphics.drawable.Drawable
import android.view.View

/**
 * ******************(^_^)***********************<br>
 * User: fee(QQ/WeiXin:1176610771)<br>
 * <P>DESC:
 * </p>
 * ******************(^_^)***********************
 */
class ShapeSelector : WithColorSelector<Drawable, View, ShapeSelector>() {

    /**
     * 将 本Selector构建的 状态选择对象设置给[theEffectedView]
     * @param theEffectedView 要适用各状态 Selector的 View
     */
    override fun into(theEffectedView: View) {
        TODO("Not yet implemented")
    }

    /**
     * 构建出 stateResult
     * @return [SR] 构建出的 状态集结果对象
     */
    override fun build(): Drawable? {
        TODO("Not yet implemented")
    }
}