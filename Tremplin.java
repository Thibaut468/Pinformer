import java.awt.*;

//C'est un jump permettant de projeter le joueur en l'air si ce dernier atteint le tremplin par le côté (est bloqué selon Y)

public class Tremplin extends Objet {

	private int hauteurSaut;
	
	public Tremplin(Jeu jeu, int id, int x, int y, int hauteurSaut){
        super(jeu,id,x,y,36,48);
        this.hauteurSaut=hauteurSaut;
	}
	
	public void tick(){}
    
    public void action(Personnage p, String s){
            p.saut(hauteurSaut);
    } //Projette le personnage
    
	public void aff(Graphics g){ //Affichage
         g.drawImage(jeu.textures.tremplin,x,y,null);
    }
}
