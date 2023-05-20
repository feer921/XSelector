package com.fee.xselector.ui.login

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Build.VERSION_CODES.S
import android.os.Bundle
import com.fee.thexselector.ShapeType
import com.fee.thexselector.XSelector
import com.fee.xselector.BaseActivity
import com.fee.xselector.databinding.ActivityXselectorBinding

/**
 ******************(^_^)***********************<br>
 * Author: fee(QQ/WeiXin:1176610771)<br>
 * Date: 2023/5/20<br>
 * Time: 12:20<br>
 * <P>DESC:
 *
 * </p>
 * ******************(^_^)***********************
 */
class XSelectorActivity: BaseActivity() {
    private lateinit var mViewBinding: ActivityXselectorBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewBinding = ActivityXselectorBinding.inflate(layoutInflater)
        setContentView(mViewBinding.root)
        initViews()
    }

    private fun initViews() {
        //颜色　Selector
        mViewBinding.tv1.isClickable = true
        XSelector.colorSelector()
            .stateDef(Color.BLACK)
            .statePressed(Color.RED)
            .into(mViewBinding.tv1)

        //背景
        XSelector.colorSelector()
            .stateDef(Color.CYAN)
            .statePressed(Color.RED)
            .into(mViewBinding.tv2)
            .withShapeItem()
            .solidColor(Color.BLUE)
//            .corners(20f)
            .into(mViewBinding.tv2)

        //各种状态
        mViewBinding.tv3.isClickable = true
        XSelector.shapeSelector()
            .stateDef {
                solidColor(Color.YELLOW)
                corners(30f)
                stroke(Color.BLACK,1)

            }
            .statePressed {
                solidColor(Color.GREEN)
                corners(30f)
            }
            .into(mViewBinding.tv3)

        //圆形
        XSelector.shapeItem()
            .shape(GradientDrawable.OVAL)
            .solidColor(Color.GRAY)
            .into(mViewBinding.tv4)
    }


}