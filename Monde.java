import java.awt.*;
import java.util.LinkedList;

public class Monde {

    private Jeu jeu;

    private int largeur;
    private int hauteur;
    private int spawnX;
    private int spawnY;
    public LinkedList<Bloc> blocs = new LinkedList<Bloc>();
    public LinkedList<Entite> entites = new LinkedList<Entite>();

    public Monde(String chemin, Jeu jeu){
        this.jeu = jeu;
        generation(chemin);
    }

    private void generation(String chemin){
        String fichier = chargementMonde.chargement(chemin);
        String[] separation = fichier.split("\\s+");
        largeur = Integer.parseInt(separation[0]);
        hauteur = Integer.parseInt(separation[1]);
        spawnX = Integer.parseInt(separation[2]);
        spawnY = Integer.parseInt(separation[3]);

        int x;
        int y;
        int id;

        for(int i=4;i<separation.length-2;i+=3){
            id = Integer.parseInt(separation[i]);
            x = Integer.parseInt(separation[i+1]);
            y = Integer.parseInt(separation[i+2]);
            switch(id){
                case 1:
                    blocs.add(new PlateformeFixe(x, y));
                    break;
                default:
                    break;
            }
        }
        entites.add(new Joueur(spawnX,spawnY,64,64,10,8,this.jeu));
    }

    public void tick(){
        for (Bloc b: blocs) {
            b.tick();
        }
        for (Entite e : entites) {
            e.tick();
        }
    }

    public void aff(Graphics g){
        for (Bloc b: blocs) {
            b.aff(g);
        }
        for (Entite e : entites) {
            e.aff(g);
        }
    }

    public boolean blocDetection(int x, int y){
        for(Bloc b : blocs){
            if(b.y==y && (x>b.x && x<(b.x+b.LARGEUR)) && b.solide()) return true;
        }
        return false;
    }
}
