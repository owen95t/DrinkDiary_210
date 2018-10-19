package model;

import Photo.PhotoObj;

import java.io.Serializable;

public class BeerObj extends DrinkAbstract implements Serializable {

    public BeerObj() {

    }

    @Override
    public String toString() {
        return "Name of beer: " + getName() + "\nAlcohol Percentage: " + getAlcPerc()
                + "\nType of beer: " + getType() + "\nNotes on this model: " + getNotes();
    }
    @Override
    public void userInputFeedback() {

    }

    @Override
    public int compareTo(DrinkAbstract o) {
        if (o.getName().equals(getName())) {
            return 1;
        }
        return 0;
    }

}
