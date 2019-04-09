//Cette classe abstraite correspond à toutes les entités "vivantes" cad monstres et joueur

public abstract class Personnage extends Entite {

    protected int vie;
    protected double vitesse;
    protected double depX;
    private double depY;
    protected Jeu jeu;
    private final double GRAVITE = 2; //VARIABLE QUI GERE LA GRAVITE, MODIFIABLE
    private final double FROTTEMENTS = 1; //VARIABLE QUI GERE LES FROTTEMENTS, MODIFIABLE
    private final int VMAX = 25;
    protected boolean glissade = false;
    protected boolean falling = true;
    protected boolean jumping = true;

    public Personnage(Jeu jeu, int id, int x, int y, int largeur, int hauteur, int vie, double vitesse){
        super(jeu,id, x, y, largeur, hauteur);
        this.vie=vie;
        this.vitesse=vitesse;
        this.depX=0;
        this.depY=0;
        this.jeu=jeu;
    }

    public void action(Personnage p, String s){}

    public void deplacement(){ //Déplacement du personnage
        this.deplacementX();
        this.deplacementY();
    }

    public void chute(){ //Application de la gravité
        if(falling){
            this.depY+=GRAVITE;
        }
    }

    public void frottements(){ //Application des frottements
        if(glissade && this.depX>0){
            if((this.depX-FROTTEMENTS)>0){
                this.depX-=FROTTEMENTS;
            } else {
                this.depX=0;
            }
        } else if (glissade && this.depX<0){
            if((this.depX+FROTTEMENTS)<0){
                this.depX+=FROTTEMENTS;
            } else {
                this.depX=0;
            }
        }
    }

    public void saut(double hauteurSaut){ //Pulse de saut
        jumping = true;
        this.depY-=hauteurSaut;
    }

    private void deplacementX() { //On effectue le déplacement selon X en vérifiant qu'il n'y ait pas de collisions, sinon on les traites
        mapCollisionX();
        blocCollisionX();
        objetCollisionX();
    }

    private void deplacementY(){ //On effecture le déplacement selon Y en vérifiant qu'il n'y ait pas de collisions, sinon on les traites
        mapCollisionY();
        blocCollisionY();
        objetCollisionY();
    }

    private void mapCollisionY(){ //On gère les collisions vers le bas avec la carte du monde (1280 x 720)
        int testY;
        if(this.depY>0) { //Déplacement vers le bas
            testY = (int) (y + hauteur + depY);
            if (testY  > 720) { //On est tombé trop bas
                //ENDGAME
                depY=0;
                super.y += 720-y-hauteur;
                jumping=false;
                jeu.dead();
            }
        }
    }

    private void mapCollisionX(){ //On gère les collisions gauche et droite avec la carte du monde (1280 x 720)
        int testX;
        if (this.depX > 0){ //Déplacement droite
            testX = (int) (x + largeur + depX);
            if(testX>1280){ //Collision
                this.depX=0;
                super.x += 1280-x-largeur;
                glissade = false;
            }
        } else if (this.depX < 0) { //Déplacement gauche
            testX = (int)(x+depX);
            if(testX<0){ //Collision
                this.depX = 0;
                super.x += -x;
                glissade = false;
            }
        }
    }

    private void objetCollisionX() { //On gère les collisions avec les types objets selon la gauche et la droite
        int testX;
        if (this.depX > 0){ //Déplacement droite
            testX = (int) (x + largeur + depX);
            this.jeu.getMonde().objetDetectionX(testX,y,hauteur, this);
        } else if (this.depX < 0) { //Déplacement gauche
            testX = (int)(x+depX);
            this.jeu.getMonde().objetDetectionX(testX,y,hauteur, this);
        }
    }

    private void objetCollisionY(){ //On gère les collisions avec les types objets selon le haut et le bas
        int testY;
        double dY = depY;
        if(this.depY>0){ //Déplacement vers le bas
            if(dY>VMAX) dY=VMAX;
            testY=(int)(y+hauteur+dY);
            if(this.jeu.getMonde().objetDetectionY(testY,x,largeur, this));
        } else if(this.depY<0){ //Déplacement vers le haut
            if(dY<-VMAX) dY=-VMAX;
            testY=(int)(y+dY);
            if(this.jeu.getMonde().objetDetectionY(testY,x,largeur, this));
        }
    }

