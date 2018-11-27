package gui;

import exception.DrinkAlreadyExistsException;
import model.BeerObj;
import model.DrinkAbstract;
import model.DrinkList;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

public class GUITest extends JPanel implements ListSelectionListener{

    private JList<DrinkAbstract> list;

    private DrinkList dList;

    public GUITest() {
        super(new BorderLayout());

        dList = new DrinkList();

        DrinkAbstract beer1 = new BeerObj("1", "1", "1", "1", false);
        DrinkAbstract beer2 = new BeerObj("2", "2", "2", "2", false);
        try {
            dList.addDrink(beer1);
            dList.addDrink(beer2);
        } catch (DrinkAlreadyExistsException e) {
            e.printStackTrace();
        }

        list = new JList(dList.returnList().toArray());
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.addListSelectionListener(this);
        list.setVisibleRowCount(5);
        JScrollPane listScrollPane = new JScrollPane(list);

        JButton addDrink = new JButton("Add Drink");
        AddListener addListener = new AddListener(addDrink);
        addDrink.setActionCommand("Add Drink");
        addDrink.addActionListener(addListener);
        JPanel buttonPane = new JPanel();
        buttonPane.add(addDrink);

        add(listScrollPane, BorderLayout.CENTER);
        add(buttonPane, BorderLayout.PAGE_END);

    }

    class AddListener implements ActionListener {

        private JButton button;

        public AddListener(JButton button) {
            this.button = button;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            DrinkAbstract beer3 = new BeerObj("3", "3", "3", "3", false);
            try {
                dList.addDrink(beer3);
            } catch (DrinkAlreadyExistsException e1) {
                JOptionPane.showMessageDialog(null, "Drink exists");
            }
            list.ensureIndexIsVisible(dList.size());
        }
    }

    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("ListDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        JComponent newContentPane = new GUITest();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {

    }

    public static void main(String[] args){
        GUITest test = new GUITest();
        test.createAndShowGUI();
        test.setVisible(true);

    }
}
