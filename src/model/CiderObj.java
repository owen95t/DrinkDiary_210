package model;

import Photo.PhotoObj;

import java.io.Serializable;

public class CiderObj implements Serializable, Drink {

    private String name;
    private String type;
    private String AlcPerc;
    private String notes;
    private PhotoObj photo;

    public CiderObj(String name, String type, String AlcPerc, String notes) {
        this.name = name;
        this.type = type;
        this.AlcPerc = AlcPerc;
        this.notes = notes;
    }

    public CiderObj() {

    }

    @Override
    public void addName(String name) {
        this.name = name;
    }

    @Override
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public void setAlcPerc(String alcPerc) {
        this.AlcPerc = AlcPerc;
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
        return this.AlcPerc;
    }

    @Override
    public String getNotes() {
        return this.notes;
    }

    @Override
    public PhotoObj getPhoto() {
        return photo;
    }
}
