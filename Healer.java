import java.awt.*;

public class Healer extends Objet {
    
    private int vieDonnee;
    private int id;
    
    public Healer (Jeu jeu, int id, int x, int y, int vieDonnee){
        super(jeu,id, x,y,32,32);
        this.id=id;
        this.vieDonnee = vieDonnee;
    }

    public void action(Personnage p, String s){
        heal(p,vieDonnee);
        inactif = true;
    }

    public void tick(){
    }

    public void aff(Graphics g){
        g.drawImage(jeu.textures.healer,x,y,null);
    }
    
    public void heal(Personnage p, int vieDonnee){
        if(id==20){
            if((p.getVie()+vieDonnee)>10){
                p.setVie(10);
            }else {
                p.setVie(vieDonnee + p.getVie());
            }
        }

        if(id==21){
            p.setVie(p.getVie()-vieDonnee);
        }

    }
}
