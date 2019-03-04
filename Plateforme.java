import java.awt.*;

public abstract class Plateforme extends Entite {

    public Plateforme(int x, int y, int largeur, int hauteur){
        
        super(x,y,largeur,hauteur);
        
    }
    
    public abstract void tick();

    public abstract void aff(Graphics g);

}
