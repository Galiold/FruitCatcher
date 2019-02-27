package com.KahaniM.WormsScript;

import com.KahaniM.GUI.GameScene;
import com.KahaniM.Game.Main;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Mohammad Kahani on 23/06/2017.
 */
public class TimeWorm extends Worms {

    private ImageView imv;
    public TimeWorm() {
        Image image = new Image(TimeWorm.class.getResourceAsStream("Worm3.png"));
        imv = new ImageView(image);
        setWormImage(imv);
        W1Move();
        GameScene.addImage(imv);

    }

    @Override
    public void damage() {
        Main.basket.setCanMove(false);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    Main.basket.setCanMove(true);
                });
            }
        },3000);
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
        transition.setDuration(Duration.seconds(6));

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
