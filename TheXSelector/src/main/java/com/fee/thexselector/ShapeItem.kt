package com.fee.thexselector

import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.view.View
import androidx.annotation.ColorInt
import androidx.annotation.Px

/**
 * @author feer921
 * 对应于 xx.shape.xml 编写 Shape
 * <shape xmlns:android="http://schemas.android.com/apk/res/android"
        android:shape="rectangle"
        >
       1:
        <corners android:radius="9dp"
        android:topLeftRadius="9dp"
        android:topRightRadius="9dp"
        android:bottomLeftRadius="9dp"
        android:bottomRightRadius="9dp"
        />
        2:
        <solid android:color="#fff" />
        3:
        <stroke android:color="#f00"
            android:width="1dp"
            android:dashWidth="1dp"
            android:dashGap="2dp"
        />
        4:
        <gradient android:type="linear"
            android:angle="80"
            android:centerColor="#999"
            android:centerX="9"
            android:centerY="9"
            android:endColor="#f00"
            android:gradientRadius="8dp"
            android:startColor="#800"
            android:useLevel="false"
        />
        5:
        <padding android:left="1dp"
        android:top="2dp"
        android:right="5dp"
        android:bottom="8dp"
        />
        6:
        <size android:width="119dp"
            android:height="100dp"
            />
</shape>
 */
class ShapeItem : ISelector<Drawable, View, ShapeItem> {
    @ShapeType
    var shapeType = GradientDrawable.RECTANGLE

    /**
     * 1、对应  <solid android:color="#fff" />属性
     */
    @ColorInt
    var solidColor: Int? = null

    fun shape(@ShapeType targetShapeType: Int): ShapeItem{
        shapeType = targetShapeType
        return this
    }

    /**
     * 1、对应 配置 <solid android:color="[solidColor]" /> 属性
     */
    fun solidColor(@ColorInt solidColor: Int?): ShapeItem {
        this.solidColor = solidColor
        return this
    }
    fun solidColor(solidColorStr: String): ShapeItem {
        return solidColor(AbsSelector.parseColorStrToInt(solidColorStr))
    }

    @Px
    var sizeWidth: Int? = null
    @Px
    var sizeHeight: Int? = null

    /**
     * 2、对应 配置 <size android:width="[sizeWidth]"
                android:height="[sizeHeight]"
            />
     */
    fun sizeWidth(@Px sizeWidth: Int?,@Px sizeHeight: Int?): ShapeItem{
        this.sizeWidth = sizeWidth
        this.sizeHeight = sizeHeight
        return this
    }
    @Px
    private var paddingLeft: Int? = null

    @Px
    private var paddingTop: Int? = null

    @Px
    private var paddingRight: Int? = null

    @Px
    private var paddingBottom: Int? = null

    /**
     * 3、相当于配置 <padding android:left="[left]"
                        android:top="[top]"
                        android:right="[right]"
                        android:bottom="[bottom]"
                    />
     */
    fun padding(@Px left: Int,@Px top: Int,@Px right: Int, @Px bottom: Int): ShapeItem {
        paddingLeft = left
        paddingTop = top
        paddingRight = right
        paddingBottom = bottom
        return this
    }


    @Px
    private var cornerRadius: Float? = null

    @Px
    private var cornerTopLeftRadius: Float? = null

    @Px
    private var cornerTopRightRadius: Float? = null

    @Px
    private var cornerBottomRightRadius: Float? = null

    @Px
    private var cornerBottomLeftRadius: Float? = null

    /**
     * 4、相当于配置 <corners android:radius="[cornerRadius]"
                        android:topLeftRadius="[topLeftRadius]"
                        android:topRightRadius="[topRightRadius]"
                        android:bottomLeftRadius="[bottomLeftRadius]"
                        android:bottomRightRadius="[bottomRightRadius]"
                        />
     */
    fun corners(
        @Px cornerRadius: Float?, @Px topLeftRadius: Float? = null, @Px topRightRadius: Float? = null,
        @Px bottomLeftRadius: Float? = null, @Px bottomRightRadius: Float? = null
    ): ShapeItem {
        this.cornerRadius = cornerRadius
        cornerTopLeftRadius = topLeftRadius ?: cornerRadius
        cornerTopRightRadius = topRightRadius ?: cornerRadius
        cornerBottomLeftRadius = bottomLeftRadius ?: cornerRadius
        cornerBottomRightRadius = bottomRightRadius ?: cornerRadius
        return this
    }

    // stroke ----------------- @start--------------

    /**
     * 描边 线颜色 可以没有(会为主题默认颜色)
     */
    @ColorInt
    private var strokeColor: Int? = null

    /**
     * 描边线宽度，如果 非大于 0，则描边 无意义
     */
    @Px
    private var strokeWidth: Int? = null

    @Px
    private var strokeDashWidth: Float? = null

    @Px
    private var strokeDashGap: Float? = null

