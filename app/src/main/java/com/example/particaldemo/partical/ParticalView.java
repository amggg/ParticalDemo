package com.example.particaldemo.partical;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ParticalView extends View {
    private Paint paint;

    private GestureDetector gestureDetector;
    //上升粒子数据
    private List<ParticalBean> list;
    //爆炸粒子数据
    private List<List<BoomParicalBean>> boomParicas;
    //爆炸最大半径
    private int fragmentsRaduis;
    //爆炸粒子最大半径
    private int particalRaduis;
    //粒子移动距离
    private int boomParticalMoveY;
    //粒子移动速度
    private int boomParicalSpeed;

    public ParticalView(Context context) {
        super(context);
        init();
    }

    public ParticalView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ParticalView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(2);

        fragmentsRaduis = 300;
        particalRaduis = 20;
        boomParticalMoveY = 80;
        boomParicalSpeed = 3;

        list = new ArrayList<>();
        boomParicas = new ArrayList<>();

        gestureDetector = new GestureDetector(getContext(), new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onDown(MotionEvent e) {
                ViewCompat.postInvalidateOnAnimation(ParticalView.this);
                list.add(ParticalFactory.createPartical(Color.parseColor(getRandColor()), (int) e.getX(), (int)e.getY(), (int) (Math.random() * (15-5)+5), (int) (Math.random() * 1000)));
                return true;
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.BLACK);
        if(!list.isEmpty()){
            for (ParticalBean particalBean : list) {
                if(!particalBean.isBoom()){
                    paint.setColor(particalBean.getColor());
                    paint.setShadowLayer(16,0,0, particalBean.getColor());
                    canvas.drawCircle(particalBean.getX(), particalBean.getY(), 50, paint);
                    particalBean.setY(particalBean.getY() - particalBean.getSpeed());
                }
            }
            for (int i=0; i<list.size(); i++){
                ParticalBean particalBean = list.get(i);
                if(!particalBean.isBoom()){
                    if (particalBean.getY() <= particalBean.getDistance()){
                        //碎片个数
                        int fragments = (int) (Math.random() * (100 - 60) + 60);
                        List<BoomParicalBean> boomParicalBeans = new ArrayList<>();
                        for (int j=0; j < fragments ;j++){
                            double angle = (j * 2 * Math.PI) / fragments;
                            int raduis = (int) (Math.random() * fragmentsRaduis);
                            int x = (int) (Math.cos(angle) * raduis) + particalBean.getX();
                            int y = (int) (Math.sin(angle) * raduis) + particalBean.getY();
                            BoomParicalBean boomPartical = ParticalFactory.createBoomPartical(x, y, boomParicalSpeed, (int) (Math.random() * particalRaduis), y + boomParticalMoveY);
                            boomParicalBeans.add(boomPartical);
                        }
                        particalBean.setBoomParicalBeans(boomParicalBeans);
                        particalBean.setBoom(true);
                    }
                }
            }

            //爆炸粒子
            for (int p=0; p< list.size(); p++){
                ParticalBean particalBean = list.get(p);
                if(particalBean.isBoom()){
                    List<BoomParicalBean> boomParicalBeans = particalBean.getBoomParicalBeans();
                    for (int i=0; i<boomParicalBeans.size() ;i++){
                        BoomParicalBean boomParicalBean = boomParicalBeans.get(i);
                        paint.setColor(particalBean.getColor());
                        paint.setShadowLayer(16,0,0, particalBean.getColor());
                        canvas.drawCircle(boomParicalBean.getX(), boomParicalBean.getY(), boomParicalBean.getRaduis(), paint);
                        boomParicalBean.setY(boomParicalBean.getY() + boomParicalBean.getSpeed());

                        if(boomParicalBean.getY() >= boomParicalBean.getDistance()){
                            boomParicalBeans.remove(boomParicalBean);
                        }

                        if(boomParicalBeans.isEmpty()){
                            list.remove(particalBean);
                            ViewCompat.postInvalidateOnAnimation(ParticalView.this);
                        }
                    }
                }
            }
        }

        if(!list.isEmpty()){
            ViewCompat.postInvalidateOnAnimation(ParticalView.this);
        }
    }


    /**
     * 获取十六进制的颜色代码.例如  "#5A6677"
     * 分别取R、G、B的随机值，然后加起来即可
     *
     * @return String
     */
    public static String getRandColor() {
        String R, G, B;
        Random random = new Random();
        R = Integer.toHexString(random.nextInt(256)).toUpperCase();
        G = Integer.toHexString(random.nextInt(256)).toUpperCase();
        B = Integer.toHexString(random.nextInt(256)).toUpperCase();

        R = R.length() == 1 ? "0" + R : R;
        G = G.length() == 1 ? "0" + G : G;
        B = B.length() == 1 ? "0" + B : B;

        return "#" + R + G + B;
    }
}
