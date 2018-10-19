package model;

import Photo.PhotoObj;

import java.io.Serializable;

public abstract class DrinkAbstract implements Serializable, Drink, Comparable<DrinkAbstract>{

    protected String name;
    protected String type;
    protected String alcPerc;
    protected String notes;
    protected PhotoObj photo;

    public void setName(String name){
        this.name = name;
    }

    public void setType(String type){
        this.type = type;
    }

    public void setAlcPerc(String alcPerc){
        this.alcPerc = alcPerc;
    }

    public void setNotes(String notes){
        this.notes = notes;
    }

    public void addPhoto(PhotoObj photoObj){
        this.photo = photoObj;
    }

    public String getName(){
        return name;
    }

    public String getType(){
        return type;
    }

    public String getAlcPerc(){
        return alcPerc;
    }

    public String getNotes(){
        return notes;
    }

    public PhotoObj getPhoto(){
        return photo;
    }

    public abstract String toString();

    public abstract void userInputFeedback();

    public int compareTo(Drink drink) {
        if (drink.getName().equals(getName())) {
            return 1;
        }
        return 0;
    }
}
