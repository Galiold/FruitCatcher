package com.KahaniM.WormsScript;

import com.KahaniM.GUI.GameScene;
import com.KahaniM.GUI.MainMenu;
import com.KahaniM.GUI.MenuController;
import com.KahaniM.Game.Main;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.util.Random;

/**
 * Created by Mohammad Kahani on 23/06/2017.
 */
public class HeartWorm extends Worms {
    private ImageView[] heartImages = GameScene.getHeartImages();
    private ImageView imv;
    public HeartWorm() {
        Image image = new Image(HeartWorm.class.getResourceAsStream("Worm2.png"));
        imv = new ImageView(image);
        setWormImage(imv);
        W1Move();
        GameScene.addImage(imv);
    }


    @Override
    public void damage() {
        switch (Main.player.getHeart()) {
            case 3:
                Main.player.setHeart(Main.player.getHeart() - 1);
                heartImages[2].setVisible(false);
                break;
            case 2:
                Main.player.setHeart(Main.player.getHeart() - 1);
                heartImages[1].setVisible(false);
                break;
            case 1:
                Main.player.setHeart(Main.player.getHeart() - 1);
                heartImages[0].setVisible(false);
                MenuController.input.setPlayer(Main.player.getName(),Main.player.getPlayerScore());
                MenuController.input.write();
                System.exit(0);
                break;
        }
    }



    public void W1Move(){
        Random rand = new Random();
        TranslateTransition transition = new TranslateTransition();
        int value = rand.nextInt(930);
        //System.out.println(value);
        transition.setFromX(value);
        transition.setFromY(40);
        transition.setToX(value);
        transition.setToY(576);
        transition.setDuration(Duration.seconds(3));

        transition.setNode(imv);
        transition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                status = false;
            }
        });
        transition.play();
    }


}
