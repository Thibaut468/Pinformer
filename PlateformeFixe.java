import java.awt.*;
import java.awt.image.BufferedImage;

public class PlateformeFixe extends Bloc {

    private int ID;
    public PlateformeFixe(int x, int y, int id){
        super(x, y, id, 0);
        this.ID=id;
    }
}
