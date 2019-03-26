import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Bloc  {

    protected chargementImage textures = new chargementImage();
    protected final int largeur = 64;
    protected final int hauteur = 32;
    public int x;
    public int y;
    protected int id;
    protected int vitesse;
    protected boolean inactif = false;

    public Bloc(int x, int y, int id, int vitesse){
        this.x=x;
        this.y=y;
        this.id=id;
        this.vitesse = vitesse;
    }

    public abstract void tick();

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
                case 9:
                g.drawImage(textures.p_entier,x,y,null);
                break;
            case 10:
                g.drawImage(textures.p_gauche,x,y,null);
                break;
            case 11 :
                g.drawImage(textures.p_milieu,x,y,null);
                break;
            case 12 :
                g.drawImage(textures.p_droite,x,y,null);
                break;
            case 16 :
                g.drawImage(textures.p_ephemere,x,y,null);
            default :
                break;
        }
    }

    public void action(){}

    public boolean solide(){
        return true;
    }

    public int getId() { return this.id; }

    public int getVitesse(){ return this.vitesse; }

    public boolean getInactif() { return this.inactif; }
}
