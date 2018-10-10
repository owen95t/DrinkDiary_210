package model;

import Photo.PhotoObj;

import java.io.Serializable;

public class WineObj extends DrinkAbstract implements Serializable{

    private String name;
    private String type;
    private String alcPerc;
    private String notes;
    private PhotoObj photo;

    public WineObj() {

    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public void setAlcPerc(String alcPerc) {
        this.alcPerc = alcPerc;
    }

    @Override
    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public void addPhoto(PhotoObj photoObj) {

    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public String getAlcPerc() {
        return this.alcPerc;
    }

    @Override
    public String getNotes() {
        return this.notes;
    }

    @Override
    public PhotoObj getPhoto() {
        return photo;
    }

    @Override
    public String toString() {
        return "Name of model: " + getName() + "\nAlcohol Percentage: " + getAlcPerc()
                + "\nType of model: " + getType() + "\nNotes on this model: " + getNotes();
    }
}
