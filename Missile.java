import java.awt.*;

public class Missile extends Objet {

    private int id;
    private int direction;
    private final int VITESSE = 4;
    private int degats;
    private boolean inactif = false;

    public Missile(Jeu jeu, int id, int x, int y, int degats, int direction) {
        super(jeu, id, x, y, 40, 27);
        this.id = id;
        this.direction=direction;
        this.degats=degats;
    }

    @Override
    public void action(Personnage p, String s) {
        p.setVie(p.getVie() - degats);
        inactif=true;
    }

    @Override
    public void tick() {
        //System.out.println("DEPMISS");
        x+=direction*VITESSE;
        if(x>1280 || x <-40){
            inactif = true;
        }
    }

    @Override
    public void aff(Graphics g) {
        if(direction == 1) g.drawImage(jeu.textures.missileD,x,y,null);
        else g.drawImage(jeu.textures.missileG,x,y,null);
    }

    public boolean getInactif(){
        return inactif;
    }
}
