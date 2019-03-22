import java.awt.*;

public class Tremplin extends Objet {

	private int id = 22; 
	
	public Tremplin(Jeu jeu, int x, int y, int id){
        super(jeu,id,x,y,32,32);
        this.id=id;
	}
	
	public void jump(Personnage p){
		if(p.getX()==x){
			p.saut(20);
		}
	}
	
	public void tick(){
    }
    
    public void action(Personnage p){
		jump(p);
		inactif = true;
    }
    
	public void aff(Graphics g){
         //g.setColor(Color.magenta);
         //g.fillRect(this.x,this.y,20,20);
         g.drawImage(jeu.textures.healer,x,y,null);
    }

}
