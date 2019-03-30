import java.awt.*;

public class Pic extends Bloc {

    private int pointeX;
    private int pointeY;
    private int degats;
    private int cpt;

    public Pic(int id, int x, int y, int degats) {
        super(x,y,id,0);
        this.degats=degats;
        this.cpt=240;

        if(id==24){
            pointeY=y-1;
            pointeX=x+30/2;
        } else { //id==25
            pointeY=y+25+1;
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
        if(id==24) g.drawImage(textures.pic_bas,x,y,null);
        if(id==25) g.drawImage(textures.pic_haut,x,y,null);
    }

    public int getPointeX(){ return pointeX; }

    public int getPointeY(){ return pointeY; }
}
