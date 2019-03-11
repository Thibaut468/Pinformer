import java.awt.*;

public class PlateformeFixe extends Plateforme {
    
    public PlateformeFixe(int x, int y, int largeur, int hauteur, int nbreImage){
        
        super(x,y,largeur,hauteur, nbreImage);
        
    }
    
    public void tick(){
        
    }

    public void aff(Graphics g){
        g.fillRect(super.x,super.y,super.largeur,super.hauteur);
    }
}
