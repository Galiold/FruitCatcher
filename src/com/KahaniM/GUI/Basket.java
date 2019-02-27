package com.KahaniM.GUI;

import com.KahaniM.Game.Main;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Created by Mohammad Kahani on 24/06/2017.
 */
public class Basket {
    private boolean canMove = true;
    private int imageX;
    private final int imageY = 576;
    private ImageView imv;
    private int size;
    private final int primarySize = 100;
    private boolean shrunk;
    private boolean enlarged;

    public boolean isEnlarged() {
        return enlarged;
    }

    public void setEnlarged(boolean enlarged) {
        this.enlarged = enlarged;
    }

    public boolean isCanMove() {
        return canMove;
    }

    public void setCanMove(boolean canMove) {
        this.canMove = canMove;
    }

    public boolean isShrunk() {
        return shrunk;
    }

    public void setShrunk(boolean Shrunk) {
        this.shrunk = Shrunk;
    }

    public Basket () {
        size = primarySize;
        imv = new ImageView();
        Image image = new Image(GameScene.class.getResourceAsStream("Basket.png"));
        imv.setImage(image);
        setBasket();
    }

    public void setBasket() {
        imv.setLayoutX(400);
        imv.setLayoutY(Main.barY-size);
        imv.setFitWidth(size);
        imv.setPreserveRatio(true);

    }

    public int getSize() {
        return size;
    }

    public int getImageX() {
        return imageX;
    }

    public void setImageX(int imageX) {
        this.imageX = imageX;
    }

    public int getImageY() {
        return imageY;
    }


    public void incX() {
        if (imageX + imv.getFitWidth() < 630)
            imageX += 30;
    }

    public void decX() {
        if (imageX> (-400))
            imageX -= 30;
    }

    public void updateImageView() {
        imv.setX(imageX);
        imv.setFocusTraversable(true);

    }


    public ImageView getImv(){
        return imv;
    }

    public void halfSize() {
        size = primarySize /2;
    }

    public void normalSize() {
        size = primarySize;
        if (imageX > 530 ) imageX = 530;
    }

    public void doubleSize() {
        size *= 2;
    }
}
