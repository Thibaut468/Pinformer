import java.awt.*;

public class Starter extends Bloc {

    private boolean extended = false;

    public Starter(int x, int y, int id) {
        super(x, y, id, 0);
    }

    public void tick() {
    }

    public void action(Joueur j, int clicks){
        if(!extended){
            //Quand extend : h=47
            //Avant extend : h = 23
            j.deplacementInit(clicks);
            id=27;
            hauteur=47;
            y=y-24;
            extended = true;
        }
    }

    public void aff(Graphics g){
        if(id == 26) g.drawImage(textures.starter_ferme,x,y,null);
        if(id == 27) g.drawImage(textures.starter_ouvert,x,y,null);
    }
}
