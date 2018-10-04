package test;

import org.junit.jupiter.api.Test;
import model.BeerObj;
import static org.junit.jupiter.api.Assertions.*;

class TestBeerObj {

    @Test
    void addName() {
        BeerObj beer = new BeerObj();
        beer.setName("Kokanee");
        assertTrue(beer.getName().equals("Kokanee"));
    }

    @Test
    void setType() {
        BeerObj beer = new BeerObj();
        beer.setType("Lager");
        assertTrue(beer.getType().equals("Lager"));
    }

    @Test
    void setNotes() {
        BeerObj beer = new BeerObj();
        beer.setNotes("Tasty");
        assertTrue(beer.getNotes().equals("Tasty"));
    }

    @Test
    void addPhoto() {
    }

    @Test
    void setAlcPerc() {
        BeerObj beer = new BeerObj();
        beer.setAlcPerc("5%");
        assertTrue(beer.getAlcPerc().equals("5%"));
    }


}