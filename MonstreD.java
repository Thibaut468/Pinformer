import java.awt.*;
import java.util.Iterator;
import java.util.LinkedList;

public class MonstreD extends Monstre {

    private int id;
    private final int intervalTir = 50;
    private LinkedList<Balle> balles = new LinkedList<Balle>();
    private int compt = 0;
    private Monde monde;

    public MonstreD(Jeu jeu, int id, int x, int y, int vitesse, int degat, int ralenti, int positionFinaleX, Monde monde){
        super(jeu, id, x, y, 48, 48, vitesse, degat, ralenti, positionFinaleX);
        this.ralenti = ralenti;
        this.id=id;
        falling=false;
        this.monde = monde;
    }

    public void tick() {
        deplacement();
        this.compt++;
        cpt++;
        if(this.compt>=intervalTir){
            this.compt=0;
            balles.add(new Balle(jeu, x, y, sens, degats, ralenti));
        }

        //Update des balles
        for (Balle b : balles) {
            b.tick();
        }

        //Collision joueur
        balleDetectionX(x, y, 20,this.jeu.getMonde().getJoueur());
        balleDetectionY(y, x,20,this.jeu.getMonde().getJoueur());

        //Suppresion
        Iterator<Balle> it = balles.iterator();
        while(it.hasNext()){
            if(it.next().getInactif()) it.remove();
        }


    }

    public void aff(Graphics g) {
        g.drawImage(this.jeu.textures.monstre_contact,x,y,null);
        for (Balle b : balles) {
            b.aff(g);
        }
    }
    
    public LinkedList<Balle> renvBalles() {
        return this.balles;
    }

    public boolean balleDetectionX(int x, int y, int h, Personnage p){
        for(Balle b : balles){
            if(b.x<p.x && (b.x+b.largeur)>p.x && (p.y+h)>b.y && p.y<(b.y+b.hauteur) && !b.getInactif()){
                b.action(p, "X");
                return true;
            }
        }
        return false;
    }

    public boolean balleDetectionY(int y, int x, int l, Personnage p){
        for(Balle b : balles){
            if(b.y<p.y && (b.y+b.hauteur)>p.y && (p.x+l)>b.x && p.x<(b.x+b.largeur) && !b.getInactif()){
                b.action(p,"Y");
                return true;
            }
        }
        return false;
    }
}

