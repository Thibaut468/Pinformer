import java.awt.*;

public class MonstreC extends Monstre {

    private int id;

    public MonstreC(Jeu jeu, int id, int x, int y, int vitesse, int degats, int positionFinaleX){
        super(jeu, id, x, y, 64, 64, vitesse, degats, 0, positionFinaleX);
        this.id=id;
    }

    public void tick() {
        deplacement();
        cpt++;
    }

    public void aff(Graphics g) {
        g.drawImage(jeu.textures.monstre_contact,x,y,null);
    }
}
