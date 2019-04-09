import java.awt.*;

public class Balle extends Entite {

    private int yinit;
    private int dt;
    private boolean sens;
    private int vitesse;
    private int xinit;
    private int direction;
    private int degats;
    private boolean inactif = false;


    public Balle(Jeu jeu, int x, int y, boolean sensMonstre, int degats) {
        super(jeu, 100, x, y, 20, 20);
        this.yinit = y + 1;
        this.xinit = x;
        this.sens = sensMonstre;
        this.vitesse = 1;
        this.dt = 1;
        this.direction = 1;
        this.degats = degats;
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

    private void deplacement() {
        //il faut calculer diff pour trouver une equation ou le projectile est lancé à x=0
        // g= 9.81
        // cos(alpha) = racine2/2 donc cos carré = 1/2

        int diff;
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