public class PlateformeEphemere extends Bloc {

    private PlateformeFixe p;
    private boolean activated = false;
    private int compt;
    private boolean inactif = false;

    public PlateformeEphemere(int x, int y, int id) {
        super(x, y, id, 0);
        p = new PlateformeFixe(x,y,id);
    }

    public void tick() {

        if(activated) compt++;

        int DELAY = 10;
        if(compt >= DELAY){
            inactif = true;
            activated = false;
            compt = 0;
        }
        p.tick();
    }

    public void action(){
        if(!inactif && !activated) {
            activated = true;
        }
    }

    public boolean getInactif(){
        return inactif;
    }
}
