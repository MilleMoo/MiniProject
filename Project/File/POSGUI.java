package Project.File;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Scanner;

public class POSGUI extends JFrame{
    private JTextArea billTextArea;
    private JComboBox<String> styleComboBox;
    private JComboBox<String> sizeComboBox;
    private JComboBox<String> fabricComboBox;
    private JButton addItemButton;
    private JButton removeItemButton; 
    private JButton clearBillButton; 
    private JButton payButton;
    private JTextField nameTextField;
    private JLabel nameLabel;
    private ArrayList<Clothes> Stock = new ArrayList<>();
    private int count = -1;

    private String Name;
    private Scanner sc = new Scanner(System.in);

    public POSGUI() {
        setTitle("Point of Sale System");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        nameTextField = new JTextField(20);
        nameLabel = new JLabel("Enter Staff Name:");

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7, 2));

        JLabel styleLabel = new JLabel("Style:");
        String[] styles = { "TShirt", "Polo", "Jacket" };
        styleComboBox = new JComboBox<>(styles);

        JLabel sizeLabel = new JLabel("Size:");
        String[] sizes = { "S (32\" A / 23\" B) + 50Bath", "M (34\" A / 24\" B) + 100Bath",
                "L (36\" A / 25\" B) + 150Bath", "XL (38\" A / 26\" B) + 200Bath" };
        sizeComboBox = new JComboBox<>(sizes);

        JLabel fabricLabel = new JLabel("Fabric:");
        String[] fabrics = { "Cotton", "Satin", "Nylon" };
        fabricComboBox = new JComboBox<>(fabrics);

        addItemButton = new JButton("Add Item");
        addItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedStyle = (String) styleComboBox.getSelectedItem();
                String selectedSize = extractSize((String) sizeComboBox.getSelectedItem());
                String selectedFabric = (String) fabricComboBox.getSelectedItem();

                Clothes newItem = null;

                if (selectedStyle.equals("TShirt")) {
                    newItem = new TShirt(1, selectedSize, "", 1); 
                    count++;
                } else if (selectedStyle.equals("Polo")) {
                    newItem = new Polo(2, selectedSize, "", 1);
                    count++;
                } else if (selectedStyle.equals("Jacket")) {
                    newItem = new Jacket(3, selectedSize, "", 1);
                    count++;
                }

                if (newItem != null) {
                    // Calculate size and fabric charges
                    int sizeCharges = 0;
                    int fabricCharges = 0;

                    switch (selectedSize) {
                        case "S":
                            sizeCharges += 50;
                            break;
                        case "M":
                            sizeCharges += 100;
                            break;
                        case "L":
                            sizeCharges += 150;
                            break;
                        case "XL":
                            sizeCharges += 200;
                            break;
                    }

                    switch (selectedFabric) {
                        case "Cotton":
                            fabricCharges += 300;
                            break;
                        case "Satin":
                            fabricCharges += 350;
                            break;
                        case "Nylon":
                            fabricCharges += 400;
                            break;
                    }

                    // Set charges for the item
                    newItem.setSizeCharges(sizeCharges);
                    newItem.setFabricCharges(fabricCharges);
                    newItem.setOverAllCharges(sizeCharges + fabricCharges);

                    Stock.add(newItem);

                    ShowNewClothes(selectedFabric, selectedSize, selectedStyle, newItem);
                }
            }
        });

        
        removeItemButton = new JButton("Remove Item by Index");
        removeItemButton.setBackground(Color.decode("#ed4a6d"));
        removeItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Stock.size() != 0) {
                    int selectedIndex = Integer
                            .parseInt(JOptionPane.showInputDialog("Enter the index of the item to remove:"));
                    if (selectedIndex >= 0 && selectedIndex < Stock.size()) {
                        Clothes removedItem = Stock.remove(selectedIndex);
                        int start = -1, end = -1;
                        String billText = billTextArea.getText();
                        for (int i = 0; i <= selectedIndex; i++) {
                            start = billText.indexOf("Clothes:", start + 1);
                            end = billText.indexOf("Bath", end + 1);
                            if (start == -1 || end == -1) {
                                break;
                            }
                        }
                        if (start != -1 && end != -1) {
                            int nextStart = billText.indexOf("Clothes:", end + 1);
                            if (nextStart == -1) {
                                billTextArea.replaceRange("", start, billText.length());
                            } else {
                                billTextArea.replaceRange("", start, nextStart);
                            }
                        }

                        JOptionPane.showMessageDialog(null, "Removed item:\n" + removedItem.toString());
                        count--;
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid index. Please enter a valid index.");
                    }
                }
            }
        });

        JButton editItemButton = new JButton("Edit Item");
        editItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Stock.size() != 0) {
                    int selectedIndex = Integer
                            .parseInt(JOptionPane.showInputDialog("Enter the index of the item to edit:"));
                    if (selectedIndex >= 0 && selectedIndex < Stock.size()) {
                        Clothes selectedItem = Stock.get(selectedIndex);

                        // Display the selected item's details
                        String selectedSize = selectedItem.getSize();
                        String selectedFabric = Integer.toString(selectedItem.getFabric());

                        // Allow the user to make modifications
                        String newSize = JOptionPane.showInputDialog("Enter new size:", selectedSize);
                        String newFabric = JOptionPane.showInputDialog("Enter new fabric:", selectedFabric);

                        // Update the item with the new values
                        selectedItem.setSize(newSize);
                        selectedItem.setFabric(Integer.parseInt(newFabric));

                        // Update the display
                        ShowNewClothes(selectedFabric, newSize, Integer.toString(selectedItem.getStyle()), selectedItem);
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid index. Please enter a valid index.");
                    }
                }
            }
        });

        clearBillButton = new JButton("Clear Bill");
        clearBillButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Stock.clear();
                billTextArea.setText("");
                count = 0;
            }
        });

        payButton = new JButton("Pay");
        payButton.setBackground(Color.decode("#4aed78"));
        payButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String staffName = nameTextField.getText();
                double totalBill = calculateTotalBill();
                String billMessage = "\n\n---------------Bill---------------" + "\n\n----------------------------------\n\n";

                for (int i = 0; i < Stock.size(); i++) {
                    Clothes item = Stock.get(i);
                    String itemInfo = ShowAllClothes(i)+"Price: " + item.getOverAllCharges() + " Bath\n\n";
                    billMessage += itemInfo;
                }

                billMessage += "Total Bill: " + totalBill + " Bath" + "\n\nStaff Name : "+ staffName +"\n\n-----------Thank You---------------";

                JOptionPane.showMessageDialog(null, billMessage, "Payment", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        billTextArea = new JTextArea();
        billTextArea.setEditable(false);

        panel.add(styleLabel);
        panel.add(styleComboBox);
        panel.add(sizeLabel);
        panel.add(sizeComboBox);
        panel.add(fabricLabel);
        panel.add(fabricComboBox);
        panel.add(nameLabel);
        panel.add(nameTextField);
        panel.add(addItemButton);
        panel.add(editItemButton);
        panel.add(removeItemButton); 
        panel.add(clearBillButton); 
        panel.add(payButton);
        

        JScrollPane scrollPane = new JScrollPane(billTextArea);

        getContentPane().add(panel, BorderLayout.NORTH);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
        setLocationRelativeTo(null);

        // Input staff name
        // System.out.print("Input Staff Name : ");
        // this.Name = sc.nextLine();
    }

    private String extractSize(String selectedSize) {
        String[] parts = selectedSize.split(" \\+");
        return parts[0].trim();
    }

    private double calculateTotalBill() {
        double totalBill = 0.0;
        for (Clothes item : Stock) {
            totalBill += item.getOverAllCharges();
        }
        return totalBill;
    }

    private int getIndexOfSelectedItem(String selectedItemText) {
        for (int i = 0; i < Stock.size(); i++) {
            Clothes item = Stock.get(i);
            String itemInfo = "Style: " + item.getStyle() + "\n" +
                    "Size: " + item.getSize() + "\n" +
                    "Fabric: " + item.getFabric() + "\n" +
                    "Price: " + item.getOverAllCharges() + " Bath\n\n";
            if (itemInfo.equals(selectedItemText)) {
                return i;
            }
        }
        return -1;
    }
    
    public void ShowNewClothes(String selectedFabric, String selectedSize, String selectedStyle,  Clothes newItem) {
        billTextArea.append("Clothes: " + count + "\n");
        billTextArea.append("Style: " + selectedStyle + "\n");
        billTextArea.append("Size: " + selectedSize + "\n");
        billTextArea.append("Fabric: " + selectedFabric + "\n");
        billTextArea.append("Price: " + newItem.getOverAllCharges() + " Bath\n\n");
    }

    public String ShowAllClothes(int index) {
            Clothes clothes = Stock.get(index);
            return "Clothes: " + index + "\n" + "Style: " + clothes.getStyle() + "\n" + "Size: " +
            clothes.getSize() + "\n" + "Fabric: " + clothes.getFabric() + "\n";
    }
}
