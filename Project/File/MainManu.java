package Project.File;

import java.util.*;

public abstract class MainManu {
    private double Money;
    private int Index;
    private String Size;
    private String Color;
    private int Fabric;
    private int FabricCharges;
    private int SizeCharges;
    private int Select;
    private int count = 0;
    private Scanner sc = new Scanner(System.in);
    private ArrayList<Clothes> Stock = new ArrayList<>();
    public abstract void Bill();
    private String Style = "--------Manu----------\n1.)TShirt\n2.)Polo\n3.)Jacket\nChoose the style of shirt you want to buy : ";

    public MainManu() {
    }
    
    public void MainManu1() {//เลือกสินค้าว่าจะมีลักษณะเป็นยังไง
            System.out.print(Style);
            Clothes Item = null;
            this.Select = Integer.parseInt(sc.nextLine());
            switch (this.Select) {
                case 1:
                    Input();
                    Item = new TShirt(this.Select, this.Size, this.Color, this.Fabric);
                    Stock.add(Item);
                    break;
                case 2:
                    Input();
                    Item = new Polo(this.Select, this.Size, this.Color, this.Fabric);
                    Stock.add(Item);
                    break;
                case 3:
                    Input();
                    Item = new Jacket(this.Select, this.Size, this.Color, this.Fabric);
                    Stock.add(Item);
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
            ShowValueInArrayList();
        if (this.count >= 1 ) {
            MainManu2();
        }
    }

    private void MainManu2() {//มีไว้แก้ไขหรือเพิ่มสินค้าก่อนคิดเงิน
        System.out.print("\n--------Manu----------\n" +
                "1.)Choose AntherOne\n" +
                "2.)EditStyle\n" +
                "3.)EditSize\n" +
                "4.)EditColor\n" +
                "5.)EditFabric\n" +
                "6.)Remove out\n" +
                "7.)Remove All Clothes in Stock\n"+
                "8.)ready to pay\n" +
                "Choose Function : ");
        switch (Integer.parseInt(sc.nextLine())) {
            case 1:
                MainManu1();
                break;
            case 2:
                EditStyle();
            break;
            case 3:
                EditSize();
            break;
            case 4:
                EditColor();
            break;
            case 5:
                EditFabric();
            break;
            case 6:
                RemoveClothes();
            break;
            case 7:
                RemoveAll();
                break;
            case 8:
                Bill();
                return;

            default:
                System.out.println("Invalid choice");
                break;
        }
    }
    
    
    public void Input() {//รับค่าขนาดเสื้อสีและชนิดของผ้า
        System.out.print("\nSize A 32\" B 23\" : S + 50Bath\n" +
                "Size A 34\" B 24\" : M + 100Bath\n" +
                "Size A 36\" B 25\" : L + 150Bath\n" +
                "Size A 38\" B 26\" : XL + 200Bath\n" +
                "Please choose Size : ");
        this.Size = sc.nextLine();
        if(this.Size.equalsIgnoreCase("S") || this.Size.equalsIgnoreCase("M")
                || this.Size.equalsIgnoreCase("L") || this.Size.equalsIgnoreCase("XL")) {//Check Size select Not pass
            this.Size = this.Size.toUpperCase();
        System.out.print("\nPlease choose Color : ");
        this.Color = sc.nextLine();
        this.Color = this.Color.toUpperCase();
        System.out.print("\n\n1.) Cotton + 300Bath\n" +
                "2.) Satin + 350Bath\n" +
                "3.) Nylon + 400Bath\n" +
                "Please choose Fabric : ");
        this.Fabric = Integer.parseInt(sc.nextLine());
        if(this.Fabric >= 4 || this.Fabric <=  0){//Check Fabric select Not pass
            System.out.println("You input Fabric wrong please input one more time");
                Input();
            }
        this.count++;
        } else {
            System.out.println("You input wrong Size please input one more time");
            Input();
        }
    }
    
    public String ShowStyle(int Select) {//แปลงตัวเลขเป็นตัวอักษรเพื่อที่จะได้รู้รูปแบบของผ้า
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

    public String ShowFabric(int Select) {//แปลงตัวเลขเป็นตัวอักษรเพื่อที่จะได้รู้ชนิดของผ้า
        String Fabric;
        switch (Select) {
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

    public void ShowValueInArrayList() {//แสดงค่าทั้งหมดในArraylist
        System.out.println("Clothes in your the List: ");
        this.Money = 0;
        for (int i = 0; i < Stock.size(); i++) {
            Clothes item = Stock.get(i);
            CheckBill(i);
            System.out.println("Clothes: " + (i+1) + " Price: " + item.getOverAllCharges() + " Bath");
            System.out.println("Style: " + ShowStyle(item.getStyle()));
            System.out.println("Size: " + item.getSize() + " Price: " + item.getSizeCharges() + " Bath");
            System.out.println("Color: " + item.getColor());
            System.out.println("Fabric: " + ShowFabric(item.getFabric()) + " Price: " + item.getFabricCharges() + " Bath" + "\n");
            this.Money = this.Money + item.getFabricCharges() + item.getSizeCharges();
        }
        System.out.println("total price : "+this.Money);
    }
    
    public void EditSize() {//Edit Size in Index
        System.out.println("\nSize A 32\" B 23\" : S +50Bath\n" +
                "Size A 34\" B 24\" : M +100Bath\n" +
                "Size A 36\" B 25\" : L +150Bath\n" +
                "Size A 38\" B 26\" : XL +2000Bath\n");
        System.out.print("Please input Size : ");
        this.Size = sc.nextLine();
        if(this.Size.equalsIgnoreCase("S") || this.Size.equalsIgnoreCase("M")
        || this.Size.equalsIgnoreCase("L") || this.Size.equalsIgnoreCase("XL") ){//Check Size select Not pass
        System.out.print("Please input Clothes you want to set : ");
        this.Index = Integer.parseInt(sc.nextLine()) - 1;
        if (this.Index < -1 || this.Index > this.count - 1) {//Check index select Not pass
                System.out.println("You input Index wrong please input one more time");
                EditSize();
        }
            Clothes item = Stock.get(Index);
            item.setSize(this.Size);
            ShowValueInArrayList();
            MainManu2();
        } else {
            System.out.println("You input wrong Size please input one more time");
            EditSize();
        }
    }

    public void EditColor() {//Edit Color in Index
        System.out.print("Please input Color : ");
        this.Color = sc.nextLine();
        System.out.print("Please input Clothes you want to set : ");
        this.Index = Integer.parseInt(sc.nextLine()) - 1;
        if (this.Index < 1 && this.Index > this.count) {//Check index select pass
            Clothes item = Stock.get(Index);
            item.setColor(this.Color);
            ShowValueInArrayList();
            MainManu2();
        }else {//Check index select Not pass
            System.out.println("You input wrong Index please input one more time");
            EditColor();
        }
    }
    
    public void EditFabric() {//Edit Fabric in Index
        System.out.println("------Fabric------\n" +
                "1.) Cotton +300Bath\n" +
                "2.) Satin + 350Bath\n" +
                "3.) Nylon + 400Bath\n");
        try {
        System.out.print("Please input Fabric : ");
        this.Fabric = Integer.parseInt(sc.nextLine());
        if(this.Fabric >= 4 || this.Fabric <=  0){//Check Fabric select Not pass
                System.out.println("You input Fabric wrong please input one more time");
                EditFabric();
            }
        System.out.print("Please input Clothes you want to set : ");
        this.Index = Integer.parseInt(sc.nextLine()) - 1;
        if (this.Index < -1 || this.Index > this.count - 1) {//Check index select Not pass
                System.out.println("You input Index wrong please input one more time");
                EditFabric();
            }
            Clothes item = Stock.get(Index);
            item.setFabric(this.Fabric);
            ShowValueInArrayList();
            MainManu2();
        } catch (Exception e) {
            System.out.println("Please Don't Input String in this step");
            EditFabric();
        }
    }

    public void RemoveClothes() {//Remove Clothes in Stock
        System.out.print("Please input Clothes you want to Remove : ");
        try{
        this.Index = Integer.parseInt(sc.nextLine()) - 1;
        if(this.Index <= this.count - 1 && this.Index >= 0){//Check index pass
            Stock.remove(Index);
            this.count--;
            ShowValueInArrayList();
        } else {//Check index Not pass
            System.out.println("You input index wrong please input one more time");
            RemoveClothes();
        }
        } catch (Exception e) {
            System.out.println("Please Don't Input String in this step");
            RemoveClothes();
        }
    }

    public void RemoveAll() {//Remove Clothes all in Stock
        this.Stock.clear();
        this.count = 0;
        MainManu1();
    }
    
    public void EditStyle() {//Edit Style in Index
        System.out.println("------Style------\n" +
                "1.)TShirt\n" +
                "2.)Polo\n" +
                "3.)Jacket\n");
        try {
            System.out.print("Please input Style : ");
            this.Select = Integer.parseInt(sc.nextLine());
            if (this.Select >= 4 || this.Select <= 0) {//Check style select Not pass
                System.out.println("You input Style wrong please input one more time");
                EditStyle();
            }
            System.out.print("Please input Clothes you want to set : ");
            this.Index = Integer.parseInt(sc.nextLine()) - 1;
            if (this.Index < -1 || this.Index > this.count - 1) {//Check index select Not pass
                System.out.println("You input Index wrong please input one more time");
                EditStyle();
            }
            Clothes item = Stock.get(Index);
            item.setStyle(Fabric);
            ShowValueInArrayList();
            MainManu2();
        } catch (Exception e) {
            System.out.println("Please Don't Input String in this step");
            EditStyle();
        }
    }
    
    private void CheckBill(int index) {
            this.FabricCharges = 0;
            this.SizeCharges = 0;
            Clothes Item = Stock.get(index);
            switch (Item.getSize()) {
                case "S":
                    this.SizeCharges += 50;
                    break;
                case "M":
                    this.SizeCharges += 100;
                    break;
                case "L":
                    this.SizeCharges += 150;
                    break;
                case "XL":
                    this.SizeCharges += 200;
                    break;
            }
            switch (Item.getFabric()) {
                case 1:
                    this.FabricCharges += 300;
                    break;
                case 2:
                    this.FabricCharges += 350;
                    break;
                case 3:
                    this.FabricCharges += 400;
                    break;
            }
            Item.setFabricCharges(this.FabricCharges);
            Item.setSizeCharges(this.SizeCharges);
            Item.setOverAllCharges(this.FabricCharges + this.SizeCharges);
        }
    }
