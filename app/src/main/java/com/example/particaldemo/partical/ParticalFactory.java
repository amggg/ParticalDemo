package com.example.particaldemo.partical;


import android.graphics.Paint;

/**
 * 粒子工厂
 */
class ParticalFactory {

    /** 生成上升粒子
     * @param paint
     * @param x
     * @param y
     * @param speed
     * @param distance
     * @return
     */
    static ParticalBean createPartical(Paint paint, int x, int y, int speed, int distance){
        return new ParticalBean(paint, x, y, speed, distance);
    }

    /** 生成爆炸粒子
     * @param x
     * @param y
     * @param raduis
     * @param speed
     * @param distance
     * @return
     */
    static BoomParicalBean createBoomPartical(int x, int y, int raduis, int speed, int distance){
        return new BoomParicalBean(x, y, speed, raduis, distance);
    }
}
