import java.awt.*;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class PlateformeMobile extends Bloc{

    private int vitesse;
    private int positionInitialeX;
    private int positionInitialeY;
    private int positionFinaleX;
    private int id;
    boolean sens = false;

    
     public PlateformeMobile(int x, int y, int id, int vitesse, int positionFinaleX){
        
        super(x,y,id, vitesse);
        this.id=id;
        this.vitesse=vitesse;
        this.positionFinaleX=positionFinaleX;
        this.positionInitialeX=super.x;
        this.positionInitialeY=super.y;
        
    }
    
    public void tick(){
        this.deplacement();
    }

    
    public void deplacement () {
              // Plateforme mobile horizontale
        if(!sens && (id==5 || id==6 || id==7 || id==8)){
            super.x+=vitesse;
        }
        
        if(super.x>=positionFinaleX && (id==5 || id==6 || id==7 || id==8)){
                        sens=true;
                }

        if(sens && (id==5 || id==6 || id==7 || id==8)){
            super.x-=vitesse;
        }
        // Plateforme mobile vertiale
        if(super.x<=positionInitialeX && (id==5 || id==6 || id==7 || id==8)){
                        sens=false;
                }
                
                if(!sens && (id==9 || id==10 || id==11 || id==12)){
            super.y+=vitesse;
        }
        
        if(super.y>=positionFinaleX && (id==9 || id==10 || id==11 || id==12)){
                        sens=true;
                }

        if(sens && (id==9 || id==10 || id==11 || id==12)){
            super.y-=vitesse;
        }
        
        if(super.y<=positionInitialeY && (id==9 || id==10 || id==11 || id==12)){
                        sens=false;
                }

		
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
