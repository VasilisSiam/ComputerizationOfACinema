package com.example.demo;

import javafx.application.Application;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.lang.*;
import javafx.util.Duration;
import javafx.animation.*;


import java.sql.SQLException;
import java.text.ParseException;
import java.util.Objects;

public class Main extends Application {
    static boolean result;
    Stage window;
    Scene loginScene,MainMenuScene,MusicScene,DirectorsScene,UpcomingScene,ActorScene,MovieScene,LogScene;

    @Override
    public void start(Stage primaryStage) throws Exception {
        //Load Login page file
        FXMLLoader login = new FXMLLoader(getClass().getResource("loginPage.fxml"));
        Parent loginRoot = login.load();
        loginScene = new Scene(loginRoot);

        //Load mainMenu Page file

        FXMLLoader MainMenu= new FXMLLoader(getClass().getResource("MainMenu.fxml"));
        Parent MainMenuRoot= MainMenu.load();
        MainMenuScene = new Scene(MainMenuRoot);
       

        //load Music page file
        FXMLLoader Music = new FXMLLoader(getClass().getResource("Music.fxml"));
        Parent MusicRoot = Music.load();
        MusicScene= new Scene(MusicRoot);


        FXMLLoader Directors=new FXMLLoader(getClass().getResource("Directors.fxml"));
      Parent DirectorsRoot=Directors.load();
      DirectorsScene=new Scene(DirectorsRoot);

      FXMLLoader Upcoming=new FXMLLoader(getClass().getResource("Upcoming.fxml"));
      Parent UpcomingRoot=Upcoming.load();
      UpcomingScene=new Scene(UpcomingRoot);

      FXMLLoader Movie=new FXMLLoader(getClass().getResource("Movie.fxml"));
      Parent MovieRoot=Movie.load();
      MovieScene=new Scene(MovieRoot);

      FXMLLoader Actor=new FXMLLoader(getClass().getResource("Actor.fxml"));
      Parent ActorRoot=Actor.load();
      ActorScene=new Scene(ActorRoot);

      FXMLLoader Log=new FXMLLoader(getClass().getResource("Log.fxml"));
      Parent LogRoot=Log.load();
      LogScene=new Scene(LogRoot);

        //End of load Fxml Files



// event handler gia otan pataei to koympi sign in - start
        LoginController loginCntrl = login.getController();
        loginCntrl.getAuthButton().setOnAction(e -> {
            if(loginCntrl.getUserTextField().getText().equals("Vasilis") && loginCntrl.getPassWordTextField().getText().equals("Vasilis")){
                primaryStage.setScene(MainMenuScene);
            }else{
                loginCntrl.getOutputText().setText("Authentication Error.");
            }
        });
        //Ftiaxnw ena antikeimeno MainMenuController gia diaxeirish


       MainMenuController MainC= MainMenu.getController();

        //OPEn pages -start
        //Event handling gia otan pataw to koumpi Music
        MainC.getMusic().setOnAction(e -> {
            primaryStage.setScene(MusicScene);
        });

        //Event Handling gia otan pataw to koumpi Directors
        MainC.getDirector().setOnAction(e ->{
            primaryStage.setScene(DirectorsScene);
        });

        MainC.getCloseButton().setOnAction(e ->{
            primaryStage.setScene(MainMenuScene);
        });

        MainC.getUpcoming().setOnAction(e ->{
            primaryStage.setScene(UpcomingScene);
        });
        MainC.getActor().setOnAction(e ->{
            primaryStage.setScene(ActorScene);
        });
        MainC.getMovie().setOnAction(e ->{
            primaryStage.setScene(MovieScene);
        });
        MainC.getLog_File().setOnAction(e ->{
            primaryStage.setScene(LogScene);
        });
        //Event Handlers gia gobackButton

        ActorController ActorC=Actor.getController();
        ActorC.getGoBackButton().setOnAction(e ->{
            primaryStage.setScene(MainMenuScene);
        });

        MovieController MovieC=Movie.getController();
        MovieC.getGoBackButton().setOnAction(e -> {
                    primaryStage.setScene(MainMenuScene);
                });
        DirectorsController DirectorD=Directors.getController();
        DirectorD.getGoBackButton().setOnAction(e -> {
            primaryStage.setScene(MainMenuScene);
        });
        MusicController MusicC=Music.getController();
        MusicC.getGoBackButton().setOnAction(e -> {
            primaryStage.setScene(MainMenuScene);
        });
        LogControler logC=Log.getController();
        logC.getBack().setOnAction(e ->{
            primaryStage.setScene(MainMenuScene);
        });

        UpcomingController UpcomingC=Upcoming.getController();
        UpcomingC.getGoBackButton().setOnAction(e -> {
            primaryStage.setScene(MainMenuScene);
        });

        //event Handling for closing window
        MainC.getCloseButton().setOnAction(e -> {
            primaryStage.close();
        });


    ActorC.getInsertButton().setOnAction(e ->{
        insertActor(ActorC);
    });
    DirectorD.getInsertButton().setOnAction(e ->{
        insertDirector(DirectorD);
    });
        MovieC.getInsertButton().setOnAction(e ->{
            insertMovie(MovieC);
        });

        MusicC.getInsertButton().setOnAction(e ->{
                insertMusic(MusicC);
        });
        UpcomingC.getInsertButton().setOnAction(e ->{
            insertUpcoming(UpcomingC);
        });
        ActorC.getDeleteButton().setOnAction(e ->{
            deleteActor(ActorC);
        });
        MovieC.getDeleteButton().setOnAction(e ->{
            deleteMovie(MovieC);
        });
        MusicC.getDeleteButton().setOnAction(e ->{
            deleteMusic(MusicC);
        });
        DirectorD.getDeleteButton().setOnAction(e ->{
            deleteDirector(DirectorD);
        });
        UpcomingC.getDeleteButton().setOnAction(e ->{
            deleteUpcoming(UpcomingC);
        });

        //event handling gia filtra ston movie
        final ToggleGroup myFiltersMovie=MovieC.getFilters();
        MovieC.getGreek().setToggleGroup(myFiltersMovie);
        MovieC.getUsa().setToggleGroup(myFiltersMovie);
        MovieC.getForeign().setToggleGroup(myFiltersMovie);
        MovieC.getNoFilter().setToggleGroup(myFiltersMovie);
        myFiltersMovie.selectedToggleProperty().addListener(e ->{
            String epilogh=((RadioButton)myFiltersMovie.getSelectedToggle()).getText();
            MovieC.getSelectedFilter().setText("Show only"+epilogh);
            try{
                MovieC.filterBy(epilogh);
            }catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException classNotFoundException) {
                classNotFoundException.printStackTrace();
            }
        });
        final ToggleGroup myFilterActor=ActorC.getFilters();
        ActorC.getFemale().setToggleGroup(myFilterActor);
        ActorC.getMale().setToggleGroup(myFilterActor);
        ActorC.getNoFilter().setToggleGroup(myFilterActor);
        myFilterActor.selectedToggleProperty().addListener(e ->{
            String epilogh=((RadioButton)myFilterActor.getSelectedToggle()).getText();
            ActorC.getSelectedFilter().setText("Show only"+epilogh);
            try{
                ActorC.filterBy(epilogh);
            }catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException classNotFoundException) {
                classNotFoundException.printStackTrace();
            }

        });

        MusicC.getFilter().textProperty().addListener(e ->{
            String epilogh=MusicC.getFilter().getText();
            try{
                MusicC.filterBy(epilogh);
            }catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException classNotFoundException) {
            classNotFoundException.printStackTrace();
        }

        });
        DirectorD.getFilter().textProperty().addListener(e -> {
                    String epilogh = DirectorD.getFilter().getText();
                    try {
                        DirectorD.filterBy(epilogh);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    } catch (ClassNotFoundException classNotFoundException) {
                        classNotFoundException.printStackTrace();
                    }
                });



        primaryStage.setTitle("Paramount");
        primaryStage.setScene(loginScene);
        primaryStage.show();



        //FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("LoginPage.fxml"));
        //Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        //stage.setTitle("Hello!");
        //stage.setScene(scene);
        //stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
