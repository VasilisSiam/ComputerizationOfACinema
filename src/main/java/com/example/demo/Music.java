package com.example.demo;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Music {
    public StringProperty id;
    public StringProperty  music_title;

    public StringProperty movie_title;
    public   StringProperty composer;

    public Music() {
        this.id = new SimpleStringProperty();
        this.music_title = new SimpleStringProperty();
        this.movie_title = new SimpleStringProperty();
        this.composer = new SimpleStringProperty();
    }

    public StringProperty idProperty() {
        return id;
    }

    public StringProperty music_titleProperty() {
        return music_title;
    }

    public StringProperty movie_titleProperty() {
        return movie_title;
    }

    public StringProperty composerProperty() {
        return composer;
    }

    public void setMusic_title(String music_title) {
        this.music_title.set(music_title);
    }

    public void setMovie_title(String movie_title) {
        this.movie_title.set(movie_title);
    }

    public void setComposer(String composer) {
        this.composer.set(composer);
    }

    public void setId(String id) {
        this.id.set(id);
    }

    @Override
    public String toString() {
        return this.id.get()+","+this.music_title.get()+","+this.movie_title.get()+","+this.composer.get();
    }
}
