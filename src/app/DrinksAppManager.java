package app;

import com.sun.xml.internal.bind.v2.TODO;
import exception.DrinkAlreadyExistsException;
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
                    while (i > 7 || i < 1) {
                        System.out.println("Invalid Selection");
                        System.out.println("Please try again: ");
                        i = input.nextInt();
                    }
                    cycle = true;
                    menuSelection(i);
                } catch (Exception e) {
                    e.printStackTrace();
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
        System.out.println("5. Remove drink from list");
        System.out.println("6. Load");
        System.out.println("7. Exit");
    }

    public void menuSelection(int x) throws SaveFailedException, LoadFailException, DrinkDoesNotExistException{
        if (x == 1) {
            System.out.println("Creating new drink object...");
            newDrinkObj();
        } else if (x == 2) {
            drinkList.getList();
        } else if (x == 3) {
            System.out.println("Saving...");
            //saveDrinkList(drinkList, "DrinkListTest.txt");
            drinkList.save();
            System.out.println("File saved\n");
        } else if (x == 4) {
            insideDrinkList();
        } else if (x == 5) {
            removeDrinkList();
        } else if (x == 6) {
            System.out.println("Loading...");
            //loadDrinkList("DrinkListTest.txt");
            drinkList.load();
        } else if (x == 7) {
            System.out.println("Exiting. Goodbye!");
            status = false;
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
            } catch (DrinkAlreadyExistsException d) {
                System.out.println("Drink Already Exists. Please try again\n");
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("System does not recognize input!");
                System.out.println("Please try again: ");
                selection = input.nextInt();
            }
        }
    }

    public void newDrinkSelection(int i) throws DrinkAlreadyExistsException {
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
        System.out.println("Add drink to favourite list? (yes/no)");

        if (input.nextLine().toLowerCase().equals("yes")) {
            drinkTemp.setFav(true);
            drinkList.addFavDrink(drinkTemp);
        }
        drinkList.addDrink(drinkTemp);

    }

    public void removeDrinkList() {
        System.out.println("What drink would you like to remove?");
        drinkList.getList();

        Scanner input = new Scanner(System.in);
        String tempName = input.nextLine();

        System.out.println("From which list? (main/fav)");
        String listInput = input.nextLine();

        if (listInput.toLowerCase().equals("main")) {
            System.out.println("Main chosen. Items removed from the main list will also be removed from the favourites list");
            drinkList.removeDrinkName(tempName);
            drinkList.removeFavDrinkName(tempName);
            System.out.println("Drink removed from both lists.");
        } else if (listInput.toLowerCase().equals("fav")) {
            drinkList.removeFavDrinkName(tempName);
            System.out.println("Drink removed from favourites list ");
        }
    }

    public void insideDrinkList() {
        boolean stat = true;

        if (drinkList.isEmpty()) {
            System.out.println("List is empty!");
            stat = false;
        }

        if(!drinkList.isEmpty()){
            drinkList.getList();
        }
        Scanner input = new Scanner(System.in);

        while (stat) {
            System.out.println("Select drink (by name) or exit (type 'exit')");
            String name = input.nextLine();
            if (name.toLowerCase().equals("exit")) {
                stat = false;
                break;
            } else {
                boolean stat2 = true;
                while(stat2){
                    if (drinkList.getDrinkName(name) == null) {
                        System.out.println("Drink does not exist.");
                        break;
                    } else {
                        System.out.println(drinkList.getDrinkName(name).toString());
                        stat2 = false;
                        //TODO: add extra functionality here
                    }
                }
            }
        }
    }

    public void saveDrinkList(DrinkList list, String filename) throws SaveFailedException{
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

    public void loadDrinkList(String filename) throws LoadFailException{
        DrinkList drinkListTemp = null;
        drinkList.clear();

        FileInputStream fis;
        try {
            fis = new FileInputStream(filename);
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

        drinkList = drinkListTemp;


        if (drinkList != null) {
            System.out.println("Save file loaded.\n");
            System.out.println("File contains the following items:");
            drinkList.getList();
            System.out.println("\n");
        }

    }


}
