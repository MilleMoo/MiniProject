package Project.File;

public interface Clothes {
    public void setSize(String Size);//แก้ไขขนาดของเสื้อ

    public void setColor(String Color);//แก้ไขสีของเสื้อ
    
    public void setFabric(int fabric);//แก้ไขชนิดของผ้า

    public void setStyle(int Style);//แก้ไขรูปแบบของผ้า
    public void setFabricCharges(int FabricCharges);
    public void setSizeCharges(int SizeCharges);
    public void setOverAllCharges(int OverAllCharges);

    
    public String getSize();//รับค่าขนาดของเสื้อ
    public String getColor();//รับค่าสีของเสื้อ
    public int getFabric();//รับค่าชนิดของผ้า

    public int getStyle();//รับค่ารูปแบบของผ้า

    public int getFabricCharges();

    public int getSizeCharges();
    public int getOverAllCharges();
}
