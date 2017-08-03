package com.hencoder.hencoderpracticedraw2.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ComposePathEffect;
import android.graphics.CornerPathEffect;
import android.graphics.DashPathEffect;
import android.graphics.DiscretePathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathEffect;
import android.graphics.SumPathEffect;
import android.media.effect.Effect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Practice12PathEffectView extends View {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Path path = new Path();

    public Practice12PathEffectView(Context context) {
        super(context);
    }

    public Practice12PathEffectView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice12PathEffectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        paint.setStyle(Paint.Style.STROKE);

        path.moveTo(50, 100);
        path.rLineTo(50, 100);
        path.rLineTo(80, -150);
        path.rLineTo(100, 100);
        path.rLineTo(70, -120);
        path.rLineTo(150, 80);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 使用 Paint.setPathEffect() 来设置不同的 PathEffect

        // 第一处：CornerPathEffect,参数是圆角的半径
        PathEffect pathEffect1=new CornerPathEffect(20);
        paint.setPathEffect(pathEffect1);
        canvas.drawPath(path, paint);

        canvas.save();
        canvas.translate(500, 0);
        // 第二处：DiscretePathEffect
        PathEffect pathEffect2=new DiscretePathEffect(20,5);
        paint.setPathEffect(pathEffect2);
        canvas.drawPath(path, paint);
        canvas.restore();

        canvas.save();
        canvas.translate(0, 200);
        // 第三处：DashPathEffect
        /*
         * 它的构造方法 DashPathEffect(float[] intervals, float phase) 中， 第一个参数 intervals 是一个数组，
         * 它指定了虚线的格式：数组中元素必须为偶数（最少是 2 个）
         * ，按照「画线长度、空白长度、画线长度、空白长度」……的顺序排列，
         * 例如上面代码中的 20, 5, 10, 5 就表示虚线是按照「画 20 像素、空 5 像素、画 10 像素、空 5 像素」的模式来绘制；第二个参数 phase 是虚线的偏移量。
         **/
        PathEffect pathEffect3=new DashPathEffect(new float[]{20, 10, 5, 10}, 0);
        paint.setPathEffect(pathEffect3);
        canvas.drawPath(path, paint);
        canvas.restore();

        canvas.save();
        canvas.translate(500, 200);
        Path path1=new Path();
        path1.rLineTo(30,0);
        path1.rLineTo(0,30);
        path1.close();
        PathEffect pathEffect4=new PathDashPathEffect(path1,40, 0,
                PathDashPathEffect.Style.ROTATE);
        paint.setPathEffect(pathEffect4);
        // 第四处：PathDashPathEffect
        canvas.drawPath(path, paint);
        canvas.restore();

        canvas.save();
        canvas.translate(0, 400);
        /*
        * 这是一个组合效果类的 PathEffect 。它的行为特别简单，就是分别按照两种 PathEffect 分别对目标进行绘制。
        **/
        // 第五处：SumPathEffect
        PathEffect dashEffect = new DashPathEffect(new float[]{20, 10}, 0);
        PathEffect discreteEffect = new DiscretePathEffect(20, 5);
        PathEffect pathEffect5=new SumPathEffect(dashEffect,discreteEffect);
        paint.setPathEffect(pathEffect5);
        canvas.drawPath(path, paint);
        canvas.restore();

        canvas.save();
        canvas.translate(500, 400);
        PathEffect pathEffect6=new ComposePathEffect(dashEffect,discreteEffect);
        paint.setPathEffect(pathEffect6);
        // 第六处：ComposePathEffect
        canvas.drawPath(path, paint);
        canvas.restore();
    }
}
