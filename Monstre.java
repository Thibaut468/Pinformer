import java.awt.*;

public abstract class Monstre extends Entite {
	
	protected int vieEnlevee; // si c'est un montre gentil, on met un vie enlevé négatif comme ca dans la méthode perd vie ca fera - (un nombre négatif) ce qui rajoutera des vies)
	protected int vitesseRalenti; // pareil comme commentaire pour la vitesse de la ligne d'avant
	protected int vitesse;
    protected int depX;
    protected int depY;
    protected Color couleur;
	
	public Monstre(int x, int y, int largeur, int hauteur, int vieE, int vitesse, int vitesseR, Color couleur) {
		
		super(x, y, largeur, hauteur);
		this.vieEnlevee=vieE;
		this.vitesse=vitesse;
        this.depX=0;
        this.depY=0;
        this.couleur = couleur;
        this.vitesseRalenti = vitesseR;
	}
	
	
	
    public abstract void tick();

    public abstract void aff(Graphics g);
	
	public void perdVie(Joueur j) {
		int nouvelleVie = j.getVie() - this.vieEnlevee;
		j.setVie(nouvelleVie);
		
	}
	
	public void perdVitesse(Joueur j) {
		int nouvelleVitesse = j.getVitesse() - this.vitesseRalenti;
		j.setVitesse(nouvelleVitesse);
		
	}
		
		
}