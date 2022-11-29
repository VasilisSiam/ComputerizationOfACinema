package com.example.demo;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Upcoming {
    private StringProperty u_id;
    private StringProperty upcoming_Title;
    private StringProperty releasedate;
    private StringProperty paragogi;

    public Upcoming(){
        this.u_id=new SimpleStringProperty();
        this.upcoming_Title=new SimpleStringProperty();
        this.releasedate=new SimpleStringProperty();
        this.paragogi=new SimpleStringProperty();
    }

    public String getU_id() {
        return u_id.get();
    }

    public StringProperty u_idProperty() {
        return u_id;
    }

    public void setU_id(String u_id) {
        this.u_id.set(u_id);
    }

    public String getUpcoming_Title() {
        return upcoming_Title.get();
    }

    public StringProperty upcoming_TitleProperty() {
        return upcoming_Title;
    }

    public void setUpcoming_Title(String upcoming_Title) {
        this.upcoming_Title.set(upcoming_Title);
    }

    public String getReleaseDate() {
        return releasedate.get();
    }

    public StringProperty releaseDateProperty() {
        return releasedate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releasedate.set(releaseDate);
    }

    public String getParagogi() {
        return paragogi.get();
    }

    public StringProperty paragogiProperty() {
        return paragogi;
    }

    public void setParagogi(String paragogi) {
        this.paragogi.set(paragogi);
    }
}
