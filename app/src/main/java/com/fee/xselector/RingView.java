package com.fee.xselector;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;


/**
 * ******************(^_^)***********************<br>
 * User: fee(QQ/WeiXin:1176610771)<br>
 * Date: 2023/5/7<br>
 * Time: 15:20<br>
 * <P>DESC:
 * </p>
 * ******************(^_^)***********************
 */
public class RingView extends View {

    private final static int CIRCLE_ANIMATION = 0x100;
    private final static int CIRCLE_START = 0x100 - 1;

    private final Paint mPaint;
    private int mCircleX;// 圆心x轴坐标
    private int mCircleY;// 圆心Y轴坐标
    /** 最大笔触宽 **/
    private int maxStrokeWidth = 3;
    /** 笔触宽比率 **/
    private double strokeRatio = 1;
    private int[] strokesWidthArray = null;
    private int firstCircleRadius = 1;
    private boolean mIsRunningAnimation = false;
    /**
     * 透明度比率 255/距离
     */
    double alphaRatio = 1;
    /**
     * 需要显示的圆数
     */
    private static final int NUM = 5;// 条纹个数
    /**
     * 允许的最大半径
     */
    private int maxRadius;
    private int trackRadius;
    private int[] preDrawRadiusArray;
    /** 两个圆之间的间距 **/
    private int distance = 0;
    /** 半径步进跨度 **/
    private int step = 5;
    /**
     * 所有需绘出的圆的半径
     */
    private int[] circleRadiusArray = null;

