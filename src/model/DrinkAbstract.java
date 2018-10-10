package model;

import Photo.PhotoObj;

public abstract class DrinkAbstract implements Drink{

    private String name;
    private String type;
    private String alcPerc;
    private String notes;
    private PhotoObj photo;

    public void setName(String name){}

    public void setType(String type){}

    public void setAlcPerc(String alcPerc){}

    public void setNotes(String notes){}

    public void addPhoto(PhotoObj photoObj){}

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
}
