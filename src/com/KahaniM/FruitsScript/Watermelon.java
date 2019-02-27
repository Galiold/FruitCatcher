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
import java.util.Timer;
import java.util.TimerTask;


public class Watermelon extends Fruits {
    ImageView imv;
    public Watermelon() {
        super(1);
        setRadius(10);
        setNumPS(4);
        setFallType("straight");
        setPoints(60);
        setSpeed(100);
        Image image = new Image(GameScene.class.getResourceAsStream("Watermelon.png"));
        imv = new ImageView(image);
        setFruitImage(imv);
        GameScene.addImage(imv);
        imv.setVisible(false);
        WMove();
        imv.setVisible(true);

    }

    public void WMove(){
        Random rand = new Random();
        TranslateTransition transition = new TranslateTransition();
        int value = rand.nextInt(930);
        //System.out.println(value);
        transition.setFromX(value);
        transition.setFromY(40);
        transition.setToX(value);
        transition.setToY(576);
        transition.setDuration(Duration.seconds(4));

        transition.setNode(imv);
        transition.play();
        transition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                status = false;
            }
        });

    }


    @Override
    public void reward(Label label, Label scoreBoard) {
        Main.player.setPlayerScore(Main.player.getPlayerScore()+this.getPoints());
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(()->{
                    label.setText(String.valueOf(getWaterMelonCount()));
                    scoreBoard.setText(String.valueOf(Main.player.getPlayerScore()));
                    System.out.println("Player score :" + Main.player.getPlayerScore());
                });
            }
        },0);

    }





}

