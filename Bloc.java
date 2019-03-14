import java.awt.*;
import java.awt.image.BufferedImage;

public class Bloc  {

    protected chargementImage textures = new chargementImage();
    protected final int LARGEUR = 64;
    protected final int HAUTEUR = 64;
    public int x;
    public int y;
    protected int id;

    public Bloc(int x, int y, int id){
        this.x=x;
        this.y=y;
        this.id=id;
    }

    public void tick(){

    }

    public void aff(Graphics g){
        switch(id){
            case 1 :
                g.drawImage(textures.plateforme,x,y,null);
                break;
            default :
                break;
        }
    }

    public boolean solide(){
        return true;
    }
}
