package com.fee.thexselector

import android.view.View

/**
 * ******************(^_^)***********************<br>
 * User: fee(QQ/WeiXin:1176610771)<br>
 * Date: 2020/8/17<br>
 * Time: 17:59<br>
 * <P>DESC:
 * </p>
 * ******************(^_^)***********************
 */
interface ISelector<SR, V : View,I> {
    /**
     * 构建出 stateResult
     * @return [SR] 构建出的 状态集结果对象
     */
    fun build(): SR?

    /**
     * 将 本Selector构建的 状态选择对象设置给[theEffectedView]
     * @param theEffectedView 要适用各状态 Selector的 View
     * @param isNeedReBuild 是否需要重新构建出 [selector] 结果
     */
    fun into(theEffectedView: V,isNeedReBuild: Boolean = false): I
}