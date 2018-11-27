package model;

import java.util.ArrayList;
import java.util.List;

public class DrinkContainer extends DrinkAbstract{
    private List<DrinkAbstract> fridge = new ArrayList<>();
    private String name;

    public DrinkContainer(String name) {
        this.name = name;
    }

    public void addDrink(DrinkAbstract drinkAbstract) {
        fridge.add(drinkAbstract);
    }

    public void display() {
        for (DrinkAbstract drinkAbstract : fridge) {
            System.out.println(drinkAbstract.getName());
        }
    }

    @Override
    public String toString() {
        return null;
    }
}
