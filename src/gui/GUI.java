package gui;

import exception.DrinkAlreadyExistsException;
import exception.LoadFailException;
import exception.SaveFailedException;
import model.*;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GUI extends JFrame implements ActionListener{
    JPanel mainPanel, tmpPanel;
    JLabel lblName, lblType, lblAlcPerc, lblNotes, mainLabel, favLabel, favourited;
    JTextField name, dType, alcPerc, notes;
    JList listMain, listFav;
    JList<DrinkAbstract> testJList;
    JButton save, load, newDrink, removeDrink, addNew, cancel;
    JScrollPane list1, list2;
    DefaultListModel defaultList, defaultListFav;
    JOptionPane newDrinkPopUp;
    JRadioButton Beer, Wine, Cider, HardLiquor, Other, yesFav, notFav;
    JDialog drinkAlreadyExistsDialog;
    JFrame drinkDialog;
    ButtonGroup G1, G2;

    DrinkList drinkList;
    DrinkAbstract Beer1, Beer2;

    public static void main(String[] args) {
        GUI gui = new GUI();
        gui.setVisible(true);
    }
    public GUI() {
        super("Beer Diary");
        setSize(600, 500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        drinkList = new DrinkList();
        DrinkAbstract Beer1 = new BeerObj("Kokanee", "5", "Lager", "Good", false);
        DrinkAbstract Beer2 = new BeerObj("Molson", "5", "Lager", "Good", false);
        try {
            drinkList.addDrink(Beer1);
            drinkList.addDrink(Beer2);
        } catch (DrinkAlreadyExistsException e) {
            System.out.println("Drink Already Exists");
        }

        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(5, 1));
        add(mainPanel);

        mainLabel = new JLabel("Main List:");
        tmpPanel = new JPanel();
        tmpPanel.add(mainLabel);
        mainPanel.add(tmpPanel);

        ListCellRenderer renderer = new TitleListCellRenderer();

        //Main List

        testJList = new JList(drinkList.returnList().toArray());
        testJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        testJList.setCellRenderer(renderer);
        testJList.setSelectedIndex(0);
        testJList.setVisibleRowCount(5);
        testJList.addListSelectionListener(new myListHandler());

        list1 = new JScrollPane(testJList);
        list1.setPreferredSize(new Dimension(200, 100));
        tmpPanel = new JPanel();
        tmpPanel.add(list1);
        mainPanel.add(tmpPanel);

        //Fav List


        favLabel = new JLabel("Favourites List:");
        tmpPanel = new JPanel();
        tmpPanel.add(favLabel);
        mainPanel.add(tmpPanel);

        defaultListFav = new DefaultListModel();

        listFav = new JList(defaultListFav);
        listFav.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listFav.setSelectedIndex(0);
//        listFav.addListSelectionListener(this);
        listFav.setVisibleRowCount(5);
        listFav.setLayoutOrientation(JList.VERTICAL);
        listFav.addListSelectionListener(new myListHandler());
        listFav.setCellRenderer(renderer);
        list2 = new JScrollPane(listFav);
        list2.setPreferredSize(new Dimension(200, 100));
        tmpPanel = new JPanel();
        tmpPanel.add(list2);
        mainPanel.add(tmpPanel);

        save = new JButton("Save");
        load = new JButton("Load");
        removeDrink = new JButton("Remove Drink");
        newDrink = new JButton("New Drink");
        tmpPanel = new JPanel();
        tmpPanel.add(newDrink);
        tmpPanel.add(save);
        tmpPanel.add(load);
        tmpPanel.add(removeDrink);
        mainPanel.add(tmpPanel);

        newDrink.setActionCommand("new");
        newDrink.addActionListener(new newDrinkHandler());
        load.setActionCommand("load");
        load.addActionListener(this);
        save.setActionCommand("save");
        save.addActionListener(this);
        removeDrink.setActionCommand("remove");
        removeDrink.addActionListener(this);

        //listMain.addMouseListener(new myMouseHandler());
        listFav.addMouseListener(new myMouseHandler());

    }

    public void actionPerformed(ActionEvent event){
        if (event.getActionCommand().equals("load")) {
            try {
                load();
            } catch (LoadFailException l) {
                l.printStackTrace();
            }
        } else if (event.getActionCommand().equals("Save")) {
            try {
                save();
            } catch (SaveFailedException s) {
                s.printStackTrace();
            }
        } else if (event.getActionCommand().equals("remove")) {
            removeHelper();
        }
    }

    public void removeHelper() {
        DrinkAbstract tmp = (DrinkAbstract) listMain.getSelectedValue();
        defaultListFav.removeElement(tmp);
    }

    //for creating a new drink
    private class newDrinkHandler implements ActionListener{
        public void actionPerformed(ActionEvent event) {
            drinkDialog = new JFrame();
            drinkDialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            drinkDialog.setSize(450, 250);
            drinkDialog.setLayout(new GridLayout(8, 2));

            lblName = new JLabel("Name: ");
            name = new JTextField(20);
            tmpPanel = new JPanel();
            tmpPanel.add(lblName);
            tmpPanel.add(name);
            drinkDialog.add(tmpPanel);

            lblType = new JLabel("Type: ");
            dType = new JTextField(20);
            tmpPanel = new JPanel();
            tmpPanel.add(lblType);
            tmpPanel.add(dType);
            drinkDialog.add(tmpPanel);

            lblAlcPerc = new JLabel("Alcohol Percentage: ");
            alcPerc = new JTextField(20);
            tmpPanel = new JPanel();
            tmpPanel.add(lblAlcPerc);
            tmpPanel.add(alcPerc);
            drinkDialog.add(tmpPanel);

            lblNotes = new JLabel("Notes: ");
            notes = new JTextField(20);
            tmpPanel = new JPanel();
            tmpPanel.add(lblNotes);
            tmpPanel.add(notes);
            drinkDialog.add(tmpPanel);

            G1 = new ButtonGroup();

            Beer = new JRadioButton("Beer");
            Wine = new JRadioButton("Wine");
            Cider = new JRadioButton("Cider");
            HardLiquor = new JRadioButton("Hard Liquor");
            Other = new JRadioButton("Other");
            tmpPanel = new JPanel();
            G1.add(Beer);
            G1.add(Wine);
            G1.add(Cider);
            G1.add(HardLiquor);
            G1.add(Other);
            tmpPanel.add(Beer);
            tmpPanel.add(Wine);
            tmpPanel.add(Cider);
            tmpPanel.add(HardLiquor);
            tmpPanel.add(Other);
            drinkDialog.add(tmpPanel);

            G2 = new ButtonGroup();

            favourited = new JLabel("Favourite?");
            yesFav = new JRadioButton("Yes");
            notFav = new JRadioButton("No");
            tmpPanel = new JPanel();
            G2.add(yesFav);
            G2.add(notFav);
            tmpPanel.add(favourited);
            tmpPanel.add(yesFav);
            tmpPanel.add(notFav);
            drinkDialog.add(tmpPanel);

            addNew = new JButton("Create Drink");
            cancel = new JButton("Cancel");
            tmpPanel = new JPanel();
            tmpPanel.add(addNew);
            tmpPanel.add(cancel);
            drinkDialog.add(tmpPanel);

            addNew.setActionCommand("create");
            addNew.addActionListener(new myNewDrinkHandler());
            cancel.setActionCommand("close");
            cancel.addActionListener(new myDisposed());

            drinkDialog.setVisible(true);
        }
    }
    private class myListHandler implements ListSelectionListener{
        public void valueChanged(ListSelectionEvent event) {
            if (event.getValueIsAdjusting() == false) {

            }
        }
    }

    private class myDisposed implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            drinkDialog.dispose();
        }
    }

    private class myMouseHandler extends MouseAdapter{
        public void mouseClicked(MouseEvent e) {
            JList theList = (JList) e.getSource();
            if (e.getClickCount() == 2) {
                int index = theList.locationToIndex(e.getPoint());
                if (index >= 0) {
                    Object o = theList.getModel().getElementAt(index);
                    System.out.println("Double-clicked on: " + o.toString());
                }
            }
        }
    }
    //for creating a new drink
    private class myNewDrinkHandler implements ActionListener {
        public void actionPerformed(ActionEvent event){
            try {
                if (Beer.isSelected()) {
                    createNew("beer");
                    drinkDialog.dispose();
                } else if (Wine.isSelected()) {
                    createNew("wine");
                    drinkDialog.dispose();
                } else if (Cider.isSelected()) {
                    createNew("cider");
                    drinkDialog.dispose();
                } else if (HardLiquor.isSelected()) {
                    createNew("hard liquor");
                    drinkDialog.dispose();
                } else if (Other.isSelected()) {
                    createNew("other");
                    drinkDialog.dispose();
                }
            } catch (DrinkAlreadyExistsException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Drink Already Exists!");
            }

        }
    }

    public void createNew(String type) throws DrinkAlreadyExistsException{
        boolean fav = false;
        if (yesFav.isSelected()) {
            fav = true;
        }
        if(type.toLowerCase().equals("beer")){
            DrinkAbstract drink = new BeerObj(name.getText(), dType.getText(), alcPerc.getText(), notes.getText(), fav);
            drinkList.addDrink(drink);
            defaultList.addElement(drink);
            favHelper(fav, drink);
        } else if(type.toLowerCase().equals("wine")){
            DrinkAbstract drink = new WineObj(name.getText(), dType.getText(), alcPerc.getText(), notes.getText(), fav);
            drinkList.addDrink(drink);
            defaultList.addElement(drink);
            favHelper(fav, drink);
        } else if (type.toLowerCase().equals("cider")) {
            DrinkAbstract drink = new CiderObj(name.getText(), dType.getText(), alcPerc.getText(), notes.getText(), fav);
            drinkList.addDrink(drink);
            defaultList.addElement(drink);
            favHelper(fav, drink);
        } else if (type.toLowerCase().equals("hard liquor")) {
            DrinkAbstract drink = new HardLiquorObj(name.getText(), dType.getText(), alcPerc.getText(), notes.getText(), fav);
            drinkList.addDrink(drink);
            defaultList.addElement(drink);
            favHelper(fav, drink);
        } else if (type.toLowerCase().equals("other")) {
            DrinkAbstract drink = new OtherObj(name.getText(), dType.getText(), alcPerc.getText(), notes.getText(), fav);
            drinkList.addDrink(drink);
            defaultList.addElement(drink);
            favHelper(fav, drink);
        }

        testJList.updateUI();
    }
    public void favHelper(boolean f,  DrinkAbstract drink){

        try {
            if (f) {
                drinkList.addFavDrink(drink);
                defaultListFav.addElement(drink);
            }
        } catch (DrinkAlreadyExistsException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Drink Already Exists!");
        }
    }


    public void load() throws LoadFailException{
        drinkList.load();

        defaultList.clear();
        ArrayList<DrinkAbstract> mainL = drinkList.returnList();
        for (DrinkAbstract drink : mainL) {
            defaultList.addElement(drink);
        }

        defaultListFav.clear();
        ArrayList<DrinkAbstract> favL = drinkList.returnFavList();
        for (DrinkAbstract drink : favL) {
            defaultListFav.addElement(drink);
        }

    }

    public void save() throws SaveFailedException{
        drinkList.save();
    }

    private class TitleListCellRenderer extends JLabel implements ListCellRenderer<DrinkAbstract> {

        public TitleListCellRenderer() {
            setOpaque(true);
        }
        @Override
        public Component getListCellRendererComponent(JList<? extends DrinkAbstract> list, DrinkAbstract value, int index, boolean isSelected, boolean cellHasFocus) {
            setText(value.getName());

            Color foreground;
            Color background;
            JList.DropLocation dropLocation = list.getDropLocation();
            if (dropLocation != null
                    && !dropLocation.isInsert()
                    && dropLocation.getIndex() == index) {

                background = Color.BLUE;
                foreground = Color.WHITE;
            }else if (isSelected) {
                background = Color.BLUE;
                foreground = Color.WHITE;
            } else {
                background = Color.WHITE;
                foreground = Color.BLACK;
            }
            setBackground(background);
            setForeground(foreground);

            return this;
        }
    }



}
