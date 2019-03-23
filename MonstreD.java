import java.awt.*;
import java.util.LinkedList;

public class MonstreD extends Monstre {

    private int id;
    private final int intervalTir = 3000;
    private LinkedList<Balle> balles = new LinkedList<Balle>();
    private int compt = 0;

    public MonstreD(Jeu jeu, int id, int x, int y, int vitesse, int degat, int ralenti, int positionFinaleX){
        super(jeu, id, x, y, 64, 64, vitesse, degat, ralenti, positionFinaleX);
        this.ralenti = ralenti;
        this.id=id;
        falling=false;
    }

    public void tick() {
        deplacement();
        this.compt++;
        cpt++;
        if(this.compt>=intervalTir){
            this.compt=0;
            balles.add(new Balle(jeu, x, y, sens, degats, ralenti));
        }
    }
    public void aff(Graphics g) {
        g.drawImage(this.jeu.textures.monstre_contact,x,y,null);
    }
}