    /**
     * 5、相当于配置： <stroke android:color="[strokeColor]"
                            android:width="[strokeWidth]" //只有 >0 描边才有意义
                            android:dashWidth="[strokeDashWidth]"
                            android:dashGap="[strokeDashGap]"
                        />
     */
    fun stroke(
        @ColorInt strokeColor: Int,//描边颜色是可以没有(会为主题默认颜色)
        @Px strokeWidth: Int,// 描边线宽
        @Px strokeDashWidth: Float? = null,
        @Px strokeDashGap: Float? = null
    ): ShapeItem {
        this.strokeColor = strokeColor
        this.strokeWidth = strokeWidth
        this.strokeDashWidth = strokeDashWidth
        this.strokeDashGap = strokeDashGap
        return this
    }
    // stroke ----------------- @end ---------------


    // gradient ----------------- @start ---------------
    /**
     * 渐变 类型
     */
    @GradientType
    private var gradientType: Int? = null
    /** 渐变角度 **/
    private var gradientAngle: Int? = null
    private var gradientUseLevel: Boolean? = null
    private var gradientCenterX: Int? = null
    private var gradientCenterY: Int? = null
    /**
     * 渐变 半径
     */
    @Px
    private var gradientRadius: Int? = null
    @ColorInt
    private var gradientStartColor: Int? = null

    @ColorInt
    private var gradientCenterColor: Int? = null

    @ColorInt
    private var gradientEndColor: Int? = null

    /**
     * 6、相当于配置 <gradient android:type="[gradientType]"
     *                     android:startColor="[startColor]"
                            android:centerColor="[centerColor]"
                            android:endColor="[endColor]"
                            android:angle="[angle]"
                            android:gradientRadius="[gradientRadius]"
                            android:centerX="[centerX]"
                            android:centerY="[centerY]"
                            android:useLevel="[useLevel]"
                        />
     */
    fun gradient(
        @GradientType gradientType: Int? = null,
        @ColorInt startColor: Int? = null,
        @ColorInt centerColor: Int? = null,
        @ColorInt endColor: Int? = null,
        angle: Int? = null,
        @Px gradientRadius: Int? = null,
        centerX: Int? = null,
        centerY: Int? = null,
        useLevel: Boolean? = null
    ): ShapeItem {
        this.gradientType = gradientType
        gradientStartColor = startColor
        gradientCenterColor = centerColor
        gradientEndColor = endColor
        gradientAngle = angle
        this.gradientRadius = gradientRadius
        gradientCenterX = centerX
        gradientCenterY = centerY
        gradientUseLevel = useLevel
        return this
    }
    // gradient ----------------- @end ---------------
    /**
     * 构建出 stateResult
     * @return [SR] 构建出的 状态集结果对象
     */
    override fun build(): Drawable? {
        val drawable = GradientDrawable()
        drawable.shape = shapeType
        //1: corner
        cornerRadius?.let {
            if (it >= 0) {
                drawable.cornerRadius = it
            }
        }
        val radius = cornerRadius ?: 0f
        var eightCornerRadius: FloatArray? = null
        if (cornerTopLeftRadius != null || cornerTopRightRadius != null || cornerBottomLeftRadius != null || cornerBottomRightRadius != null) {
            eightCornerRadius = FloatArray(8){index ->
                when (index) {
                    0,1 -> {
                        cornerTopLeftRadius ?: radius
                    }
                    2,3 -> {
                        cornerTopRightRadius ?: radius
                    }
                    4,5 -> {
                        cornerBottomLeftRadius ?: radius
                    }
                    6,7 -> {
                        cornerBottomRightRadius ?: radius
                    }
                    else -> {radius}
                }
            }
        }
        eightCornerRadius?.let {
            drawable.cornerRadii = it
        }
        //2、solid
        solidColor?.let { argb ->
            drawable.setColor(argb)
        }
        //3、stroke
        strokeWidth?.let { strokeW ->
            if (strokeW > 0) {//只有 描边宽 大于 0 才有意义
                drawable.setStroke(
                    strokeW,
                    strokeColor ?: 0,
                    strokeDashWidth ?: 0f,
                    strokeDashGap ?: 0f
                )
            }
        }
        //4、size
        if (sizeWidth != null && sizeWidth!! >0 && sizeHeight != null && sizeHeight!! > 0 ) {
            drawable.setSize(sizeWidth!!,sizeHeight!!)
        }
        //5、padding API >= 29
        if (Build.VERSION.SDK_INT >= 29) {
            if (paddingLeft != null || paddingTop != null || paddingRight != null || paddingBottom != null) {
                drawable.setPadding(
                    paddingLeft ?: 0,
                    paddingTop ?: 0,
                    paddingRight ?: 0,
                    paddingBottom ?: 0
                )
            }
        }
        //6、gradient
        return drawable
    }

    /**
     * 将 本Selector构建的 状态选择对象设置给[theEffectedView]
     * @param theEffectedView 要适用各状态 Selector的 View
     * @param isNeedReBuild 是否需要重新构建出 [selector] 结果
     */
    override fun into(theEffectedView: View, isNeedReBuild: Boolean): ShapeItem {
        build()?.let {
            theEffectedView.background = it
        }
        return this
    }

}