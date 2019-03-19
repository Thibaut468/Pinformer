import java.io.*;
import java.util.List;

public class chargementFichier {

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

    public static void ecritureParam(String nomFichier, String[] lignes){ //Pour param, ligne 1 : zqsd || fleches et lignes 2 : 1 2 3.... (choix);
        Writer sortie=null;
        File fichier = new File(nomFichier);
        fichier.delete();
        try{
            sortie = new PrintWriter(new BufferedWriter(new FileWriter(nomFichier)));
            for(int i=0;i<lignes.length-1;i++){
                sortie.write(lignes[i]+"\n");
            }
            sortie.write(lignes[lignes.length-1]);
        }
        catch(IOException exc){
            exc.printStackTrace();
        }
        try{
            sortie.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }

    }
}
