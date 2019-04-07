import java.awt.*;

public abstract class Bloc  {

    protected chargementImage textures = new chargementImage();
    protected int largeur = 64;
    protected int hauteur = 32;
    public int x;
    public int y;
    protected int id;
    protected int vitesse;
    protected boolean inactif = false;

    public Bloc(int x, int y, int id, int vitesse){

        if(id==24 || id == 25){ //Si le bloc est un pic
            largeur = 30;
            hauteur = 25;
        } else if(id == 26){ //Si le bloc est le starter de départ
            hauteur = 23;
        } else if(id == 27){ //Si le bloc est un canon
            largeur = 62;
            hauteur = 43;
        }

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
            case 9 :
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
            case 13 :
                g.drawImage(textures.p_arrivee,x,y,null);
                break;
            case 14 :
                g.drawImage(textures.p_arrivee,x,y,null);
                break;
            case 15 :
                g.drawImage(textures.p_arrivee,x,y,null);
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
