import java.awt.*;
import java.awt.image.BufferedImage;

public class Arrivee extends Bloc {
	
	private Bloc b;
	private int id;

	public Arrivee (int x, int y, int id){
		super (x,y,id, 0);
		b = new PlateformeFixe(x,y,id);
		this.id=id;
		
	}
	
	public Arrivee (int x, int y, int id, int vitesse, int PosFin){
		super (x,y,id, vitesse);
		b = new PlateformeMobile(x, y, id, vitesse, PosFin);
		this.id=id;
		
	}

	public void tick() {

	}
}
