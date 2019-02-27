package com.KahaniM.GUI;

import com.KahaniM.Game.FileStream;
import com.KahaniM.Game.Main;
import com.KahaniM.Game.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

import static com.KahaniM.Game.Main.player;

public class MenuController {
    private FXMLLoader loader = new FXMLLoader();
    private Pane menuPane;
    public static FileStream input;

    public static Stage stage = new Stage();

    @FXML
    private TextField nameField;

    @FXML
    private Label label;

    @FXML
    private Button btnNewGame;

    @FXML
    private Button btnCredits;

    @FXML
    private Button btnHTP;

    @FXML
    private Button btnHighScores;


    @FXML
    void newGame (ActionEvent event) {
        String name = nameField.getText();

        if(!name.equals("")) {
            MainMenu.firstPage.hide();
            Main.player = new Player(name);
            input =new FileStream();
            input.setPlayer(Main.player);
            System.out.println("Player name set: " + name);


            try {
                MainMenu.runAnotherApp(GameScene.class);
            } catch (Exception e) {
                e.printStackTrace();
            }


        } else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Empty Name Field!");
            alert.setHeaderText("You haven't entered a name!");
            alert.setContentText("In order to play, you should enter a name " +
                    "to have your highest score saved under that name, " +
                    "so please enter a name and then try again.");

            alert.showAndWait();
        }
    }

    @FXML
    void howToPlay(ActionEvent event) throws IOException {
        menuPane = loader.load(getClass().getResource("Rules.fxml"));
        stage.setScene(new Scene(menuPane,500,700));
        stage.setResizable(false);

        MainMenu.firstPage.hide();
        stage.show();
    }

    @FXML
    void showHighScores(ActionEvent event){
//        menuPane = loader.load(getClass().getResource("HighScores.fxml"));
//        stage.setScene(new Scene(menuPane,500,700));
//        stage.setResizable(false);
//
//        MainMenu.firstPage.hide();
//        stage.show();
        FileStream fileStream = new FileStream();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("High Scores");
        alert.setHeaderText(null);
        alert.setContentText(fileStream.printFile());
        alert.showAndWait();
    }

    @FXML
    void showCredits(ActionEvent event) throws IOException{
        menuPane = loader.load(getClass().getResource("Credits.fxml"));
        stage.setScene(new Scene(menuPane,500,700));
        stage.setResizable(false);

        MainMenu.firstPage.hide();
        stage.show();
    }

}


