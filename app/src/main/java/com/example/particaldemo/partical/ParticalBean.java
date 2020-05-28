package com.example.particaldemo.partical;

import android.graphics.Paint;
import java.util.List;

/**
 * 上升粒子
 */
public class ParticalBean {
    //颜色
    private int color;
    //x位置
    private int x;
    //y位置
    private int y;
    //速率
    private int speed;
    //移动距离
    private int distance;
    //是否爆炸
    private boolean isBoom;
    //爆炸后粒子数据
    private List<BoomParicalBean> boomParicalBeans;

    public ParticalBean(int color, int x, int y, int speed, int distance) {
        this.color = color;
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.distance = distance;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public boolean isBoom() {
        return isBoom;
    }

    public void setBoom(boolean boom) {
        isBoom = boom;
    }

    public List<BoomParicalBean> getBoomParicalBeans() {
        return boomParicalBeans;
    }

    public void setBoomParicalBeans(List<BoomParicalBean> boomParicalBeans) {
        this.boomParicalBeans = boomParicalBeans;
    }

}
