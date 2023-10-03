package File;

public class Polo extends Jacket{
    // นี่คือ constructor ของคลาส Polo ที่ใช้สำหรับการสร้างอ็อบเจ็กต์ของคลาส Polo
    public Polo(int Style, String Size, String Color, int Fabric) {
        // ใช้ super เพื่อเรียก constructor ของคลาส Jacket ที่มีการรับพารามิเตอร์เหล่านี้ 
        //โดยทำให้คลาส Polo สืบทอดคุณสมบัติและเมธอดจากคลาส
        super(Style, Size, Color, Fabric);
    }
}
