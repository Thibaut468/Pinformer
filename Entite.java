package fr.pinformer;

import java.awt.*;

public abstract class Entite {

    protected int x;
    protected int y;
    protected int largeur;
    protected int hauteur;

    public Entite(int x, int y, int largeur, int hauteur){
        this.x=x;
        this.y=y;
        this.largeur=largeur;
        this.hauteur=hauteur;
    }

    public abstract void tick();

    public abstract void aff(Graphics g);


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
