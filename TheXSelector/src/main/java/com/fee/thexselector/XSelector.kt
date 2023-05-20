package com.fee.thexselector

import android.content.Context
import android.graphics.drawable.Drawable

/**
 * ******************(^_^)***********************<br>
 * User: fee(QQ/WeiXin:1176610771)<br>
 * Date: 2020/8/17<br>
 * Time: 17:20<br>
 * <P>DESC:
 * [XSelector] API　入口
 * </p>
 * ******************(^_^)***********************
 */
class XSelector private constructor() {

    private var mContext: Context? = null

    companion object Instance {
        @JvmStatic
        val me: XSelector by lazy(LazyThreadSafetyMode.NONE) {
            XSelector()
        }

        /**
         * 以[ShapeItem]作为各状态下(默认、点击、焦点、使能...)的资源构建出 最终的　[Selector]
         */
        fun shapeSelector() = ShapeSelector()

        /**
         * 以[Drawable]作为各状态下(默认、点击、焦点、使能...)的资源构建出　最终的　[Selector]
         */
        fun drawableSelector() = DrawableSelector()

        /**
         * 可构建出不需要各状态(默认、点击、焦点、使能...)的(仅默认状态下)[Drawable]资源(eg.:控件背景[Drawable])
         * 注：虽然默认功能是构建出默认状态下的[Drawable],但强大的是也可以使用类下API,来依附一个[DrawableSelector]仍能构建出
         * 各状态下的 [Drawable]
         */
        fun shapeItem() = ShapeItem()

        /**
         * 以颜色为选项构建出各状态下(默认、点击、焦点、使能...)的　[Selector]
         * 作用目标是　TextView　及其子类
         */
        fun colorSelector() = ColorSelector()
    }

    fun init(context: Context) {
        this.mContext = context
    }

//    /**
//     * 以颜色为选项构建出各状态下(默认、点击、焦点、使能...)的　[Selector]
//     * 作用目标是　TextView　及其子类
//     */
//    fun colorSelector(): ColorSelector {
//        return ColorSelector()
//    }

//
//    fun shapeSelector(): ShapeSelector {
//        return ShapeSelector()
//    }

    fun getContext(): Context? {
        return mContext
    }


}