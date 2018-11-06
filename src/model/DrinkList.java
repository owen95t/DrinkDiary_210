package model;

import exception.DrinkAlreadyExistsException;

import java.io.Serializable;
import java.util.AbstractList;
import java.util.ArrayList;

public class DrinkList extends AbstractList implements Serializable{

    private ArrayList<DrinkAbstract> dList;
    private ArrayList<DrinkAbstract> favDList;

    public DrinkList() {
        dList = new ArrayList<>();
        favDList = new ArrayList<>();
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

    //add drink to main list
    public void addDrink(DrinkAbstract drink) throws DrinkAlreadyExistsException{
        if (!doesDrinkExist(drink)) {
            dList.add(drink);
            System.out.println("Drink added.\n");
        }
    }
    //add drink to fav list
    public void addFavDrink(DrinkAbstract drinkAbstract) throws DrinkAlreadyExistsException{
        if (!doesDrinkExist(drinkAbstract)) {
            favDList.add(drinkAbstract);
            System.out.println("Drink added.\n");
        }
    }
    //Removes drink from main list
    public void removeDrink(DrinkAbstract drinkAbstract) throws DrinkAlreadyExistsException{
        if (doesDrinkExist(drinkAbstract)) {
            dList.remove(drinkAbstract);
            System.out.println("Drink removed.\n");
        }
    }
    //removes drink from fav list
    public void removeFavDrink(DrinkAbstract drinkAbstract) throws DrinkAlreadyExistsException{
        if (doesFavDrinkExist(drinkAbstract)) {
            favDList.remove(drinkAbstract);
            System.out.println("Drink removed.\n");
        }
    }
    //remove drink from main list BY NAME
    public void removeDrinkName(String name) {
        for(int i = 0; i < dList.size(); i++) {
            if (dList.get(i).getName().equals(name)) {
                dList.remove(dList.get(i));
            }
        }
    }
    //remove drink from fav list BY NAME
    public void removeFavDrinkName(String name) {
        for(int i = 0; i < favDList.size(); i++) {
            if (favDList.get(i).getName().equals(name)) {
                favDList.remove(favDList.get(i));
                break;
            }
            if (i == favDList.size() - 1) {
                System.out.println("This drink is not in the favourites list.");
            }
        }
    }



    public DrinkAbstract getDrink(DrinkAbstract drink, String a) throws DrinkAlreadyExistsException{
        DrinkAbstract drinkTemp = null;
        if (a.equals("fav")) {
            if (doesDrinkExist(drink)) {
                for(int i = 0; i < favDList.size(); i++) {
                    if (favDList.get(i).equals(drink.getName())) {
                        drinkTemp = favDList.get(i);
                    }
                }
            }
        }

        return drinkTemp;
    }

    public boolean doesDrinkExist(DrinkAbstract name) throws DrinkAlreadyExistsException{
        boolean bool = false;
        if(dList.contains(name)){
            bool = true;
        }
        return bool;
    }

    public boolean doesFavDrinkExist(DrinkAbstract name) throws DrinkAlreadyExistsException{
        boolean bool = false;
        if (favDList.contains(name)) {
            bool = true;
        }
        return bool;
    }

    public void getList() {
        int i = 1;
        int j = 1;

        if (dList.isEmpty()) {
            System.out.println("Main list is empty");
        } else {
            System.out.println("Showing main list:");
            for (DrinkAbstract drinkAbstract : dList) {
                System.out.println(i + ". " + drinkAbstract.getName() + "("+drinkAbstract.getType()+")");
                i++;
            }
        }

        if (favDList.isEmpty()) {
            System.out.println("Favourites list is empty");
        } else {
            System.out.println("Showing favourites list: ");
            for (DrinkAbstract drinkAbstract : favDList) {
                System.out.println(j + ". " + drinkAbstract.getName() + "("+drinkAbstract.getType()+")");
                j++;
            }
        }


    }

    public void clear() {
        dList.clear();
        favDList.clear();
    }



}
