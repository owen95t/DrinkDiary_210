package model;


import java.io.Serializable;

public class CiderObj extends DrinkAbstract implements Serializable {

    public CiderObj() {

    }

    public CiderObj(String name, String alcPerc, String notes, String type, boolean fav) {
        this.name = name;
        this.alcPerc = alcPerc;
        this.notes = notes;
        this.type = type;
        setFav(fav);
    }

    @Override
    public String toString() {
        return "Name of cider: " + getName() + "\nAlcohol Percentage: " + getAlcPerc()
                + "\nType of cider: " + getType() + "\nNotes on this drink: " + getNotes();
    }

}
