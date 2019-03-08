import java.awt.*;

public abstract class Personnage extends Entite {

    protected int vie;
    protected double vitesse;
    protected double depX;
    protected double depY;
    protected Jeu jeu;
    protected final double GRAVITE = 2;
    protected boolean falling = true;
    protected boolean jumping = false;

    public Personnage(int x, int y, int largeur, int hauteur, int vie, double vitesse, Jeu jeu){
        super(x, y, largeur, hauteur);
        this.vie=vie;
        this.vitesse=vitesse;
        this.depX=0;
        this.depY=0;
        this.jeu=jeu;
    }

    public void deplacement(){
        this.deplacementX();
        this.deplacementY();
    }

    public void chute(){
        if(falling || jumping){
            depY+=GRAVITE;
            System.out.println("Gravite car chute : "+falling);
            System.out.println("Gravite car saut : "+jumping);
        }
    }

    public void deplacementX() {
        int testX = 0;
        if (this.depX > 0) {
            testX = (int) (x + largeur + depX);
            if (testX < this.jeu.getLargeur()) {
                super.x += (int)this.depX;
            }
        } else if (this.depX <0){
            testX = (int) (x + depX);
            if (testX > 0) {
                super.x += (int)this.depX;
            }
        }
    }
    public void deplacementY(){
        int testY = 0;
        if (this.depY > 0) {
            testY = (int) (y + hauteur + depY);
            if (testY < this.jeu.getHauteur()) {
                super.y += (int)this.depY;
                falling = true;
                jumping = false;
            } else {
                falling = false;
                jumping = false;
            }
        } else if (this.depY <0){
            testY = (int) (y + depY);
            if (testY > 0) {
                super.y += (int)this.depY;
                jumping = true;
                falling = false;
            } else {
                jumping = false;
                falling = true;
            }
        }
    }

    public void setVie(int v){
        this.vie=v;
    }

    public int getVie(){
        return this.vie;
    }

    public void setVitesse(double vitesse){
        this.vitesse=vitesse;
    }

    public double getVitesse(){
        return this.vitesse;
    }
}
