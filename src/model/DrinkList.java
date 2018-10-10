package model;

import java.util.ArrayList;

public class DrinkList extends ArrayList {
    private ArrayList<BeerObj> bList;

    public DrinkList() {
        bList = new ArrayList<>();
    }

    //getters and setters
    public void addBeer(BeerObj beer) {
        bList.add(beer);
    }

    public BeerObj getBeer(String bName) {
        doesBeerExist(bName);
        BeerObj beer = new BeerObj();
        for(int i = 0; i < bList.size(); i++) {
            if (bList.get(i).getName().equals(bName)) {
                beer = bList.get(i);
            }
        }
        return beer;
    }

    public boolean doesBeerExist(String bName) {
        boolean beer = false;
        for(int i = 0; i < bList.size(); i++) {
            if (bList.get(i).getName().equals(bName)) {
                beer = true;
            } else {
                return beer;
            }
        }
        return beer;
    }

    public ArrayList<BeerObj> getbList() {
        return bList;
    }



}
