package model;

import Photo.PhotoObj;

import java.io.Serializable;

public class OtherObj extends DrinkAbstract implements Serializable{

    public OtherObj() {

    }

    public OtherObj(String name, String alcPerc, String notes, String type, boolean fav) {
        this.name = name;
        this.alcPerc = alcPerc;
        this.notes = notes;
        this.type = type;
        setFav(fav);
    }

    @Override
    public String toString() {
        return "Name of drink: " + getName() + "\nAlcohol Percentage: " + getAlcPerc()
                + "\nType of drink: " + getType() + "\nNotes on this drink: " + getNotes();
    }
}
