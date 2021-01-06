package model;

import Photo.PhotoObj;

import java.io.Serializable;

public class WineObj extends DrinkAbstract implements Serializable{

    public WineObj() {

    }

    public WineObj(String name, String alcPerc, String notes, String type, boolean fav) {
        this.name = name;
        this.alcPerc = alcPerc;
        this.notes = notes;
        this.type = type;
        setFav(fav);
    }

    @Override
    public String toString() {
        return "Name of wine: " + getName() + "\nAlcohol Percentage: " + getAlcPerc()
                + "\nType of wine: " + getType() + "\nNotes on this drink: " + getNotes();
    }

}
