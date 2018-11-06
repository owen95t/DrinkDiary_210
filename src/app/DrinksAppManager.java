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

    private ArrayList<DrinkAbstract> dList = new ArrayList<>();
    private DrinkList drinkList = new DrinkList();
    private ArrayList<DrinkAbstract> favList = new ArrayList<>();


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
            //save(dList, "test2.txt");
            //saveFav(favList, "test.txt");

            saveDrinkList(drinkList, "DrinkListTest.txt");

            System.out.println("File saved\n");
        } else if (x == 4) {
            insideList();
        } else if (x == 5) {
            //removeDrink();
            removeDrinkList();
        } else if (x == 6) {
            System.out.println("Loading...");
            //load("test2.txt");
            //loadFav("test.txt");

            loadDrinkList("DrinkListTest.txt");

            //showDrinksList();
        } else if (x == 7) {
            System.out.println("Exiting. Goodbye!");
            status = false;
        }
    }


    //TODO: instead of having this method, just call getList Method from DrinkList
//    public void showDrinksList() {
//        if (dList.isEmpty()) {
//            System.out.println("\nList is empty\n");
//        } else {
//            System.out.println("\nShowing drinks list:");
//        }
//        int i = 1;
//        for (DrinkAbstract drink : dList) {
//            System.out.println(i + ". " + drink.getName() + "("+drink.getType()+")");
//            i++;
//        }
//        System.out.println("\n");
//        if (favList.isEmpty()) {
//            System.out.println("Favourite list is empty");
//        } else {
//            System.out.println("Showing favourite drinks list");
//        }
//        int j = 1;
//        for (DrinkAbstract drink : favList) {
//            System.out.println(j + ". " + drink.getName() + "("+drink.getType()+")");
//            j++;
//        }
//        System.out.println("\n");
//    }

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
                System.out.println("System does not recognize input");
                System.out.println("Please try again: ");
                selection = input.nextInt();
            }
        }
    }

    //TODO: implement addDrink method in here
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

        if (input.nextLine().equals("yes")) {
//            addDrinkToFavList(drinkTemp);
            drinkList.addFavDrink(drinkTemp);
        }
        //addDrinkToList(drinkTemp);
        drinkList.addDrink(drinkTemp);

    }
    //TODO: instead of using this method, just call addDrink method in DrinkList
//    public void addDrinkToList(DrinkAbstract drinkAbstract) throws DrinkAlreadyExistsException{
//        if (dList.contains(drinkAbstract)) {
//            throw new DrinkAlreadyExistsException("Drink already exists");
//        } else {
//            dList.add(drinkAbstract);
//            System.out.println("Drink added.\n");
//        }
//    }
    //TODO: instead of using this method, just call addDrink method in DrinkList
//    public void addDrinkToFavList(DrinkAbstract drinkAbstract) throws DrinkAlreadyExistsException {
//        if (favList.contains(drinkAbstract)) {
//            throw new DrinkAlreadyExistsException("Drink already exists in list");
//        } else {
//            drinkAbstract.setFav(true);
//            favList.add(drinkAbstract);
//            System.out.println("Drink added to favourites list.\n");
//        }
//
//    }

    //TODO: implement remove drink method in DrinkList
//    public void removeDrink() {
//        System.out.println("What drink would you like to remove?");
//        System.out.println("All drinks:");
//        for (DrinkAbstract drink: dList) {
//            System.out.println(drink.getName());
//        }
//        System.out.println("\nFavourites List: ");
//        for (DrinkAbstract drink : favList) {
//            System.out.println(drink.getName());
//        }
//        Scanner input = new Scanner(System.in);
//        String tempName = input.nextLine();
//
//        System.out.println("From which list? (main/fav)");
//        String listInput = input.nextLine();
//        if (listInput.equals("both") || listInput.equals("main")) {
//            System.out.println("Main chosen. Items removed from main list will also be removed from favourites list");
//            for(int i = 0; i < dList.size(); i++) {
//                if (dList.get(i).getName().equals(tempName)) {
//                    dList.remove(dList.get(i));
//                }
//            }
//            for(int i = 0; i < favList.size(); i++) {
//                if (favList.get(i).getName().equals(tempName)) {
//                    favList.remove(favList.get(i));
//                }
//            }
//            System.out.println("Drink is removed from both lists.\n");
//        } else if (listInput.equals("fav")) {
//            for(int i = 0; i < favList.size(); i++) {
//                if (favList.get(i).getName().equals(tempName)) {
//                    favList.remove(favList.get(i));
//                    System.out.println("Drink is removed from favourites list.\n");
//                    break;
//                }
//                if (i == favList.size()-1) {
//                    System.out.println("This drink is not in the favourites list.");
//                }
//            }
//
//        }
//    }

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

    public void insideList(){
        boolean first = true;

        if (dList.isEmpty()) {
            System.out.println("List is empty");
            first = false;
        }

        for (Drink drink : dList) {
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
                for (DrinkAbstract drink : dList) {
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

    /*
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
    public void saveFav(ArrayList<DrinkAbstract> list, String filename) throws SaveFailedException{
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
        //DrinkList drinkListTemp = null;
        //drinkList.clear();
        dList.clear();
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
        dList = bList2;

        //drinkList = drinkListTemp;

        if (dList != null) {
            System.out.println("Save file loaded.");
            System.out.println("File contains the following items:");
            for (DrinkAbstract drink : dList) {
                System.out.println(drink.getName() + "("+drink.getType()+")");
            }
            System.out.println("\n");
        }

    }
    public void loadFav(String filename) throws LoadFailException{
        ArrayList<DrinkAbstract> bList2 = null;
        favList.clear();
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
        favList = bList2;

        if (favList != null) {
            System.out.println("Save file loaded.");
            System.out.println("File contains the following items:");
            for (DrinkAbstract drink : favList) {
                System.out.println(drink.getName() + "("+drink.getType()+")");
            }
            System.out.println("\n");
        }

    }
    */
    
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

        FileInputStream fis = null;
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
            System.out.println("Save file loaded.");
            System.out.println("File contains the following items:");
            drinkList.getList();
            System.out.println("\n");
        }

    }

}
