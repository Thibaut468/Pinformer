import java.awt.*;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

public class MonstreContact extends Monstre {
    
    private int positionInitialeX;
    private int positionFinaleX;
    private int vitesse;
    boolean sens = false;

	public MonstreContact(Jeu jeu, int x, int y, int largeur, int hauteur, int vie, int vieE, double vitesseR, int vitesse, Color couleur, int positionFinaleX) {
		super(jeu, x, y, largeur, hauteur, vieE, vie, vitesseR, couleur);
        this.vitesse=vitesse;
        this.positionFinaleX=positionFinaleX;
        this.positionInitialeX=this.x;
	}
	
	
    public void tick(){
        this.deplacement();
    
	}

    public void aff(Graphics g){
		//g.setColor(this.couleur);
		//g.fillOval(super.x,super.y,super.largeur,super.hauteur);
		g.drawImage(super.jeu.textures.monstre_contact,x,y,null);
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
    
     
    public void deplacement () {
       //à faire en boucle
       
        if(!sens){
            super.x+=vitesse;
        }
        
        if(super.x>=positionFinaleX){
			sens=true;
		}

        if(sens){
            super.x-=vitesse;
        }
        
        if(super.x<=positionInitialeX){
			sens=false;
		}
		
    }
}

