import java.awt.*;
import java.util.Iterator;
import java.util.LinkedList;

//Monstre à distance, envoie des projectiles

public class MonstreD extends Monstre {

    private LinkedList<Balle> balles = new LinkedList<Balle>(); //on utilise une LinkedList pour gérer les balles car on effectue des suppressions (petite échelle, pas besoin d'une Arraylist)
    private int compt = 0; //Compteur gérant les intervalles de tir

    public MonstreD(Jeu jeu, int id, int x, int y, int vitesse, int degat, int positionFinaleX){
        super(jeu, id, x, y, 48, 48, vitesse, degat, positionFinaleX);
        falling=false;
    }

    public void tick() {
        deplacement();
        this.compt++; //Intervalle de tir
        cpt++; //Gestion multitouch
        int intervalTir = 50; //On fixe l'intervalle entre des tirs à 50
        if (this.compt >= intervalTir) { //Si on peut tirer
            this.compt = 0;
            balles.add(new Balle(jeu, x, y, sens, degats)); //On ajoute une nouvelle balle dans le jeu
        }

        //Update des balles
        for (Balle b : balles) {
            b.tick();
        }

        //Collision joueur
        balleDetectionX(x, y, 20, this.jeu.getMonde().getJoueur());
        balleDetectionY(y, x, 20, this.jeu.getMonde().getJoueur());

        //Suppresion des balles inactives
        Iterator<Balle> it = balles.iterator();
        while (it.hasNext()) {
            if (it.next().getInactif()) it.remove();
        }
    }

    public void aff(Graphics g) {
        g.drawImage(this.jeu.textures.monstre_contact,x,y,null);
        for (Balle b : balles) {
            b.aff(g);
        }
    }

    private void balleDetectionX(int x, int y, int h, Personnage p){  //On vérifie si une balle touche le joueur sur le coté
        for(Balle b : balles){
            if(b.getX()<p.getX() && (b.getX()+b.largeur)>p.getX() && (p.getY()+h)>b.getY() && p.getY()<(b.getY()+b.hauteur) && !b.getInactif()){
                b.action(p, "X");
                return;
            }
        }
    }

    private void balleDetectionY(int y, int x, int l, Personnage p){ //On vérifie si une balle touche le joueur sur en haut ou en bas
        for(Balle b : balles){
            if(b.getY()<p.getY() && (b.getY()+b.hauteur)>p.getY() && (p.getX()+l)>b.getX() && p.getX()<(b.getX()+b.largeur) && !b.getInactif()){
                b.action(p,"Y");
                return;
            }
        }
    }
}

