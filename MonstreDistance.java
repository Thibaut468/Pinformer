import java.awt.*;

public class MonstreDistance extends Monstre {
	private Balle balle;
	
	private double vitesseB;
	private int duree;
    private int compteur = 0;
    private int intervalleTire;
	
	public MonstreDistance(int intervalleTire, int xB, int yB, int largB, int hautB, int x, int y, int largeur, int hauteur, int vie, int vieE, double vitesse, double vitesseR, Color couleur, double vitesseB, int duree, Jeu jeu) {
		super(x, y, largeur, hauteur, vieE, vie, vitesseR, couleur, jeu);
		this.balle = new Balle(xB, yB, largB, hautB);
		this.vitesseB = vitesseB;
		this.duree=duree;
        this.intervalleTire=intervalleTire;
	}
	
	
    public void tick(){
        compteur = compteur +1;
        if (compteur > intervalleTire) {
         while (this.monstreEstMort()==false) {
             // il rest envoyer en boucles des balles en boucle chaque 10
		double temps = 0; 
		for(int i = 0; i<this.duree; i++) {
			this.balle.setX((int)(vitesseB*(1.0/Math.sqrt(2))*temps));
			this.balle.setY((int)(-0.5*9.81*temps*temps+vitesseB*(1.0/Math.sqrt(2))*temps));
		}
        }
        compteur = 0;
    }
	}

    public void aff(Graphics g){
		g.setColor(this.couleur);
		g.fillOval(super.x,super.y,super.largeur,super.hauteur);
	}
		
		
	 // condition de déplacement de balle, voir si elle touche
	 // méthode qui renvoie si la balle a touché de le personnage ou pas
		
        public boolean balleAtouche(Joueur j){
		if ((balle.getX() == j.getX())&&(balle.getY() == j.getY())) {
			return true;
		} else {
			return false;
		}
    }


	
	public void aTouche(Joueur j){
		if (this.balleAtouche(j) == true) {
			this.perdVie(j);
			this.perdVitesse(j);
		}
	}
    
    public void monstreDistanceEstTouche(Joueur j) {
        // du coup ici pas besoin du fait que c'est l'un ou l'autre qui attaque, parce que si le joueur et le monstre se touche ca veut
        // direct dire que le joueur attaque le monstre, vu que ce monstre ne peut pas se battre en corps à corps
        // et ses balles iront trop loin pour toucher le personnage auquel il est collé
        if ((this.getX() == j.getX())&&(this.getY() == j.getY())) {
            this.monstrePerdvie();
        }
    }
}

