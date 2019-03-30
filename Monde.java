import java.awt.*;
import java.util.Iterator;
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
    private Starter starter;
    public Bloc c;


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


        starter = new Starter(spawnX,spawnY,26);
        blocs.add(starter);
        joueur = new Joueur(this.jeu, spawnX+8,spawnY-48,48,48,10,8);
        entites.add(joueur);

        int update = 0;

        for(int i=4;i<separation.length;i+=update){
            int id = Integer.parseInt(separation[i]);
            System.out.println(id);
            int x = Integer.parseInt(separation[i + 1]);
            int y = Integer.parseInt(separation[i + 2]);
            if(id >= 1 && id <= 4 || id==13) {
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
                    case 13 :
						blocs.add(new PlateformeFixe(x, y, 13));
						break;
                    default:
                        break;
                }
            } else if(id >=5 && id <=15 && id!=13){
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
                    case 9:
                        blocs.add(new PlateformeMobile(x, y, 9, VPLAT, positionFinaleX));
                        break;
                    case 10:
                        blocs.add(new PlateformeMobile(x, y, 10, VPLAT, positionFinaleX));
                        break;
                    case 11:
                        blocs.add(new PlateformeMobile(x, y, 11, VPLAT, positionFinaleX));
                        break;
                    case 12:
                        blocs.add(new PlateformeMobile(x, y, 12, VPLAT, positionFinaleX));
                        break;  
                    case 14:                        
						blocs.add(new PlateformeMobile(x, y, 14, VPLAT, positionFinaleX));
						break;
					case 15:                        
						blocs.add(new PlateformeMobile(x, y, 15, VPLAT, positionFinaleX));
						break;
                    default:
                        break;
                }
            } else if(id == 16){
                update = 3;
                blocs.add(new PlateformeEphemere(x,y,16));
            } else if (id==20 || id==21) { //Healer 20 et Drainer 21
                int vieDonnee = Integer.parseInt(separation[i + 3]);
                update = 4;
                entites.add(new Healer(this.jeu, id, x, y, vieDonnee));
            } else if (id==22) { //Tremplin
                int hauteurSaut = Integer.parseInt(separation[i + 3]);
                update = 4;
                entites.add(new Tremplin(this.jeu, id, x, y, hauteurSaut));
            } else if(id == 23) { //Portail
                update = 3;
                entites.add(new Portail(this.jeu, id, x, y));
            } else if(id == 24 || id == 25) { //Pics
                int degats = Integer.parseInt(separation[i + 3]);
                update = 4;
                blocs.add(new Pic(id, x, y, degats));
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
                entites.add(new MonstreD(this.jeu, id, x, y, vitesse, degats, ralenti, positionFinaleX, this));
            }
        }
    }

    public void tick(){

        //On supprime les éléments inactifs
        Iterator<Entite> it = entites.iterator();
        while(it.hasNext()){
            if(it.next().getInactif()) it.remove();
        }

        Iterator<Bloc> it2 = blocs.iterator();
        while(it2.hasNext()){
            if(it2.next().getInactif()) it2.remove();
        }

        //On tick tout


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

    public boolean blocDetectionY(int y, int x, int l){
        for(Bloc b : blocs){
            if(b.y<y && (b.y+b.hauteur)>y && (x+l)>b.x && x<(b.x+b.largeur) && b.solide()){
                c=b;
                return true;
            }
        }
        return false;
    }

    public boolean blocDetectionX(int x, int y, int h){
        for(Bloc b : blocs){
             if(b.x<x && (b.x+b.largeur)>x && (y+h)>b.y && y<(b.y+b.hauteur) && b.solide()){
                 c=b;
                 return true;
             }
        }
        return false;
    }

    public boolean objetDetectionX(int x, int y, int h, int l, Personnage p){
        for(Entite e : entites){
            if(e.x<x && (e.x+e.largeur)>x && (y+h)>e.y && y<(e.y+e.hauteur) && !e.getInactif()){
                e.action(p, "X");
                    return true;
            }
        }
        return false;
    }

    public boolean objetDetectionY(int y, int x, int l, int h, Personnage p){
        for(Entite e : entites){
            if(e.y<y && (e.y+e.hauteur)>y && (x+l)>e.x && x<(e.x+e.largeur) && !e.getInactif()){
                if(e.getId()!=22) { //Tremplin
                    e.action(p, "Y");
                    return true;
                }
            }
        }
        return false;
    }

    
    public Joueur getJoueur() {
        return this.joueur;
    }

    public Starter getStarter(){ return starter; }

    public int getLargeur() {
        return largeur;
    }

    public int getHauteur(){
        return hauteur;
    }
}
