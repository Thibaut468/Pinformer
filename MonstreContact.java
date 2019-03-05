import java.awt.*;

public class MonstreContact extends Monstre {
	
	public MonstreContact(int x, int y, int largeur, int hauteur, int vieE, int vitesse, int vitesseR, Color couleur) {
		super(x, y, largeur, hauteur, vieE, vitesse, vitesseR, couleur);
	}
	
	
    public void tick(){
	}

    public void aff(Graphics g){
		g.setColor(this.couleur);
		g.fillOval(super.x,super.y,super.largeur,super.hauteur);
	}
		
		
	public void estTouche(Joueur j){
		if ((j.getX() == this.x)&&(j.getY()== this.y)){ // provisoire je sais pas comment on va modéliser le contact?
			this.perdVie(j);
			this.perdVitesse(j);
		}
	}
}
