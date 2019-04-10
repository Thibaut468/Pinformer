import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Fenêtre permettant d'initialiser le départ et la vitesse de départ, déterminer avec la barre espace

public class FenetreVitesse extends JFrame implements ActionListener {

    public JButton clique;
    public JLabel afficheT;
    private JPanel conteneur1;
    public int time = 5;
    private int  timesClicked;
    private boolean timerLance = false;
    private Jeu jeu;
    
    private int y0;
    private int x0;

    public FenetreVitesse(Jeu jeu) {
        this.jeu = jeu;

        this.timesClicked=0;

        /** Mise en place d'une fenetre en relatif **/
        Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        int fheight = (int)dimension.getHeight();
        int fwidth  = (int)dimension.getWidth();

        this.setTitle("");
        this.setSize(200,200);
        this.setLocation(fwidth/2+350,fheight/2-250);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setResizable(false);

        ImageIcon ico = new ImageIcon("./textures/icone.png");
        this.setIconImage(ico.getImage());

        Font police = new Font(" Arial ", Font.BOLD, 16);


        clique = new JButton();
        clique.setFont(police);
        clique.setText("Appuyer Espace");
        clique.setBounds(8, 100, 170, 50);
        clique.setBackground(Color.black);
        clique.setForeground(Color.white);
        clique.setFocusPainted(false);
        clique.addActionListener(this);

        afficheT = new JLabel();
        afficheT.setFont(police);
        afficheT.setBounds(15, 25, 150, 50);
        afficheT.setBackground(Color.PINK);

        afficheT.setText("Temps restant : " + time);

        conteneur1 = new JPanel();
        conteneur1.setLayout(null);
        conteneur1.setBounds(0, 0, 200, 200);
        conteneur1.add(afficheT);
        conteneur1.add(clique);

        this.add(conteneur1);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == clique) {
            /*
             si c est le premier clique sur le bouton clique, cela veut dire que le timer n a pas encore ete lance
             lancer le timer a travers un thread afin que le temps soit decompte en meme temps que l utilisateur
             utilise l interface graphique en cliquant sur le bouton clique
             booleean timerLance : si le timer a deja ete lance donc on le lance pas dans ce cas la
             ou si le thread a pas encore été lancé donc c'est le premier clique
              */
            if (!timerLance) {
                ThreadTimer threadTimer = new ThreadTimer(this, jeu);
                threadTimer.start();
                timesClicked++;
                jeu.getMonde().getJoueur().setStarterVitesse(jeu.getMonde().getJoueur().getStarterVitesse()+1);
                initAffHauteur();
                affHauteur();
                timerLance = true;
            } else {
                if (time > 0) {
                    timesClicked++;
                    jeu.getMonde().getJoueur().setStarterVitesse(jeu.getMonde().getJoueur().getStarterVitesse()+1);
                    affHauteur();
                }
            }
        }
    }
    
    private void initAffHauteur(){ //On initialise l'affichage de la hauteur
		y0 = jeu.getMonde().getSpawnY()-48+24;
		x0 = jeu.getMonde().getSpawnX()+8+24;
	}
	
	private void affHauteur(){ //Affichage simple de la hauteur du saut, équations permettant de déterminer cette hauteur via les équations de trajectoires
		jeu.setStarterX((int) ((((-2*Math.sin(2*Math.PI/2.5)*Math.pow(timesClicked*9,2))/(2*9.8))+x0)));
		jeu.setStarterY((int) (y0-((Math.pow(timesClicked*9,2)*Math.sin(Math.PI/2.5)*Math.sin(2*Math.PI/2.5))/(2*9.8))));
	}

}
