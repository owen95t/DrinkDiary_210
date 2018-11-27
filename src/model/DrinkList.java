package model;

import exception.DrinkAlreadyExistsException;
import exception.LoadFailException;
import exception.SaveFailedException;

import java.io.*;
import java.util.AbstractList;
import java.util.ArrayList;

public class DrinkList extends AbstractList implements Serializable{

    private ArrayList<DrinkAbstract> dList;
    private ArrayList<DrinkAbstract> favDList;

    public DrinkList() {
        dList = new ArrayList<>();
        favDList = new ArrayList<>();
    }


    //this override is never used?
    @Override
    public int size() {
        return dList.size();
    }

    //this override is also never used?
    @Override
    public Object get(int index) {
        DrinkAbstract dTemp = dList.get(index);
        return dTemp;
    }

    //GETTERS AND SETTERS (add,remove,etc):

    //add drink to main list
    public void addDrink(DrinkAbstract drink) throws DrinkAlreadyExistsException{
        if (!doesDrinkExist(drink)) {
            dList.add(drink);
            System.out.println("Drink added to main list.\n");
        } else {
            throw new DrinkAlreadyExistsException("Drink Already Exists!!!");
        }
    }
    //add drink to fav list
    public void addFavDrink(DrinkAbstract drinkAbstract) throws DrinkAlreadyExistsException{
        if (!doesDrinkExist(drinkAbstract)) {
            favDList.add(drinkAbstract);
            System.out.println("Drink added to favourites list.\n");
        } else {
            throw new DrinkAlreadyExistsException("Drink Already Exists!!!");
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

    public DrinkAbstract getDrinkName(String name) {
        DrinkAbstract drinkTemp = null;
        for(int i = 0; i < dList.size(); i++) {
            if (dList.get(i).getName().equals(name)) {
                drinkTemp = dList.get(i);
            }
//            if (i == dList.size() - 1) {
//                System.out.println("Drink is not in the list!");
//            }
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

    public void transferList1(ArrayList<DrinkAbstract> list1) {
        this.dList = list1;
    }

    public void transferList2(ArrayList<DrinkAbstract> list2) {
        this.favDList = list2;
    }

    public ArrayList returnList() {
        return dList;
    }

    public ArrayList returnFavList() {
        return favDList;
    }

    public void save() throws SaveFailedException{
        DrinkList tmp = new DrinkList();
        tmp.transferList1(dList);
        tmp.transferList2(favDList);

        try {
            FileOutputStream fileOutput = new FileOutputStream("DrinkListSave.txt");
            ObjectOutputStream out = new ObjectOutputStream(fileOutput);
            out.writeObject(tmp);
            out.close();
            fileOutput.close();
        } catch (SaveFailedException s) {
            throw new SaveFailedException();
        } catch (IOException e) {
            System.out.println("IOException caught");
        }
    }

    public void load() throws LoadFailException{
        DrinkList drinkListTemp = null;
        this.clear();

        FileInputStream fis;
        try {
            fis = new FileInputStream("DrinkListSave.txt");
            ObjectInputStream in = new ObjectInputStream(fis);
            drinkListTemp = (DrinkList) in.readObject();
            fis.close();
            in.close();
        } catch (LoadFailException l) {
            System.out.println("Load Failed.");
            l.printStackTrace();
            System.out.println(l.toString());
        } catch (Exception e) {
            System.out.println("IOException caught");
            System.out.println(e.toString());
            e.printStackTrace();
        }

        transferList1(drinkListTemp.returnList());
        transferList2(drinkListTemp.returnFavList());

    }

    public void clear() {
        dList.clear();
        favDList.clear();
    }

    @Override
    public boolean isEmpty() {
        if (dList.isEmpty()) {
            return true;
        }
        return false;
    }

}
