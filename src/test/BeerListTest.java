package test;

import model.DrinkList;
import model.BeerObj;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BeerListTest {
    @Test
    void getBeer() {
        BeerObj beer = new BeerObj();
        beer.setName("Kokanee");
        DrinkList beerList = new DrinkList();


    }

    @Test
    void doesBeerExist() {
        BeerObj beer = new BeerObj();
        beer.setName("Kokanee");
        DrinkList beerList = new DrinkList();



    }

    @Test
    void beerDoesNotExist() {
        BeerObj beer = new BeerObj();
        BeerObj beer2 = new BeerObj();

        beer.setName("Kokanee");
        beer2.setName("Molson");

        DrinkList beerList = new DrinkList();



    }

}