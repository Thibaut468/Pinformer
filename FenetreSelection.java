import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

/** FENETRE PRINCIPALE AVANT JEU **/

public class FenetreSelection extends JFrame implements ActionListener {

    private JButton boutonJouer;
    private JButton boutonParam;
    private JButton boutonCredits;
    private JButton boutonQuitter;
    private boolean jeuIsRunning = false;
    private FenetreParametres parametres;
    private FenetreMonde fenetreMonde;
    private JFrame menu;

    public FenetreSelection(){

        //Initialisation du JAR et des fichiers externes
        init();

        /** Création frame principale du menu **/
        menu = new JFrame();
        menu.setTitle("Menu du jeu");
        menu.setSize(500,500);
        menu.setLocationRelativeTo(null);
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menu.setResizable(false);

        ImageIcon ico = new ImageIcon(getClass().getResource("/textures/fonds/icone.png"));
        menu.setIconImage(ico.getImage());

        Font police = new Font(" Arial ",Font.BOLD,18);

        Color couleur = new Color(166, 39, 86);

        /** Création des boutons **/
        boutonJouer = new JButton();
        boutonJouer.setFont(police);
        boutonJouer.setText("JOUER");
        boutonJouer.setBounds(125,150,250,50);

        boutonJouer.setBackground(couleur);
        boutonJouer.setForeground(Color.white);
        boutonJouer.setFocusPainted(false);

        boutonParam = new JButton();
        boutonParam.setFont(police);
        boutonParam.setText("PARAMETRES");
        boutonParam.setBounds(125,225,250,50);
        boutonParam.setBackground(couleur);
        boutonParam.setForeground(Color.white);
        boutonParam.setFocusPainted(false);

        boutonCredits = new JButton();
        boutonCredits.setFont(police);
        boutonCredits.setText("CREDITS");
        boutonCredits.setBounds(125,300,250,50);
        boutonCredits.setBackground(couleur);
        boutonCredits.setForeground(Color.white);
        boutonCredits.setFocusPainted(false);

        boutonQuitter = new JButton();
        boutonQuitter.setFont(police);
        boutonQuitter.setText("QUITTER");
        boutonQuitter.setBounds(125,375,250,50);
        boutonQuitter.setBackground(couleur);
        boutonQuitter.setForeground(Color.white);
        boutonQuitter.setFocusPainted(false);

        JPanel conteneur1 = new JPanel();
        conteneur1.setLayout(null);
        conteneur1.setBounds(100,125,400,475);
        conteneur1.add(boutonJouer);
        conteneur1.add(boutonParam);
        conteneur1.add(boutonCredits);
        conteneur1.add(boutonQuitter);
		
		JLabel imageFond;
		imageFond = new JLabel(new ImageIcon(getClass().getResource("/textures/fonds/fond_menu.png")));
		imageFond.setLocation(0,0);
		imageFond.setSize(500,500);
		conteneur1.add(imageFond);
		
        menu.add(conteneur1);

        boutonJouer.addActionListener(this);
        boutonParam.addActionListener(this);
        boutonCredits.addActionListener(this);
        boutonQuitter.addActionListener(this);

        /** Création des fenetres ouvrables avec les boutons, mises en non visibles au départ **/
        parametres = new FenetreParametres();
        fenetreMonde = new FenetreMonde();

        menu.setVisible(true);

        playSound("musique_3.wav"); //On lance la musique
    }

    public void actionPerformed (ActionEvent e){ //Gestion des évenements avec l'appui sur les différents boutons

        if (e.getSource()== boutonJouer && !jeuIsRunning){
			fenetreMonde.setVisible(true);
			this.setVisible(false);
        }
        if (e.getSource()== boutonParam){
            parametres.setVisible(true);
        }
        if (e.getSource()==boutonCredits){
            Object[] message = {
                    "             PINFORMER",
                    "            Jeu cree par",
                    " Julie, Zineb, Thibaut et Adrien",
                    "    PROJET IF - S4 - 2018/2019"
            };
            JOptionPane.showMessageDialog(this,message,"CREDITS",JOptionPane.INFORMATION_MESSAGE);
        }
        if (e.getSource()==boutonQuitter){
            System.exit(0);
        }
    }

    private void playSound(String str){ //Méthode prise et adapté d'un tuto JAVA permettant de lancer une des musiques de notre projet
        try{
            String s = "/musiques/"+str;
            AudioInputStream AudioInput = AudioSystem.getAudioInputStream(getClass().getResource(s));

            int bytesPerFrame = AudioInput.getFormat().getFrameSize();
            int numBytes = 1024 * bytesPerFrame;
            byte[] tableau = new byte[numBytes];


            AudioFormat audioFormat = AudioInput.getFormat();
            DataLine.Info Info = new DataLine.Info(SourceDataLine.class,audioFormat);

            SourceDataLine line=(SourceDataLine)AudioSystem.getLine(Info);
            line.open(audioFormat);
            line.start();

            int nb;
            while ( (nb = AudioInput.read(tableau,0,numBytes )) != -1 ){
                line.write(tableau,0,nb);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void init(){
        File Nparam = new File("./param.txt");
        File Nmondes = new File("./mondes.txt");

        try {
            if (!Nparam.exists()) { //on sors le fichier param du JAR
                String param = chargementFichier.chargement("sauvegardes/param.txt", true);
                String[] separation = param.split("\\s+");
                chargementFichier.ecritureParam("./param.txt",separation);
            }

            if (!Nmondes.exists()) { //On sort le fichier mondes du JAR
                Nmondes.createNewFile();
                String param = chargementFichier.chargement("sauvegardes/mondes.txt", true);
                String[] separation = param.split("\\s+");
                chargementFichier.ecritureParam("./mondes.txt",separation);
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
