import java.awt.*;

public class Missile extends Objet {

    private int direction;
    private final int VITESSE = 4;
    private int degats;
    private boolean inactif = false;

    public Missile(Jeu jeu, int id, int x, int y, int degats, int direction) {
        super(jeu, id, x, y, 40, 27);
        this.direction=direction;
        this.degats=degats;
    }

    @Override
    public void action(Personnage p, String s) { //Si on touche le joueur, lui enlève de la vie et rend le missile inactif (va se faire supprimer de la linkedlist des missiles puis garbage collector)
        p.setVie(p.getVie() - degats);
        inactif=true;
    }

    @Override
    public void tick() { //On update le missile en le déplaçant et en vérifiant si il sort de la map
        x+=direction*VITESSE;
        if(x>1280 || x <-40){
            inactif = true;
        }
    }

    @Override
    public void aff(Graphics g) { //On affiche le missile dans le bon sens en fonction de sa direction
        if(direction == 1) g.drawImage(jeu.textures.missileD,x,y,null);
        else g.drawImage(jeu.textures.missileG,x,y,null);
    }

    /** GETTERS AND SETTERS **/

    public boolean getInactif(){
        return inactif;
    }
}
