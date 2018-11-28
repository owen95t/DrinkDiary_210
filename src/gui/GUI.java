package gui;

import exception.DrinkAlreadyExistsException;
import exception.LoadFailException;
import exception.SaveFailedException;
import jdk.nashorn.internal.scripts.JO;
import model.*;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;

public class GUI extends JFrame implements ActionListener{
    JPanel mainPanel, tmpPanel;
    JLabel lblName, lblType, lblAlcPerc, lblNotes, mainLabel, favLabel, favourited;
    JTextField name, dType, alcPerc, notes;
    JList listMain, listFav;
    JList<DrinkAbstract> testJList;
    JButton save, load, newDrink, removeDrink, addNew, cancel, favMarker, unFavMarker;
    JScrollPane list1, list2;
    DefaultListModel defaultList, defaultListFav;
    JOptionPane newDrinkPopUp;
    JRadioButton Beer, Wine, Cider, HardLiquor, Other, yesFav, notFav;
    JDialog drinkAlreadyExistsDialog;
    JFrame drinkDialog;
    ButtonGroup G1, G2;

    private JTextField filename = new JTextField(), dir = new JTextField();

    ListSelectionModel listSelectionModel;

    DrinkList drinkList;

    public boolean saveState;



    public static void main(String[] args) {
        GUI gui = new GUI();
        gui.setVisible(true);
    }

    public GUI() {
        super("Drink Diary");

        setSize(650, 350);

        drinkList = new DrinkList();

        mainPanel = new JPanel();
        add(mainPanel);

        mainLabel = new JLabel("Welcome to your drink diary!\nHere is your drinks list");
        tmpPanel = new JPanel();
        tmpPanel.add(mainLabel);
        mainPanel.add(tmpPanel);

        //Because JList/DefaultListModel shows .toString() as the title we need to change it
        //With ListCellRenderer, it does not show toString, we use a CellRenderer which we can manipulate how it renders what it shows
        ListCellRenderer renderer = new TitleListCellRenderer();

        //Main List
        defaultList = new DefaultListModel();
        listMain = new JList(defaultList);
        listMain.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listMain.setSelectedIndex(0);
        listMain.setVisibleRowCount(5);
        //listMain.addListSelectionListener(new myListHandler());
        listMain.setCellRenderer(renderer);


        list1 = new JScrollPane(listMain);
        list1.setPreferredSize(new Dimension(300, 200));
        tmpPanel = new JPanel();
        tmpPanel.add(list1);
        mainPanel.add(tmpPanel, BorderLayout.CENTER);

        listSelectionModel = listMain.getSelectionModel();
        listSelectionModel.addListSelectionListener(new myListHandler());

        save = new JButton("Save");
        load = new JButton("Load");
        removeDrink = new JButton("Remove Drink");
        newDrink = new JButton("New Drink");
        favMarker = new JButton("Favourite");
        unFavMarker = new JButton("Unfavourite");
        JPanel buttonPane = new JPanel();
        buttonPane.add(newDrink);
        buttonPane.add(save);
        buttonPane.add(load);
        buttonPane.add(removeDrink);
        buttonPane.add(favMarker);
        buttonPane.add(unFavMarker);
        mainPanel.add(buttonPane, BorderLayout.SOUTH);

        newDrink.setActionCommand("new");
        newDrink.addActionListener(new newDrinkHandler());
        load.setActionCommand("load");
        load.addActionListener(this);
        save.setActionCommand("save");
        save.addActionListener(this);
        removeDrink.setActionCommand("remove");
        removeDrink.addActionListener(new myRemoveHelper());
        favMarker.setActionCommand("fav");
        favMarker.addActionListener(new myFavouriteHelper());
        unFavMarker.setActionCommand("unfav");
        unFavMarker.addActionListener(new myFavouriteHelper());

        listMain.addMouseListener(new myMouseHandler());

        this.addWindowListener(new myWindowHandler());

        //ATTEMPT AT JMenuBar
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu menuFile = new JMenu("File");
        JMenu menuHelp = new JMenu("Help");
        menuBar.add(menuFile);
        menuBar.add(menuHelp);

        JMenuItem fOpen = new JMenuItem("Open file");
        JMenuItem fSave = new JMenuItem("Save file");
        JMenuItem fExit = new JMenuItem("Exit");
        menuFile.add(fOpen);
        menuFile.add(fSave);
        menuFile.add(fExit);

        fOpen.addActionListener(new myOpenFileHandler());
        fSave.addActionListener(new mySaveFileHandler());

    }

