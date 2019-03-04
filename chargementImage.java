import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.io.File;

public class chargementImage {

    public BufferedImage joueur;

    public chargementImage(){

        joueur=load("./textures/joueur.png");

    }

    public BufferedImage load(String acces){
        try {
            return ImageIO.read(new File(acces));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
