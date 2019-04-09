import java.awt.*;

//Le starter est le bloc de spawn du joueur, permettant à ce dernier d'être projeté en jeu

public class Starter extends Bloc {

    private boolean extended = false; //Vérifie l'état du starter

    public Starter(int x, int y, int id) {
        super(x, y, id, 0);
    }

    public void tick() {}

    public void action(Joueur j){ //On projette le joueur si le starter n'est pas encore étendu
        if(!extended){
            //Quand extend : h=47
            //Avant extend : h=23
            j.deplacementInit();
            id=27;
            hauteur=47;
            y=y-24; //Gère l'affichage du nouveau starter version étendue
            extended = true;
        }
    }

    public void aff(Graphics g){
        if(id == 26) g.drawImage(textures.starter_ferme,x,y,null); //Starter non étendu
        if(id == 27) g.drawImage(textures.starter_ouvert,x,y,null); //Starter étendu
    }
}
