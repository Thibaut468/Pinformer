import java.awt.*;
import java.awt.Graphics;

public class PlateformeMobile extends Plateforme {

    private int vitesse;
    private int positionInitialeX;
    private int positionFinaleX;
    boolean sens = false;

    
     public PlateformeMobile(int x, int y, int largeur, int hauteur, int vitesse, int positionFinaleX, int nbreImage){
        
        super(x,y,largeur,hauteur, nbreImage);
        this.vitesse=vitesse;
        this.positionFinaleX=positionFinaleX;
        this.positionInitialeX=super.x;
        
    }
    
    public void tick(){
        
        this.deplacement();
    }

    public void aff(Graphics g){
        g.setColor(Color.black);
        g.fillRect(x,y,largeur,hauteur);
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
