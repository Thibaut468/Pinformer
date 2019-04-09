import java.awt.*;

//Monstre de contact, n'envoie pas de projectile

public class MonstreC extends Monstre {

    public MonstreC(Jeu jeu, int id, int x, int y, int vitesse, int degats, int positionFinaleX){
        super(jeu, id, x, y, 48, 48, vitesse, degats, positionFinaleX);
    }

    public void tick() { //On l'update (déplacement + compteur multitouch)
        deplacement();
        cpt++;
    }

    public void aff(Graphics g) {
        g.drawImage(jeu.textures.monstre_contact,x,y,null);
    } // On l'affiche
}
