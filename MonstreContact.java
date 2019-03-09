import java.awt.*;

public class MonstreContact extends Monstre {
	
	public MonstreContact(int x, int y, int largeur, int hauteur, int vie, int vieE, double vitesseR, Color couleur, Jeu jeu) {
		super(x, y, largeur, hauteur, vieE, vie, vitesseR, couleur, jeu);
	}
	
	
    public void tick(){
	}

    public void aff(Graphics g){
		g.setColor(this.couleur);
		g.fillOval(super.x,super.y,super.largeur,super.hauteur);
	}
		
		
	public void joueurEstTouche(Joueur j){
		if ((j.getX() == this.x)&&(j.getY()== this.y)){ // provisoire je sais pas comment on va modéliser le contact?
			this.perdVie(j);
			this.perdVitesse(j);
		}
	}
    
    public void monstreContactEstTouche(){
        // condition que c'est le joueur qui attaque le monstre pas l'inverse
        this.monstrePerdvie();
    }
}
