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
            } else if (id==20 || id==21) { //Healer 20 et Drainer 21
                int vieDonnee = Integer.parseInt(separation[i + 3]);
                update = 4;
                entites.add(new Healer(this.jeu, id, x, y, vieDonnee));
            } else if (id==22){ //Tremplin
                int hauteurSaut = Integer.parseInt(separation[i+3]);
                update = 4;
                entites.add(new Tremplin(this.jeu, id, x, y, hauteurSaut));
            } else if (id==30){ //MonstreContact
                int vitesse = Integer.parseInt(separation[i + 3]);
                int degats = Integer.parseInt(separation[i + 4]);
                int positionFinaleX = Integer.parseInt(separation[i + 5]);
                update = 6;
                entites.add(new MonstreC(this.jeu, id, x, y, vitesse, degats, positionFinaleX));
            } else if (id==31) { //MonstreDistance
                int vitesse = Integer.parseInt(separation[i + 3]);
                int degats = Integer.parseInt(separation[i + 4]);
                int ralenti = Integer.parseInt(separation[i + 5]);
                int positionFinaleX = Integer.parseInt(separation[i + 6]);
                update = 7;
                entites.add(new MonstreD(this.jeu, id, x, y, vitesse, degats, ralenti, positionFinaleX));
            }
        }
    }

    public void tick(){

        //Suppresion des inactifs
        /*
        for(Entite e : entites){
            if(e.getInactif())  entites.remove(e);
        }
        */


        //On tick tout

        for (Bloc b: blocs) {
            b.tick();
        }
        for (Entite e : entites) {
            if(!e.getInactif())
            e.tick();
        }


        /*
        for (Balle b : lesBalles) {
            b.tick();
			b.aTouche(joueur);
			if (b.aT()){
			lesBalles.remove(b);
			
			}
        }
        */

        
}
    public void aff(Graphics g){
        for (Bloc b: blocs) {
            b.aff(g);
        }
        for (Entite e : entites) {
            if(!e.getInactif())
            e.aff(g);
        }

        /*
        for (Balle b : lesBalles) {
            b.aff(g);
        }
        */
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

    public Joueur getJoueur(){ return this.joueur; }

}
