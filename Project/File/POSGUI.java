package File;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class POSGUI extends JFrame {
    private JTextArea billTextArea;
    private JComboBox<String> styleComboBox;
    private JComboBox<String> sizeComboBox;
    private JComboBox<String> fabricComboBox;
    private JButton addItemButton;
    private JButton removeItemButton;
    private JButton clearBillButton;
    private JButton payButton;
    private JLabel nameTextField;
    private JLabel nameLabel;
    private JTextField receivePaymentTextField;
    private boolean Promotion = false;
    private JTextField ColorTextField;
    private JLabel ColorLabel;
    private ArrayList<Clothes> Stock = new ArrayList<>();

    public POSGUI(String Name) {// โค้ดใน constructor สำหรับกำหนดค่าเริ่มต้นและรับค่าNameมาจากclass login
        setTitle("POS");// กำหนดชื่อหัวเรื่องของหน้าต่าง GUI เป็น "POS"
        setSize(450, 500);// กำหนดขนาดของหน้าต่าง GUI เป็นความกว้าง 450 พิกเซลและความสูง 500 พิกเซล.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// กำหนดการกระทำที่เกิดขึ้นเมื่อผู้ใช้ปิดหน้าต่าง GUI
                                                       // ให้แอปพลิเคชันจบการทำงาน

        receivePaymentTextField = new JTextField("0.0"); // กำหนดการกระทำที่เกิดขึ้นเมื่อผู้ใช้ปิดหน้าต่าง GUI
                                                         // ให้แอปพลิเคชันจบการทำงาน

        nameTextField = new JLabel(Name);// สร้าง JLabel เพื่อแสดงชื่อของลูกค้าที่ถูกส่งมาผ่านพารามิเตอร์ Name
        nameLabel = new JLabel("Staff Name:");

        ColorTextField = new JTextField("Black");// สร้างช่องข้อมูลใน GUI สำหรับรับสีของสินค้าและกำหนดค่าเริ่มต้นให้เป็น
                                                 // "Black"
        ColorLabel = new JLabel("Enter Color:");

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(9, 2));// กำหนดเค้าโครงของ JPanel เป็น GridLayout ที่มี 9 แถวและ 2 คอลัมน์

        JLabel styleLabel = new JLabel("Style:");
        String[] styles = { "TShirt", "Polo", "Jacket" };
        styleComboBox = new JComboBox<>(styles);// สร้าง JComboBox เพื่อให้ผู้ใช้เลือกรายละเอียดเกี่ยวกับสินค้าเช่น สไตล
                                                // , ขนาด, เนื้อผ้า

        JLabel sizeLabel = new JLabel("Size:");
        String[] sizes = { "S (32\" A / 23\" B) + 50Bath", "M (34\" A / 24\" B) + 100Bath",
                "L (36\" A / 25\" B) + 150Bath", "XL (38\" A / 26\" B) + 200Bath" };
        sizeComboBox = new JComboBox<>(sizes);// สร้าง JComboBox เพื่อให้ผู้ใช้เลือกรายละเอียดเกี่ยวกับสินค้าเช่น ขนาด

        JLabel fabricLabel = new JLabel("Fabric:");
        String[] fabrics = { "Cotton +300Bath", "Satin +350Bath", "Nylon +400Bath" };
        fabricComboBox = new JComboBox<>(fabrics);// สร้าง JComboBox เพื่อให้ผู้ใช้เลือกรายละเอียดเกี่ยวกับสินค้าเช่น
                                                  // เนื้อผ้า

        addItemButton = new JButton("Add Clothes in Cart");// เมื่อคลิกจะเพิ่มสินค้าลงในตะกร้า
        addItemButton.setBackground(Color.decode("#6C83AB"));
        addItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String setColor = ColorTextField.getText();
                if (setColor != null) {
                    setColor = setColor.toUpperCase();
                    String selectedStyle = (String) styleComboBox.getSelectedItem();
                    String selectedSize = extractSize((String) sizeComboBox.getSelectedItem());
                    String selectedFabric = (String) fabricComboBox.getSelectedItem();

                    Clothes newItem = null;
                    if (selectedStyle.equals("TShirt")) {
                        newItem = new TShirt(1, selectedSize, setColor, getFabricStringToInt(selectedFabric));
                    } else if (selectedStyle.equals("Polo")) {
                        newItem = new Polo(2, selectedSize, setColor, getFabricStringToInt(selectedFabric));
                    } else if (selectedStyle.equals("Jacket")) {
                        newItem = new Jacket(3, selectedSize, setColor, getFabricStringToInt(selectedFabric));
                    }

                    if (newItem != null) {
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

                        newItem.setSizeCharges(sizeCharges);
                        newItem.setFabricCharges(fabricCharges);
                        newItem.setOverAllCharges(sizeCharges + fabricCharges);

                        Stock.add(newItem);
                        billTextArea.setText("");
                        for (int i = 0; i < Stock.size(); i++) {
                            billTextArea.append(ShowAllClothes(i));
                        }
                        double totalBill = calculateTotalBill();
                        billTextArea.append("Total : " + totalBill + " Bath\n");
                    }
                } else if (setColor == null) {
                    JOptionPane.showInputDialog("Please Input Color");
                }
            }
        });

        removeItemButton = new JButton("Remove Clothes in Cart by Index");
        removeItemButton.setBackground(Color.decode("#F4A7AF"));// เมื่อคลิกจะลบสินค้าออกจากตะกร้าตามIndexที่ระบุ.
        removeItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!Stock.isEmpty()) {
                    if (Stock.size() != 0) {
                        String selectedIndexStr = JOptionPane.showInputDialog("Enter the index of the item to remove:");

                        if (selectedIndexStr != null) {
                            try {
                                int selectedIndex = Integer.parseInt(selectedIndexStr);
                                if (selectedIndex >= 0 && selectedIndex < Stock.size()) {
                                    Stock.remove(selectedIndex);
                                    billTextArea.setText("");
                                    for (int i = 0; i < Stock.size(); i++) {
                                        billTextArea.append(ShowAllClothes(i));
                                    }
                                    double totalBill = calculateTotalBill();
                                    billTextArea.append("Total : " + totalBill + " Bath\n");
                                    JOptionPane.showMessageDialog(null, "Removed Cloth: " + selectedIndex);
                                } else {
                                    JOptionPane.showMessageDialog(null, "Invalid index. Please enter a valid index.");
                                }
                            } catch (NumberFormatException ex) {
                                JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid number.");
                            }
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Your Cart is Empty");
                }
            }
        });

        JButton editItemButton = new JButton("Edit Clothes in Cart");// เมื่อคลิกจะแก้ไขข้อมูลของสินค้าในตะกร้าตามIndexที่ระบุ
        editItemButton.setBackground(Color.decode("#6C83AB"));
        editItemButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (!Stock.isEmpty()) {
                    if (Stock.size() != 0) {
                        String selectedIndexText = JOptionPane.showInputDialog("Enter the index of the item to edit:");
                        if (selectedIndexText != null) {
                            try {
                                Clothes clothes = null;
                                int selectedIndex = Integer.parseInt(selectedIndexText);
                                if (selectedIndex >= 0 && selectedIndex < Stock.size()) {
                                    Clothes selectedItem = Stock.get(selectedIndex);

                                    String selectedSize = selectedItem.getSize();
                                    String selectedFabric = getFabricIntToString(selectedItem.getFabric());
                                    String selectedStyle = getStyleIntToString(selectedItem.getStyle());
                                    String selectedColor = selectedItem.getColor();

                                    String newSizeInput = JOptionPane.showInputDialog("Enter new size:", selectedSize);
                                    if (newSizeInput != null) {
                                        String newSize = CheckWrongSize(newSizeInput, selectedSize);
                                        String newFabricInput = JOptionPane.showInputDialog("Enter new fabric:",
                                                selectedFabric);
                                        if (newFabricInput != null) {
                                            String newFabric = CheckWrongFabric(newFabricInput, selectedFabric);
                                            String newStyleInput = JOptionPane.showInputDialog("Enter new style:",
                                                    selectedStyle);
                                            if (newStyleInput != null) {
                                                String newStyle = CheckWrongStyle(newStyleInput, selectedStyle);
                                                String newColor = JOptionPane.showInputDialog("Enter new Color:",
                                                        selectedColor);
                                                if (newColor != null) {

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
                                                    if (selectedStyle == newStyle) {
                                                        selectedItem.setColor(newColor);
                                                        selectedItem.setFabric(getFabricStringToInt(newFabric));
                                                        selectedItem.setSize(newSize);
                                                        CheckBill(selectedIndex);

                                                    } else if (selectedStyle != newStyle) {
                                                        if (newStyle.equalsIgnoreCase("TShirt")) {
                                                            clothes = new TShirt(1, newSize, newColor,
                                                                    getFabricStringToInt(newFabric));
                                                            Stock.add(selectedIndex, clothes);
                                                        } else if (newStyle.equalsIgnoreCase("Polo")) {
                                                            clothes = new Polo(2, newSize, newColor,
                                                                    getFabricStringToInt(newFabric));
                                                            Stock.add(selectedIndex, clothes);
                                                        } else if (newStyle.equalsIgnoreCase("Jacket")) {
                                                            clothes = new Jacket(3, newSize, newColor,
                                                                    getFabricStringToInt(newFabric));
                                                            Stock.add(selectedIndex, clothes);
                                                        }
                                                        Stock.remove(selectedIndex + 1);
                                                        CheckBill(selectedIndex);
                                                    }
                                                    billTextArea.setText("");
                                                    for (int i = 0; i < Stock.size(); i++) {
                                                        billTextArea.append(ShowAllClothes(i));
                                                    }
                                                    double totalBill = calculateTotalBill();
                                                    billTextArea.append("Total : " + totalBill + " Bath\n");
                                                }
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
                } else {
                    JOptionPane.showMessageDialog(null, "Your Cart is Empty");
                }
            }
        });

        clearBillButton = new JButton("Clear Clothes in Cart");// เมื่อคลิกจะลบสินค้าทั้งหมดออกจากตะกร้า.
        clearBillButton.setBackground(Color.decode("#F4A7AF"));
        clearBillButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (!Stock.isEmpty()) {
                    Stock.clear();
                    billTextArea.setText("");
                    setPromotion(false);
                } else {
                    JOptionPane.showMessageDialog(null, "Your Cart is Empty");
                }
            }
        });

        payButton = new JButton("Pay");
        payButton.setBackground(Color.decode("#4aed78"));// เมื่อคลิกจะทำการชำระเงินและแสดงใบเสร็จ.
        payButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Stock.size() != 0) {
                    String staffName = nameTextField.getText();
                    String receivedPaymentText = receivePaymentTextField.getText();
                    LocalDateTime currentTime = LocalDateTime.now();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                    String formattedTime = currentTime.format(formatter);
                    if (!staffName.isEmpty() && !receivedPaymentText.isEmpty()) {
                        try {
                            double totalBill = calculateTotalBill();
                            double receivedPayment = Double.parseDouble(receivedPaymentText);
                            if (totalBill <= receivedPayment) {
                                JDialog dialog = new JDialog();
                                dialog.setTitle("Bill");
                                dialog.setModal(true);
                                dialog.setSize(260, 1000);
                                JTextArea billTextArea = new JTextArea();
                                billTextArea.setEditable(false);
                                billTextArea.setWrapStyleWord(true);
                                billTextArea.setLineWrap(true);

                                JScrollPane scrollPane = new JScrollPane(billTextArea);
                                dialog.add(scrollPane);
                                String promotion = "Not have Promotion";
                                String billMessage = "----------------------Bill------------------------------\n"
                                        + "                        OnlyCloths              \n" +
                                        "              " + formattedTime +

                                        "\n--------------------------------------------------------\n\n";
                                if (getPromotion()) {
                                    promotion = "Discount 10 %";
                                }
                                for (int i = 0; i < Stock.size(); i++) {
                                    billMessage += ShowAllClothes(i);
                                }
                                billMessage += "Total : " + totalBill + " Bath\n"
                                        + "Promotion: " + promotion
                                        + "\nDiscount: " + calculatePromotion(totalBill) +
                                        "\nTotal Amount: " + (totalBill - calculatePromotion(totalBill)) + " Bath\n"
                                        + "Cash: " + receivedPayment + " Bath"
                                        + "\nChange: "
                                        + calculateChangeMoney(totalBill, receivedPayment, getPromotion()) + " Bath" +
                                        "\n\nStaff Name : " + staffName + "\nTrack Number: "
                                        + ThreadLocalRandom.current().nextInt()
                                        + "\n\n--------------------Thank You---------------------";

                                billTextArea.setText(billMessage);
                                dialog.setLocationRelativeTo(null);
                                dialog.setResizable(true);
                                dialog.setVisible(true);
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

        JButton promotionButton = new JButton("Use Promotion");// เมื่อคลิกจะใช้โปรโมชั่นโดยให้ผู้ใช้ป้อนรหัสโปรโมชั่น.
        promotionButton.setBackground(Color.decode("#00B9AF"));
        promotionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!getPromotion()) {
                    String setPromotion = JOptionPane.showInputDialog("Enter Code to get Promotion:");
                    if (setPromotion != null) {
                        try {
                            if (setPromotion.equalsIgnoreCase("CanIGetGradeA")) {
                                setPromotion(true);
                                JOptionPane.showMessageDialog(null, "Promotion Correct, You get 10% discount");
                            } else {
                                setPromotion(false);
                                JOptionPane.showMessageDialog(null, "Promotion Not Correct");
                            }
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(null,
                                    "Promotion Not Correct");
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "You get Promotion already");
                }
            }
        });

        billTextArea = new JTextArea();
        billTextArea.setEditable(false);
        panel.setBackground(Color.decode("#D9E1E2"));

        panel.add(styleLabel);// แอดทุกปุ่มเข้าไปใน panel ทั้งหมด
        panel.add(styleComboBox);
        panel.add(sizeLabel);
        panel.add(sizeComboBox);
        panel.add(fabricLabel);
        panel.add(fabricComboBox);
        panel.add(ColorLabel);
        panel.add(ColorTextField);
        panel.add(nameLabel);
        panel.add(nameTextField);
        panel.add(new JLabel("Receive Payment:"));
        panel.add(receivePaymentTextField);
        panel.add(addItemButton);
        panel.add(editItemButton);
        panel.add(removeItemButton);
        panel.add(clearBillButton);
        panel.add(payButton);
        panel.add(promotionButton);

        JScrollPane scrollPane = new JScrollPane(billTextArea);
        billTextArea.setBackground(Color.decode("#EBECE7"));

        getContentPane().add(panel, BorderLayout.NORTH);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
        setLocationRelativeTo(null);
    }

    private String extractSize(String selectedSize) {// เมธอดที่ใช้สำหรับดึงขนาดเสื้อผ้าจากข้อความที่ถูกเลือก
        String[] parts = selectedSize.split(" \\+");
        return parts[0].trim();
    }

    private double calculateTotalBill() { // เมธอดที่ใช้สำหรับคำนวณราคารวมของสินค้าทั้งหมดในตะกร้า
        double totalBill = 0.0;
        for (Clothes item : Stock) {
            totalBill += item.getOverAllCharges();
        }
        return totalBill;
    }

    private double calculateChangeMoney(double totalBill, double receivedPayment, boolean promotion) {// เมธอดที่ใช้สำหรับคำนวณเงินทอนที่ต้องส่งให้กับลูกค้า
        if (promotion) {
            return receivedPayment - (totalBill - (totalBill * 10) / 100);
        } else {
            return receivedPayment -= totalBill;
        }
    }

    public double calculatePromotion(double Overall) {// เมธอดที่ใช้สำหรับคำนวณส่วนลดจากโปรโมชั่นขึ้นอยู่กับราคารวมทั้งหมด
        if (getPromotion()) {
            return (Overall * 10) / 100;
        } else {
            return 0;
        }
    }

    public String ShowAllClothes(int index) { // สร้างสตริงที่แสดงรายละเอียดของสินค้า
        Clothes clothes = Stock.get(index);
        return "Clothes: " + index + "\n" + "Style: " + getStyleIntToString(clothes.getStyle()) + "\n" + "Size: " +
                clothes.getSize() + "\nColor: " + clothes.getColor() + "\n" + "Fabric: "
                + getFabricIntToString(clothes.getFabric()) + "\n" + "Price: "
                + clothes.getOverAllCharges() + " Bath\n\n";
    }

    public String getFabricIntToString(int getFabric) {// แปลงค่าเลขจำนวนเต็มเป็นสตริงสำหรับเนื้อผ้า
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

    public String getStyleIntToString(int getStyle) {// แปลงค่าเลขจำนวนเต็มเป็นสตริงสำหรับสไตล์เสื้อผ้า
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

    public void setStyleStringToInt(String getStyle, Clothes Select) {// แปลงสตริงที่แทนสไตล์เสื้อผ้าเป็นค่าเลขจำนวนเต็มและกำหนดในอ็อบเจกต์
                                                                      // Clothes
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

    public void setFabricStringToInt(String getFabric, Clothes Select) {// แปลงสตริงที่แทนเนื้อผ้าเป็นค่าเลขจำนวนเต็มและกำหนดในอ็อบเจกต์
                                                                        // Clothes
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

    public int getFabricStringToInt(String getFabric) {// แปลงสตริงที่แทนเนื้อผ้าเป็นค่าเลขจำนวนเต็ม
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
        return Fabric;
    }

    public String ShowStyle(int Select) {// แปลงค่าเลขจำนวนเต็มเป็นสตริงสำหรับสไตล์เสื้อผ้า
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

    public String ShowFabric(int Select) {// แปลงค่าเลขจำนวนเต็มเป็นสตริงสำหรับเนื้อผ้า
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

    private void CheckBill(int index) {// คำนวณและกำหนดค่าค่าใช้จ่ายรวมสำหรับสินค้าในตะกร้า
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

    private String CheckWrongSize(String newSize, String selectedSize) {// ตรวจสอบและแก้ไขขนาดเสื้อผ้าที่ป้อนหากไม่ตรงกับรูปแบบที่มีในร้าน
        if (newSize.equalsIgnoreCase("S") || newSize
                .equalsIgnoreCase("M") || newSize.equalsIgnoreCase("L")
                || newSize.equalsIgnoreCase("XL") || newSize.equalsIgnoreCase("S (32\" A / 23\" B)")
                || newSize.equalsIgnoreCase("M (34\" A / 24\" B)") || newSize.equalsIgnoreCase("L (36\" A / 25\" B)")
                || newSize.equalsIgnoreCase("XL (38\" A / 26\" B)")) {
            return newSize;
        } else {
            JOptionPane.showMessageDialog(null, "Size is wrong Input Size agin");
            String newSizeInput = JOptionPane.showInputDialog("Enter new size:", selectedSize);
            if (newSizeInput != null) {
                newSize = CheckWrongSize(newSizeInput, selectedSize);
            }
            return newSize;
        }
    }

    private String CheckWrongFabric(String newFabric, String selectedFabric) { // ตรวจสอบและแก้ไขเนื้อผ้าที่ป้อนหากไม่ใช่ประเภทที่มีในร้าน
        String fabric = newFabric.toUpperCase();
        if (fabric.contains("COTTON") || fabric.contains("SATIN")
                || fabric.contains("NYLON")) {
            return newFabric;
        } else {
            JOptionPane.showMessageDialog(null, "Fabric is wrong Input Fabric agin");
            String newFabricInput = JOptionPane.showInputDialog("Enter new Fabric:", selectedFabric);
            if (newFabricInput != null) {
                newFabric = CheckWrongFabric(newFabricInput, selectedFabric);
            }
            return newFabric;
        }
    }

    private String CheckWrongStyle(String newStyle, String selectedStyle) {// ตรวจสอบและแก้ไขสไตล์เสื้อผ้าที่ป้อนหากไม่ใช่ประเภทที่มีในร้าน
        String style = newStyle.toUpperCase();
        if (style.contains("TSHIRT") || style.contains("POLO")
                || style.contains("JACKET")) {
            return newStyle;
        } else {
            JOptionPane.showMessageDialog(null, "Style is wrong Input Style agin");
            String newStyleInput = JOptionPane.showInputDialog("Enter new Style:", selectedStyle);
            if (newStyleInput != null) {
                newStyle = CheckWrongStyle(newStyleInput, selectedStyle);
            }
            return newStyle;
        }
    }

    public void setPromotion(boolean Promotion) { // เมธอด setter สำหรับตัวแปร flag โปรโมชั่น
        this.Promotion = Promotion;
    }

    public boolean getPromotion() {// เมธอด getter สำหรับตัวแปร flag โปรโมชั่น
        return this.Promotion;
    }

}
