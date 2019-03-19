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
    private Joueur joueur;
    private MonstreDistance Monstred1;
    private MonstreDistance Monstred2;
    private LinkedList<Balle> balles = new LinkedList<Balle>();
    private final int VPLAT=2;

    public Monde(String chemin, Jeu jeu){
        this.jeu = jeu;
        generation(chemin);
    }

    private void generation(String chemin){
        String fichier = chargementFichier.chargement(chemin);
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
                    blocs.add(new PlateformeFixe(x, y, 1));
                    break;
                case 2:
                    blocs.add(new PlateformeFixe(x, y, 2));
                    break;
                case 3:
                    blocs.add(new PlateformeFixe(x, y, 3));
                    break;
                case 4:
                    blocs.add(new PlateformeFixe(x, y, 4));
                    break;
                case 5:
                    blocs.add(new PlateformeMobile(x, y, 5, VPLAT, 400));
                    break;
				case 6:
                    blocs.add(new PlateformeMobile(x, y, 6, VPLAT, 400));
                    break;
                case 7:
                    blocs.add(new PlateformeMobile(x, y, 7, VPLAT, 464));
                    break;
			    case 8:
                    blocs.add(new PlateformeMobile(x, y, 8, VPLAT, 528));
                    break;
                default:
                    break;
            }
        }
        
        //Monstred1 = new MonstreDistance(100, 10, 200, 60, 60, 30, 1, 2, 1, Color.blue, this.jeu, 750, 1);
        //Monstred2 = new MonstreDistance(100, 10, 500, 60, 60, 30, 1, 1, 1, Color.black, this.jeu, 750, -1);
        joueur = new Joueur(spawnX,spawnY,48,48,10,8,this.jeu);
        entites.add(joueur);
        entites.add(new MonstreContact(80, 300, 60, 60, 10, 1, 1, 1, Color.pink, this.jeu,  750));
        
        //entites.add(Monstred1);
        //entites.add(Monstred2);
    }

    public void tick(){
        for (Bloc b: blocs) {
            b.tick();
        }
        for (Entite e : entites) {
            e.tick();
        }

        /*
        if (Monstred1.compt() == true) {
			Balle balle1 = Monstred1.creationBalle();
			balles.add(balle1);
            entites.add(balle1);
        }
        if (Monstred2.compt() == true) {
            Balle balle2 = Monstred2.creationBalle();
			balles.add(balle2);
            entites.add(balle2);
		}
		*/
		
		for (int i=0; i<balles.size(); i++) {
			if (!balles.get(i).aT()){
			balles.get(i).aTouche(joueur);
			}
			
			if (balles.get(i).aT()){
			entites.remove(balles.get(i));
			balles.remove(i);
			
			}
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

    public boolean blocDetectionY(int y, int x, int l){
        for(Bloc b : blocs){
            if(b.y<y && (b.y+b.HAUTEUR)>y && (x+l)>b.x && x<(b.x+b.LARGEUR) && b.solide()){
                return true;
            }
        }
        return false;
    }

    public boolean blocDetectionX(int x, int y, int h){
        for(Bloc b : blocs){
             if(b.x<x && (b.x+b.LARGEUR)>x && (y+h)>b.y && y<(b.y+b.HAUTEUR) && b.solide()){
                 return true;
             }
        }
        return false;
    }

    public Bloc getBloc(int y, int x, int l){
        for(Bloc b : blocs){
            if(b.y<y && (b.y+b.HAUTEUR)>y && (x+l)>b.x && x<(b.x+b.LARGEUR) && b.solide()){
                return b;
            }
        }
        return (new Bloc(0,0,-1,0));
    }
}
