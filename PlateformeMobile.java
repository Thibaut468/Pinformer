import java.awt.*;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class PlateformeMobile extends Entite{

    private int vitesse;
    private int positionInitialeX;
    private int positionFinaleX;

    
     public PlateformeMobile(int x, int y, int largeur, int hauteur, int vitesse, int positionFinaleX, int positionInitialeX){
        
        super(x,y,largeur,hauteur);
        this.vitesse=vitesse;
        this.positionFinaleX=positionFinaleX;
        this.positionInitialeX=positionInitialeX;
        
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
       
       int xinf = super.x;
       
        if(super.x!=positionFinaleX && xinf<super.x){
            xinf=super.x;
            super.x+=vitesse;
            
        }
        if(super.x!=positionInitialeX && xinf>super.x){
            xinf=super.x;
            super.x-=vitesse;
        }
    }

    public boolean solide(){
        return true;
    }

}
