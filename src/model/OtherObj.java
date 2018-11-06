package model;

import Photo.PhotoObj;

import java.io.Serializable;

public class OtherObj extends DrinkAbstract implements Serializable{

    public OtherObj() {

    }

    @Override
    public String toString() {
        return "Name of drink: " + getName() + "\nAlcohol Percentage: " + getAlcPerc()
                + "\nType of drink: " + getType() + "\nNotes on this model: " + getNotes();
    }
}
