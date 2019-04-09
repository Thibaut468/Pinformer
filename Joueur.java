import java.awt.*;

//Cette classe gère l'entité Joueur, et notamment ses déplacements et l'utilisation des différentes touches.
//Le starter et la trajectoire de ce dernier est indiqué ci dessous.

public class Joueur extends Personnage {

    private int compt = 0;
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

        if(jeu.getPropulsion()){ //Si le starter est lancé, on exécute la trajectoire
            deplacementInit();
        }
        if(jeu.getInit() && !jeu.getPropulsion()) { //Si le jeu est initialisé et que la propulsion est terminée, on laisse les commandes au joueur
            if (!(compt == 0)) compt--; //Timer de saut du personnage

            if (super.jeu.gauche) super.depX = -vitesse; //SI TOUCHE DE GAUCHE
            if (super.jeu.droite) super.depX = vitesse; //SI TOUCHE DE DROITE
            if (!super.jeu.gauche && !super.jeu.droite) super.glissade = true; //ON ACTIVE LES FROTTEMENTS SI L'UTILISATEUR N'APPUIE PLUS POUR FREINER LE PERSONNAGE

            super.chute(); //Application de la gravité
            super.frottements(); //Application des frottements
            if (super.jeu.haut && !jumping && compt == 0) { //Si le personnage ne saute pas, et que son timer de saut est réinitialisé
                super.saut(25); //Saut
                compt += 15; //On relance un timer
            }

            super.deplacement(); //On effectue tous les déplacements
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


     public void deplacementInit() {

		if(!starterStarted){ //Si le starter n'est pas encore démarré
			v0 = starterVitesse*9; //On définit la vitesse en fonction des appuis sur ESPACE
			starterStarted = true; //On lance le starter
		}
        
        double alphar= Math.PI/2.5;
        
        int xmax = (int) (x0-(v0*v0)/(10*Math.sin(2*alphar))); //On prévoit le x max

         //MISE EN PLACE DE L'EQUATION DE LA TRAJECTOIRE
        int pas = 1;
        for (int i=0; i<3; i++) {
            if(a>xmax && y<720){
                int y = (int)(y0+(+9.8/(2*(v0*v0)*Math.pow((Math.cos(alphar)),2))*(x-x0)*(x-x0)+(x-x0)*Math.tan(alphar)));
                this.setX(a);
                this.setY(y); //le signe est changé par rapport au word car le repère en fenetre java est inversé (y vers le bas)
                a-=pas;
            } else {
                jeu.setPropulsion(false);
            }
            if(yBuff-y<0){
                jeu.setPropulsion(false);
                depX=-starterVitesse*2; //Coefficient pour l'atterissage
            }
            yBuff=y;

        }
    }

    /** GETTERS AND SETTERS **/

    public void setStarterVitesse(int v){ this.starterVitesse = v;}

    public int getStarterVitesse(){ return this.starterVitesse; }
}
