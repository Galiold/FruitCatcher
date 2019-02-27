package com.KahaniM.Game;

import com.KahaniM.GUI.GameScene;
import com.KahaniM.GUI.MenuController;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Mohammad Kahani on 23/06/2017.
 */
public class TimerClock {
    private int time;
    private Label timerLabel;

    public TimerClock(Label timerLabel) {
        this.timerLabel = timerLabel;
        timerLabel.setLayoutY(9);
        timerLabel.setLayoutX(14);
        timerLabel.setPrefSize(73, 34);
        timerLabel.setStyle("-fx-background-color: seagreen");
        timerLabel.setFont(new Font("Magnificent", 25));
        timerLabel.setAlignment(Pos.CENTER);
        timerLabel.setContentDisplay(ContentDisplay.CENTER);
        GameScene.addLabel(timerLabel);
    }
    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }


    public void timer() {
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() ->{
                    if (Main.player.getPlayerTime() > 120){
                        timerLabel.setText("2:"+String.valueOf(Main.player.getPlayerTime()-120));
                        Main.player.setPlayerTime(Main.player.getPlayerTime() - 1);
                    } else if (Main.player.getPlayerTime() == 120) {
                        timerLabel.setText("2:00");
                        Main.player.setPlayerTime(Main.player.getPlayerTime() - 1);
                    } else if (Main.player.getPlayerTime() > 60) {
                        timerLabel.setText("1:" + String.valueOf(Main.player.getPlayerTime() - 60));
                        Main.player.setPlayerTime(Main.player.getPlayerTime() - 1);
                    } else if (Main.player.getPlayerTime() == 60) {
                        timerLabel.setText("1:00");
                        Main.player.setPlayerTime(Main.player.getPlayerTime() - 1);
                    } else if(Main.player.getPlayerTime()>0) {
                        timerLabel.setText("0:" + String.valueOf(Main.player.getPlayerTime()));
                        Main.player.setPlayerTime(Main.player.getPlayerTime()-1);
                    }else if(Main.player.getPlayerTime()==0){
                        System.exit(0);
                    }
                    MenuController.input.setPlayer(Main.player.getName(),Main.player.getPlayerScore());
                    MenuController.input.write();

                });
            }

        }, 0, 1000);

    }


}
