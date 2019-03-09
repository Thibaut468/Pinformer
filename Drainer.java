import java.awt.*;

public class Drainer extends Objet {
    
    private int vieEnlevee;
    
    public Drainer (int x, int y,int largeur, int hauteur, int vieEnlevee){
        super(x,y,largeur,hauteur);
        this.vieEnlevee = vieEnlevee;
    }

    public void tick(){
    }

    public void aff(Graphics g){
        g.setColor(new Color(76, 76, 255));
        g.fillRect(x, y, hauteur, largeur);
    }
    
    public void heal(Joueur joueur, int vieEnlevee){
        joueur.setVie(joueur.getVie()-vieEnlevee);
        
    }

}
