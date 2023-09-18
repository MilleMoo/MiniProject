package Project.File;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class POSGUI extends JFrame {
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
    private JTextField receivePaymentTextField;
    private ArrayList<Clothes> Stock = new ArrayList<>();
    private int count = -1;

    public POSGUI() {
        setTitle("POS");
        setSize(450, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        receivePaymentTextField = new JTextField(10); // 10 เป็นความยาวที่เหมาะสมของช่องข้อมูล

        nameTextField = new JTextField(20);
        nameLabel = new JLabel("Enter Staff Name:");

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(8, 2));

        JLabel styleLabel = new JLabel("Style:");
        String[] styles = { "TShirt", "Polo", "Jacket" };
        styleComboBox = new JComboBox<>(styles);

        JLabel sizeLabel = new JLabel("Size:");
        String[] sizes = { "S (32\" A / 23\" B) + 50Bath", "M (34\" A / 24\" B) + 100Bath",
                "L (36\" A / 25\" B) + 150Bath", "XL (38\" A / 26\" B) + 200Bath" };
        sizeComboBox = new JComboBox<>(sizes);

        JLabel fabricLabel = new JLabel("Fabric:");
        String[] fabrics = { "Cotton +300Bath", "Satin +350Bath", "Nylon +400Bath" };
        fabricComboBox = new JComboBox<>(fabrics);

        addItemButton = new JButton("Add Clothes in Cart");
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
                        case "S (32\" A / 23\" B)":
                            sizeCharges += 50;
                            break;
                        case "M (34\" A / 24\" B)":
                            sizeCharges += 100;
                            break;
                        case "L (36\" A / 25\" B)":
                            sizeCharges += 150;
                            break;
                        case "XL (38\" A / 26\" B)":
                            sizeCharges += 200;
                            break;
                    }

                    switch (selectedFabric) {
                        case "Cotton +300Bath":
                            fabricCharges += 300;
                            break;
                        case "Satin +350Bath":
                            fabricCharges += 350;
                            break;
                        case "Nylon +400Bath":
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

        removeItemButton = new JButton("Remove Clothes in Cart by Index");
        removeItemButton.setBackground(Color.decode("#ed4a6d"));
        removeItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Stock.size() != 0) {
                    String selectedIndexStr = JOptionPane.showInputDialog("Enter the index of the item to remove:");

                    // Check if the user clicked "Cancel" or closed the dialog
                    if (selectedIndexStr != null) {
                        try {
                            int selectedIndex = Integer.parseInt(selectedIndexStr);
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
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid number.");
                        }
                    }
                }
            }
        });

        JButton editItemButton = new JButton("Edit Clothes in Cart");
        editItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Stock.size() != 0) {
                    String selectedIndexText = JOptionPane.showInputDialog("Enter the index of the item to edit:");
                    if (selectedIndexText != null) {
                        try {
                            int selectedIndex = Integer.parseInt(selectedIndexText);
                            if (selectedIndex >= 0 && selectedIndex < Stock.size()) {
                                Clothes selectedItem = Stock.get(selectedIndex);

                                // Display the selected item's details
                                String selectedSize = selectedItem.getSize();
                                String selectedFabric = getFabricIntToString(selectedItem.getFabric());
                                String selectedStyle = getStyleIntToString(selectedItem.getStyle());

                                // Allow the user to make modifications
                                String newSizeInput = JOptionPane.showInputDialog("Enter new size:", selectedSize);
                                if (newSizeInput != null) {
                                    String newSize = CheckWrongSize(newSizeInput);
                                    String newFabricInput = JOptionPane.showInputDialog("Enter new fabric:",
                                            selectedFabric);
                                    if (newFabricInput != null) {
                                        String newFabric = CheckWrongFabic(newFabricInput);
                                        String newStyleInput = JOptionPane.showInputDialog("Enter new style:",
                                                selectedStyle);
                                        if (newStyleInput != null) {
                                            String newStyle = CheckWrongStyle(newStyleInput);

                                            switch (newSize) {
                                                case "S", "s":
                                                    newSize = "S (32\" A / 23\" B)";
                                                    break;
                                                case "M", "m":
                                                    newSize = "M (34\" A / 24\" B)";
                                                    break;
                                                case "L", "l":
                                                    newSize = "L (36\" A / 25\" B)";
                                                    break;
                                                case "XL", "xl":
                                                    newSize = "XL (38\" A / 26\" B)";
                                                    break;
                                            }

                                            // Update the item with the new values
                                            selectedItem.setSize(newSize);
                                            setFabricStringToInt(newFabric, selectedItem);
                                            setStyleStringToInt(newStyle, selectedItem);
                                            CheckBill(selectedIndex);

                                            // Update the display
                                            ShowNewClothes(ShowFabric(selectedItem.getFabric()), newSize,
                                                    ShowStyle(selectedItem.getStyle()), selectedItem);
                                        }
                                    }
                                }
                            } else {
                                JOptionPane.showMessageDialog(null,
                                "Invalid index. Please enter a valid index.");
                            }
                            } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(null,
                                    "Invalid input. Please enter Not have in cart.");
                        }
                    }
                }
            }
        });

        clearBillButton = new JButton("Clear Clothes in Cart");
        clearBillButton.addActionListener(new ActionListener() {

    @Override
            public void actionPerformed(ActionEvent e) {
                Stock.clear();
                billTextArea.setText("");
                count = -1;
            }
        });

        payButton = new JButton("Pay");
        payButton.setBackground(Color.decode("#4aed78"));
        payButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Stock.size() != 0) {
                    String staffName = nameTextField.getText();
                    String receivedPaymentText = receivePaymentTextField.getText(); // รับค่าจากช่องรับเงิน
                    if (!staffName.isEmpty() && !receivedPaymentText.isEmpty()) {
                        try {
                            double totalBill = calculateTotalBill();
                            double receivedPayment = Double.parseDouble(receivedPaymentText);
                            if (totalBill <= receivedPayment) {
                                // เก็บค่าเวลาปัจจุบัน
                                LocalDateTime currentTime = LocalDateTime.now();
                                DateTimeFormatter formatter = DateTimeFormatter
                                        .ofPattern("yyyy - MM - dd HH : mm : ss");
                                String formattedTime = currentTime.format(formatter);
                                String billMessage = "\n\n----------------------Bill----------------------------\n"
                                        + "Date - Time : " + formattedTime
                                        + "\n------------------------------------------------------\n\n";

                                for (int i = 0; i < Stock.size(); i++) {
                                    Clothes item = Stock.get(i);
                                    String itemInfo = ShowAllClothes(i) + "Price: " + item.getOverAllCharges()
                                            + " Bath\n\n";
                                    billMessage += itemInfo;
                                }

                                billMessage += "Total Amount: " + totalBill + " Bath\n" + "Cash: "
                                        + receivedPayment + " Bath\n" +
                                        "Change: " + calculateChangeMoney(receivedPayment, totalBill) + " Bath"
                                        + "\n\nStaff Name : " + staffName
                                        + "\n\n--------------------Thank You---------------------";

                                JOptionPane.showMessageDialog(null, billMessage, "Payment",
                                        JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                JOptionPane.showMessageDialog(null,
                                        "Received payment is insufficient to cover the total bill.", "Payment Error",
                                        JOptionPane.ERROR_MESSAGE);
                            }
                        } catch (NumberFormatException ENF) {
                            JOptionPane.showMessageDialog(null,
                                    "Invalid received payment value. Please enter a valid number.", "Payment Error",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Please enter staff name and received payment.",
                                "Payment Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null,
                            "No items in the bill. Please add items before making a payment.", "Payment Error",
                            JOptionPane.ERROR_MESSAGE);
                }
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
        panel.add(new JLabel("Receive Payment:"));
        panel.add(receivePaymentTextField);
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

    private double calculateChangeMoney(double totalBill, double receivedPayment) {
        totalBill -= receivedPayment;
        return totalBill;
    }

    public void ShowNewClothes(String selectedFabric, String selectedSize, String selectedStyle, Clothes newItem) {
        billTextArea.append("Clothes: " + count + "\n");
        billTextArea.append("Style: " + selectedStyle + "\n");
        billTextArea.append("Size: " + selectedSize + "\n");
        billTextArea.append("Fabric: " + selectedFabric + "\n");
        billTextArea.append("Price: " + newItem.getOverAllCharges() + " Bath\n\n");
    }

    public void ShowNewClothes(int selectedFabric, String selectedSize, String selectedStyle, Clothes newItem) {
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

    public String getFabricIntToString(int getFabric) {
        String Fabric;
        switch (getFabric) {
            case 1:
                Fabric = "Cotton";
                break;
            case 2:
                Fabric = "Satin";
                break;
            case 3:
                Fabric = "Nylon";
                break;

            default:
                Fabric = "Not have Fabric";
                break;
        }
        return Fabric;
    }

    public String getStyleIntToString(int getStyle) {
        String Style;
        switch (getStyle) {
            case 1:
                Style = "TShirt";
                break;
            case 2:
                Style = "Polo";
                break;
            case 3:
                Style = "Jacket";
                break;

            default:
                Style = "Not have Style";
                break;
        }
        return Style;
    }

    public void setStyleStringToInt(String getStyle, Clothes Select) {
        int Style;
        switch (getStyle) {
            case "TShirt", "tshirt":
                Style = 1;
                break;
            case "Polo", "polo":
                Style = 2;
                break;
            case "Jacket", "jacket":
                Style = 3;
                break;

            default:
                Style = 0;
                break;
        }
        Select.setStyle(Style);
    }

    public void setFabricStringToInt(String getFabric, Clothes Select) {
        int Fabric;
        switch (getFabric) {
            case "Cotton", "cotton", "Cotton +300Bath", "cotton +300Bath":
                Fabric = 1;
                break;
            case "Satin", "satin", "satin +350Bath", "Satin +350Bath":
                Fabric = 2;
                break;
            case "Nylon", "nylon", "nylon +400Bath", "Nylon +400Bath":
                Fabric = 3;
                break;

            default:
                Fabric = 0;
                break;
        }
        Select.setFabric(Fabric);
    }

    public String ShowStyle(int Select) {// แปลงตัวเลขเป็นตัวอักษรเพื่อที่จะได้รู้รูปแบบของผ้า
        String Style;
        switch (Select) {
            case 1:
                Style = "TShirt";
                break;
            case 2:
                Style = "Polo";
                break;
            case 3:
                Style = "Jacket";
                break;

            default:
                Style = "Not have Style";
                break;
        }
        return Style;
    }

    public String ShowFabric(int Select) {// แปลงตัวเลขเป็นตัวอักษรเพื่อที่จะได้รู้ชนิดของผ้า
        String Fabric;
        switch (Select) {
            case 1:
                Fabric = "Cotton +300Bath";
                break;
            case 2:
                Fabric = "Satin +350Bath";
                break;
            case 3:
                Fabric = "Nylon +400Bath";
                break;

            default:
                Fabric = "Not have Fabric";
                break;
        }
        return Fabric;
    }

    private void CheckBill(int index) {
        int Size = 0;
        int fabric = 0;
        Clothes Item = Stock.get(index);
        switch (Item.getSize()) {
            case "S (32\" A / 23\" B)":
                Size += 50;
                break;
            case "M (34\" A / 24\" B)":
                Size += 100;
                break;
            case "L (36\" A / 25\" B)":
                Size += 150;
                break;
            case "XL (38\" A / 26\" B)":
                Size += 200;
                break;
        }
        switch (Item.getFabric()) {
            case 1:
                fabric += 300;
                break;
            case 2:
                fabric += 350;
                break;
            case 3:
                fabric += 400;
                break;
        }
        Item.setOverAllCharges(fabric += Size);
    }

    private String CheckWrongSize(String newSize) {
        String size = newSize.toUpperCase();
        if (size.contains("S") || size.contains("M") || size.contains("L")
                || size.contains("XL")) {
            return newSize;
        } else {
            newSize = JOptionPane.showInputDialog("Wrong Size please input agin:");
            CheckWrongSize(newSize);
            return newSize;
        }
    }

    private String CheckWrongFabic(String newFabric) {
        String fabric = newFabric.toUpperCase();
        if (fabric.contains("COTTON") || fabric.contains("SATIN")
                || fabric.contains("NYLON")) {
            return newFabric;
        } else {
            newFabric = JOptionPane.showInputDialog("Wrong Fabic please input agin:");
            CheckWrongFabic(newFabric);
            return newFabric;
        }
    }

    private String CheckWrongStyle(String newStyle) {
        String style = newStyle.toUpperCase();
        if (style.contains("TSHIRT") || style.contains("POLO")
                || style.contains("JACKET")) {
            return newStyle;
        } else {
            newStyle = JOptionPane.showInputDialog("Wrong Style please input agin:");
            CheckWrongStyle(newStyle);
            return newStyle;
        }
    }
}
