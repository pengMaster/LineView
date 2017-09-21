package com.mtm.lineview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.mtm.lineview.R;


/**
 * ***************************************************************************
 * Author : MTM Created by WangPeng    ***    ***     **********   ***    ***
 * Data : 2017-9-21 11:48             ****   ****    **********   ****   ****
 * Project : Line  View              *** *  * ***       ***      *** *  * ***
 * MinSdkVersion : 16               ***  * *  ***      ***      ***  * *  ***
 * Version : V1.0                  ***   **   ***     ***      ***   **   ***
 * Description :
 * ***************************************************************************
 */
public class LineView extends View{

    private Paint paint;
    private int mLineColor;
    private int mLineSpace;
    private int mLineCount;
    private int mLineHeight;//此属性有待于改善
    private int mLineWidth;

    public LineView(Context context) {
        super(context);
    }

    public LineView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.LineView);
        mLineCount = array.getInt(R.styleable.LineView_lineCount, 10);
        mLineSpace = array.getDimensionPixelOffset(R.styleable.LineView_lineSpace, 10);
        mLineColor = array.getColor(R.styleable.LineView_lineColor, 0xccc);
        mLineHeight = array.getDimensionPixelOffset(R.styleable.LineView_lineHeight, 1);
        mLineWidth = array.getDimensionPixelOffset(R.styleable.LineView_lineWidth, 3);
        paint = new Paint();
        array.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = measureWidth(widthMeasureSpec);
        int height = measureHeight(heightMeasureSpec);
        Log.e("width", width + "");
        Log.e("height", height + "");
        //保存测量结果
        setMeasuredDimension(width,height);
    }

    /**
     * 根据系统传给的高度的描述信息heightMeasureSpec，测量出view的高度
     * @param heightMeasureSpec
     * @return
     */
    private int measureHeight(int heightMeasureSpec) {
        //规格
        int mode = MeasureSpec.getMode(heightMeasureSpec);
        //尺寸
        int size = MeasureSpec.getSize(heightMeasureSpec);
        int result = size;
        //不是精确值计算
        if (mode!=MeasureSpec.EXACTLY) {
            size = getPaddingTop() + getPaddingBottom() + mLineHeight;
            result =  Math.min(result, size);
        }
        return result;
    }

    /**
     * 根据系统传给的宽度的描述信息widthMeasureSpec，测量出view的宽度
     * @param widthMeasureSpec
     * @return
     */
    private int measureWidth(int widthMeasureSpec) {
        //规格
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        //尺寸
        int size = MeasureSpec.getSize(widthMeasureSpec);
        int result = size;
        //不是精确值计算
        if (mode!=MeasureSpec.EXACTLY) {
            size = getPaddingLeft() + getPaddingRight() + mLineCount * mLineWidth +(mLineCount-1)*mLineSpace;
            result =  Math.min(result, size);
        }
        return result;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        int leftSpace=getPaddingLeft();
        for (int i=0;i<mLineCount;i++){
            int x=leftSpace+i*(mLineSpace+mLineWidth);
            paint.setColor(mLineColor);
            canvas.drawLine(x,mLineHeight/2,x+mLineWidth,mLineHeight/2,paint);
        }
    }
}
