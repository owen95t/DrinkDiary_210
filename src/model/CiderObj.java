package model;


import java.io.Serializable;

public class CiderObj extends DrinkAbstract implements Serializable {

    public CiderObj() {

    }

    @Override
    public String toString() {
        return "Name of cider: " + getName() + "\nAlcohol Percentage: " + getAlcPerc()
                + "\nType of cider: " + getType() + "\nNotes on this model: " + getNotes();
    }

}
