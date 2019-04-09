public class Arrivee extends Bloc {

	private Bloc b;

	//Une plateforme arrivée est une simple plateforme fixe ou mobile, mais on la dissocie par son ID différent qui permet de finir le jeu lors d'une collision selon Y

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
