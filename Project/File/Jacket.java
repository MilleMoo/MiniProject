package Project.File;

public class Jacket implements Clothes{
    private String Size;
    private String Color;
    private int Fabric;
    private int Style;
    private int FabricCharges;
    private int SizeCharges;
    private int OverAllCharges;

    public Jacket(int Style, String Size, String Color, int Fabric) {// นี่คือ constructor ของคลาส Jacket
                                                                     // ที่ใช้สำหรับการสร้างอ็อบเจ็กต์ของคลาส Jacket
        setColor(Color);
        setSize(Size);
        setFabric(Fabric);
        setStyle(Style);
    }

    @Override
    public void setSize(String Size) {// ใช้สำหรับการกำหนดขนาดของเสื้อ (Size) เมื่อผู้ใช้ต้องการแก้ไขขนาดของเสื้อ
        this.Size = Size;
    }

    @Override
    public void setColor(String Color) {// ใช้สำหรับการกำหนดสีของเสื้อ (Color) เมื่อผู้ใช้ต้องการแก้ไขสีของเสื้อ
        this.Color = Color;
    }

    @Override
    public void setFabric(int fabric) {// ใช้สำหรับการกำหนดชนิดของผ้า (Fabric) เมื่อผู้ใช้ต้องการแก้ไขชนิดของผ้า
        this.Fabric = fabric;
    }

    @Override
    public void setStyle(int Style) {// ใช้สำหรับการกำหนดรูปแบบของผ้า (Style) เมื่อผู้ใช้ต้องการแก้ไขรูปแบบของเสื้อ
        this.Style = Style;
    }

    @Override
    public String getSize() {// ใช้สำหรับการกำหนดค่าค่าใช้จ่ายเพิ่มเติมที่เกี่ยวข้องกับผ้า(Fabric Charges)
                             // เมื่อผู้ใช้ต้องการแก้ไขค่านี้
        return this.Size;
    }

    @Override
    public String getColor() {// ใช้สำหรับการกำหนดค่าค่าใช้จ่ายเพิ่มเติมที่เกี่ยวข้องกับขนาด (Size Charges)
                              // เมื่อผู้ใช้ต้องการแก้ไขค่านี้
        return this.Color;
    }

    @Override
    public int getFabric() {// ใช้สำหรับการกำหนดค่ารวมของค่าใช้จ่าย (Overall Charges)
                            // เมื่อผู้ใช้ต้องการแก้ไขค่านี้
        return this.Fabric;
    }
    
    @Override
    public int getStyle() {// ใช้สำหรับรับค่าขนาดของเสื้อ (Size) ที่ถูกตั้งค่า
        return this.Style;
    }

    @Override
    public void setFabricCharges(int FabricCharges) {// ใช้สำหรับรับค่าสีของเสื้อ (Color) ที่ถูกตั้งค่า
        this.FabricCharges = FabricCharges;
    }

    @Override
    public void setSizeCharges(int SizeCharges) {// ใช้สำหรับรับค่าชนิดของผ้า (Fabric) ที่ถูกตั้งค่า
        this.SizeCharges = SizeCharges;
    }

    @Override
    public int getFabricCharges() {// ใช้สำหรับรับค่ารูปแบบของผ้า (Style) ที่ถูกตั้งค่า
        return this.FabricCharges;
    }

    @Override
    public int getSizeCharges() {// ใช้สำหรับรับค่าค่าใช้จ่ายเพิ่มเติมที่เกี่ยวข้องกับผ้า (Fabric Charges)ที่ถูกตั้งค่า
        return this.SizeCharges;
    }

    @Override
    public void setOverAllCharges(int OverAllCharges) {// ใช้สำหรับรับค่าค่าใช้จ่ายเพิ่มเติมที่เกี่ยวข้องกับขนาด (Size
                                                       // Charges)ที่ถูกตั้งค่า
        this.OverAllCharges = OverAllCharges;
    }

    @Override
    public int getOverAllCharges() {// ใช้สำหรับรับค่ารวมของค่าใช้จ่าย (Overall Charges) ที่ถูกตั้งค่า
        return OverAllCharges;
    }
}
