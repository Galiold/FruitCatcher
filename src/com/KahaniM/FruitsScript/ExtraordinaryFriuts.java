package com.KahaniM.FruitsScript;

import com.KahaniM.GUI.GameScene;
import com.KahaniM.Game.Main;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Mohammad Kahani on 22/06/2017.
 */
public class ExtraordinaryFriuts extends Fruits {

    private int xtraType;
    ImageView imv;
    private ImageView[] heartImages = GameScene.getHeartImages();
    public ExtraordinaryFriuts(int xtraType) {
        super(4);
        this.xtraType = xtraType;
        Random random = new Random();
        setRadius(20);
        setNumPS(1);
        setFallType("Diagonal");
        setPoints(random.nextInt(100) + 40);
        setImage(this.xtraType);
        xMove();
    }


    public void setImage(int xtraType){
        switch(xtraType){
            case 1:
                Image image1 = new Image(Orange.class.getResourceAsStream("Diamond_Blue.png"));
                imv = new ImageView(image1);
                setFruitImage(imv);
                GameScene.addImage(imv);
                break;
            case 2:
                Image image2 = new Image(Orange.class.getResourceAsStream("Diamond_Green.png"));
                imv = new ImageView(image2);
                setFruitImage(imv);
                GameScene.addImage(imv);
                break;
            case 3:
                Image image3 = new Image(Orange.class.getResourceAsStream("Diamond_Purple.png"));
                imv = new ImageView(image3);
                setFruitImage(imv);
                GameScene.addImage(imv);
                break;
            case 4:
                Image image4 = new Image(Orange.class.getResourceAsStream("Diamond_Yellow.png"));
                imv = new ImageView(image4);
                setFruitImage(imv);
                GameScene.addImage(imv);
        }
    }

    public int getType() {
        return xtraType;
    }

    public void setType(int type) {
        this.xtraType = type;
    }


    public void xMove(){

        Random rand = new Random();
        TranslateTransition transition = new TranslateTransition();
        int value = rand.nextInt(930);
        int value1 = rand.nextInt(930);
        transition.setFromX(value);
        transition.setFromY(40);
        transition.setToX(value1);
        transition.setToY(576);
        transition.setDuration(Duration.seconds(2));

        transition.setNode(imv);
        System.out.println("In random type :"+xtraType);
        transition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                status = false;
            }
        });

        transition.play();

    }

    public void reward() {
        if (getFruitImage().getBoundsInParent().intersects(Main.basket.getImv().getBoundsInParent())) {
            setStatus(false);
            getFruitImage().setVisible(false);

            switch (this.xtraType) {
                case 1:                             //blue diamond caught
                    if (!Main.basket.isEnlarged() && !Main.basket.isShrunk()) {
                        Main.basket.doubleSize();
                        Main.basket.setEnlarged(true);

                        new Timer().schedule(new TimerTask() {
                            @Override
                            public void run() {
                                Main.basket.normalSize();
                                Main.basket.setEnlarged(false);
                            }
                        }, 10000);
                    }
                    else if (Main.basket.isShrunk()){
                        Main.basket.normalSize();
                    }
                    break;

                case 2:                 //green diamond caught
                    GameScene.noWorm();
                    break;

                case 3:                             //purple diamond caught
                    Main.player.setPlayerTime(Main.player.getPlayerTime() + 10);
                    break;

                case 4:                             //yellow diamond caught
                    switch (Main.player.getHeart()) {
                        case 2:
                            Main.player.setHeart(Main.player.getHeart() + 1);
                            heartImages[2].setVisible(true);
                            break;
                        case 1:
                            Main.player.setHeart(Main.player.getHeart() + 1);
                            heartImages[1].setVisible(true);
                            break;
                        case 0:
                            Main.player.setHeart(Main.player.getHeart() + 1);
                            heartImages[0].setVisible(true);
                            break;
                    }
                    break;
            }
        }

    }
}