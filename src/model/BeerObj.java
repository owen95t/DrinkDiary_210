package model;

import Photo.PhotoObj;

import java.io.Serializable;

public class BeerObj implements Serializable, Drink {

    private String name;
    private String type;
    private String AlcPerc;
    private String notes;
    private PhotoObj photo;

    public BeerObj(String name, String Type, String notes, String alcPerc) {
        this.name = name;
        this.type = Type;
        this.notes = notes;
        this.AlcPerc = alcPerc;
    }
    public BeerObj(){

    }
    //REQUIRES: String parameter
    //MODIFIES: this.name
    //EFFECTS: changes the value of Name
    public void addName(String name) {
        this.name = name;
    }

    //REQUIRES: String parameter type
    //MODIFIES: this
    //EFFECTS: Changes the value of type
    public void setType(String type) {
        this.type = type;
    }

    //REQUIRES: String parameter notes
    //MODIFIES: this
    //EFFECTS: Changes the value of Notes
    public void setNotes(String notes) {
        this.notes = notes;
    }

    //REQUIRES: Object paramter Photo
    //MODIFIES: this
    //EFFECTS: Changes the object Photo
    public void addPhoto(PhotoObj photo) {
        this.photo = photo;
    }

    //REQUIRES: String parameter AlcPerc
    //MODIFIES: this
    //EFFECTS: Changes the value of AlcPerc (Alcohol Percentage)
    public void setAlcPerc(String alcPerc) {
        this.AlcPerc = alcPerc;
    }

    //REQUIRES: nothing
    //MODIFIES: nothing
    //EFFECTS: returns name
    public String getName() {
        return name;
    }

    //REQUIRES: nothing
    //MODIFIES: nothing
    //EFFECTS: returns type
    public String getType() {
        return type;
    }

    //REQUIRES: nothing
    //MODIFIES: nothing
    //EFFECTS: returns alcperc
    public String getAlcPerc() {
        return AlcPerc;
    }

    //REQUIRES: nothing
    //MODIFIES: nothing
    //EFFECTS: return notes
    public String getNotes() {
        return notes;
    }

    //REQUIRES: nothing
    //MODIFIES:nothing
    //EFFECTS: return photo object
    public PhotoObj getPhoto() {
        return photo;
    }

    @Override
    public String toString() {
        return "Name of model: " + getName() + "\nAlcohol Percentage: " + getAlcPerc()
                + "\nType of model: " + getType() + "\nNotes on this model: " + getNotes();
    }
}
