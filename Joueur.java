import java.awt.*;

public class Joueur extends Personnage {

    public Joueur(int x, int y, int largeur, int hauteur, int vie, int vitesse, Jeu jeu){
        super(x, y, largeur, hauteur, vie, vitesse, jeu);
    }
    public void tick() {

        depX=0;
        depY=0;

        if(super.jeu.haut && vie<10) vie++;
        if(super.jeu.bas && vie>0) vie--;
        if(super.jeu.gauche) super.depX=-vitesse;
        if(super.jeu.droite) super.depX=vitesse;

        super.chute();
        if(super.jeu.haut) super.saut(30);
        super.deplacement();

    }

    public void aff(Graphics g) {
        g.drawImage(this.jeu.textures.joueur,super.x,super.y,null);

        switch(vie){
            case 0:
                g.drawImage(this.jeu.textures.l0,30,10,null);
                break;
            case 1:
                g.drawImage(this.jeu.textures.l1,30,10,null);
                break;
            case 2:
                g.drawImage(this.jeu.textures.l2,30,10,null);
                break;
            case 3:
                g.drawImage(this.jeu.textures.l3,30,10,null);
                break;
            case 4:
                g.drawImage(this.jeu.textures.l4,30,10,null);
                break;
            case 5:
                g.drawImage(this.jeu.textures.l5,30,10,null);
                break;
            case 6:
                g.drawImage(this.jeu.textures.l6,30,10,null);
                break;
            case 7:
                g.drawImage(this.jeu.textures.l7,30,10,null);
                break;
            case 8:
                g.drawImage(this.jeu.textures.l8,30,10,null);
                break;
            case 9:
                g.drawImage(this.jeu.textures.l9,30,10,null);
                break;
            case 10:
                g.drawImage(this.jeu.textures.l10,30,10,null);
                break;
            default :
                break;
        }
    }
}
