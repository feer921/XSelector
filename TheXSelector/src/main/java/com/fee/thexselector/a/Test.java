package com.fee.thexselector.a;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;

import com.fee.thexselector.ColorSelector;
import com.fee.thexselector.ShapeItem;
import com.fee.thexselector.StateItem;
import com.fee.thexselector.StateItemDrawable;

import kotlin.Pair;

/**
 * ******************(^_^)***********************<br>
 * User: fee(QQ/WeiXin:1176610771)<br>
 * Date: 2020/8/17<br>
 * Time: 17:18<br>
 * <P>DESC:
 * </p>
 * ******************(^_^)***********************
 */
class Test {
    public static void main(String[] args) {
        new ColorSelector().defState("");

        StateListDrawable stateListDrawable = new StateListDrawable();
        StateItemDrawable itemDrawable = new StateItemDrawable();
        itemDrawable.statePressed(true)
                .statePressed(false)
                .stateChecked(true)
                .stateItemValue(null);
        Pair<int[], Drawable> drawablePair = itemDrawable.itemStatesPairStateValue();
        stateListDrawable.addState(drawablePair.getFirst(),drawablePair.getSecond());

        StateItem<Drawable> drawableStateItem = new StateItem<>(null);

    }
}
