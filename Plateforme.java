import java.awt.*;

public abstract class Plateforme extends Entite {

	public int nbreImage;
	
    public Plateforme(int x, int y, int largeur, int hauteur, int nbreImage){
        
        super(x,y,largeur,hauteur);
        this.nbreImage=nbreImage;
        
    }
    
    public abstract void tick();

    public abstract void aff(Graphics g);

}
