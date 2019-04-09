import java.awt.*;

public class Portail extends Objet {

    private int xTP = 0;
    private int yTP = 0;

    public Portail(Jeu jeu, int id, int x, int y) {
        super(jeu, id, x, y, 48, 48);
    }

    private void initialisation(){
        xTP = (int)(Math.random()*(jeu.getMonde().getLargeur()-jeu.getMonde().getJoueur().largeur+1));
        yTP = (int)(Math.random()*(jeu.getMonde().getHauteur()-jeu.getMonde().getJoueur().hauteur-64+1)); //48 pour la hauteur d'eau
    }

    public void action(Personnage p, String s) {
        initialisation();
        p.setX(xTP);
        p.setY(yTP);
        inactif=true;
    }

    public void tick() {

    }

    public void aff(Graphics g) {
        g.drawImage(jeu.textures.portail,x,y,null);
    }
}