    private class myOpenFileHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser jfc = new JFileChooser();
            int rVal = jfc.showOpenDialog(GUI.this);
            if (rVal == JFileChooser.APPROVE_OPTION) {
                File file = jfc.getSelectedFile();
                String name = file.getName();
                try {
                    load(name);
                } catch (LoadFailException l) {
                    JOptionPane.showMessageDialog(null, "Load Failed!");
                }
//                filename.setText(jfc.getSelectedFile().getName());
//                dir.setText(jfc.getCurrentDirectory().toString());
            }
            if (rVal == JFileChooser.CANCEL_OPTION) {
                filename.setText("You pressed cancel");
                dir.setText("");
            }
        }
    }

    private class mySaveFileHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            JFileChooser c = new JFileChooser();
            // Demonstrate "Save" dialog:
            int rVal = c.showSaveDialog(GUI.this);
            if (rVal == JFileChooser.APPROVE_OPTION) {
                File file = c.getSelectedFile();
                String name = file.getName()+".txt";
                try {
                    save(name);
                } catch (SaveFailedException s) {
                    JOptionPane.showMessageDialog(null, "Save failed!");
                }
//                filename.setText(c.getSelectedFile().getName());
//                dir.setText(c.getCurrentDirectory().toString());
            }
            if (rVal == JFileChooser.CANCEL_OPTION) {
                filename.setText("You pressed cancel");
                dir.setText("");
            }
        }
    }


    private class myWindowHandler extends WindowAdapter{
        public void windowClosing(WindowEvent event) {
            int reply = 0;
            if (saveState) {
                reply = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", "Exit confirm", JOptionPane.YES_NO_OPTION);
            } else if (!saveState) {
                reply = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit without saving?", "Exit confirm", JOptionPane.YES_NO_OPTION);
            }

            if (reply == JOptionPane.YES_OPTION) {
                System.exit(0);
            } else {
                setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            }
        }
    }

    public void actionPerformed(ActionEvent event){
        if (event.getActionCommand().equals("load")) {
            try {
                load();
            } catch (LoadFailException l) {
                l.printStackTrace();
                JOptionPane.showMessageDialog(null, "Load failed!");
            }
        } else if (event.getActionCommand().equals("save")) {
            try {
                save();
            } catch (SaveFailedException s) {
                s.printStackTrace();
                JOptionPane.showMessageDialog(null, "Save failed!");
            }
        }
    }

    public class myFavouriteHelper implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            DrinkAbstract drinkTemp = null;
            int index = listMain.getSelectedIndex();
            if (index >= 0) {
                drinkTemp = (DrinkAbstract) defaultList.getElementAt(index);
                if (drinkTemp != null) {
                    if (!drinkTemp.getFav()) {
                        DrinkAbstract drinkReplacement = drinkTemp;
                        drinkReplacement.setFav(true);
                        defaultList.set(index, drinkReplacement);
                    } else if (drinkTemp.getFav()) {
                        DrinkAbstract drinkReplacement = drinkTemp;
                        drinkReplacement.setFav(false);
                        defaultList.set(index, drinkReplacement);
                    }
                }
            }
        }
    }

    public class myRemoveHelper implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int index = listMain.getSelectedIndex();
            if (index >= 0) { //Remove only if a particular item is selected
                DrinkAbstract drinkRemoved = (DrinkAbstract) defaultList.getElementAt(index);
                int reply = JOptionPane.showConfirmDialog(null, "Are you sure you want to remove " + drinkRemoved.getName() + " " + drinkRemoved.getType() + "?", "Remove confirm", JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_OPTION) {
                    drinkList.removeDrink(drinkRemoved);
                    defaultList.removeElementAt(index);
                }
            }
        }
    }

    //for creating a new drink GUI popup
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
        public void valueChanged(ListSelectionEvent e) {
            ListSelectionModel lsm = (ListSelectionModel)e.getSource();

            int firstIndex = e.getFirstIndex();
            int lastIndex = e.getLastIndex();
            boolean isAdjusting = e.getValueIsAdjusting();

            if (lsm.isSelectionEmpty()) {
                System.out.println("None");
            } else {
                // Find out which indexes are selected.
                int minIndex = lsm.getMinSelectionIndex();
                int maxIndex = lsm.getMaxSelectionIndex();
                for (int i = minIndex; i < maxIndex; i++) {
                    if (lsm.isSelectedIndex(i)) {
                        System.out.println(" " + i);
                    }
                }
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
                    DrinkAbstract temp = (DrinkAbstract) o;
                    System.out.println("Double-clicked on: " + o.toString());
                    JOptionPane.showMessageDialog(null, o.toString(), ((DrinkAbstract) o).getName(), JOptionPane.INFORMATION_MESSAGE);
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
            DrinkAbstract drink = new BeerObj(name.getText(), alcPerc.getText(), notes.getText(), dType.getText(), fav);
            drinkList.addDrink(drink);
            defaultList.addElement(drink);
        } else if(type.toLowerCase().equals("wine")){
            DrinkAbstract drink = new WineObj(name.getText(), alcPerc.getText(), notes.getText(), dType.getText(), fav);
            drinkList.addDrink(drink);
            defaultList.addElement(drink);
        } else if (type.toLowerCase().equals("cider")) {
            DrinkAbstract drink = new CiderObj(name.getText(), alcPerc.getText(), notes.getText(), dType.getText(), fav);
            drinkList.addDrink(drink);
            defaultList.addElement(drink);
        } else if (type.toLowerCase().equals("hard liquor")) {
            DrinkAbstract drink = new HardLiquorObj(name.getText(), alcPerc.getText(), notes.getText(), dType.getText(), fav);
            drinkList.addDrink(drink);
            defaultList.addElement(drink);
        } else if (type.toLowerCase().equals("other")) {
            DrinkAbstract drink = new OtherObj(name.getText(), alcPerc.getText(), notes.getText(), dType.getText(), fav);
            drinkList.addDrink(drink);
            defaultList.addElement(drink);
        }
    }

    public void load() throws LoadFailException{
        drinkList.load();
        defaultList.clear();
        ArrayList<DrinkAbstract> mainL = drinkList.returnList();
        for (DrinkAbstract drink : mainL) {
            defaultList.addElement(drink);
        }
        saveState = false;
    }

    public void load(String filename) throws LoadFailException{
        drinkList.load(filename);
        defaultList.clear();
        ArrayList<DrinkAbstract> mainL = drinkList.returnList();
        for (DrinkAbstract drink : mainL) {
            defaultList.addElement(drink);
        }
        saveState = false;
    }

    public void save() throws SaveFailedException{
        drinkList.save();
        JOptionPane.showMessageDialog(null, "File Saved.");
        saveState = true;
    }
    public void save(String filename) throws SaveFailedException{
        drinkList.save(filename);
        JOptionPane.showMessageDialog(null, "File Saved.");
        saveState = true;
    }

    private class TitleListCellRenderer extends JLabel implements ListCellRenderer<DrinkAbstract> {
        public TitleListCellRenderer() {
            setOpaque(true);
        }
        @Override
        public Component getListCellRendererComponent(JList<? extends DrinkAbstract> list, DrinkAbstract value, int index, boolean isSelected, boolean cellHasFocus) {

            setText(value.getName() + " " + value.getType() + " " +value.isFav());

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
