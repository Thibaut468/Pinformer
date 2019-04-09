public class Arrivee extends Bloc {

	private Bloc b;

	public Arrivee (int x, int y, int id){
		super (x,y,id, 0);
		b = new PlateformeFixe(x,y,id);
	}
	
	public Arrivee (int x, int y, int id, int vitesse, int PosFin){
		super (x,y,id, vitesse);
		b = new PlateformeMobile(x, y, id, vitesse, PosFin);
		this.id=id;
	}

	public void tick() { b.tick(); }
}
