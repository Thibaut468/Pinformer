import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;

/** FENETRE DE CHOIX DES NIVEAUX **/

public class FenetreMonde extends JFrame implements ActionListener {
    
    private JButton boutonNiveau1;
    private JButton boutonNiveau2;
    private JButton boutonNiveau3;
    private JButton boutonNiveau4;
    private JButton boutonRetour;

    private JLabel timerN1;
	private JLabel timerN2;
	private JLabel timerN3;
	private JLabel timerN4;

	private Jeu jeu;
    private boolean jeuIsRunning = false;


	private String[] dataMonde = new String[4]; //4 lignes = 4 niveaux, temps pour chaque niveau en ms, 0 si non fini
	private int lastNivDebloque;
    
    public FenetreMonde(){

		this.setTitle("Choix des niveaux"); 
		this.setLayout(null); 
		this.setSize(500,500); 
		this.setLocationRelativeTo(null);
		this.setResizable(false); 
		this.setVisible(false);

		ImageIcon ico = new ImageIcon("./textures/fonds/icone.png");
		this.setIconImage(ico.getImage());

		/** Lecture du fichier texte contenant les parametres **/
		String fichier = chargementFichier.chargement("./sauvegardes/mondes.txt");
		String[] separation = fichier.split("\\s+");
		lastNivDebloque = 1;
		for(int i=0; i<separation.length;i++){
			dataMonde[i]=separation[i];
			if(!(Integer.parseInt(dataMonde[i])==0)) lastNivDebloque++;
		}

		Font police = new Font(" Arial ",Font.BOLD,12);
		Font police2 = new Font(" Arial ",Font.BOLD,15);
		Color couleur = new Color(166, 39, 86);

		/** On met en place tous les affichages de score **/
		timerN1 = new JLabel("Score : "+(Integer.parseInt(dataMonde[0])/1000)+" secondes", SwingConstants.CENTER);
		timerN1.setFont(police2);
		timerN1.setForeground(Color.white);
		timerN1.setBounds(75,110, 150, 50);

		timerN2 = new JLabel("Score : "+(Integer.parseInt(dataMonde[1])/1000)+" secondes", SwingConstants.CENTER);
		timerN2.setFont(police2);
		timerN2.setForeground(Color.white);
		timerN2.setBounds(275,110, 150, 50);

		timerN3 = new JLabel("Score : "+(Integer.parseInt(dataMonde[2])/1000)+" secondes", SwingConstants.CENTER);
		timerN3.setFont(police2);
		timerN3.setForeground(Color.white);
		timerN3.setBounds(75,210, 150, 50);

		timerN4 = new JLabel("Score : "+(Integer.parseInt(dataMonde[3])/1000)+" secondes", SwingConstants.CENTER);
		timerN4.setFont(police2);
		timerN4.setForeground(Color.white);
		timerN4.setBounds(275,210, 150, 50);

		/** Et chaque bouton **/

		boutonNiveau1 = new JButton();
		boutonNiveau1.setFont(police);
		boutonNiveau1.setText("Niveau 1");
		boutonNiveau1.setBounds(75,150,150,50);

		boutonNiveau1.setBackground(couleur);
		boutonNiveau1.setForeground(Color.white);
		boutonNiveau1.setFocusPainted(false);

		boutonNiveau2 = new JButton();
		boutonNiveau2.setFont(police);
		boutonNiveau2.setText("Niveau 2");
		boutonNiveau2.setBounds(275,150,150,50);
		if(lastNivDebloque>=2) boutonNiveau2.setBackground(couleur);
		else boutonNiveau2.setBackground(Color.gray);
		boutonNiveau2.setForeground(Color.white);
		boutonNiveau2.setFocusPainted(false);
		
		boutonNiveau3 = new JButton();
		boutonNiveau3.setFont(police);
		boutonNiveau3.setText("Niveau 3");
		boutonNiveau3.setBounds(75,250,150,50);
		if(lastNivDebloque>=3) boutonNiveau3.setBackground(couleur);
		else boutonNiveau3.setBackground(Color.gray);
		boutonNiveau3.setForeground(Color.white);
		boutonNiveau3.setFocusPainted(false);
		
		boutonNiveau4 = new JButton();
		boutonNiveau4.setFont(police);
		boutonNiveau4.setText("Niveau 4");
		boutonNiveau4.setBounds(275,250,150,50);
		if(lastNivDebloque>=4) boutonNiveau4.setBackground(couleur);
		else boutonNiveau4.setBackground(Color.gray);
		boutonNiveau4.setForeground(Color.white);
		boutonNiveau4.setFocusPainted(false);
		
		boutonRetour = new JButton();
		boutonRetour.setFont(police);
		boutonRetour.setText("Retour");
		boutonRetour.setBounds(175,350,150,50);
		boutonRetour.setBackground(couleur);
		boutonRetour.setForeground(Color.white);
		boutonRetour.setFocusPainted(false);
		
        JPanel conteneur1 = new JPanel();
        conteneur1.setLayout(null);
        conteneur1.setBounds(0,0,500,500);
		conteneur1.add(timerN1);
		conteneur1.add(timerN2);
		conteneur1.add(timerN3);
		conteneur1.add(timerN4);
        conteneur1.add(boutonNiveau1);
        conteneur1.add(boutonNiveau2);
        conteneur1.add(boutonNiveau3);
        conteneur1.add(boutonNiveau4);
        conteneur1.add(boutonRetour);

        JLabel imageFondMonde;
		imageFondMonde = new JLabel(new ImageIcon("./textures/fonds/fond_menu.png"));
		imageFondMonde.setLocation(0,0);
		imageFondMonde.setSize(500,500);
	
		conteneur1.add(imageFondMonde);
		
        this.add(conteneur1);
        
        boutonNiveau1.addActionListener(this);
        boutonNiveau2.addActionListener(this);
		boutonNiveau3.addActionListener(this);
        boutonNiveau4.addActionListener(this);
        boutonRetour.addActionListener(this);
        
    }


        public void actionPerformed(ActionEvent e) { //Gestion des événements en fonction du bouton sélectionné
		if (e.getSource()== boutonNiveau1 && !jeuIsRunning){
            jeu = new Jeu("Pinformer",1280,720, 1, dataMonde);
            jeuIsRunning = true;
            jeu.start();
            this.setVisible(false);
        }
        if (e.getSource()==boutonNiveau2 && !jeuIsRunning && lastNivDebloque>=2){
			jeu = new Jeu("Pinformer",1280,720, 2, dataMonde);
			jeuIsRunning = true;
			jeu.start();
			this.setVisible(false);
		}
		if (e.getSource()==boutonNiveau3 && !jeuIsRunning && lastNivDebloque>=3){
			jeu = new Jeu("Pinformer",1280,720, 3, dataMonde);
			jeuIsRunning = true;
			jeu.start();
			this.setVisible(false);
		}
		if (e.getSource()==boutonNiveau4 && !jeuIsRunning && lastNivDebloque>=3){
			jeu = new Jeu("Pinformer",1280,720, 4, dataMonde);
			jeuIsRunning = true;
			jeu.start();
			this.setVisible(false);
		}
		if (e.getSource()==boutonRetour){
			this.setVisible(false);
		}
    }
}
