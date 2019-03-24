import java.awt.*;
import java.util.LinkedList;

public abstract class Monstre extends Personnage {

    protected int ralenti;
	protected int degats; // si c'est un montre gentil, on met un vie enlevé négatif comme ca dans la méthode perd vie ca fera - (un nombre négatif) ce qui rajoutera des vies)
    protected double depX;
    protected double depY;
    protected int positionInitialeX;
    protected int positionFinaleX;
    protected boolean sens = false;
    protected int cpt;

	
	public Monstre(Jeu jeu, int id, int x, int y, int largeur, int hauteur, int vitesse, int degats, int ralenti, int positionFinaleX) {
        super(jeu, id, x, y, largeur, hauteur, 1, vitesse); // j'ai mis vitesse = 0 car les monstres sont fixes
		this.degats = degats;
		this.ralenti = ralenti;
        this.depX=vitesse;
        this.depY=0;
        this.positionInitialeX=x;
        this.positionFinaleX=positionFinaleX;
	}

	public void action(Personnage p, String s){
        if(s.equals("X") && cpt >=240){ //Monstre touche le joueur
            touche(p);
            cpt=0;
        }

        if(s.equals("Y")){ //Joueur touche le monstre
            estTouche();
        }
    }

    public abstract void tick();

    public abstract void aff(Graphics g);

    public void deplacement () {

        if(vitesse!=0) {
            if (super.x >= positionFinaleX) {
                sens = true;
            }
            if (super.x <= positionInitialeX) {
                sens = false;
            }
            if(sens) depX=-vitesse;
            if(!sens) depX=vitesse;
        }

        if(!blocCollisionX()) super.x+=depX;
    }

    public boolean blocCollisionX(){
        int testX=0;
        if(this.depX>0){ //Déplacement droite
            testX = (int) (x+largeur+depX);
            if(!this.jeu.getMonde().blocDetectionX(testX, y, hauteur)){ //pas de collisions
                return false;
            } else { //collision
                sens=!sens;
                return true;
            }
        } else if(this.depX<0){ //Déplacement gauche
            testX = (int)(x+depX);
            if(!this.jeu.getMonde().blocDetectionX(testX, y, hauteur)){
                return false;
            } else { //collision
                sens=!sens;
                return true;
            }
        }
        return false;
    }
	
	public void perdVie(Personnage j) {
		int nouvelleVie = j.getVie() - this.degats;
		j.setVie(nouvelleVie);
		
	}
	
	public void perdVitesse(Personnage j) {
		double nouvelleVitesse = j.getVitesse() - this.ralenti;
		j.setVitesse(nouvelleVitesse);
		
	}

    private void touche(Personnage j){
        perdVie(j);
        perdVitesse(j);
    }

    private void estTouche(){
        vie=0;
        inactif=true;
    }
}
