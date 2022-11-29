package com.example.demo;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Directors {
    private StringProperty D_id,D_name,movie_title,D_age;

    public Directors(){
        this.D_id=new SimpleStringProperty();
        this.D_name=new SimpleStringProperty();
        this.D_age=new SimpleStringProperty();
        this.movie_title=new SimpleStringProperty();
    }

    public String getD_id() {
        return D_id.get();
    }

    public StringProperty d_idProperty() {
        return D_id;
    }

    public void setD_id(String d_id) {
        this.D_id.set(d_id);
    }

    public String getD_name() {
        return D_name.get();
    }

    public StringProperty d_nameProperty() {
        return D_name;
    }

    public void setD_name(String d_name) {
        this.D_name.set(d_name);
    }

    public String getMovie_title() {
        return movie_title.get();
    }

    public StringProperty movie_titleProperty() {
        return movie_title;
    }

    public void setMovie_title(String movie_title) {
        this.movie_title.set(movie_title);
    }

    public String getD_age() {
        return D_age.get();
    }

    public StringProperty d_ageProperty() {
        return D_age;
    }

    public void setD_age(String d_age) {
        this.D_age.set(d_age);
    }

    @Override
    public String toString() {
        return "Directors{" +
                "D_id=" + D_id +
                ", D_name=" + D_name +
                ", movie_title=" + movie_title +
                ", D_age=" + D_age +
                '}';
    }
}
