package com.KahaniM.FruitsScript;

import com.KahaniM.GUI.GameScene;
import com.KahaniM.Game.Main;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.util.Random;
import java.util.TimerTask;

/**
 * Created by Mohammad Kahani on 22/06/2017.
 */
public class Orange extends Fruits {
    ImageView imv;
    public Orange() {
        super(2);
        setRadius(10);
        setNumPS(3);
        setFallType("Diagonal");
        setPoints(40);
        setSpeed(40);
        Image image = new Image(Orange.class.getResourceAsStream("orange.png"));
        imv = new ImageView(image);
        setFruitImage(imv);
        GameScene.addImage(imv);
        imv.setVisible(false);
        OMove();
        imv.setVisible(true);

    }

    public void OMove(){

        Random rand = new Random();
        TranslateTransition transition = new TranslateTransition();
        int value = rand.nextInt(930);
        int value1 = rand.nextInt(930);
        //System.out.println(value);
        transition.setFromX(value);
        transition.setFromY(40);
        transition.setToX(value1);
        transition.setToY(576);
        transition.setDuration(Duration.seconds(5));

        transition.setNode(imv);
        transition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                status = false;
            }
        });

        transition.play();

    }


    @Override
    public void reward(Label label, Label scoreBoard) {
        Main.player.setPlayerScore(Main.player.getPlayerScore()+this.getPoints());
        new java.util.Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    label.setText(String.valueOf(getOrangeCount()));
                    scoreBoard.setText(String.valueOf(Main.player.getPlayerScore()));
                    System.out.println("Player score :" + Main.player.getPlayerScore());
                });
            }
        }, 0);

    }



}
