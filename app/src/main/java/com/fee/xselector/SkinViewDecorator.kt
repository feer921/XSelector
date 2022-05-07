package com.fee.xselector

import android.graphics.Color
import android.util.AttributeSet
import android.view.View
import android.widget.EditText

/**
 */
class SkinViewDecorator : AbsViewDecorator(null) {
    override fun decorateView(theView: View, attrsOfView: AttributeSet?) {
        super.decorateView(theView, attrsOfView)
        if (theView is EditText) {
            theView.setHintTextColor(Color.RED)
        }
    }
    override fun doDecorateView(theView: View, attrsOfView: AttributeSet?) {
        super.doDecorateView(theView, attrsOfView)

    }
}