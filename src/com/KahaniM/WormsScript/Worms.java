package com.KahaniM.WormsScript;

import com.KahaniM.Game.Main;
import javafx.scene.image.ImageView;

/**
 * Created by Mohammad Kahani on 22/06/2017.
 */
    public abstract class Worms {
    private double x,y;
    protected boolean status = true;
    private ImageView wormImage;


    public void damage(){}

    public void backToNormal(){}

    public void setInvisible() {
        wormImage.setVisible(false);
    }
    public boolean isCaught() {
        if(wormImage.getBoundsInParent().intersects(Main.basket.getImv().getBoundsInParent())){
            status = false;
            wormImage.setVisible(false);
            return true;
        }

        return false;
    }

    public boolean getStatus() {
        return status;
    }

    public ImageView getWormImage() {
        return wormImage;
    }

    public void setWormImage(ImageView wormImage) {
        this.wormImage = wormImage;
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
}