//insert gia tous pinakes

    public static void insertActor (ActorController ActorCntrl){
       String a_id=ActorCntrl.getIdColumnInput().getText();
       String actor_name=ActorCntrl.getNameColumnInput().getText();
       String gender=ActorCntrl.getGenderColumnInput().getText();
       String age=ActorCntrl.getAgeColumnInput().getText();
       String movie=ActorCntrl.getMovieColumnInput().getText();
        try{
            boolean result = ActorCntrl.insert( a_id,actor_name,gender,age,movie);
            if (result) {
                ActorCntrl.getMovieColumnInput().setText("");
                ActorCntrl.getAgeColumnInput().setText("");
                ActorCntrl.getGenderColumnInput().setText("");
                ActorCntrl.getNameColumnInput().setText("");
                ActorCntrl.getIdColumnInput().setText("");

                ActorCntrl.getInsertError().setText("Η ΝΕΑ ΕΓΓΡΑΦΗ ΑΠΟΘΗΚΕΥΤΗΚΕ ΜΕ ΕΠΙΤΥΧΙΑ");
                Text message = ActorCntrl.getInsertError();
                makeTextFade(message);
            }else {
                ActorCntrl.getInsertError().setStyle("-fx-fill : red");
                ActorCntrl.getInsertError().setText("ΠΑΡΑΚΑΛΩ ΕΙΣΑΓΕΤΑΙ ΣΩΣΤΑ ΤΑ ΣΤΟΙΧΕΙΑ");
                Text message = ActorCntrl.getInsertError();
                makeTextFade(message);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
    public static void insertDirector (DirectorsController DirectorCntrl){
        String d_id=DirectorCntrl.getIdColumnInput().getText();
        String d_name=DirectorCntrl.getNameColumnInput().getText();
        String movie=DirectorCntrl.getMovieColumnInput().getText();
        String age=DirectorCntrl.getAgeColumnInput().getText();
        try{
            boolean result = DirectorCntrl.insert( d_id,d_name,movie,age);
            if (result) {
                DirectorCntrl.getMovieColumnInput().setText("");
                DirectorCntrl.getAgeColumnInput().setText("");
                DirectorCntrl.getNameColumnInput().setText("");
                DirectorCntrl.getIdColumnInput().setText("");

                DirectorCntrl.getInsertError().setText("Η ΝΕΑ ΕΓΓΡΑΦΗ ΑΠΟΘΗΚΕΥΤΗΚΕ ΜΕ ΕΠΙΤΥΧΙΑ");
                Text message = DirectorCntrl.getInsertError();
                makeTextFade(message);
            }else {
                DirectorCntrl.getInsertError().setStyle("-fx-fill : red");
                DirectorCntrl.getInsertError().setText("ΠΑΡΑΚΑΛΩ ΕΙΣΑΓΕΤΑΙ ΣΩΣΤΑ ΤΑ ΣΤΟΙΧΕΙΑ");
                Text message = DirectorCntrl.getInsertError();
                makeTextFade(message);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

        public static void insertMusic (MusicController MusicCntrl){
            String m_id=MusicCntrl.getIdColumnInput().getText();
            String music_title=MusicCntrl.getMusicColumnInput().getText();
            String movie_title=MusicCntrl.getMovieNumberColumnInput().getText();
            String composer=MusicCntrl.getNumOfComposerColumnInput().getText();
            try{
                boolean result = MusicCntrl.insert( m_id,music_title,movie_title,composer);
                if (result) {
                    MusicCntrl.getNumOfComposerColumnInput().setText("");
                    MusicCntrl.getMovieNumberColumnInput().setText("");
                    MusicCntrl.getMusicColumnInput().setText("");
                    MusicCntrl.getIdColumnInput().setText("");

                    MusicCntrl.getInsertError().setText("Η ΝΕΑ ΕΓΓΡΑΦΗ ΑΠΟΘΗΚΕΥΤΗΚΕ ΜΕ ΕΠΙΤΥΧΙΑ");
                    Text message = MusicCntrl.getInsertError();
                    makeTextFade(message);
                }else {
                    MusicCntrl.getInsertError().setStyle("-fx-fill : red");
                    MusicCntrl.getInsertError().setText("ΠΑΡΑΚΑΛΩ ΕΙΣΑΓΕΤΑΙ ΣΩΣΤΑ ΤΑ ΣΤΟΙΧΕΙΑ");
                    Text message = MusicCntrl.getInsertError();
                    makeTextFade(message);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

    }
    public static void insertMovie (MovieController MovieCntrl){
        String m_id=MovieCntrl.getIdColumnInput().getText();
        String title=MovieCntrl.getTitleColumnInput().getText();
        String paragogi=MovieCntrl.getProductionColumnInput().getText();
        String Date=MovieCntrl.getDateColumnInput().getText();
        String critique=MovieCntrl.getCritiqueColumnInput().getText();
        try{
            boolean result = MovieCntrl.insert( m_id,title,paragogi,Date,critique);
            if (result) {
                MovieCntrl.getIdColumnInput().setText("");
                MovieCntrl.getTitleColumnInput().setText("");
                MovieCntrl.getProductionColumnInput().setText("");
                MovieCntrl.getDateColumnInput().setText("");
                MovieCntrl.getCritiqueColumnInput().setText("");
                MovieCntrl.getInsertError().setText("Η ΝΕΑ ΕΓΓΡΑΦΗ ΑΠΟΘΗΚΕΥΤΗΚΕ ΜΕ ΕΠΙΤΥΧΙΑ");
                Text message = MovieCntrl.getInsertError();
                makeTextFade(message);
            }else {
                MovieCntrl.getInsertError().setStyle("-fx-fill : red");
                MovieCntrl.getInsertError().setText("ΠΑΡΑΚΑΛΩ ΕΙΣΑΓΕΤΑΙ ΣΩΣΤΑ ΤΑ ΣΤΟΙΧΕΙΑ");
                Text message = MovieCntrl.getInsertError();
                makeTextFade(message);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
    public static void insertUpcoming (UpcomingController UpcomingCntrl){
        String up_id=UpcomingCntrl.getIdColumnInput().getText();
        String releaseDate=UpcomingCntrl.getReleaseDateColumnInput().getText();
        String movie_title=UpcomingCntrl.getMovieTitleColumnInput().getText();
        String producer=UpcomingCntrl.getNumOfComposerColumnInput().getText();
        try{
            boolean result = UpcomingCntrl.insert( up_id,movie_title,releaseDate,producer);
            if (result) {
                UpcomingCntrl.getNumOfComposerColumnInput().setText("");
                UpcomingCntrl.getReleaseDateColumnInput().setText("");
                UpcomingCntrl.getMovieTitleColumnInput().setText("");
                UpcomingCntrl.getIdColumnInput().setText("");

                UpcomingCntrl.getInsertError().setText("Η ΝΕΑ ΕΓΓΡΑΦΗ ΑΠΟΘΗΚΕΥΤΗΚΕ ΜΕ ΕΠΙΤΥΧΙΑ");
                Text message = UpcomingCntrl.getInsertError();
                makeTextFade(message);
            }else {
                UpcomingCntrl.getInsertError().setStyle("-fx-fill : red");
                UpcomingCntrl.getInsertError().setText("ΠΑΡΑΚΑΛΩ ΕΙΣΑΓΕΤΑΙ ΣΩΣΤΑ ΤΑ ΣΤΟΙΧΕΙΑ");
                Text message = UpcomingCntrl.getInsertError();
                makeTextFade(message);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
//delete gia tous pinakes

    public static void deleteActor(ActorController ActorCntrl) {
        Actor selectedItem = ActorCntrl.getActorView().getSelectionModel().getSelectedItem();

        try {
            boolean result = ActorCntrl.delete(selectedItem);
            if (result) {
                Text message = ActorCntrl.getDeleteError();
                message.setText("Η ΕΓΓΡΑΦΗ ΔΙΑΓΡΑΦΤΗΚΕ ΜΕ ΕΠΙΤΥΧΙΑ!");
                makeTextFade(message);

            } else {
                ActorCntrl.getDeleteError().setStyle("-fx-fill : red");
                ActorCntrl.getDeleteError().setText("ΕΠΙΛΕΞΤΕ ΜΙΑ ΓΡΑΜΜΗ ΓΙΑ ΔΙΑΓΡΑΦΗ");
                Text message = ActorCntrl.getDeleteError();
                makeTextFade(message);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException classNotFoundException) {
            classNotFoundException.printStackTrace();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
        public static void deleteMovie(MovieController MovieCntrl)  {
            Movie selectedItem = MovieCntrl.getMovieView().getSelectionModel().getSelectedItem();

            try {
                boolean result = MovieCntrl.delete(selectedItem);
                if (result) {
                    Text message = MovieCntrl.getDeleteError();
                    message.setText("Η ΕΓΓΡΑΦΗ ΔΙΑΓΡΑΦΤΗΚΕ ΜΕ ΕΠΙΤΥΧΙΑ!");
                    makeTextFade(message);

                }else {
                    MovieCntrl.getDeleteError().setStyle("-fx-fill : red");
                    MovieCntrl.getDeleteError().setText("ΕΠΙΛΕΞΤΕ ΜΙΑ ΓΡΑΜΜΗ ΓΙΑ ΔΙΑΓΡΑΦΗ");
                    Text message = MovieCntrl.getDeleteError();
                    makeTextFade(message);

                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException classNotFoundException) {
                classNotFoundException.printStackTrace();
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
    }



    public static void deleteMusic(MusicController MusicCntrl)  {
        Music selectedItem = MusicCntrl.getMusicView().getSelectionModel().getSelectedItem();

        try {
            boolean result = MusicCntrl.delete(selectedItem);
            if (result) {
                Text message = MusicCntrl.getDeleteError();
                message.setText("Η ΕΓΓΡΑΦΗ ΔΙΑΓΡΑΦΤΗΚΕ ΜΕ ΕΠΙΤΥΧΙΑ!");
                makeTextFade(message);

            }else {
                MusicCntrl.getDeleteError().setStyle("-fx-fill : red");
                MusicCntrl.getDeleteError().setText("ΕΠΙΛΕΞΤΕ ΜΙΑ ΓΡΑΜΜΗ ΓΙΑ ΔΙΑΓΡΑΦΗ");
                Text message = MusicCntrl.getDeleteError();
                makeTextFade(message);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException classNotFoundException) {
            classNotFoundException.printStackTrace();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
    public static void deleteUpcoming(UpcomingController UpcomingCntrl)  {
        Upcoming selectedItem = UpcomingCntrl.UpcomingView().getSelectionModel().getSelectedItem();

        try {
            boolean result = UpcomingCntrl.delete(selectedItem);
            if (result) {
                Text message = UpcomingCntrl.getDeleteError();
                message.setText("Η ΕΓΓΡΑΦΗ ΔΙΑΓΡΑΦΤΗΚΕ ΜΕ ΕΠΙΤΥΧΙΑ!");
                makeTextFade(message);

            }else {
                UpcomingCntrl.getDeleteError().setStyle("-fx-fill : red");
                UpcomingCntrl.getDeleteError().setText("ΕΠΙΛΕΞΤΕ ΜΙΑ ΓΡΑΜΜΗ ΓΙΑ ΔΙΑΓΡΑΦΗ");
                Text message = UpcomingCntrl.getDeleteError();
                makeTextFade(message);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException classNotFoundException) {
            classNotFoundException.printStackTrace();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }

    public static void deleteDirector(DirectorsController DirectorCntrl)  {
        Directors selectedItem = DirectorCntrl.DirectorsView().getSelectionModel().getSelectedItem();

        try {
            boolean result = DirectorCntrl.delete(selectedItem);
            if (result) {
                Text message = DirectorCntrl.getDeleteError();
                message.setText("Η ΕΓΓΡΑΦΗ ΔΙΑΓΡΑΦΤΗΚΕ ΜΕ ΕΠΙΤΥΧΙΑ!");
                makeTextFade(message);

            }else {
                DirectorCntrl.getDeleteError().setStyle("-fx-fill : red");
                DirectorCntrl.getDeleteError().setText("ΕΠΙΛΕΞΤΕ ΜΙΑ ΓΡΑΜΜΗ ΓΙΑ ΔΙΑΓΡΑΦΗ");
                Text message = DirectorCntrl.getDeleteError();
                makeTextFade(message);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException classNotFoundException) {
            classNotFoundException.printStackTrace();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }

    private static FadeTransition createFader(Node node) {
        FadeTransition fade = new FadeTransition(Duration.seconds(4), node);
        fade.setFromValue(1);
        fade.setToValue(0);

        return fade;
    }

    private static void  makeTextFade(Text message){
        FadeTransition fader = createFader(message);
        SequentialTransition seqT = new SequentialTransition(
                message,
                fader
        ) ;
        seqT.play();
    }
}