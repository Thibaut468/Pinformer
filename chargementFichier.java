import java.io.*;
import java.net.URL;

//Classe un peu difficile, prise en grande partie sur un cours de P2I-2 (données capteurs) mais adapté pour le cas présent, permet de lire un fichier texte placé dans un dossier annexe au code
//Le chemin est spécifié par un string et ce sont des méthodes statiques que l'on peut récupérer partout dans nos class.
//On peut considérer cette classe comme un "Util"

public class chargementFichier {

    public static String chargement(String chemin, boolean jar){
        StringBuilder builder = new StringBuilder();
        URL url = ClassLoader.getSystemClassLoader().getResource(chemin);
        try {
            BufferedReader br;
            if (jar) {
                br = new BufferedReader(new InputStreamReader(url.openStream())); //Création d'un buffer qui récupère le fichier texte
            } else{
                br = new BufferedReader(new FileReader(chemin));
            }
            String ligne;
            ligne=br.readLine(); //On ligne chaque ligne du buffer
            while(ligne != null){
                builder.append(ligne).append("\n"); //On extrait les lignes
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
        fichier.delete(); //On supprime le fichier initial, que l'on va recréer intégralement
        try{
            sortie = new PrintWriter(new BufferedWriter(new FileWriter(nomFichier)));
            for(int i=0;i<lignes.length-1;i++){
                sortie.write(lignes[i]+"\n"); //On écrit chaque ligne jusqu'à l'avant dernière
            }
            sortie.write(lignes[lignes.length-1]); //On écrit la dernière à part pour éviter le saut de ligne inutile à la fin
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
