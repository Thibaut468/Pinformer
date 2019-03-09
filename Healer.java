import java.awt.*;

public class Healer extends Objet {
    
    private int vieDonnee;
    
    public Healer (int x, int y,int largeur, int hauteur, int vieDonnee){
        super(x,y,largeur,hauteur);
        this.vieDonnee = vieDonnee;
    }

    public void tick(){

    }

    public void aff(Graphics g){
        g.setColor(new Color(255, 119, 128));
        g.fillRect(x, y, hauteur, largeur);
    }
    
    public void heal(Joueur joueur, int vieDonnee){
        joueur.setVie(vieDonnee+joueur.getVie());
        
    }

}
