import java.awt.*;

public class Joueur extends Personnage {

    private int compt = 0;

    public Joueur(Jeu jeu, int x, int y, int largeur, int hauteur, int vie, int vitesse){
        super(jeu,0, x, y, largeur, hauteur, vie, vitesse);
    }
    public void tick() {

        //depX=0;
        //depY=0;

        if (!(compt==0)) compt --;

        ///if(super.jeu.haut && vie<10) vie++;
        //if(super.jeu.bas && vie>0) vie--;

        if(super.jeu.gauche) super.depX=-vitesse;
        if(super.jeu.droite) super.depX=vitesse;
        if(!super.jeu.gauche && !super.jeu.droite) super.glissade=true;

        super.chute();
        super.frottements();
        if(super.jeu.haut && !jumping && compt==0){
            super.saut(25);
            compt+=15;
        }
        //if(super.enTrainDeSauter) super.saut(60,8);
        //if(super.jeu.haut && !enTrainDeSauter) super.saut(60,8);
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
