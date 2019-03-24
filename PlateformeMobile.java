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
        
        super(x,y,id, vitesse);
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
       
<<<<<<< HEAD
        if(!sens){
            super.x+=vitesse;
        }
        
        if(super.x>=positionFinaleX){
=======
        if(!sens ){
            super.x+=vitesse;
        }
        
        if(super.x>=positionFinaleX ){
>>>>>>> parent of d0ceb78... .
			sens=true;
		}

        if(sens){
            super.x-=vitesse;
        }
        
<<<<<<< HEAD
        if(super.x<=positionInitialeX){
			sens=false;
		}
		
=======
        if(super.x<=positionInitialeX ){
			sens=false;
		}
	
>>>>>>> parent of d0ceb78... .
    }

    public boolean solide(){
        return true;
    }

    public int getVitesse(){
         if(sens==false) return vitesse;
         if(sens==true) return -vitesse;
         return 0;
    }

}
