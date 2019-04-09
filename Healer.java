import java.awt.*;

//Objet donnant de la vie si utiliser comme Healer mais pour aussi enlever de la vie si utilisé comme drainer (en fonction de son ID)

public class Healer extends Objet {
    
    private int vieDonnee;
    private int id;
    
    public Healer (Jeu jeu, int id, int x, int y, int vieDonnee){
        super(jeu,id, x,y,32,32);
        this.id=id;
        this.vieDonnee = vieDonnee;
    }

    public void action(Personnage p, String s){ //Soigne ou dommage le personnage
        heal(p,vieDonnee);
        inactif = true;
    }

    public void tick(){} //Pas d'update particulière

    public void aff(Graphics g){
        g.drawImage(jeu.textures.healer,x,y,null);
    } //Affichage de son sprite
    
    private void heal(Personnage p, int vieDonnee){ //Méthode rendant de la vie au personnage ou en enlevant en fonction de son ID
        if(id==20){
            if((p.getVie()+vieDonnee)>10){ //Plafond sup à 10
                p.setVie(10);
            }else {
                p.setVie(vieDonnee + p.getVie());
            }
        }

        if(id==21){
            if((p.getVie()-vieDonnee)<0){ //Plafond inf à 0
                p.setVie(0);
            } else {
                p.setVie(p.getVie()-vieDonnee);
            }
        }
    }
}
