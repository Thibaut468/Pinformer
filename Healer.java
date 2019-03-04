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
    }
    
    public void heal(Joueur joueur, int vieDonnee){
        joueur.setVie(vieDonnee+joueur.getVie());
        
    }

}
