import java.awt.*;

public class Balle extends Entite {

    //Attributs
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

    public void action(Personnage p, String s) { //Si le joueur est touché par la balle, on lui enlève de la vie
        p.setVie(p.getVie() - degats);
        this.inactif=true;
    }

    public void tick() {
        this.deplacement(); //On déplace la balle a chaque tick suivant une équation

        //On vérifie si des balles sont hors du cadre, si oui on les rend inactives, ce qui les supprimera puis garbage collector
        if (this.y > 1280+hauteur) inactif = true;
        if (this.y<-hauteur) inactif = true;
    }



    public void aff(Graphics g) { //On affiche la balle (simple rectangle noir)
        g.setColor(Color.black);
        g.fillRect(super.x, super.y, super.largeur, super.hauteur);
    }

    private void deplacement() { //Cette méthode contient une équation permettant de projeter la balle avec une courbe spécifique vers le bas ou le haut.
        //il faut calculer diff pour trouver une equation ou le projectile est lancé à x=0
        // g= 9.81
        // cos(alpha) = racine2/2 donc cos carré = 1/2

        int diff;
        if (!sens) { //Equation dans le sens 1
            super.x = super.x - dt * vitesse;
            diff = Math.abs(super.x - xinit);
        } else { //Equation en sens inverse
            super.x = super.x + dt * vitesse;
            diff = Math.abs(xinit - super.x);
        }
        super.y = (int) ((direction * 9.81 * (diff * diff)) / (10 * vitesse * vitesse) + diff + yinit);
    }

    /** GETTERS AND SETTERS **/

    public boolean getInactif(){
        return inactif;
    }

}