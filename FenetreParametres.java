import javax.swing.*;
import java.awt.*; 

	public class FenetreParametres extends JFrame {
		
		public FenetreParametres(){
			this.setTitle("Parametres"); 
			this.setLayout(null); 
			this.setSize(500,500); 
			this.setLocation(100,50); 
			this.setResizable(false); 
			this.setVisible(false);	
			
			JPanel conteneur1 = new JPanel();
			conteneur1.setLayout(null);
			conteneur1.setBounds(0,0,500,500);
			
			this.add(conteneur1);
			JLabel imageFondParametre;
			imageFondParametre = new JLabel(new ImageIcon("./textures/vert.jpg"));
			imageFondParametre.setLocation(0,0);
			imageFondParametre.setSize(500,500);
			
			
			conteneur1.add(imageFondParametre);
			
		}
	} 
