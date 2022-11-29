package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MainMenuController {


    @FXML
    private Button Movie,Actor,Music,Director,Upcoming,closeButton;
    @FXML
    private Button Log_File;
    public Button getLog_File(){
        return Log_File;
    }
    public Button getMovie() {
        return Movie;
    }

    public Button getActor() {
        return Actor;
    }

    public Button getMusic() {
        return Music;
    }

    public Button getDirector() {
        return Director;
    }

    public Button getUpcoming() {
        return Upcoming;
    }

    public Button getCloseButton() {
        return closeButton;
    }


}
