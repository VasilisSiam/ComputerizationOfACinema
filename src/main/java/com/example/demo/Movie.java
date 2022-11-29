package com.example.demo;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Movie {
    private StringProperty id;
    private StringProperty movie_title;
    private StringProperty paragogi;
    private StringProperty releasedate;
    private  StringProperty critique;
    public Movie() {
        this.id=new SimpleStringProperty();
        this.movie_title=new SimpleStringProperty();
        this.paragogi=new SimpleStringProperty();
        this.releasedate=new SimpleStringProperty();
        this.critique=new SimpleStringProperty();
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public void setMovie_title(String movie_title) {
        this.movie_title.set(movie_title);
    }

    public void setParagogi(String paragogi) {
        this.paragogi.set(paragogi);
    }

    public void setReleasedate(String releasedate) {
        this.releasedate.set(releasedate);
    }

    public void setCritique(String critique) {
        this.critique.set(critique);
    }

    public StringProperty idProperty() {
        return id;
    }

    public StringProperty movie_titleProperty() {
        return movie_title;
    }


    public StringProperty paragogiProperty() {
        return paragogi;
    }


    public StringProperty releasedateProperty() {
        return releasedate;
    }


    public StringProperty critiqueProperty() {
        return critique;
    }

    public String getId() {
        return id.get();
    }

    public String getMovie_title() {
        return movie_title.get();
    }

    public String getParagogi() {
        return paragogi.get();
    }

    public String getReleasedate() {
        return releasedate.get();
    }

    public String getCritique() {
        return critique.get();
    }
}
