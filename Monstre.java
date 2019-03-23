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
        this.depX=0;
        this.depY=0;
        this.positionInitialeX=x;
        this.positionFinaleX=positionFinaleX;
	}

	public void action(Personnage p, String s){
	    if(cpt>=240){
	        cpt=0;
            if(s.equals("X")){ //Monstre touche le joueur
                touche(p);
            }

            if(s.equals("Y")){ //Joueur touche le monstre
                estTouche();
            }
        }
    }

    public abstract void tick();

    public abstract void aff(Graphics g);

    public void deplacement () {
        if(vitesse!=0) {
            if (!sens) {
                super.x += vitesse;
            }
            if (super.x >= positionFinaleX) {
                sens = true;
            }
            if (sens) {
                super.x -= vitesse;
            }
            if (super.x <= positionInitialeX) {
                sens = false;
            }
        }
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
