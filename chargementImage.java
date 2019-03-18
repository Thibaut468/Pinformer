import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.io.File;

public class chargementImage {

    public BufferedImage joueur, backgroundJeu, l10, l9,l8, l7, l6, l5, l4, l3, l2, l1, l0, p_entier, p_gauche, p_droite, p_milieu, healer;

    public chargementImage(){

        joueur=load("./textures/parrot2.png");
        backgroundJeu=load("./textures/background.png");
        //plateforme = load("./textures/plateforme.png");
        p_entier = load("./textures/P_entier.png");
        p_gauche = load("./textures/P_gauche.png");
        p_droite = load("./textures/P_droite.png");
        p_milieu = load("./textures/P_milieu.png");

        healer = load("./textures/coeur.png");
        
        l10=load("./textures/lifebar/100.png");
        l9=load("./textures/lifebar/90.png");
        l8=load("./textures/lifebar/80.png");
        l7=load("./textures/lifebar/70.png");
        l6=load("./textures/lifebar/60.png");
        l5=load("./textures/lifebar/50.png");
        l4=load("./textures/lifebar/40.png");
        l3=load("./textures/lifebar/30.png");
        l2=load("./textures/lifebar/20.png");
        l1=load("./textures/lifebar/10.png");
        l0=load("./textures/lifebar/00.png");
    }

    public BufferedImage load(String acces){
        try {
            return ImageIO.read(new File(acces));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