    private int runSpeed = 120;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (!mIsRunningAnimation) {
                return;
            }
            switch (msg.what) {
                case CIRCLE_START:
                    invalidate();
                    sendEmptyMessage(CIRCLE_ANIMATION);
                    break;
                case CIRCLE_ANIMATION:
                    if (preDrawRadiusArray[NUM - 1] == 0) {
                        int reachCircle = (trackRadius - firstCircleRadius) / distance;
                        if (reachCircle > 0) {
                            if (preDrawRadiusArray[reachCircle] == 0) {
                                preDrawRadiusArray[reachCircle] = firstCircleRadius;
                            }
                        }
                        trackRadius += step;
                    }

                    for (int i = 0; i < NUM; i++) {
                        if (preDrawRadiusArray[i] == 0)
                            continue;
                        if (preDrawRadiusArray[i] >= maxRadius) {
                            preDrawRadiusArray[i] = firstCircleRadius;
                        } else {
                            preDrawRadiusArray[i] += step;
                        }
                    }
                    // trackRadius += step;
                    sendEmptyMessageDelayed(CIRCLE_ANIMATION, runSpeed);
                    invalidate();
                    break;
            }
            // invalidate();
        }
    };

    public RingView(Context context) {

        this(context, null);

    }

    public void setCenterPoint(int circleX, int circleY, int originalCirCleRadius) {
        if (mCircleX != 0) {
            return;
        }
        mCircleX = circleX;
        mCircleY = circleY;
        maxRadius = circleY;
        firstCircleRadius = originalCirCleRadius - 5;
        trackRadius = firstCircleRadius;
        // 圆辐射范围
        int radiateRange = maxRadius - originalCirCleRadius;
        // 同间距 同心圆 的间距
        distance = radiateRange / NUM;
        int dif = 0;
        // 为了绘出不等距离 同心圆 具体公式 Num * distance + Num * (NUM -1) * dif /2 =
        // radiateRange;
        // distance = (2 * radiateRange - (dif * NUM * NUM )+ (NUM * dif)) / (2
        // * NUM);
        alphaRatio = 255.0 / radiateRange; // 表示每个距离的透明度
        strokeRatio = (dip2px(maxStrokeWidth) + 0.0) / radiateRange;
        // 算出各圆半径
        circleRadiusArray = new int[NUM];
        preDrawRadiusArray = new int[NUM];
        int temp = firstCircleRadius;
        for (int i = 0; i < NUM; i++) {
            if (i == 0) {
                preDrawRadiusArray[i] = temp;
            } else {
                preDrawRadiusArray[i] = 0;
            }
            circleRadiusArray[i] = temp;
            temp += distance + dif * i;
        }

    }

    public RingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mPaint = new Paint();
        this.mPaint.setAntiAlias(true); // 消除锯齿
        this.mPaint.setStyle(Paint.Style.STROKE); // 绘制空心圆
        density = context.getResources().getDisplayMetrics().density;
        // strokeWidth = dip2px(mContext, strokeWidth);
        strokesWidthArray = new int[NUM];
        int tempStroke = 1;
        for (int i = 0; i < NUM; i++) {
            strokesWidthArray[i] = dip2px(tempStroke);
            tempStroke += 2;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (!mIsRunningAnimation) {
            return;
        }
        // if(!firstDraw){
        // int reachCircle = (trackRadius - firstCircleRadius ) /distance;
        // if(originalRadiusArray[NUM -1] == 0 ){
        // if(reachCircle > 0){
        // if(originalRadiusArray[reachCircle] == 0){
        // originalRadiusArray[reachCircle] = firstCircleRadius;
        // }
        // }
        // }
        // else{
        //
        // }
        // for(int i = 0 ; i < NUM ; i++){
        // if(originalRadiusArray[i] == 0 )continue;
        // if(originalRadiusArray[i] >= maxRadius){
        // originalRadiusArray[i] = firstCircleRadius;
        // }
        // else{
        // originalRadiusArray[i] += step;
        // }
        // }
        // trackRadius += step;
        //
        // }
        // if(firstDraw){
        // firstDraw = false;
        // }
        for (int curCircleRadius : preDrawRadiusArray) {
            int distanceFirstCircle = curCircleRadius - firstCircleRadius;
            int alpha = (int) (255 - (distanceFirstCircle * alphaRatio + 20));
            if (alpha < 0) {
                alpha = 0;
            }
            float strokeWidth = (float) (distanceFirstCircle * strokeRatio);
            if (strokeWidth < 1) {
                strokeWidth = 1;
            }
            this.mPaint.setStrokeWidth(strokeWidth);
            this.mPaint.setARGB(alpha, 212, 225, 233);
            canvas.drawCircle(mCircleX, mCircleY, curCircleRadius, this.mPaint);
        }
        // postInvalidateDelayed(runSpeed);
    }

    float density = 0;

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public int dip2px(float dpValue) {
        return (int) (dpValue * density + 0.5f);
    }

    //boolean firstDraw = false;

    public void startAnimation() {
        mIsRunningAnimation = true;
        mHandler.sendEmptyMessage(CIRCLE_START);
        //firstDraw = true;
        // invalidate();
    }
    
    public void pauseAnimation(){
        mIsRunningAnimation = false;
        mHandler.removeMessages(CIRCLE_ANIMATION);
    }
    
    public void resumeAnimtion(){
        mIsRunningAnimation = true;
        mHandler.sendEmptyMessage(CIRCLE_ANIMATION);
    }

    public void stopAnimation() {
        if (!mIsRunningAnimation)
            return;
        mIsRunningAnimation = false;
        mHandler.removeCallbacksAndMessages(null);
        invalidate();
        // 重新赋值
        trackRadius = firstCircleRadius;
        preDrawRadiusArray[0] = firstCircleRadius;
        for (int i = 1; i < NUM; i++) {
            preDrawRadiusArray[i] = 0;
        }
    }

    /**
     * 获取当前动画运行状态
     * @return true: 正在运行；false : 已停止运行
     */
    public boolean isAnimationRunning() {
        return mIsRunningAnimation;
    }

    public void setCurRunSpeed(int speed) {
        runSpeed = speed;
    }

    public int getCurSpeed() {
        return runSpeed;
    }
}
