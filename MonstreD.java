import java.awt.*;
import java.util.LinkedList;

public class MonstreD extends Monstre {

    private int id;
    private final int intervalTir = 150;
    private LinkedList<Balle> balles = new LinkedList<Balle>();
    private int compt = 0;
    private Monde monde;

    public MonstreD(Jeu jeu, int id, int x, int y, int vitesse, int degat, int ralenti, int positionFinaleX, Monde monde){
        super(jeu, id, x, y, 48, 48, vitesse, degat, ralenti, positionFinaleX);
        this.ralenti = ralenti;
        this.id=id;
        falling=false;
        this.monde = monde;
    }

    public void tick() {
        deplacement();
        this.compt++;
        cpt++;
        if(this.compt>=intervalTir){
            this.compt=0;
            balles.add(new Balle(jeu, x, y, sens, degats, ralenti));
        }
        
        for (Balle b : balles) {
            b.tick();
             }
            /*b.aTouche(monde.getJoueur());
			if (b.aT()){
			balles.remove(b);
			
			}
        }*/
    }
    public void aff(Graphics g) {
        g.drawImage(this.jeu.textures.monstre_contact,x,y,null);
        for (Balle b : balles) {
            b.aff(g);
        }
    }
    
    public LinkedList<Balle> renvBalles() {
        return this.balles;
    }
}
