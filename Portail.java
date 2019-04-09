import java.awt.*;

//Le portail teleporte le joueur aléatoirement sur la map

public class Portail extends Objet {

    private int xTP = 0;
    private int yTP = 0;

    public Portail(Jeu jeu, int id, int x, int y) {
        super(jeu, id, x, y, 48, 48);
    }

    private void initialisation(){
        //On calcule le point aléatoire (avec des offset liés à la map (notamment l'eau en bas)
        xTP = (int)(Math.random()*(jeu.getMonde().getLargeur()-jeu.getMonde().getJoueur().largeur+1));
        yTP = (int)(Math.random()*(jeu.getMonde().getHauteur()-jeu.getMonde().getJoueur().hauteur-64+1)); //48 pour la hauteur d'eau
    }

    public void action(Personnage p, String s) { //On téléporte le joueur et on désactive le portail pour assurer sa suppresion + garbage collector
        initialisation();
        p.setX(xTP);
        p.setY(yTP);
        inactif=true;
    }

    public void tick() {}

    public void aff(Graphics g) {
        g.drawImage(jeu.textures.portail,x,y,null);
    }
}
