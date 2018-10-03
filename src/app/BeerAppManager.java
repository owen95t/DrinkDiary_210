package app;

import model.DrinkList;
import model.BeerObj;
import model.*;

import java.io.*;

import java.util.*;

public class BeerAppManager {
    private boolean status = true;

    private ArrayList<Drink> bList = new ArrayList<>();
    private DrinkList drinkList = new DrinkList();


    public void start() {
        System.out.println("Welcome to your alcohol diary");

        System.out.println("1. Add New model");
        System.out.println("2. Show Your model List");
        System.out.println("3. Save");
        System.out.println("4. Load");
        System.out.println("5. Exit");

        Scanner input = new Scanner(System.in);

        while (status) { //main loop to control the program's status
            System.out.println("Please choose an option: "); //ask for user input
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
    public void menuSelection(int x) {
        if (x == 1) {
            System.out.println("1 Chosen");
            newDrinkObj();
        } else if (x == 2) {
            System.out.println("2 Chosen");
            for (Drink drink : bList) {
                System.out.println(drink.getName());
            }
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

//        BeerObj newBeer = new BeerObj();
//        Scanner input = new Scanner(System.in);
//        System.out.println("Please enter the name of the beer: ");
//        newBeer.addName(input.nextLine());
//        System.out.println("Please enter the type of the beer: ");
//        newBeer.setType(input.nextLine());
//        System.out.println("Please enter the alcohol percentage: ");
//        newBeer.setAlcPerc(input.nextLine());
//        System.out.println("Please enter any notes you may have: ");
//        newBeer.setNotes(input.nextLine());
    }

    public void newDrinkSelection(int i) {
        Scanner input = new Scanner(System.in);
        if (i == 1) {
            BeerObj newBeer = new BeerObj();
            System.out.println("Please enter the name of the beer: ");
            newBeer.addName(input.nextLine());
            System.out.println("Please enter the type of the beer: ");
            newBeer.setType(input.nextLine());
            System.out.println("Please enter the alcohol percentage: ");
            newBeer.setAlcPerc(input.nextLine());
            System.out.println("Please enter any notes you may have: ");
            newBeer.setNotes(input.nextLine());

            bList.add(newBeer);
        } else if (i == 2) {
            WineObj newWine = new WineObj();
            System.out.println("Please enter the name of the wine: ");
            newWine.addName(input.nextLine());
            System.out.println("Please enter the type of the wine: ");
            newWine.setType(input.nextLine());
            System.out.println("Please enter the alcohol percentage: ");
            newWine.setAlcPerc(input.nextLine());
            System.out.println("Please enter any notes you may have: ");
            newWine.setNotes(input.nextLine());

            bList.add(newWine);
        }
    }


    public void insideList() {
        Scanner input = new Scanner(System.in);
        //add while loop to control this menu.
        //boolean stat = true;
        //while(stat){
        // System.out.println("Select model by name");
        // String name = input.nextLine();
        //
        System.out.println("Select model (by name)");
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

    public void insideListTemp() {
        Scanner input = new Scanner(System.in);
        boolean stat = true;
        while (stat) {
            if (bList.size() > 0) {

            }
        }
    }

//    public void save() {
//        FileOutputStream output;
//        ObjectOutputStream out;
//        try {
//            output = new FileOutputStream("test.txt");
//            out = new ObjectOutputStream(output);
//            for(int i = 0; i < bList.size(); i++) {
//                out.writeObject(bList.get(i));
//            }
//            out.close();
//
//        } catch (IOException e) {
//            System.out.println("IOException caught");
//        } catch (Exception a) {
//            System.out.println("Other Exception caught");
//        }
//
//    }

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

//    public void load() {
//        bList.clear();
//        try {
//            FileInputStream input = new FileInputStream("test.txt");
//            ObjectInputStream in = new ObjectInputStream(input);
//
//            while (true) {
//                bList.add((BeerObj) in.readObject());
//            }
//        } catch (IOException e) {
//            System.out.println("File not found");
//        } catch (Exception a) {
//            System.out.println("Other exception caught");
//        }
//
//
//        System.out.println("Save file loaded: " + "/n");
//        for (BeerObj beer : bList) {
//            System.out.println(beer.getName());
//        }
//    }

    public void load() {
        ArrayList<Drink> bList2 = null;
        bList.clear();
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("test.txt");
            ObjectInputStream in = new ObjectInputStream(fis);
            bList2 = (ArrayList<Drink>) in.readObject();

        } catch (EOFException a) {
            //end of file, skips to finally.
        } catch (Exception e) {
            System.out.println("Exception caught");
            System.out.println(e.toString());
        }
//        finally { //finally runs no matter what
//            try {
//                fis.close();
//            } catch (Exception f) {
//                System.out.println("FIS not open.");
//            }
//        }
        bList = bList2;

        System.out.println("Save file loaded: ");
        for (Drink drink : bList) {
            System.out.println(drink.getName());
        }
    }

}
