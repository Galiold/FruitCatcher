package com.KahaniM.FruitsScript;


import com.KahaniM.Game.Main;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.util.Date;

public class Fruits extends Thread{
    private int radius;
    private int NumPS;
    private int speed;
    private int points;
    private String fallType;
    private double x,y;
    protected boolean status = true;
    private ImageView fruitImage;
    long startTime;
    int type;
    private static int waterMelonCount;
    private static int orangeCount;
    private static int apricotCount;

    public static int getWaterMelonCount() {
        return waterMelonCount;
    }

    public static void setWaterMelonCount(int waterMelonCount) {
        Fruits.waterMelonCount = waterMelonCount;
    }

    public static int getOrangeCount() {
        return orangeCount;
    }

    public static void setOrangeCount(int orangeCount) {
        Fruits.orangeCount = orangeCount;
    }

    public static int getApricotCount() {
        return apricotCount;
    }

    public static void setApricotCount(int apricotCount) {
        Fruits.apricotCount = apricotCount;
    }


    public Fruits(int type) {
        this.type = type;
        Date date = new Date();
        startTime = date.getTime();
    }

    public int getType () {
        return type;
    }

    public void reward(Label label, Label scoreBoard){
    }


    public void reward(){}

    public boolean isCaught() {
        if(fruitImage.getBoundsInParent().intersects(Main.basket.getImv().getBoundsInParent())){
            status = false;
            fruitImage.setVisible(false);
            return true;
        }
        return false;
    }

    public void setInvisible() {
        fruitImage.setVisible(false);
    }

    public ImageView getFruitImage() {
        return fruitImage;
    }

    public void setFruitImage(ImageView fruitImage) {
        this.fruitImage = fruitImage;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public int getNumPS() {
        return NumPS;
    }

    public void setNumPS(int numPS) {
        NumPS = numPS;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getFallType() {
        return fallType;
    }

    public void setFallType(String fallType) {
        this.fallType = fallType;
    }

    public void changeStatus(Fruits fruit){
        fruit.setStatus(false);
    }
}
