package Project.File;
import java.util.*;
public class POS extends MainManu {
    private String Name;
    private Scanner sc = new Scanner(System.in);

    public POS() {
        System.out.print("Input Staff Name : ");
        this.Name = sc.nextLine();
    }
    @Override
    public void Bill() {
        System.out.println("\n\n\n---------------Bill---------------\n\n----------------------------------");
        ShowValueInArrayList();
        System.out.println("----------------------------------\n"+
                "Staff Name : " + this.Name +
                "\n\n-----------Thank You-----------------------");
        
    }
    
}
