package app;

import exception.DrinkDoesNotExistException;
import exception.LoadFailException;
import exception.SaveFailedException;
import model.DrinkList;
import model.BeerObj;
import model.*;

import java.io.*;

import java.util.*;

public class DrinksAppManager {
    private boolean status = true;

    private ArrayList<DrinkAbstract> bList = new ArrayList<>();
    private DrinkList drinkList = new DrinkList();


    public void start() {
        System.out.println("Welcome to your alcohol diary");

        Scanner input = new Scanner(System.in);

        while (status) { //ui loop to control the program's status
            displayMainMenu(); //ask for user input
            boolean cycle = false; //create a boolean for the next while loop
            while (cycle == false) { //while loop that will keep track of user valid/invalid input
                try {
                    int i = input.nextInt();
                    while (i > 6 || i < 1) {
                        System.out.println("Invalid Selection");
                        System.out.println("Please try again: ");
                        i = input.nextInt();
                    }
                    cycle = true;
                    menuSelection(i);
                } catch (Exception e) {
                    System.out.println("System does not recognize input");
                    System.out.println("Please try again:");
                    input.next(); //clear wrong input because scanner does not
                    continue; //conitnue to loop
                }

            }

        }

    }

    public void displayMainMenu() {
        System.out.println("Please choose an option: ");
        System.out.println("1. Add new drink");
        System.out.println("2. Show your drinks list");
        System.out.println("3. Save");
        System.out.println("4. Choose item from list");
        System.out.println("5. Load");
        System.out.println("6. Exit");
    }

    public void menuSelection(int x) throws SaveFailedException, LoadFailException, DrinkDoesNotExistException{
        if (x == 1) {
            System.out.println("Creating new drink object...");
            newDrinkObj();
        } else if (x == 2) {
            showDrinksList();
        } else if (x == 3) {
            System.out.println("Saving...");
            save(bList, "test2.txt");
            System.out.println("File saved");
        } else if (x == 4) {
            insideList();
        } else if (x == 5) {
            System.out.println("Loading...");
            load("test2.txt");
        } else if (x == 6) {
            System.out.println("Exiting. Goodbye!");
            status = false;
        }
    }

    public void showDrinksList() {
        if (bList.isEmpty()) {
            System.out.println("List is empty");
        } else {
            System.out.println("Showing drinks list:");
        }
        for (DrinkAbstract drink : bList) {
            System.out.println(drink.getName());
        }
    }

    public void newDrinkObj() {
        boolean stat = true;
        Scanner input = new Scanner(System.in);
        int selection = 0;
        while (stat) {
            System.out.println("What type of drink would you like to add?");
            System.out.println("1. Beer");
            System.out.println("2. Wine");
            System.out.println("3. Cider");
            System.out.println("4. Hard Liquor");
            System.out.println("5. Other");
            System.out.println("6. Return to main menu");
            try {
                selection = input.nextInt();
                while (selection > 6 || selection < 1) {
                    System.out.println("Invalid selection");
                    System.out.println("Please try again: ");
                    selection = input.nextInt();
                }
                if (selection == 6) {
                    stat = false;
                    break;
                }
                stat = false;
                newDrinkSelection(selection);
            } catch (Exception e) {
                System.out.println("System does not recognize input");
                System.out.println("Please try again: ");
                selection = input.nextInt();
            }
        }
    }

    public void newDrinkSelection(int i) {
        DrinkAbstract drinkTemp = null;
        Scanner input = new Scanner(System.in);


        try {
            if (i == 1) {
                drinkTemp = new BeerObj();
            } else if (i == 2) {
                drinkTemp = new WineObj();
            } else if (i == 3) {
                drinkTemp = new CiderObj();
            } else if (i == 4) {
                drinkTemp = new HardLiquorObj();
            } else if (i == 5) {
                drinkTemp = new OtherObj();
            }
        } catch (Exception e) {
            System.out.println("Exception caught");
        }

        System.out.println("Please enter the name/brand of the drink: ");
        drinkTemp.setName(input.nextLine());
        System.out.println("Please enter the type of the drink: ");
        drinkTemp.setType(input.nextLine());
        //should try catch here? change String to int
        System.out.println("Please enter the alcohol percentage: ");
        drinkTemp.setAlcPerc(input.nextLine());

        System.out.println("Please enter any notes you may have: ");
        drinkTemp.setNotes(input.nextLine());

        bList.add(drinkTemp);
    }

    public void insideList() throws DrinkDoesNotExistException{
        boolean first = true;

        if (bList.isEmpty()) {
            System.out.println("List is empty");
            first = false;
        }

        for (Drink drink : bList) {
            System.out.println(drink.getName());
        }

        Scanner input = new Scanner(System.in);

        while (first == true) {
            boolean stat = true;
            System.out.println("Select drink (by name) or exit (type 'exit')");
            String name = input.nextLine();
            if (name.toLowerCase().equals("exit")) {
                first = false;
                break;
            }

            while (stat) {
                for (DrinkAbstract drink : bList) {
                    if (drink.getName().equals(name)) {
                        System.out.println(drink.toString());
                        stat = false; //if this a drink matches, print to string
                        break; //break from this loop.
                    }
                }
                if (stat == false) { //this if statement checks whether the above loop found the object or not
                    break; //if it did find the object and print the info, break out of this while loop
                } //or else ask use to try again
                //if this parts run then the code above failed to find a drink
                System.out.println("Drink does not exist");
                System.out.println("Please try again: ");
                stat = false;
            }
        }
    }

    public void save(ArrayList<DrinkAbstract> list, String filename) throws SaveFailedException{
        try {
            FileOutputStream fileOutput = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(fileOutput);
            out.writeObject(list);
            out.close();
            fileOutput.close();
        } catch (SaveFailedException s) {
            throw new SaveFailedException();
        } catch (IOException e) {
            System.out.println("IOException caught");
        }
    }

    public void load(String filename) throws LoadFailException{
        ArrayList<DrinkAbstract> bList2 = null;
        bList.clear();
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(fis);
            bList2 = (ArrayList<DrinkAbstract>) in.readObject();
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
        bList = bList2;

        if (bList != null) {
            System.out.println("Save file loaded.");
            System.out.println("File contains the following items:");
            for (DrinkAbstract drink : bList) {
                System.out.println(drink.getName());
            }
        }

    }

}
