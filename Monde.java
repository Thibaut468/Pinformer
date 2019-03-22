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
    
	private Tremplin tremplin1;
   
    private MonstreDistance Monstred1;
    private MonstreDistance Monstred2;
    private LinkedList<Balle> balles = new LinkedList<Balle>();

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

        joueur = new Joueur(this.jeu, spawnX,spawnY,48,48,10,8);
        entites.add(joueur);
        
        /**/tremplin1 = new Tremplin(this.jeu, 100, 150,48);
        /**/entites.add(tremplin1);

        int update = 0;

        for(int i=4;i<separation.length;i+=update){
            int id = Integer.parseInt(separation[i]);
            System.out.println(id);
            int x = Integer.parseInt(separation[i + 1]);
            int y = Integer.parseInt(separation[i + 2]);
            if(id >= 1 && id <= 4) {
                update = 3;
                switch (id) {
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
                    default:
                        break;
                }
            } else if(id >=5 && id <=8){
                int VPLAT = Integer.parseInt(separation[i+3]);
                int positionFinaleX = Integer.parseInt(separation[i+4]);
                update = 5;
                switch(id){
                    case 5:
                        blocs.add(new PlateformeMobile(x, y, 5, VPLAT, positionFinaleX));
                        break;
                    case 6:
                        blocs.add(new PlateformeMobile(x, y, 6, VPLAT, positionFinaleX));
                        break;
                    case 7:
                        blocs.add(new PlateformeMobile(x, y, 7, VPLAT, positionFinaleX));
                        break;
                    case 8:
                        blocs.add(new PlateformeMobile(x, y, 8, VPLAT, positionFinaleX));
                        break;
                    default:
                        break;
                }
            } else if (id==20 || id==21) {
                int vieDonnee = Integer.parseInt(separation[i + 3]);
                update = 4;
                entites.add(new Healer(this.jeu, id, x, y, vieDonnee));
            }
        }
        
        //Monstred1 = new MonstreDistance(this.jeu, 100, 10, 200, 60, 60, 30, 1, 2, 1, Color.blue, 750, 1);
        //Monstred2 = new MonstreDistance(this.jeu, 100, 10, 500, 60, 60, 30, 1, 1, 1, Color.black, 750, -1);
        //entites.add(new MonstreContact(this.jeu, 80, 300, 60, 60, 10, 1, 1, 1, Color.pink, 750));
        
        //entites.add(Monstred1);
        //entites.add(Monstred2);
    }

    public void tick(){

        //Suppresion des inactif

        for(Entite e : entites){
            if(e.getInactif())  entites.remove(e);
        }

        //On tick tout

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

    public boolean objetDetectionX(int x, int y, int h){
        for(Entite e : entites){
            if(e.x<x && (e.x+e.largeur)>x && (y+h)>e.y && y<(e.y+e.hauteur)){
                return true;
            }
        }
        return false;
    }

    public boolean objetDetectionY(int y, int x, int l){
        for(Entite e : entites){
            if(e.y<y && (e.y+e.hauteur)>y && (x+l)>e.x && x<(e.x+e.largeur)){
                return true;
            }
        }
        return false;
    }

    public Entite getEntiteX(int x, int y, int h){
        for(Entite e : entites){
            if(e.x<x && (e.x+e.largeur)>x && (y+h)>e.y && y<(e.y+e.hauteur)){
                return e;
            }
        }
        return (new Healer(jeu,-1,0,0,0));
    }

    public Entite getEntiteY(int y, int x, int l){
        for(Entite e : entites){
            if(e.y<y && (e.y+e.hauteur)>y && (x+l)>e.x && x<(e.x+e.largeur)){
                return e;
            }
        }
        return (new Healer(jeu,-1,0,0,0));
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
