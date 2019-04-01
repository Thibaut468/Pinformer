import java.awt.*;

public class Joueur extends Personnage {

    private int compt = 0;
    private boolean pulse = false;
    private int x0;
    private int y0;
    private int v0;
    private int a;
    private int starterVitesse;
    private int yBuff;
    
    private boolean starterStarted = false;

    public Joueur(Jeu jeu, int x, int y, int largeur, int hauteur, int vie, int vitesse){
        super(jeu,0, x, y, largeur, hauteur, vie, vitesse);
        this.x0=x;
        this.y0=y;
        this.yBuff = y;
        a=x0;
    }
    public void tick() {

        //depX=0;
        //depY=0;

        if(jeu.getPropulsion()){
            deplacementInit(starterVitesse);
            //System.out.println("Propulseee");
        }
        if(jeu.getInit() && !jeu.getPropulsion()) {
            //System.out.println("Jeu");
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
     */

     public void deplacementInit(int vitesse) {
     /*
     * il faut que le x0 et le y0 mis en paramètre soit une plateforme en bas et qu'elle soit de x0 = 700 et y0=750, choisi toujours comme point de départ initial du joueur !
     * il faut trouver un moyen d'arêter le saut lorsque la y du joueur soit égal à l'un des y d'une plateforme qu'il rencontre
     * vraiment les collision idk :
      */
		if(!starterStarted){
			v0 = vitesse*9;
			System.out.println("Times Clicked Perso : "+vitesse);
			starterStarted = true;
		}
        
        double alphar= Math.PI/2.5;
        
        int xmax = (int) (x0-(v0*v0)/(10*Math.sin(2*alphar)));
        //System.out.println(xmax);
        
                
        int pas = 1;
        for (int i=0; i<3; i++) {
            if(a>xmax && y<720){    ///il faut rajouter ici une condition sur le x et y du joueur, arrêter la boucle lorsque le x et y du joueur soit égal à celui d'une plateforme + que ce soit une plateforme assez haute, style : y = 50
                //System.out.println("Tick Propulsion");
                int y = (int)(y0+(+9.8/(2*(v0*v0)*Math.pow((Math.cos(alphar)),2))*(x-x0)*(x-x0)+(x-x0)*Math.tan(alphar)));
                //System.out.println(yBuff-y);
                this.setX(a);
                this.setY(y); //le signe est changé par rapport au word car le repère en fenetre java est inversé (y vers le bas)
                a-=pas;
                //System.out.println("y="+y);
                //System.out.println("a="+a);
            } else {
                //System.out.println("Fin propulsion");
                jeu.setPropulsion(false);
            }
            if(yBuff-y<0){
                jeu.setPropulsion(false);
                depX=-15;
            }
            yBuff=y;

        }
    }

    public void setStarterVitesse(int v){ this.starterVitesse = v;}
}
