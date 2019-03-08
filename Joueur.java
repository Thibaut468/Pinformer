import java.awt.*;

public class Joueur extends Personnage {

    public Joueur(int x, int y, int largeur, int hauteur, int vie, int vitesse, Jeu jeu){
        super(x, y, largeur, hauteur, vie, vitesse, jeu);
    }
    public void tick() {

        depX=0;
        depY=0;

        //if(super.jeu.haut) super.depY=-vitesse;
        //if(super.jeu.bas) super.depY=vitesse;
        if(super.jeu.gauche) super.depX=-vitesse;
        if(super.jeu.droite) super.depX=vitesse;

        super.chute();
        if(super.jeu.haut) super.saut(30);
        super.deplacement();

    }

    public void aff(Graphics g) {
        g.drawImage(this.jeu.textures.joueur,super.x,super.y,null);
    }
}
