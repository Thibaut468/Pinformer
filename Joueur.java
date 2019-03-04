import java.awt.*;

public class Joueur extends Personnage {

    private Jeu jeu;

    public Joueur(int x, int y, int largeur, int hauteur, int vie, int vitesse, Jeu jeu){
        super(x, y, largeur, hauteur, vie, vitesse);
        this.jeu=jeu;
    }
    public void tick() {

        depX=0;
        depY=0;

        if(this.jeu.haut) super.depY=-vitesse;
        if(this.jeu.bas) super.depY=vitesse;
        if(this.jeu.gauche) super.depX=-vitesse;
        if(this.jeu.droite) super.depX=vitesse;

        super.deplacement();
    }

    public void aff(Graphics g) {
        g.fillRect(super.x,super.y,super.largeur,super.hauteur);
    }
}
