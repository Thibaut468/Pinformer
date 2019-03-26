import java.awt.*;

public class Pic extends Objet {

    private int pointeX;
    private int pointeY;
    private int degats;
    private int cpt;

    public Pic(Jeu jeu, int id, int x, int y, int degats) {
        super(jeu,id,x,y,30,25);
        this.degats=degats;
        this.cpt=240;

        if(id==24){
            pointeY=y;
            pointeX=x+30/2;
        } else { //id==25
            pointeY=y+25;
            pointeX=x+30/2;
        }
    }

    public void action(Personnage p, String s) {
        if(cpt>=240){
            p.setVie(p.getVie()-degats);
            cpt=0;
        }
    }


    public void tick() {
        cpt++;
    }


    public void aff(Graphics g) {
        if(id==24) g.drawImage(jeu.textures.pic_bas,x,y,null);
        if(id==25) g.drawImage(jeu.textures.pic_haut,x,y,null);
    }

    public int getPointeX(){ return pointeX; }

    public int getPointeY(){ return pointeY; }
}
