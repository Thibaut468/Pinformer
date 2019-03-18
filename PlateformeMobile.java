import java.awt.*;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class PlateformeMobile extends Bloc{

    private int vitesse;
    private int positionInitialeX;
    private int positionFinaleX;
    private int id;
    boolean sens = false;

    
     public PlateformeMobile(int x, int y, int id, int vitesse, int positionFinaleX){
        
        super(x,y,id);
        this.id=id;
        this.vitesse=vitesse;
        this.positionFinaleX=positionFinaleX;
        this.positionInitialeX=super.x;
        
    }
    
    public void tick(){
        this.deplacement();
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

    public boolean solide(){
        return true;
    }

}
