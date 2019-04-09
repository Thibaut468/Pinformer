// Cette classe permet de créer une plateforme qui ne bouge pas
// De couleur violette dans l'interface graphique


public class PlateformeFixe extends Bloc {

    public PlateformeFixe(int x, int y, int id){
        super(x, y, id, 0);
    }

    public void tick() {}
}
