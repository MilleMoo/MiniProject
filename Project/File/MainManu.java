package Project.File;

import java.util.*;

public class MainManu {
    private double Money;
    private int Index;
    private String Size;
    private String Color;
    private int Fabric;
    private int Select;
    private int count = 0;
    private Scanner sc = new Scanner(System.in);
    private ArrayList<Clothes> Stock = new ArrayList<>();
    private String Style = "--------Manu----------\n1.)TShirt\n2.)Polo\n3.)Jacket\nChoose the style of shirt you want to buy : ";

    public MainManu() {
        MainManu1();
    }
    
    public void MainManu1() {//เลือกสินค้าว่าจะมีลักษณะเป็นยังไง
            System.out.print(Style);
            Clothes Item = null;
            this.Select = sc.nextInt();
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
                "2.)EditSize\n" +
                "3.)EditColor\n" +
                "4.)EditFabric\n" +
                "5.)Remove out\n" +
                "6.)ready to pay\n" +
                "Choose Function : ");
        switch (sc.nextInt()) {
            case 1:
            MainManu1();
                break;
            case 2:
                EditSize();
                break;
            case 3:
                EditColor();
                break;
            case 4:
                EditFabric();
                break;
            case 5:
                Remove();
                break;
            case 6:

                break;

            default:
                System.out.println("Invalid choice");
                break;
        }
    }
    
    
    public void Input() {//รับค่าขนาดเสื้อสีและชนิดของผ้า
        System.out.print("\nSize A 32\" B 23\" : S\n" +
                "Size A 34\" B 24\" : M\n" +
                "Size A 36\" B 25\" : L\n" +
                "Size A 38\" B 26\" : XL\n" +
                "Please choose Size : ");
        sc.nextLine();
        this.Size = sc.nextLine();
        System.out.print("\nPlease choose Color : ");
        this.Color = sc.nextLine();
        System.out.print("\n\n1.) Cotton\n" +
                "2.) Satin\n" +
                "3.) Nylon\n" +
                "Please choose Fabric : ");
        this.Fabric = sc.nextInt();
        sc.nextLine();
        this.count++;
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
        for (int i = 0; i < Stock.size(); i++) {
            Clothes item = Stock.get(i);
            System.out.println("Clothes: " + count);
            System.out.println("Style: " + ShowStyle(item.getStyle()));
            System.out.println("Size: " + item.getSize());
            System.out.println("Color: " + item.getColor());
            System.out.println("Fabric: " + ShowFabric(item.getFabric()) + "\n");
        }
    }
    
    public void EditSize() {
        sc.nextLine();
        System.out.print("Please input Size : ");
        this.Size = sc.nextLine();
        System.out.print("Please input Clothes you want to set : ");
        this.Index = Integer.parseInt(sc.nextLine()) - 1;
        Clothes item = Stock.get(Index);
        item.setSize(this.Size);
        ShowValueInArrayList();
    }

    public void EditColor() {
        sc.nextLine();
        System.out.print("Please input Color : ");
        this.Color = sc.nextLine();
        System.out.print("Please input Clothes you want to set : ");
        this.Index = Integer.parseInt(sc.nextLine()) - 1;
        Clothes item = Stock.get(Index);
        item.setColor(this.Color);
        ShowValueInArrayList();
    }
    
    public void EditFabric() {
        sc.nextLine();
        System.out.print("Please input Fabric : ");
        this.Fabric = sc.nextInt();
        System.out.print("Please input Clothes you want to set : ");
        this.Index = Integer.parseInt(sc.nextLine()) - 1;
        Clothes item = Stock.get(Index);
        item.setFabric(this.Fabric);
        ShowValueInArrayList();
    }

    public void Remove() {
        System.out.print("Please input Clothes you want to set : ");
        this.Index = Integer.parseInt(sc.nextLine()) - 1;
        Stock.remove(Index);
        ShowValueInArrayList();
    }
}