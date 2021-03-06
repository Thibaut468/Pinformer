import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.net.URL;

//Simple classe qui charge absolument toutes les images utilisées dans le jeu dans un soucis d'optimisation

public class chargementImage {

    public BufferedImage joueur, backgroundJeu, water, l10, l9,l8, l7, l6, l5, l4, l3, l2, l1, l0, canonD, canonG, missileD, missileG, starter_ferme, starter_ouvert, p_arrivee, p_entier, p_gauche, p_droite, p_milieu, p_ephemere, healer, monstre_contact, tremplin, portail, pic_bas, pic_haut;

    public chargementImage(){

        //Lecture du fichier de paramètre
        String fichier = chargementFichier.chargement("./param.txt", false);
        String[] separation = fichier.split("\\s+");
        int choix = Integer.parseInt(separation[1]);

        switch(choix){ //On choisi l'image du joueur en fonction du choix fait dans les paramètres
            case 1 :
                joueur=load("/textures/joueur/joueur1.png");
                break;
            case 2 :
                joueur=load("/textures/joueur/joueur2.png");
                break;
            case 3 :
                joueur=load("/textures/joueur/joueur3.png");
                break;
            case 4 :
                joueur=load("/textures/joueur/joueur4.png");
                break;
            case 5 :
                joueur=load("/textures/joueur/joueur5.png");
                break;
            case 6 :
                joueur=load("/textures/joueur/joueur6.png");
                break;
            case 7 :
                joueur=load("/textures/joueur/joueur7.png");
                break;
            case 8 :
                joueur=load("/textures/joueur/joueur8.png");
                break;
            default :
                joueur=load("/textures/joueur/joueur1.png");
                break;


        }

        monstre_contact = load("/textures/objets/monstre_contact.png");
        backgroundJeu=load("/textures/fonds/background.png");
        water=load("/textures/fonds/water.png");

        p_entier = load("/textures/plateformes/P_entier.png");
        p_gauche = load("/textures/plateformes/P_gauche.png");
        p_droite = load("/textures/plateformes/P_droite.png");
        p_milieu = load("/textures/plateformes/P_milieu.png");
        p_arrivee = load("/textures/plateformes/P_arrivee.png");
        p_ephemere = load("/textures/plateformes/P_ephemere.png");

        healer = load("/textures/objets/coeur.png");
        tremplin = load("/textures/objets/tremplin.png");
        portail = load("/textures/objets/portail48.png");
        pic_bas = load("/textures/objets/pic_bas.png");
        pic_haut = load("/textures/objets/pic_haut.png");
        canonD = load("/textures/objets/canonD.png");
        canonG = load("/textures/objets/canonG.png");
        missileD = load ("/textures/objets/missileD.png");
        missileG = load ("/textures/objets/missileG.png");
        starter_ferme = load("/textures/objets/jumper1.png");
        starter_ouvert = load("/textures/objets/jumper2.png");


        //On charge la barre de vie

        l10=load("/textures/lifebar/100.png");
        l9=load("/textures/lifebar/90.png");
        l8=load("/textures/lifebar/80.png");
        l7=load("/textures/lifebar/70.png");
        l6=load("/textures/lifebar/60.png");
        l5=load("/textures/lifebar/50.png");
        l4=load("/textures/lifebar/40.png");
        l3=load("/textures/lifebar/30.png");
        l2=load("/textures/lifebar/20.png");
        l1=load("/textures/lifebar/10.png");
        l0=load("/textures/lifebar/00.png");
    }

    private BufferedImage load(String acces){
        try {
            URL img = getClass().getResource(acces);
            return ImageIO.read(img);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
