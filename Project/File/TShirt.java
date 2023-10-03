package File;

public class TShirt extends Jacket {
    // นี่คือ constructor ของคลาส TShirt ที่ใช้สำหรับการสร้างอ็อบเจ็กต์ของคลาส TShirt
    public TShirt(int Style, String Size, String Color, int Fabric) {
        // ใช้ super เพื่อเรียก constructor ของคลาส Jacket ที่มีการรับพารามิเตอร์เหล่านี้ 
        //โดยทำให้คลาส TShirt สืบทอดคุณสมบัติและเมธอดจากคลาส Jacket
        super(Style, Size, Color, Fabric);
    }
}
