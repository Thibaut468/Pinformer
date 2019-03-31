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
    private Color couleur = new Color(166, 39, 86);
    public Jeu jeu;
    public boolean jeuIsRunning = false;
    
    public FenetreMonde(){
		
		this.setTitle("Choix des niveaux"); 
		this.setLayout(null); 
		this.setSize(500,500); 
		this.setLocationRelativeTo(null);
		this.setResizable(false); 
		this.setVisible(false);	
		
		Font police = new Font(" Arial ",Font.BOLD,12);
		
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
		boutonNiveau2.setBackground(couleur);
		boutonNiveau2.setForeground(Color.white);
		boutonNiveau2.setFocusPainted(false);
		
		boutonNiveau3 = new JButton();
		boutonNiveau3.setFont(police);
		boutonNiveau3.setText("Niveau 3");
		boutonNiveau3.setBounds(75,250,150,50);
		boutonNiveau3.setBackground(couleur);
		boutonNiveau3.setForeground(Color.white);
		boutonNiveau3.setFocusPainted(false);
		
		boutonNiveau4 = new JButton();
		boutonNiveau4.setFont(police);
		boutonNiveau4.setText("Niveau 4");
		boutonNiveau4.setBounds(275,250,150,50);
		boutonNiveau4.setBackground(couleur);
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
        conteneur1.add(boutonNiveau1);
        conteneur1.add(boutonNiveau2);
        conteneur1.add(boutonNiveau3);
        conteneur1.add(boutonNiveau4);
        conteneur1.add(boutonRetour);
        
        JLabel imageFondMonde;
		imageFondMonde = new JLabel(new ImageIcon("./textures/fond_menu.png"));
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


        public void actionPerformed(ActionEvent e) {
		if (e.getSource()== boutonNiveau1 && !jeuIsRunning){

		    /*
		     avant de lancer le jeu, il faut lancer la fenetre calcul de vitesse
		     passe en parametres FenetreMonde pour qu'apres avoir compte le nombre
		     de cliques en 20 secondes, on lance le jeu
		      */
            jeu = new Jeu("Pinformer",1280,720);
            jeuIsRunning = true;
            jeu.start();
            this.setVisible(false);
            //FenetreVitesse fenetreVitesse = new FenetreVitesse(this);
        }
        if (e.getSource()==boutonNiveau2 && !jeuIsRunning){
			
		}
		if (e.getSource()==boutonNiveau3 && !jeuIsRunning){
			
		}
		if (e.getSource()==boutonNiveau4 && !jeuIsRunning){
			
		}
		if (e.getSource()==boutonRetour){
			this.setVisible(false);
		}
    }
}
