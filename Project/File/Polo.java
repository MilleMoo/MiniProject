package Project.File;

public class Polo implements Clothes{

    private String Size;
    private String Color;
    private int Fabric;
    private int Style;

    public Polo(int Style, String Size, String Color, int Fabric) {
        setColor(Color);
        setSize(Size);
        setFabric(Fabric);
        setStyle(Style);
    }

    @Override
    public void setSize(String Size) {
        this.Size = Size;
    }

    @Override
    public void setColor(String Color) {
        this.Color = Color;
    }

    @Override
    public void setFabric(int fabric) {
        this.Fabric = fabric;
    }

    @Override
    public void setStyle(int Style) {
        this.Style = Style;
    }

    @Override
    public String getSize() {
        return this.Size;
    }

    @Override
    public String getColor() {
        return this.Color;
    }

    @Override
    public int getFabric() {
        return this.Fabric;
    }
    
    @Override
    public int getStyle() {
        return this.Style;
    }
    
}
