package model;

import Photo.PhotoObj;

import java.io.Serializable;

public abstract class DrinkAbstract implements Serializable, Drink{

    protected String name;
    protected String type;
    protected String alcPerc;
    protected String notes;
    protected PhotoObj photo;
    protected boolean fav = false;

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

    public void setFav(boolean fav) {
        this.fav = fav;
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

    public boolean getFav() {
        return fav;
    }

    public abstract String toString();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DrinkAbstract)) return false;

        DrinkAbstract that = (DrinkAbstract) o;

        return name.equals(that.name) && type.equals(that.type); //modified equals... modify hashCode too?
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
