import java.awt.*;

//Classe général représentant les monstres (contact ou distance)

public abstract class Monstre extends Personnage {

	protected int degats;
    private double depX;
    private double depY;
    private int positionInitialeX;
    private int positionFinaleX;
    protected boolean sens;
    protected int cpt;

	
	public Monstre(Jeu jeu, int id, int x, int y, int largeur, int hauteur, int vitesse, int degats, int positionFinaleX) {
        super(jeu, id, x, y, largeur, hauteur, 1, vitesse);
		this.degats = degats;
        this.depX=vitesse;
        this.depY=0;
        this.positionInitialeX=x;
        this.positionFinaleX=positionFinaleX;
        this.cpt = 240;
	}

	public void action(Personnage p, String s){ //Méthode importante pour les collisions avec un joueur
        //Ici, on doit être capable de vérifier si le joueur est touché par la gauche ou la droite (Le monstre le touche)
        //Ou alors le joueur est touché par le haut ou le bas (le joueur élimine alors le monstre)

        if(s.equals("X") && cpt >=240){ //Monstre touche le joueur et un intervalle de temps est vérifié pour éviter des multitouch avec une seule collision
            touche(p); //On va mettre des degats au joueur
            cpt=0; //On réinitialise le compteur
        }

        if(s.equals("Y")){ //Joueur touche le monstre
            estTouche(); //On élimine le joueur
        }
    }

    public abstract void tick();

    public abstract void aff(Graphics g);

    public void deplacement () { //On déplace le monstre

        if(vitesse!=0) {
            if (super.x >= positionFinaleX) {
                sens = true;
            }
            if (super.x <= positionInitialeX) {
                sens = false;
            }
            if(sens) depX=-vitesse;
            if(!sens) depX=vitesse;
        }

        if(!blocCollisionX()) super.x+=depX;
    }

    public boolean blocCollisionX(){ //On vérifie si le monstre touche ou non un bloc
        int testX;
        if(this.depX>0){ //Déplacement droite
            testX = (int) (x+largeur+depX);
            if(!this.jeu.getMonde().blocDetectionX(testX, y, hauteur)){ //pas de collisions
                return false;
            } else { //collision
                sens=!sens;
                return true;
            }
        } else if(this.depX<0){ //Déplacement gauche
            testX = (int)(x+depX);
            if(!this.jeu.getMonde().blocDetectionX(testX, y, hauteur)){ //pas de collisions
                return false;
            } else { //collision
                sens=!sens;
                return true;
            }
        }
        return false;
    }
	
	private void perdVie(Personnage j) { //On enleve de la vie au joueur
		int nouvelleVie = j.getVie() - this.degats;
		j.setVie(nouvelleVie);
		
	}

    private void touche(Personnage j){
        perdVie(j);
    }

    private void estTouche(){ //On élimine le monstre et on le rend inactif pour le supprimer de l'arraylist, qui sera donc supp par la garbage collector
        vie=0;
        inactif=true;
    }
}
