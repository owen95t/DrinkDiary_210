package model;

import java.io.Serializable;
import java.util.AbstractList;
import java.util.ArrayList;

public class DrinkList extends AbstractList implements Serializable{

    private ArrayList<DrinkAbstract> dList;

    public DrinkList() {
        dList = new ArrayList<>();
    }

    @Override
    public int size() {
        return dList.size();
    }

    @Override
    public Object get(int index) {
        DrinkAbstract dTemp = dList.get(index);
        return dTemp;
    }

    //getters and setters
    public void addDrink(DrinkAbstract drink) {
        dList.add(drink);
    }

    public void removeDrink(DrinkAbstract drinkAbstract) {
        if (doesDrinkExist(drinkAbstract.name)) {
            dList.remove(drinkAbstract);
        }
    }

    public DrinkAbstract getDrink(String dName) {
        if (doesDrinkExist(dName)) {

        }
        DrinkAbstract drinkTemp = null;
        for(int i = 0; i < dList.size(); i++) {
            if (dList.get(i).getName().equals(dName)) {
                drinkTemp = dList.get(i);
            }
        }
        return drinkTemp;
    }

    public boolean doesDrinkExist(String bName) {
        boolean drink = false;
        for(int i = 0; i < dList.size(); i++) {
            if (dList.get(i).getName().equals(bName)) {
                drink = true;
            } else {
                return drink;
            }
        }
        return drink;
    }

    public ArrayList<DrinkAbstract> getdList() {
        return dList;
    }



}
