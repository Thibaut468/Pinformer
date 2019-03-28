import java.awt.*;

public class Joueur extends Personnage {

    private int compt = 0;

    public Joueur(Jeu jeu, int x, int y, int largeur, int hauteur, int vie, int vitesse){
        super(jeu,0, x, y, largeur, hauteur, vie, vitesse);
    }
    public void tick() {

        //depX=0;
        //depY=0;
        if(jeu.getInit()) {
            if (!(compt == 0)) compt--;

            ///if(super.jeu.haut && vie<10) vie++;
            //if(super.jeu.bas && vie>0) vie--;

            if (super.jeu.gauche) super.depX = -vitesse;
            if (super.jeu.droite) super.depX = vitesse;
            if (!super.jeu.gauche && !super.jeu.droite) super.glissade = true;

            super.chute();
            super.frottements();
            if (super.jeu.haut && !jumping && compt == 0) {
                super.saut(25);
                compt += 15;
            }
            //if(super.enTrainDeSauter) super.saut(60,8);
            //if(super.jeu.haut && !enTrainDeSauter) super.saut(60,8);
            super.deplacement();
        }
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
    
    /* il faut récupérér nombre de fois cliqué : c'est dans fenetre vitesse, et c'est timesclicked je ne sais pas comment faire déplacer le personnage initialement déso je vous laisse faire, mais toute la méthode est écrite ici il suffit de faire l'affichage graphique!
     * en gros j'arrive pas a faire en sorte qu'il apparaisse pas directement sur une des plateformes
     * de plus des conditions de collisions avec les plateformes
     public void deplacementInit(int vitesse, int x0, int y0, Monde monde) {
     * 
     * il faut que le x0 et le y0 mis en paramètre soit une plateforme en bas et qu'elle soit de x0 = 700 et y0=750, choisi toujours comme point de départ initial du joueur !
     * il faut trouver un moyen d'arêter le saut lorsque la y du joueur soit égal à l'un des y d'une plateforme qu'il rencontre
     * vraiment les collision idk :/
        int v0 = vitesse;
        double alphar= Math.PI/4;
        
        int xmax = (int) (x0-(v0*v0)/(10*Math.sin(2*alphar)));
        
        int pas = 1;
        int a=x0
        
        While a>xmax et   il faut rajouteur ici une condition sur le x et y du joueur, arrêter la boucle lorsque le x et y du joueur soit égal à celui d'une plateforme + que ce soit une plateforme assez haute, style : y = 50
            joueur.setX()=a;
            joueur.setY()= (int)  (y0-(+10/(2*(v0*v0)*Math.pow((Math.cos(alphar)),2))*x*x+x*Math.tan(alphar))); le signe est changé par rapport au word car le repère en fenetre java est inversé (y vers le bas)
            a=a-pas
        }
    }*/
        
        
}
