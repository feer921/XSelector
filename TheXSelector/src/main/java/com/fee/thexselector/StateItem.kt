package com.fee.thexselector

/**
 * ******************(^_^)***********************<br>
 * User: fee(QQ/WeiXin:1176610771)<br>
 * Date: 2020/8/17<br>
 * Time: 16:31<br>
 * <P>DESC:
 * 对应 <selector>
 *      <item
 *      android:state_pressed="false"
 *      android:state_enabled="true"
        android:state_accelerated="false"
        android:state_checked="false"

        android:drawable="@color/colorPrimary"
        />
 * </selector>
 * 中的一个 item配置
 * </p>
 * ******************(^_^)***********************
 */
open class StateItem<SVT>(private var stateValue: SVT?) {

    /**
     * 本 item的状态属性集，因为可以多个状态属性
     * 如果本状态属性为 null或者 empty，则表示默认的 item
     */
    private val stateAttrs: HashSet<Int> = hashSetOf()

    fun stateChecked(checked: Boolean) : StateItem<SVT>{
        val checkedState = StateAttr.CHECKED.atrrValue
        val addedStateValue = if(checked) checkedState else -checkedState
        stateAttrs.add(addedStateValue)
        stateAttrs.remove(-addedStateValue)//表示如果添加相同状态下的一个值，那么必须把该状态下的反状态值给去除，因为不支持
        //android:state_checked="false" 又 android:state_checked="true"
        return this
    }
    fun statePressed(pressed: Boolean): StateItem<SVT> {
        val pressedState = StateAttr.PRESSED.atrrValue
        val addedStateValue = if(pressed) pressedState else -pressedState
        stateAttrs.add(addedStateValue)
        stateAttrs.remove(-addedStateValue)//表示如果添加相同状态下的一个值，那么必须把该状态下的反状态值给去除，因为不支持
        return this
    }
    fun stateFocused(focused: Boolean): StateItem<SVT>{
        val defState = StateAttr.FOCUSED.atrrValue
        val addedStateValue = if (focused) defState else -defState
        stateAttrs.add(addedStateValue)
        stateAttrs.remove(-addedStateValue)//表示如果添加相同状态下的一个值，那么必须把该状态下的反状态值给去除，因为不支持
        return this
    }
    fun stateEnabled(enabled: Boolean): StateItem<SVT>{
        val defState = StateAttr.ENABLED.atrrValue
        val addedStateValue = if (enabled) defState else -defState
        stateAttrs.add(addedStateValue)
        stateAttrs.remove(-addedStateValue)//表示如果添加相同状态下的一个值，那么必须把该状态下的反状态值给去除，因为不支持
        return this
    }

    fun stateSelected(selected: Boolean): StateItem<SVT>{
        val defState = StateAttr.SELECTED.atrrValue
        val addedStateValue = if (selected) defState else -defState
        stateAttrs.add(addedStateValue)
        stateAttrs.remove(-addedStateValue)//表示如果添加相同状态下的一个值，那么必须把该状态下的反状态值给去除，因为不支持
        return this
    }

    /**
     * 注：该状态不常用
     */
    fun stateActivated(activated: Boolean): StateItem<SVT>{
        val defState = StateAttr.ACTIVATED.atrrValue
        val addedStateValue = if (activated) defState else -defState
        stateAttrs.add(addedStateValue)
        stateAttrs.remove(-addedStateValue)//表示如果添加相同状态下的一个值，那么必须把该状态下的反状态值给去除，因为不支持
        return this
    }

    /**
     * 注：该状态不常用
     */
    fun stateHovered(hovered: Boolean): StateItem<SVT>{
        val defState = StateAttr.HOVERED.atrrValue
        val addedStateValue = if (hovered) defState else -defState
        stateAttrs.add(addedStateValue)
        stateAttrs.remove(-addedStateValue)//表示如果添加相同状态下的一个值，那么必须把该状态下的反状态值给去除，因为不支持
        return this
    }
    /**
     * 注：该状态不常用
     */
    fun stateCheckable(checkable: Boolean): StateItem<SVT>{
        val defState = StateAttr.CHECKABLE.atrrValue
        val addedStateValue = if (checkable) defState else -defState
        stateAttrs.add(addedStateValue)
        stateAttrs.remove(-addedStateValue)//表示如果添加相同状态下的一个值，那么必须把该状态下的反状态值给去除，因为不支持
        return this
    }

    /**
     * 注：该状态不常用
     */
    fun stateWindowFocused(windowFocused: Boolean): StateItem<SVT>{
        val defState = StateAttr.WINDOW_FOCUSED.atrrValue
        val addedStateValue = if (windowFocused) defState else -defState
        stateAttrs.add(addedStateValue)
        stateAttrs.remove(-addedStateValue)//表示如果添加相同状态下的一个值，那么必须把该状态下的反状态值给去除，因为不支持
        return this
    }
    /**
     * 注：该状态不常用
     */
    fun stateAboveAnchor(aboveAnchor: Boolean): StateItem<SVT>{
        val defState = StateAttr.ABOVE_ANCHOR.atrrValue
        val addedStateValue = if (aboveAnchor) defState else -defState
        stateAttrs.add(addedStateValue)
        stateAttrs.remove(-addedStateValue)//表示如果添加相同状态下的一个值，那么必须把该状态下的反状态值给去除，因为不支持
        return this
    }


    fun stateValue(finalStateValue: SVT?) {
        this.stateValue = finalStateValue
    }

    fun collectItemAttrsValueInfo(): Pair<IntArray, SVT?> {
        return Pair(stateAttrs.toIntArray(), stateValue)
    }
}