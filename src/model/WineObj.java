package model;

import Photo.PhotoObj;

import java.io.Serializable;

public class WineObj extends DrinkAbstract implements Serializable{

    public WineObj() {

    }

    @Override
    public String toString() {
        return "Name of wine: " + getName() + "\nAlcohol Percentage: " + getAlcPerc()
                + "\nType of wine: " + getType() + "\nNotes on this model: " + getNotes();
    }

}
