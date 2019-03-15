import java.awt.*;
import java.awt.image.BufferedImage;

<<<<<<< HEAD
public class PlateformeFixe extends Plateforme {
    
    public PlateformeFixe(int x, int y, int largeur, int hauteur, int nbreImage){
        
        super(x,y,largeur,hauteur, nbreImage);
        
    }
    
    public void tick(){
        
    }
=======
>>>>>>> 86bfb1402e8b7fdb516cddead0b8844360e5518c

public class PlateformeFixe extends Bloc {

    private final int ID = 1;
    public PlateformeFixe(int x, int y){
        super(x, y, 1);
    }
}
