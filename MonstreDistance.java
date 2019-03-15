import java.awt.*;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
public class MonstreDistance extends Monstre {
	
    private int compteur = 0;
    private int intervalleTire;
    private int positionInitialeX;
    private int positionFinaleX;
    private int vitesse;
    boolean sens = false;
    private int xb;
    private int yb;
	
	public MonstreDistance(int intervalleTire, int x, int y, int largeur, int hauteur, int vie, int vieE, int vitesse, int vitesseR, Color couleur, Jeu jeu, int positionFinaleX) {
		super(x, y, largeur, hauteur, vieE, vie, vitesseR, couleur, jeu);
        this.intervalleTire=intervalleTire;
        this.vitesse=vitesse;
        this.positionFinaleX=positionFinaleX;
        this.positionInitialeX=this.x;
        
	}
	
	
    public void tick(){
        
        this.deplacement();
        
        
    }
    
    public Balle creationBalle() {
        System.out.println("a creee'");
        xb =this.x;
        yb =  this.y;
        Balle balle = new Balle(xb, yb, 30, 30, this.sens);
        return balle;
    }
    

    public void aff(Graphics g){
		g.setColor(this.couleur);
		g.fillOval(super.x,super.y,super.largeur,super.hauteur);
	}
		
		
	 // condition de déplacement de balle, voir si elle touche
	 // méthode qui renvoie si la balle a touché de le personnage ou pas
		
 
	public void aTouche(Joueur j){
		//if (this.balleAtouche(j) == true) {
			this.perdVie(j);
			this.perdVitesse(j);
		
	}
    
    public void monstreDistanceEstTouche(Joueur j) {
        // du coup ici pas besoin du fait que c'est l'un ou l'autre qui attaque, parce que si le joueur et le monstre se touche ca veut
        // direct dire que le joueur attaque le monstre, vu que ce monstre ne peut pas se battre en corps à corps
        // et ses balles iront trop loin pour toucher le personnage auquel il est collé
        if ((this.getX() == j.getX())&&(this.getY() == j.getY())) {
            this.monstrePerdvie();
        }
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
    
    public boolean compt() {
        compteur = compteur +1;
        //System.out.println(compteur);
        
        if (compteur > intervalleTire+200 ) {
        compteur = 0;
        return true;
        } else{
        return false;
    }
    }
    
    public boolean renvoiSens() {
        return sens;
    }
}


