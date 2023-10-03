package File;

public class Jacket implements Clothes{
    private String Size;
    private String Color;
    private int Fabric;
    private int Style;
    private int FabricCharges;
    private int SizeCharges;
    private int OverAllCharges;

    // นี่คือ constructor ของคลาส Jacket ที่ใช้สำหรับการสร้างอ็อบเจ็กต์ของคลาส Jacket
    public Jacket(int Style, String Size, String Color, int Fabric) {
        setColor(Color);
        setSize(Size);
        setFabric(Fabric);
        setStyle(Style);
    }

    @Override
    // ใช้สำหรับการกำหนดขนาดของเสื้อ (Size) เมื่อผู้ใช้ต้องการแก้ไขขนาดของเสื้อ
    public void setSize(String Size) {
        this.Size = Size;
    }

    @Override
    // ใช้สำหรับการกำหนดสีของเสื้อ (Color) เมื่อผู้ใช้ต้องการแก้ไขสีของเสื้อ
    public void setColor(String Color) {
        this.Color = Color;
    }

    @Override
    // ใช้สำหรับการกำหนดชนิดของผ้า (Fabric) เมื่อผู้ใช้ต้องการแก้ไขชนิดของผ้า
    public void setFabric(int fabric) {
        this.Fabric = fabric;
    }

    @Override
    // ใช้สำหรับการกำหนดรูปแบบของผ้า (Style) เมื่อผู้ใช้ต้องการแก้ไขรูปแบบของเสื้อ
    public void setStyle(int Style) {
        this.Style = Style;
    }

    @Override
    // ใช้สำหรับการกำหนดค่าค่าใช้จ่ายเพิ่มเติมที่เกี่ยวข้องกับผ้า(Fabric Charges) เมื่อผู้ใช้ต้องการแก้ไขค่านี้
    public String getSize() {
        return this.Size;
    }

    @Override
    // ใช้สำหรับการกำหนดค่าค่าใช้จ่ายเพิ่มเติมที่เกี่ยวข้องกับขนาด (Size Charges) เมื่อผู้ใช้ต้องการแก้ไขค่านี้
    public String getColor() {
        return this.Color;
    }

    @Override
    // ใช้สำหรับการกำหนดค่ารวมของค่าใช้จ่าย (Overall Charges) เมื่อผู้ใช้ต้องการแก้ไขค่านี้
    public int getFabric() {
        return this.Fabric;
    }
    
    @Override
    // ใช้สำหรับรับค่าขนาดของเสื้อ (Size) ที่ถูกตั้งค่า
    public int getStyle() {
        return this.Style;
    }

    @Override
    // ใช้สำหรับรับค่าสีของเสื้อ (Color) ที่ถูกตั้งค่า
    public void setFabricCharges(int FabricCharges) {
        this.FabricCharges = FabricCharges;
    }

    @Override
    // ใช้สำหรับรับค่าชนิดของผ้า (Fabric) ที่ถูกตั้งค่า
    public void setSizeCharges(int SizeCharges) {
        this.SizeCharges = SizeCharges;
    }

    @Override
    // ใช้สำหรับรับค่ารูปแบบของผ้า (Style) ที่ถูกตั้งค่า
    public int getFabricCharges() {
        return this.FabricCharges;
    }

    @Override
    // ใช้สำหรับรับค่าค่าใช้จ่ายเพิ่มเติมที่เกี่ยวข้องกับผ้า (Fabric Charges)ที่ถูกตั้งค่า
    public int getSizeCharges() {
        return this.SizeCharges;
    }

    @Override
    // ใช้สำหรับรับค่าค่าใช้จ่ายเพิ่มเติมที่เกี่ยวข้องกับขนาด (SizeCharges)ที่ถูกตั้งค่า
    public void setOverAllCharges(int OverAllCharges) {
        this.OverAllCharges = OverAllCharges;
    }

    @Override
    // ใช้สำหรับรับค่ารวมของค่าใช้จ่าย (Overall Charges) ที่ถูกตั้งค่า
    public int getOverAllCharges() {
        return OverAllCharges;
    }
}
