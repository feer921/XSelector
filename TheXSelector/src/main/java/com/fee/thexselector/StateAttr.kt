package com.fee.thexselector

/**
 * ******************(^_^)***********************<br>
 * User: fee(QQ/WeiXin:1176610771)<br>
 * Date: 2020/8/19<br>
 * Time: 14:07<br>
 * <P>DESC:
 * 穷举安卓原生支持的 状态属性对应的值，默认为状态为 true的状态值
 * eg.:
 * android:state_checked="true"
 * 如果要为 false的值，则为对应的 true时的值的 负值
 * android:state_checked="false"
 * </p>
 * ******************(^_^)***********************
 */
enum class StateAttr(val atrrValue: Int) {
    /**
     * 控件 checked 的状态的属性值
     */
    CHECKED(android.R.attr.state_checked),

    /**
     * 控件 pressed 的状态的属性值
     */
    PRESSED(android.R.attr.state_pressed),

    /**
     * 控件 focused(获取了焦点时) 的状态的属性值
     */
    FOCUSED(android.R.attr.state_focused),

    /**
     * 控件 enabled(使能的情况下) 的状态的属性值
     */
    ENABLED(android.R.attr.state_enabled),
//    /**
//     * 控件 disabled(不能点击的情况下) 的状态的属性值
//     */
//    DISABLED(-android.R.attr.state_enabled),

    /**
     * 控件 selected 的状态的属性值
     */
    SELECTED(android.R.attr.state_selected),

    /**
     * 控件 activated (激活了的) 的状态的属性值
     */
    ACTIVATED(android.R.attr.state_activated),

    /**
     * 控件 在被 如鼠标 悬停时的 状态的属性值
     */
    HOVERED(android.R.attr.state_hovered),

    CHECKABLE(android.R.attr.state_checkable),
    WINDOW_FOCUSED(android.R.attr.state_window_focused),
    ABOVE_ANCHOR(android.R.attr.state_above_anchor)
}