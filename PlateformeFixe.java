import java.awt.*;
import java.awt.image.BufferedImage;

public class PlateformeFixe extends Bloc {

    private int id;
    public PlateformeFixe(int x, int y, int id){
        super(x, y, id, 0);
        this.id=id;
    }
}
