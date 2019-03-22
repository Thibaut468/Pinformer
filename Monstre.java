import java.awt.*;
import java.util.LinkedList;

public abstract class Monstre extends Personnage {
	
	protected int vieEnlevee; // si c'est un montre gentil, on met un vie enlevé négatif comme ca dans la méthode perd vie ca fera - (un nombre négatif) ce qui rajoutera des vies)
	protected double vitesseRalenti; // pareil comme commentaire pour la vitesse de la ligne d'avant
    protected double depX;
    protected double depY;
    protected Color couleur;
    protected boolean apparent;

	
	public Monstre(Jeu jeu, int x, int y, int largeur, int hauteur, int vieE, int vie, double vitesseR, Color couleur) {
        super(jeu, x, y, largeur, hauteur, vie, 0); // j'ai mis vitesse = 0 car les monstres sont fixes
		this.vieEnlevee=vieE;
        this.depX=0;
        this.depY=0;
        this.couleur = couleur;
        this.vitesseRalenti = vitesseR;
        this.apparent = true;
	}
    

    public abstract void tick();

    public abstract void aff(Graphics g);
	
	public void perdVie(Joueur j) {
		int nouvelleVie = j.getVie() - this.vieEnlevee;
		j.setVie(nouvelleVie);
		
	}
	
	public void perdVitesse(Joueur j) {
		double nouvelleVitesse = j.getVitesse() - this.vitesseRalenti;
		j.setVitesse(nouvelleVitesse);
		
	}
    
    public void monstrePerdvie(){
        if (this.vie>0) {
        this.vie = this.vie-1;
    } else {
        this.apparent = false;
        // il faut faire disparaitre le monstre de l'écran
    }
}

    //méthode qui retourne vrai si le monstre est mort, si on recoit vrai, il faut l'effacer de l'affichage graphique
    
    public boolean monstreEstMort(){
        return this.vie == 0;
    }
    
    
		
		
}
