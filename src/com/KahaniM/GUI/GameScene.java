package com.KahaniM.GUI;


import com.KahaniM.FruitsScript.*;
import com.KahaniM.Game.Main;
import com.KahaniM.Game.TimerClock;
import com.KahaniM.WormsScript.BasketWorm;
import com.KahaniM.WormsScript.HeartWorm;
import com.KahaniM.WormsScript.TimeWorm;
import com.KahaniM.WormsScript.Worms;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Random;

import static com.KahaniM.FruitsScript.Fruits.*;

public class GameScene extends Application {
    private LinkedList<Fruits> fruits;
    private LinkedList<Worms> worms;
    private static TimerClock timer;
    private Label timerLabel = new Label();
    public static Pane root;
    private static Stage mainStage;

    public static Stage getMainStage() {
        return mainStage;
    }

    private static long bwadd = 0;
    private static long hwadd = 0;
    private static long twadd = 0;

    private Label apricotCounter = new Label();
    private Label waterMelonCounter = new Label();
    private Label orangeCounter = new Label();
    private Label scoreboard = new Label();

    private static ImageView heart1 = new ImageView(new Image(GameScene.class.getResourceAsStream("Heart.png")));
    private static ImageView heart2 = new ImageView(new Image(GameScene.class.getResourceAsStream("Heart.png")));
    private static ImageView heart3 = new ImageView(new Image(GameScene.class.getResourceAsStream("Heart.png")));
    private static ImageView[] heartImages = {heart1, heart2, heart3};

    public static ImageView[] getHeartImages() {
        return heartImages;
    }

    public static void noWorm(){
        bwadd +=10_000_000;
        hwadd +=10_000_000;
        twadd +=10_000_000;
    }

    public static TimerClock getTimer() {
        return timer;
    }

