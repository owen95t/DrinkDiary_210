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
