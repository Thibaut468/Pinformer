import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class chargementMonde {

    public static String chargement(String chemin){
        StringBuilder builder = new StringBuilder();

        try{
            BufferedReader br = new BufferedReader(new FileReader(chemin));
            String ligne;
            ligne=br.readLine();
            while(ligne != null){
                builder.append(ligne + "\n");
                ligne = br.readLine();
            }
            br.close();
        } catch(IOException e){
            e.printStackTrace();
        }

        return builder.toString();
    }
}