    public boolean blocCollisionX(){ //On gère les collisions avec les types blocs selon la gauche et la droite, et on active les blocs particuliers avec les ID
        int testX;
        if(this.depX>0){ //Déplacement droite
            testX = (int) (x+largeur+depX);
            if(!this.jeu.getMonde().blocDetectionX(testX, y, hauteur)){ //pas de collision
                super.x+=(int)depX;
                glissade = true;
                return false;
            } else { //collision
                this.depX = 0;
                Bloc c = this.jeu.getMonde().c;
                super.x += c.getX() - x - largeur;
                glissade = false;

                if(c.getId()==24 || c.getId() == 25) { //PIC --> Collision pointe --> Degats
                    Pic pic = (Pic) c;
                    pic.action(this, "X");
                }

                return true;
            }
        } else if(this.depX<0){ //Déplacement gauche
            testX = (int)(x+depX);
            if(!this.jeu.getMonde().blocDetectionX(testX, y, hauteur)){
                super.x+=(int)depX;
                glissade=true;
                return false;
            } else { //collision
                Bloc c = this.jeu.getMonde().c;
                this.depX = 0;
                super.x += (c.getX() + c.largeur) - x;
                glissade = false;

                if(c.getId()==24 || c.getId() == 25) { //PIC --> Collision pointe --> Degats
                    Pic pic = (Pic) c;
                    pic.action(this, "X");
                }

                return true;
            }
        }
        return false;
    }

    private void blocCollisionY(){ //On gère les collisions avec les types blocs selon le haut et le bas, et on active les blocs particuliers avec les ID
        int testY;
        if(depY>0) { //Déplacement vers le bas
            double dY = depY;
            if(dY>VMAX) dY=VMAX;
            testY = (int) (y + hauteur + dY);
            if (!this.jeu.getMonde().blocDetectionY(testY, x, largeur)) { //Pas de collision
                super.y += (int) this.depY;
            } else { //collision --> On avance au max
                this.depY=0;
                Bloc c = this.jeu.getMonde().c;
                super.y += c.y-y-hauteur;
                jumping=false;

                int id = c.getId();
                int vit = c.getVitesse();
                if(id==5 || id==6 || id==7 || id==8 || id == 14){ //Plateforme Mobile horizontale --> Le joueur suit la plateforme
                    super.x+=vit;
                }

                if(id==9 || id==10 || id==11 || id==12 || id == 15){ //Plateforme Mobile verticale --> Le joueur suit la plateforme
                    super.y+=vit;
                }

                if(c.getId()==24 || c.getId() == 25) { //PIC --> Collision pointe --> Degats
                    Pic pic = (Pic) c;
                    pic.action(this, "Y");
                }

                if(c.getId()==16){ //Plateforme ephemere --> Elle disparait
                   c.action();
                }

                if(c.getId()==13 ||c.getId()==14 || c.getId()==15){ //Plateforme arrivee --> ENDGAME VICTOIRE
                    jeu.finish();
                }

            }
        } else if(this.depY<0){ //Déplacement vers le haut
            double dY = depY;
            if(dY<-VMAX) dY=-VMAX;
            testY = (int) (y + dY);
            if (!this.jeu.getMonde().blocDetectionY(testY, x, largeur)){ //Pas de collision
                super.y += (int) this.depY;
            } else { //collision --> On avance au max
                this.depY = 0;
                Bloc c = this.jeu.getMonde().c;
                super.y += (c.y+c.hauteur)-y;

                if(c.getId()==24 || c.getId() == 25) { //PIC --> Collision pointe --> Degats
                    Pic pic = (Pic) c;
                    pic.action(this, "Y");
                }
            }
        }
    }


    /** GETTERS AND SETTERS **/

    public void setVie(int v){
        {
            if(v<=0){ //Si la vie est négative ou nulle, GAME OVER
                this.vie=0;
                jeu.dead();
            }
            this.vie=v;
        }
    }

    public int getVie(){
        return this.vie;
    }

    public void setVitesse(double vitesse){
        this.vitesse=vitesse;
    }

    public double getVitesse(){
        return this.vitesse;
    }
    
    public int getLargeur() {
		return this.largeur;
	}
	
	public int getHauteur() {
		return this.hauteur;
	}
}
