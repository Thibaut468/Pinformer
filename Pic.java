import java.awt.*;

//Un pic fait des dégats au joueur s'il le touche

public class Pic extends Bloc {

    private int degats;
    private int cpt;

    public Pic(int id, int x, int y, int degats) {
        super(x,y,id,0);
        this.degats=degats;
        this.cpt=240; //Compteur pour éviter le multitouch (comme avec le monstre, on laisse le temps au joueur de se replacer)
    }

    public void action(Personnage p, String s) { //On fait des dégats au personnages
        if(cpt>=240){
            p.setVie(p.getVie()-degats);
            cpt=0;
        }
    }


    public void tick() {
        cpt++;
    } //On update le compteur pour le multitouch


    public void aff(Graphics g) {
        if(id==24) g.drawImage(textures.pic_bas,x,y,null);
        if(id==25) g.drawImage(textures.pic_haut,x,y,null);
    }
}
