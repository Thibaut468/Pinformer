import java.awt.*;
import java.util.Iterator;
import java.util.LinkedList;

public class Canon extends Bloc {

    private int id;
    private int direction; //1 regard droite, -1 regard gauche
    private LinkedList<Missile> missiles = new LinkedList<Missile>();
    private int degats;
    private int interval;
    private int cpt;
    private Jeu jeu;

    public Canon(Jeu jeu, int id, int x, int y, int direction, int degats, int interval) {
        super(x,y,id, 0);
        //62 43
        this.jeu = jeu;
        this.id = id;
        this.direction = direction;
        this.degats = degats;
        this.interval = interval;
        this.cpt=interval;
    }

    @Override
    public void tick() {
        cpt++;
        for (Missile m : missiles){
            m.tick();
        }

        if(cpt>=interval){
            //System.out.println("TIR");
            cpt=0;
            if(direction == 1) missiles.add(new Missile(jeu,101, x+largeur, y+10, degats, direction));
            else missiles.add(new Missile(jeu,101, x-40, y+10, degats, direction));
        }

        //Collision joueur
        missileDetectionX(x, y, 20,this.jeu.getMonde().getJoueur());
        missileDetectionY(y, x,20,this.jeu.getMonde().getJoueur());

        //Suppresion
        Iterator<Missile> it = missiles.iterator();
        while(it.hasNext()){
            if(it.next().getInactif()) it.remove();
        }
    }

    @Override
    public void aff(Graphics g) {
        if(direction==1) g.drawImage(jeu.textures.canonD, x, y, null);
        else g.drawImage(jeu.textures.canonG,x,y,null);
        for (Missile m : missiles){
            m.aff(g);
        }
    }

    public boolean missileDetectionX(int x, int y, int h, Personnage p){
        for(Missile m : missiles){
            if(m.x<p.x && (m.x+m.largeur)>p.x && (p.y+h)>m.y && p.y<(m.y+m.hauteur) && !m.getInactif()){
                m.action(p, "X");
                return true;
            }
        }
        return false;
    }

    public boolean missileDetectionY(int y, int x, int l, Personnage p){
        for(Missile m : missiles){
            if(m.y<p.y && (m.y+m.hauteur)>p.y && (p.x+l)>m.x && p.x<(m.x+m.largeur) && !m.getInactif()){
                m.action(p,"Y");
                return true;
            }
        }
        return false;
    }
}
