import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;

public class FenetreMonde extends JFrame implements ActionListener {
    
    private JButton boutonNiveau1;
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
		boutonNiveau1.setBounds(100,20,100,40);
		boutonNiveau1.setBackground(couleur);
		boutonNiveau1.setForeground(Color.white);
		boutonNiveau1.setFocusPainted(false);
		
		
		
        JPanel conteneur1 = new JPanel();
        conteneur1.setLayout(null);
        conteneur1.setBounds(100,125,400,475);
        conteneur1.add(boutonNiveau1);
        
        this.add(conteneur1);
        
        boutonNiveau1.addActionListener(this);
		
    }


        public void actionPerformed(ActionEvent e) {
		if (e.getSource()== boutonNiveau1 && !jeuIsRunning){

		    /*
		     avant de lancer le jeu, il faut lancer la fenetre calcul de vitesse
		     passe en parametres FenetreMonde pour qu'apres avoir compte le nombre
		     de cliques en 20 secondes, on lance le jeu
		      */
            FenetreVitesse fenetreVitesse = new FenetreVitesse(this);
        }

    }
}
