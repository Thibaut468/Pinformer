import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.io.File;

public class chargementImage {

    private int choix;
    public BufferedImage joueur, backgroundJeu, water, l10, l9,l8, l7, l6, l5, l4, l3, l2, l1, l0, p_entier, p_gauche, p_droite, p_milieu, healer, monstre_contact, tremplin;

    public chargementImage(){

        String fichier = chargementFichier.chargement("./sauvegardes/param.txt");
        String[] separation = fichier.split("\\s+");
        choix=Integer.parseInt(separation[1]);

        switch(choix){
            case 1 :
                joueur=load("./textures/joueur/joueur1.png");
                break;
            case 2 :
                joueur=load("./textures/joueur/joueur2.png");
                break;
            case 3 :
                joueur=load("./textures/joueur/joueur3.png");
                break;
            case 4 :
                joueur=load("./textures/joueur/joueur4.png");
                break;
            case 5 :
                joueur=load("./textures/joueur/joueur5.png");
                break;
            case 6 :
                joueur=load("./textures/joueur/joueur6.png");
                break;
            case 7 :
                joueur=load("./textures/joueur/joueur7.png");
                break;
            case 8 :
                joueur=load("./textures/joueur/joueur8.png");
                break;
            default :
                joueur=load("./textures/joueur/joueur1.png");
                break;


        }

        monstre_contact = load("./textures/monstre_contact.png");
        backgroundJeu=load("./textures/background.png");
        water=load("./textures/water.png");
        //plateforme = load("./textures/plateforme.png");
        p_entier = load("./textures/P_entier.png");
        p_gauche = load("./textures/P_gauche.png");
        p_droite = load("./textures/P_droite.png");
        p_milieu = load("./textures/P_milieu.png");

        healer = load("./textures/coeur.png");
        tremplin = load("./textures/tremplin.png");
        
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
