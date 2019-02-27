package com.KahaniM.GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * Created by A. Goldani on 6/27/17.
 * FinalProject-Menu-last
 */

public class RulesController {


    @FXML
    private Button btnBack;

    @FXML
    void backToMainMenu(ActionEvent event) {
        MenuController.stage.hide();
        MainMenu.firstPage.show();
    }
}
