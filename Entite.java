import java.awt.*;
import java.util.LinkedList;

public abstract class Entite {

    protected int x;
    protected int y;
    protected int largeur;
    protected int hauteur;
    protected Jeu jeu;
    protected int id;
    protected boolean inactif = false;

    public Entite(Jeu jeu, int id, int x, int y, int largeur, int hauteur){
        this.x=x;
        this.y=y;
        this.largeur=largeur;
        this.hauteur=hauteur;
        this.jeu = jeu;
        this.id = id;
    }

    public abstract void action(Personnage p, String s);

    public abstract void tick();

    public abstract void aff(Graphics g);
    

    public boolean getInactif(){
        return inactif;
    }

    public int getId(){ return id; }

    public void setX(int x){
        this.x=x;
    }

    public void setY(int y){
        this.y=y;
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

}
