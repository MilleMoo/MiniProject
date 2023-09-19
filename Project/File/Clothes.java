package Project.File;

public interface Clothes {
    public void setSize(String Size);// ใช้สำหรับการกำหนดขนาดของเสื้อ (Size) เมื่อผู้ใช้ต้องการแก้ไขขนาดของเสื้อ
    public void setColor(String Color);// ใช้สำหรับการกำหนดสีของเสื้อ (Color) เมื่อผู้ใช้ต้องการแก้ไขสีของเสื้อ
    public void setFabric(int fabric);// ใช้สำหรับการกำหนดชนิดของผ้า (Fabric) เมื่อผู้ใช้ต้องการแก้ไขชนิดของผ้า
    public void setStyle(int Style);// ใช้สำหรับการกำหนดรูปแบบของผ้า (Style) เมื่อผู้ใช้ต้องการแก้ไขรูปแบบของเสื้อ
    public void setFabricCharges(int FabricCharges);// ใช้สำหรับการกำหนดค่าค่าใช้จ่ายเพิ่มเติมที่เกี่ยวข้องกับผ้า
                                                    // (Fabric Charges) เมื่อผู้ใช้ต้องการแก้ไขค่านี้
    public void setSizeCharges(int SizeCharges);// ใช้สำหรับการกำหนดค่าค่าใช้จ่ายเพิ่มเติมที่เกี่ยวข้องกับขนาด (Size
                                                // Charges) เมื่อผู้ใช้ต้องการแก้ไขค่านี้
    public void setOverAllCharges(int OverAllCharges);// ใช้สำหรับการกำหนดค่ารวมของค่าใช้จ่าย (Overall Charges)
                                                      // เมื่อผู้ใช้ต้องการแก้ไขค่านี้
    public String getSize();// ใช้สำหรับรับค่าขนาดของเสื้อ (Size) ที่ถูกตั้งค่า
    public String getColor();// ใช้สำหรับรับค่าสีของเสื้อ (Color) ที่ถูกตั้งค่า
    public int getFabric();// ใช้สำหรับรับค่าชนิดของผ้า (Fabric) ที่ถูกตั้งค่า
    public int getStyle();// ใช้สำหรับรับค่ารูปแบบของผ้า (Style) ที่ถูกตั้งค่า
    public int getFabricCharges();// ใช้สำหรับรับค่าค่าใช้จ่ายเพิ่มเติมที่เกี่ยวข้องกับผ้า (Fabric Charges)
                                  // ที่ถูกตั้งค่า
    public int getSizeCharges();// ใช้สำหรับรับค่าค่าใช้จ่ายเพิ่มเติมที่เกี่ยวข้องกับขนาด (Size Charges)
                                // ที่ถูกตั้งค่า
    public int getOverAllCharges();// ใช้สำหรับรับค่ารวมของค่าใช้จ่าย (Overall Charges) ที่ถูกตั้งค่า
}
