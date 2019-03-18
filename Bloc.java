import java.awt.*;
import java.awt.image.BufferedImage;

public class Bloc  {

    protected chargementImage textures = new chargementImage();
    protected final int LARGEUR = 64;
    protected final int HAUTEUR = 32;
    public int x;
    public int y;
    protected int id;
    protected int vitesse;

    public Bloc(int x, int y, int id, int vitesse){
        this.x=x;
        this.y=y;
        this.id=id;
        this.vitesse = vitesse;
    }

    public void tick(){

    }

    public void aff(Graphics g){
        switch(id){
            case 1:
                g.drawImage(textures.p_entier,x,y,null);
                break;
			case 2:
				g.drawImage(textures.p_gauche,x,y,null);
                break;
            case 3 :
                g.drawImage(textures.p_milieu,x,y,null);
                break;
			case 4 :
				g.drawImage(textures.p_droite,x,y,null);
                break;
            case 5:
                g.drawImage(textures.p_entier,x,y,null);
                break;
			case 6:
				g.drawImage(textures.p_gauche,x,y,null);
                break;
            case 7 :
                g.drawImage(textures.p_milieu,x,y,null);
                break;
			case 8 :
				g.drawImage(textures.p_droite,x,y,null);
                break;
            default :
                break;
        }
    }

    public boolean solide(){
        return true;
    }

    public int getId() { return this.id; }

    public int getVitesse(){ return this.vitesse; }
}
