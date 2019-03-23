import java.awt.*;

public class Tremplin extends Objet {

	private int id = 22;
	private int hauteurSaut;
	
	public Tremplin(Jeu jeu, int id, int x, int y, int hauteurSaut){
        super(jeu,id,x,y,36,48);
        this.id=id;
        this.hauteurSaut=hauteurSaut;
	}
	
	public void tick(){}
    
    public void action(Personnage p, String s){
            p.saut(hauteurSaut);
    }
    
	public void aff(Graphics g){
         //g.setColor(Color.magenta);
         //g.fillRect(this.x,this.y,20,20);
         g.drawImage(jeu.textures.tremplin,x,y,null);
    }

}
