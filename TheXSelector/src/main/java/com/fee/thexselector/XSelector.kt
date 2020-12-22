package com.fee.thexselector

import android.content.Context

/**
 * ******************(^_^)***********************<br>
 * User: fee(QQ/WeiXin:1176610771)<br>
 * Date: 2020/8/17<br>
 * Time: 17:20<br>
 * <P>DESC:
 * </p>
 * ******************(^_^)***********************
 */
class XSelector private constructor(){
    private lateinit var mContext: Context

    companion object Instance{
        @JvmStatic
        val me:XSelector by lazy {
            XSelector()
        }
    }

    fun init(context: Context) {
        this.mContext = context
    }

    fun colorSelector(): ColorSelector {
        return ColorSelector()
    }

    fun shapeSelector(): ShapeSelector {
        return ShapeSelector()
    }

    fun getContext(): Context? {
        return mContext
    }


}