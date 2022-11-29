package com.example.demo;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Actor {
    private StringProperty a_id;
    private StringProperty actor_name;
    private StringProperty gender;
    private StringProperty age;
    private StringProperty movie_title;

public Actor(){
    this.a_id=new SimpleStringProperty();
    this.actor_name=new SimpleStringProperty();
    this.age=new SimpleStringProperty();
    this.gender=new SimpleStringProperty();
    this.movie_title=new SimpleStringProperty();
}

    public void setId(String id) {
        this.a_id.set(id);
    }

    public void setActor_name(String actor_name) {
        this.actor_name.set(actor_name);
    }

    public void setGender(String gender) {
        this.gender.set(gender);
    }

    public void setAge(String age) {
        this.age.set(age);
    }

    public void setMovie(String movie) {
        this.movie_title.set(movie);
    }

    public String getId() {
        return a_id.get();
    }

    public StringProperty idProperty() {
        return a_id;
    }

    public String getActor_name() {
        return actor_name.get();
    }

    public StringProperty actor_nameProperty() {
        return actor_name;
    }

    public String getGender() {
        return gender.get();
    }

    public StringProperty genderProperty() {
        return gender;
    }

    public String getAge() {
        return age.get();
    }

    public StringProperty ageProperty() {
        return age;
    }

    public String getMovie_title() {
        return movie_title.get();
    }

    public StringProperty movieProperty() {
        return movie_title;
    }
}
