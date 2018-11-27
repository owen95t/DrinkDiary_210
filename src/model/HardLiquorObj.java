package model;

import java.io.Serializable;

public class HardLiquorObj extends DrinkAbstract implements Serializable{

    public HardLiquorObj() {

    }

    public HardLiquorObj(String name, String alcPerc, String notes, String type, boolean fav) {
        this.name = name;
        this.alcPerc = alcPerc;
        this.notes = notes;
        this.type = type;
        setFav(fav);
    }

    @Override
    public String toString() {
        return "Name of liquor: " + getName() + "\nAlcohol Percentage: " + getAlcPerc()
                + "\nType of liquor: " + getType() + "\nNotes on this model: " + getNotes();
    }

}
