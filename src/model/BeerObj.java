package model;

import Photo.PhotoObj;

import java.io.Serializable;

public class BeerObj extends DrinkAbstract implements Serializable {

    public BeerObj() {

    }

    public BeerObj(String name, String alcPerc, String notes, String type) {
        this.name = name;
        this.alcPerc = alcPerc;
        this.notes = notes;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Name of beer: " + getName() + "\nAlcohol Percentage: " + getAlcPerc()
                + "\nType of beer: " + getType() + "\nNotes on this model: " + getNotes();
    }



}
