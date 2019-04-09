import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

//Cette classe gère l'ensemble du monde (chaque composant interagisssant dans le jeu
//C'est aussi elle qui génère le monde

public class Monde {

    private Jeu jeu;

    private int largeur;
    private int hauteur;
    private int spawnX;
    private int spawnY;
    private ArrayList<Bloc> blocs = new ArrayList<>();
    private ArrayList<Entite> entites = new ArrayList<>();
    private Joueur joueur;
    private Starter starter;
    public Bloc c;


    public Monde(String chemin, Jeu jeu){
        this.jeu = jeu;
        generation(chemin); //On génère la map
    }

    private void generation(String chemin){
        /** LECTURE DES MONDES DANS UN FICHIER TEXTE **/
        String fichier = chargementFichier.chargement(chemin);
        String[] separation = fichier.split("\\s+");

        //1ère ligne du fichier texte : taille de la map
        largeur = Integer.parseInt(separation[0]);
        hauteur = Integer.parseInt(separation[1]);

        //2ème ligne spawn du joueur
        spawnX = Integer.parseInt(separation[2]);
        spawnY = Integer.parseInt(separation[3]);

        //PLACEMENT DES PREMIERS ELEMENTS
        starter = new Starter(spawnX,spawnY,26);
        blocs.add(starter);
        joueur = new Joueur(this.jeu, spawnX+8,spawnY-48,48,48,10,8);
        entites.add(joueur);

        int update = 0; //Variable assurant le parcours des paramètres propres à chaque objets/blocs (voir CR)

        //Cette boucle parcours la totalité du fichier texte et ajoute tous les éléments en fonctions de l'ID au monde

        for(int i=4;i<separation.length;i+=update){
            int id = Integer.parseInt(separation[i]);
            int x = Integer.parseInt(separation[i + 1]);
            int y = Integer.parseInt(separation[i + 2]);
            if(id >= 1 && id <= 4) { //PlateformeFixe
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
            } else if(id >=5 && id <=12){ //Plateforme mobile
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
                    default:
                        break;
                }
            } else if(id == 13){ //Arrivée fixe
                update = 3;
                blocs.add(new Arrivee(x,y,id));
            } else if (id == 14){ //Arrivée mobile X
                int vit = Integer.parseInt(separation[i+3]);
                int positionFinale = Integer.parseInt(separation[i+4]);
                update = 5;
                blocs.add(new Arrivee(x, y, 14, vit, positionFinale));
            } else if (id == 15){ //Arrivée mobile Y
                int vit = Integer.parseInt(separation[i+3]);
                int positionFinale = Integer.parseInt(separation[i+4]);
                update = 5;
                blocs.add(new PlateformeMobile(x, y, 15, vit, positionFinale));
            } else if(id == 16){ // Plateforme ephemere
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
            } else if(id == 27) { //Canon
                int direction = Integer.parseInt(separation[i+3]);
                int degats = Integer.parseInt(separation[i+4]);
                int interval = Integer.parseInt(separation[i+5]);
                update = 6;
                blocs.add(new Canon(this.jeu,id,x,y,direction,degats,interval));
            } else if (id==30){ //MonstreContact
                int vitesse = Integer.parseInt(separation[i + 3]);
                int degats = Integer.parseInt(separation[i + 4]);
                int positionFinaleX = Integer.parseInt(separation[i + 5]);
                update = 6;
                entites.add(new MonstreC(this.jeu, id, x, y, vitesse, degats, positionFinaleX));
            } else if (id==31) { //MonstreDistance
                int vitesse = Integer.parseInt(separation[i + 3]);
                int degats = Integer.parseInt(separation[i + 4]);
                int positionFinaleX = Integer.parseInt(separation[i + 5]);
                update = 6;
                entites.add(new MonstreD(this.jeu, id, x, y, vitesse, degats, positionFinaleX));
            }
        }
    }

    public void tick(){ //Update général du monde avant affichage

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

    public boolean blocDetectionY(int y, int x, int l){ //Assure la gestion des collisions entre les personnages et les blocs selon la direction Y
        for(Bloc b : blocs){
            if(b.getY()<y && (b.getY()+b.hauteur)>y && (x+l)>b.getX() && x<(b.getX()+b.largeur)){ //Collision
                c=b; //Stockage du bloc collisioné courant
                return true;
            }
        }
        return false;
    }

    public boolean blocDetectionX(int x, int y, int h){ //Assure la gestion des collisions entre les personnages et les blocs selon la direction X
        for(Bloc b : blocs){
             if(b.getX()<x && (b.getX()+b.largeur)>x && (y+h)>b.getY() && y<(b.getY()+b.hauteur)){ //Collision
                 c=b; //Stockage du bloc collisioné courant
                 return true;
             }
        }
        return false;
    }


    public boolean objetDetectionY(int y, int x, int l, Personnage p){ //Assure la gestion des collisions entre les personnages et les objets selon la direction Y
        for(Entite e : entites){
            if(e.getY()<y && (e.getY()+e.hauteur)>y && (x+l)>e.getX() && x<(e.getX()+e.largeur) && !e.getInactif()){ //Collision
                if(e.getId()!=22) { //Tremplin ne s'active pas selon Y
                    e.action(p, "Y"); //L'objet effectue son action sur le joueur
                    return true;
                }
            }
        }
        return false;
    }

    public boolean objetDetectionX(int x, int y, int h, Personnage p){ //Assure la gestion des collisions entre les personnages et les objets selon la direction X
        for(Entite e : entites){
            if(e.getX()<x && (e.getX()+e.largeur)>x && (y+h)>e.getY() && y<(e.getY()+e.hauteur) && !e.getInactif()){ //Collision
                e.action(p, "X"); //L'objet effectue son action sur le joueur
                return true;
            }
        }
        return false;
    }

    /** GETTERS AND SETTERS **/
    
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
    
    public int getSpawnX(){
		return this.spawnX;
	}
	
    public int getSpawnY(){
		return this.spawnY;
	}
}
