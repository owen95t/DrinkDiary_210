package model;

import java.io.Serializable;

public class HardLiquorObj extends DrinkAbstract implements Serializable{

    public HardLiquorObj() {

    }

    @Override
    public String toString() {
        return "Name of liquor: " + getName() + "\nAlcohol Percentage: " + getAlcPerc()
                + "\nType of liquor: " + getType() + "\nNotes on this model: " + getNotes();
    }

}
