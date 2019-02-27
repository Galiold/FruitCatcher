package com.KahaniM.Game;

import com.KahaniM.FruitsScript.Fruits;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

/**
 * Created by Mohammad Kahani on 23/06/2017.
 */
public class Player {
    private String name;
    private int PlayerScore;
    private int PlayerTime;
    private int heart;


    public Player(String name){
        setName(name);
        setHeart(3);
        setPlayerScore(0);
        setPlayerTime(120);
    }

    public  void audio() {
        try {
            File file = new File("sound/Uncharted_2_Main_Theme.wav");
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(file));
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
        } catch (Exception e) {
            System.err.println("Put the music.wav file in the sound folder if you want to play background music, only optional!");
        }
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPlayerScore() {
        return PlayerScore;
    }

    public void setPlayerScore(int playerScore) {
        PlayerScore = playerScore;
    }

    public void setPlayerScore(Fruits fruit) {
        PlayerScore += fruit.getPoints();
    }

    public int getPlayerTime() {
        return PlayerTime;
    }

    public void setPlayerTime(int playerTime) {
        PlayerTime = playerTime;
    }

    public int getHeart() {
        return heart;
    }

    public void setHeart(int heart) {
        this.heart = heart;
    }

    public void incPlayerTime(int inc) {
        PlayerTime += inc;
    }

}
