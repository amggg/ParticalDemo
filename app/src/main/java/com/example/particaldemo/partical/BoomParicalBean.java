package com.example.particaldemo.partical;

/**
 * 爆炸粒子
 */
public class BoomParicalBean {
    //x位置
    private int x;
    //y位置
    private int y;
    //速率
    private int speed;
    //移动距离
    private int distance;
    //粒子半径
    private int raduis;

    public BoomParicalBean(int x, int y, int raduis, int speed, int distance) {
        this.x = x;
        this.y = y;
        this.raduis = raduis;
        this.speed = speed;
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

    public int getRaduis() {
        return raduis;
    }

    public void setRaduis(int raduis) {
        this.raduis = raduis;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
}
