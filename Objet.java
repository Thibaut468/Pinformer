import java.awt.*;

public abstract class Objet extends Entite {

    public Objet(Jeu jeu, int id, int x, int y, int largeur, int hauteur){
        super(jeu, id, x,y,largeur,hauteur);
        
    }
    
    public abstract void aff(Graphics g);


}
