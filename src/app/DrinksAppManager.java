package app;

import model.DrinkList;
import model.BeerObj;
import model.*;

import java.io.*;

import java.util.*;

public class DrinksAppManager {
    private boolean status = true;

    private ArrayList<Drink> bList = new ArrayList<>();
    private DrinkList drinkList = new DrinkList();


    public void start() {
        System.out.println("Welcome to your alcohol diary");

        Scanner input = new Scanner(System.in);

        while (status) { //ui loop to control the program's status
            displayMainMenu(); //ask for user input
            boolean cycle = false; //create a boolean for the next while loop
            while (cycle == false) { //while loop that will keep track of user valid/invalid input
                int i = input.nextInt();
                try {
                    while (i > 5 || i < 1) {
                        System.out.println("Invalid Selection");
                        System.out.println("Please try again: ");
                        i = input.nextInt();
                    }
                    cycle = true;
                    menuSelection(i);
                } catch (Exception e) {
                    System.out.println("System does not recognize input");
                    System.out.println("Please try again:");
                    i = input.nextInt();
                }

            }

        }

    }

    public void displayMainMenu() {
        System.out.println("Please choose an option: ");
        System.out.println("1. Add new drink");
        System.out.println("2. Show your drinks list");
        System.out.println("3. Save");
        System.out.println("4. Load");
        System.out.println("5. Exit");
    }

    public void menuSelection(int x) {
        if (x == 1) {
            System.out.println("1 Chosen");
            newDrinkObj();
        } else if (x == 2) {
            System.out.println("2 Chosen");
            insideList();
        } else if (x == 3) {
            System.out.println("Saving...");
            save(bList);
            System.out.println("File saved");
        } else if (x == 4) {
            System.out.println("Loading...");
            load();
        } else if (x == 5) {
            System.out.println("3 Chosen");
            System.out.println("Exit");
            status = false;
        }
    }

    public void newDrinkObj() { //put this in a while loop and a try catch statement for errors
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
            try {
                selection = input.nextInt();
                while (selection > 5 || selection < 1) {
                    System.out.println("Invalid selection");
                    System.out.println("Please try again: ");
                    selection = input.nextInt();
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
        Drink drinkTemp = null;
        Scanner input = new Scanner(System.in);
        try {
            if (i == 1) {
                drinkTemp = new BeerObj();
            } else if (i == 2) {
                drinkTemp = new WineObj();
            } else if (i == 3) {
                drinkTemp = new CiderObj();
            } else if (i == 4) {
                drinkTemp = new HardLiqourObj();
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
        System.out.println("Please enter the alcohol percentage: ");
        drinkTemp.setAlcPerc(input.nextLine());
        System.out.println("Please enter any notes you may have: ");
        drinkTemp.setNotes(input.nextLine());

        bList.add(drinkTemp);
    }

    public void insideList() {
        for (Drink drink : bList) {
            System.out.println(drink.getName());
        }

        Scanner input = new Scanner(System.in);
        //add while loop to control this menu.
        boolean stat = true;
        //while(stat){
        // System.out.println("Select model by name");
        // String name = input.nextLine();
        //


        System.out.println("Select drink (by name)");
        String name = input.nextLine();
        //Iterator itr = bList.iterator();
//        while (itr.hasNext()) {
//            if (itr.next().) {
//
//            }
//        }
        for (int i = 0; i < bList.size(); i++) {
            if (bList.get(i).getName().equals(name)) {
                System.out.println(bList.get(i).toString());
            } else {
                System.out.println("List does not contain this beer");
            }
        }

    }
    // I want to a loop here to control how the user interacts. The user can either choose the drinks
    // from the list the user created or exit to main menu. But this part maybe can wait as if we do a
    // proper UI it will not be necessary.
    public void insideListTemp() {
        Scanner input = new Scanner(System.in);
        String name = "";
        for (Drink drink : bList) {
            System.out.println(drink.getName());
        }
        if (bList.size() == 0) {
            System.out.println("List is empty");
        } else {
            System.out.println("Select drink by name.");
            name = input.nextLine();
        }

        boolean stat = true;
        while (stat) { // ?????
            if (bList.size() > 0) {
                for (int i = 0; i < bList.size(); i++) {
                    if (bList.get(i).getName().equals(name)) {
                        System.out.println(bList.get(i).toString());
                        stat = false; //?????
                    } else {
                        System.out.println("List does not contain this beer");
                    }
                }
            }
        }
    }

    public void save(ArrayList<Drink> list) {
        try {
            FileOutputStream fileOutput = new FileOutputStream("test.txt");
            ObjectOutputStream out = new ObjectOutputStream(fileOutput);
            out.writeObject(list);
            out.close();
            fileOutput.close();
        } catch (IOException e) {
            System.out.println("IOException caught");
        }
    }

    public void load() {
        ArrayList<Drink> bList2 = null;
        bList.clear();
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("test.txt");
            ObjectInputStream in = new ObjectInputStream(fis);
            bList2 = (ArrayList<Drink>) in.readObject();
            fis.close();
            in.close();
        } catch (Exception e) {
            System.out.println("IOException caught");
            System.out.println(e.toString());
        }
        bList = bList2;

        System.out.println("Save file loaded: ");
        for (Drink drink : bList) {
            System.out.println(drink.getName());
        }
    }

}
