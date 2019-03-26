public class PlateformeEphemere extends Bloc {

    private int id;
    private PlateformeFixe p;
    private boolean activated = false;
    private int compt;
    private final int DELAY = 30;
    protected boolean inactif = false;

    public PlateformeEphemere(int x, int y, int id) {
        super(x, y, id, 0);
        p = new PlateformeFixe(x,y,id);
        this.id=id;
    }

    public void tick() {

        if(activated) compt++;

        if(compt >= DELAY){
            inactif = true;
            activated = false;
            compt = 0;
            System.out.println("INACTIVE");
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
