import java.awt.*;

public class PlateformeMobile extends Plateforme {

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
        
    }

    public void aff(Graphics g){
        g.fillRect(x,y,largeur,hauteur);
    }
    
    public void deplacement () {
       //à faire en boucle
        while(x!=positionFinaleX){
            x+=vitesse;
            
        }
        while(x!=positionInitialeX){
            x-=vitesse;
        }
    }


}
