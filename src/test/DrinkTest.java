package test;

import model.BeerObj;
import model.Drink;
import model.WineObj;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DrinkTest {

    @BeforeEach
    public void beforeEach() {

    }

    Drink newBeer = new BeerObj();
    Drink newWine = new WineObj();


    @Test
    void testAddNameBeer() {
        newBeer.setName("Kokanee");
        assertEquals("Kokanee", newBeer.getName());
    }

    @Test
    void testAddNameWine() {
        newWine.setName("Chardonnay");
        assertEquals("Chardonnay", newWine.getName());
    }

    @Test
    void testAddTypeBeer() {
        newBeer.setType("Lager");
        assertEquals("Lager", newBeer.getType());
    }

    @Test
    void testAddTypeWine() {
        newWine.setType("Red Wine");
        assertEquals("Red Wine", newWine.getType());
    }

    @Test
    void testAddAlcPercBeer() {
        newBeer.setAlcPerc("5");
        assertEquals("5", newBeer.getAlcPerc());
    }

    @Test
    void testAddAlcPercWine() {
        newWine.setAlcPerc("12");
        assertEquals("12", newWine.getAlcPerc());
    }

    @Test
    void testAddNotesBeer() {
        newBeer.setNotes("Good");
        assertEquals("Good", newBeer.getNotes());
    }

    @Test
    void testAddNotesWine() {
        newWine.setNotes("Good");
        assertEquals("Good", newWine.getNotes());
    }

}