    public void start(Stage primaryStage) throws Exception {


        root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        mainStage = primaryStage;
        primaryStage.setTitle("Fruit Basket");
        primaryStage.setOnCloseRequest(event -> {
            System.exit(0);
        });
        Scene scene = new Scene(root, 1024, 576);

        Main.basket = new Basket();
        GameScene.addImage(Main.basket.getImv());

        Main.player.audio();

        //setting the apricot counter label
        apricotCounter.setLayoutX(460);
        apricotCounter.setLayoutY(9);
        apricotCounter.setPrefSize(52,34);
        apricotCounter.setStyle("-fx-background-color: seagreen");
        apricotCounter.setText("0");
        apricotCounter.setFont(new Font("Magnificent",25));
        apricotCounter.setAlignment(Pos.CENTER);
        apricotCounter.setContentDisplay(ContentDisplay.CENTER);


        //setting the watermelon counter label
        waterMelonCounter.setLayoutX(602);
        waterMelonCounter.setLayoutY(9);
        waterMelonCounter.setPrefSize(52,34);
        waterMelonCounter.setStyle("-fx-background-color: seagreen");
        waterMelonCounter.setText("0");
        waterMelonCounter.setFont(new Font("Magnificent",25));
        waterMelonCounter.setAlignment(Pos.CENTER);
        waterMelonCounter.setContentDisplay(ContentDisplay.CENTER);


        //setting the orange counter label
        orangeCounter.setLayoutX(753);
        orangeCounter.setLayoutY(9);
        orangeCounter.setPrefSize(52,34);
        orangeCounter.setStyle("-fx-background-color: seagreen");
        orangeCounter.setText("0");
        orangeCounter.setFont(new Font("Magnificent",25));
        orangeCounter.setAlignment(Pos.CENTER);
        orangeCounter.setContentDisplay(ContentDisplay.CENTER);

        //setting the scoreboard label
        scoreboard.setLayoutX(220);
        scoreboard.setLayoutY(9);
        scoreboard.setPrefSize(110,34);
        scoreboard.setStyle("-fx-background-color: seagreen");
        scoreboard.setText("0");
        scoreboard.setFont(new Font("Magnificent",25));
        scoreboard.setAlignment(Pos.CENTER);
        scoreboard.setContentDisplay(ContentDisplay.CENTER);

        root.getChildren().addAll(apricotCounter,waterMelonCounter,orangeCounter,scoreboard);


        //setting hearts in the game
        heartImages[0].setLayoutX(867);
        heartImages[0].setLayoutY(8);
        heartImages[0].setFitWidth(40);
        heartImages[0].setFitHeight(40);
        heartImages[0].setPreserveRatio(true);

        heartImages[1].setLayoutX(907);
        heartImages[1].setLayoutY(8);
        heartImages[1].setFitWidth(40);
        heartImages[1].setFitHeight(40);
        heartImages[1].setPreserveRatio(true);

        heartImages[2].setLayoutX(947);
        heartImages[2].setLayoutY(8);
        heartImages[2].setFitWidth(40);
        heartImages[2].setFitHeight(40);
        heartImages[2].setPreserveRatio(true);


        root.getChildren().addAll(heartImages[0], heartImages[1], heartImages[2]);


        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (Main.basket.isCanMove()) {
                    if (event.getCode().equals(KeyCode.RIGHT)) {
                        Main.basket.incX();
                    } else if (event.getCode().equals(KeyCode.LEFT)) {
                        Main.basket.decX();
                    }
                    Main.basket.updateImageView();
                }
            }
        });

        fruits = new LinkedList<Fruits>();
        worms = new LinkedList<Worms>();


        timer = new TimerClock(timerLabel);
        timer.timer();

        AnimationTimer timer = new MyTimer();
        timer.start();


        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void addImage(ImageView imv) {
        root.getChildren().add(imv);
    }
    public static void addLabel(Label label){root.getChildren().add(label);}


    private class MyTimer extends AnimationTimer {
        private Random random = new Random();
        private long wadd = 0;
        private long oadd = 0;
        private long aadd = 0;
        private long xadd = 0;
        @Override
        public void handle(long now) {
            checkCaught();
            setHeart();
            adjustScen(now);
        }

        private void adjustScen(long now) {
            Main.basket.setBasket();
            createEntity(now/1000);
        }

        private void setHeart() {

        }

        private  synchronized void  createEntity(long now) {

            if (now - wadd >= 1_500_000) {
                wadd =  now;
                fruits.add(new Watermelon());

            } else if (now - oadd >= 1_000_000) {
                oadd =  now;
                fruits.add(new Orange());
            } else if (now - aadd >= 750_000) {
                aadd =  now;
                fruits.add(new Apricot());
            } else if (now - bwadd >= 2_000_000) {
                bwadd =  now;
                worms.add(new BasketWorm());
            } else if (now - hwadd >= 2_000_000) {
                hwadd =  now;
                worms.add(new HeartWorm());
            } else if (now - twadd >= 2_000_000) {
                twadd =  now;
                worms.add(new TimeWorm());
            } else if (now - xadd >= 20_000_000) {
                xadd =  now;
                fruits.add(new ExtraordinaryFriuts(random.nextInt(4)+1));

        }


    }

        private  synchronized void  checkCaught() {

            ListIterator listIt = fruits.listIterator();
            Fruits fruit;

            while (listIt.hasNext()) {
                fruit = (Fruits) listIt.next();
                if (fruit.isCaught()) {
                    if(fruit instanceof Watermelon){
                        setWaterMelonCount(getWaterMelonCount()+1);
                        fruit.reward(waterMelonCounter,scoreboard);
                        listIt.remove();
                    }
                    else if(fruit instanceof Orange){
                        setOrangeCount(getOrangeCount()+1);
                        fruit.reward(orangeCounter,scoreboard);
                        listIt.remove();
                    }
                    else if (fruit instanceof Apricot){
                        setApricotCount(getApricotCount()+1);
                        fruit.reward(apricotCounter,scoreboard);
                        listIt.remove();
                    }
                    else {
                        fruit.reward();
                        listIt.remove();
                    }
                } else if (!fruit.getStatus()) {
                    System.out.println("stat in check "+ fruit.getStatus());
                    System.out.println("total fruits: "+fruits.size());
                    fruit.setInvisible();
                    listIt.remove();
                }
            }
            listIt = worms.listIterator(



            );
            Worms worm;

            while (listIt.hasNext()) {
                worm = (Worms) listIt.next();
                if (worm.isCaught()) {
                    worm.damage();
                    listIt.remove();
                } else if (!worm.getStatus()) {
                    worm.setInvisible();
                    listIt.remove();
                }
            }

        }

    }

}

