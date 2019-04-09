import java.awt.*;

//Classe abstraite générale englobant approximativement toutes les entités de notre jeu, dont les blocs et les objets, mais aussi le personnage et les monstres
public abstract class Entite {

    protected int x;
    protected int y;
    protected int largeur;
    protected int hauteur;
    protected Jeu jeu;
    private int id;
    protected boolean inactif = false;

    public Entite(Jeu jeu, int id, int x, int y, int largeur, int hauteur){
        this.x=x;
        this.y=y;
        this.largeur=largeur;
        this.hauteur=hauteur;
        this.jeu = jeu;
        this.id = id;
    }

    public abstract void action(Personnage p, String s); //Va permettre à chaque entité d'effectué une action sur le joueur

    public abstract void tick(); //Update de chaque entité

    public abstract void aff(Graphics g); //Affichage de chaque entité


    /** GETTERS AND SETTERS **/

    public boolean getInactif(){
        return inactif;
    }

    public int getId(){ return id; }

    public int getX(){ return this.x; }

    public int getY(){ return this.y; }

    public void setX(int x){
        this.x=x;
    }

    public void setY(int y){
        this.y=y;
    }
}
