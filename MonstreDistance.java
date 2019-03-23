import java.awt.*;

public class MonstreDistance extends Monstre {
	//private Balle balle;
	
	private double vitesseBalle;
	private int duree;
	
	public MonstreDistance(int x, int y, int largeur, int hauteur, int vieE, int vitesse, int vitesseR, Color couleur, double VitessB, int duree) {
		super(x, y, largeur, hauteur, vieE, vitesse, vitesseR,couleur);
		//this.balle = new Balle();
		this.vitesseBalle = vitesseB;
		this.duree=duree;
	}
	
	
    public void tick(){
	}

    public void aff(Graphics g){
		g.setColor(this.couleur);
		g.fillOval(super.x,super.y,super.largeur,super.hauteur);
	}
		
		
	 //nombre de click ? force donné : modification de la position de la balle
	 // condition de déplacement de balle, voir si elle touche
	 // méthode qui renvoie si la balle a touché de le personnage ou pas
		
	public boolean lanceBalle(Joueur j){
		double temps = 0;
		for(int i = 0; i<this.duree; i++) {
			this.balle.setX() = vitesseB*(1.O/sqrt(2))*temps;
			this.balle.setY()= -0.5*9.81*temps*temps+VitesseB*(1.O/sqrt(2))*temps;
		}
		
		if ((balle.getX() == j.getX())&&(balle.getY() == j.getY())) {
			return true;
		} else {
			return false;
		}
	}
	
	public void aTouche(Joueur j){
		if (this.lanceBalle() == true) {
			this.perdVie(j);
			this.perdVitesse(j);
		}
	}
}

