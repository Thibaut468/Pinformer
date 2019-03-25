import java.awt.*;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Balle extends Entite {

    private int id = 100;
    private int yinit;
    private int dt;
    private boolean sens;
    private int ymax;
    private int vitesse;
    private int xinit;
    private int diff;
    private int compta = 0;
    private int direction;
    private int degats;
    private int ralenti;
    private boolean inactif = false;


    public Balle(Jeu jeu, int x, int y, boolean sensMonstre, int degats, int ralenti) {
        super(jeu, 100, x, y, 20, 20);
        this.yinit = y + 1;
        this.xinit = x;
        this.sens = sensMonstre;
        this.ymax = y - 100;
        this.vitesse = 1;
        this.dt = 1;
        this.direction = 1;
        this.degats = degats;
        this.ralenti = ralenti;
    }

    public void action(Personnage p, String s) {
        p.setVie(p.getVie() - degats);
        this.inactif=true;
    }

    public void tick() {
        this.deplacement();
    }


    public void aff(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(super.x, super.y, super.largeur, super.hauteur);
    }

    public void deplacement() {
        //il faut calculer diff pour trouver une equation ou le projectile est lancé à x=0
        // g= 9.81
        // cos(alpha) = racine2/2 donc cos carré = 1/2

        if (!sens) {
            super.x = (int) (super.x - dt * vitesse);
            diff = Math.abs(super.x - xinit);
            //System.out.println("x1=   "+ super.x);
        } else {
            super.x = (int) (super.x + dt * vitesse);
            diff = Math.abs(xinit - super.x);
            //System.out.println("x2=   "+ super.x);
        }
        super.y = (int) ((direction * 9.81 * (diff * diff)) / (10 * vitesse * vitesse) + diff + yinit);
        //System.out.println("y2=   "+ super.y);
    }

    public boolean getInactif(){
        return inactif;
    }

}