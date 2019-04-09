import java.awt.*;
import java.util.Iterator;
import java.util.LinkedList;

public class Canon extends Bloc {

    private int direction; //1 regard droite, -1 regard gauche
    private LinkedList<Missile> missiles = new LinkedList<Missile>();
    private int degats;
    private int interval;
    private int cpt;
    private Jeu jeu;

    public Canon(Jeu jeu, int id, int x, int y, int direction, int degats, int interval) {
        super(x,y,id, 0);
        this.jeu = jeu;
        this.id = id;
        this.direction = direction;
        this.degats = degats;
        this.interval = interval;
        this.cpt=interval;
    }

    @Override
    public void tick() {
        cpt++; //Timer de tir
        
        //On update tous les missiles
        for (Missile m : missiles){
            m.tick();
        }

        //On vérifie si on peut tirer
        if(cpt>=interval){
            cpt=0;
            if(direction == 1) missiles.add(new Missile(jeu,101, x+largeur, y+10, degats, direction));
            else missiles.add(new Missile(jeu,101, x-40, y+10, degats, direction));
        }
        
        //Collision joueur
        missileDetectionX(20,this.jeu.getMonde().getJoueur());
        missileDetectionY(20,this.jeu.getMonde().getJoueur());

        //Suppresion en cas de missile inactif (utilisé ou hors de map)
        Iterator<Missile> it = missiles.iterator();
        while(it.hasNext()){
            if(it.next().getInactif()) it.remove();
        }
    }

    @Override
    public void aff(Graphics g) { //Affichage des missiles
        if(direction==1) g.drawImage(jeu.textures.canonD, x, y, null);
        else g.drawImage(jeu.textures.canonG,x,y,null);
        for (Missile m : missiles){
            m.aff(g);
        }
    }

    private void missileDetectionX(int h, Personnage p){ //On vérifie si le joueur est touché par un missile selon X
        for(Missile m : missiles){
            if(m.getX()<p.getX() && (m.getX()+m.largeur)>p.getX() && (p.getY()+h)>m.getY() && p.getY()<(m.getY()+m.hauteur) && !m.getInactif()){
                m.action(p, "Xm");
                return;
            }
        }
    }

    private void missileDetectionY(int l, Personnage p){ //On vérifie si le joueur est touché par un missile selon Y
        for(Missile m : missiles){
            if(m.getY()<p.getY() && (m.getY()+m.hauteur)>p.getY() && (p.getX()+l)>m.getX() && p.getX()<(m.getX()+m.largeur) && !m.getInactif()){
                m.action(p,"Ym");
                return;
            }
        }
    }
}
