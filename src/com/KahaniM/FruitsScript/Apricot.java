package com.KahaniM.FruitsScript;

import com.KahaniM.GUI.GameScene;
import com.KahaniM.Game.Main;
import javafx.animation.PathTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.ArcTo;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Mohammad Kahani on 22/06/2017.
 */
public class Apricot extends Fruits {

    ImageView imv;
    public Apricot() {
        super(3);
        setRadius(5);
        setNumPS(2);
        setFallType("Circular");
        setPoints(30);
        setSpeed(20);
        Image image = new Image(Apricot.class.getResourceAsStream("Apricot.png"));
        imv = new ImageView(image);
        setFruitImage(imv);
        GameScene.addImage(imv);
        imv.setVisible(false);
        AMove();
        imv.setVisible(true);

    }


    public void AMove(){
        Random rand = new Random();
        int value = rand.nextInt(990)+10;
        imv.setX(value);
        imv.setY(60);

        Path path = new Path();

        path.getElements().add (new MoveTo(imv.getX(), imv.getY()));
        while (imv.getY()<700f){
            path.getElements().add (new CubicCurveTo(imv.getX()-30f, imv.getY()+40f, imv.getX(), imv.getY()+80f, imv.getX()+30f, imv.getY()+40f));
            imv.setY(imv.getY()+40f);
        }

        ArcTo arcTo = new ArcTo();
        arcTo.setX(imv.getX());
        arcTo.setY(imv.getY());
        path.getElements().add(arcTo);

        PathTransition pathTransition = new PathTransition();
        pathTransition.setNode(imv);
        pathTransition.setPath(path);
        pathTransition.setDuration(Duration.seconds(10));
        pathTransition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                status = false;
            }
        });


        pathTransition.play();
    }

    @Override
    public void reward(Label label, Label scoreBoard) {
        Main.player.setPlayerScore(Main.player.getPlayerScore()+this.getPoints());
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    label.setText(String.valueOf(getApricotCount()));
                    scoreBoard.setText(String.valueOf(Main.player.getPlayerScore()));
                    System.out.println("Player score :" + Main.player.getPlayerScore());
                });
            }
        }, 0);

    }





}
