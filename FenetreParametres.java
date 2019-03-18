import javax.swing.*;
import java.awt.*; 
import java.awt.event.*;

	public class FenetreParametres extends JFrame implements ActionListener{
		
		private JButton choix1;
		private JButton choix2;
		private JButton choix3;
		private JButton choix4;
		private JButton sauvegarde;
		
		private JRadioButton zqsd;
		private JRadioButton fleches;
		boolean unselected = false;
		boolean selected = true;
		private ButtonGroup choixtouche;
		
		private Color couleur = new Color(166, 39, 86);
		
		public FenetreParametres(){
			this.setTitle("Parametres"); 
			this.setLayout(null); 
			this.setSize(500,500); 
			this.setLocationRelativeTo(null);
			this.setResizable(false); 
			this.setVisible(false);	
			
			Font police = new Font(" Arial ",Font.BOLD,12);
			
			choix1 = new JButton();
			choix1.setFont(police);
			choix1.setText("Choix 1");
			choix1.setBounds(25,300,94,40);
			choix1.setBackground(couleur);
			choix1.setForeground(Color.white);
			
			choix2 = new JButton();
			choix2.setFont(police);
			choix2.setText("Choix 2");
			choix2.setBounds(144,300,94,40);
			choix2.setBackground(couleur);
			choix2.setForeground(Color.white);
			
			choix3 = new JButton();
			choix3.setFont(police);
			choix3.setText("Choix 3");
			choix3.setBounds(263,300,94,40);
			choix3.setBackground(couleur);
			choix3.setForeground(Color.white);
			
			choix4 = new JButton();
			choix4.setFont(police);
			choix4.setText("Choix 4");
			choix4.setBounds(382,300,94,40);
			choix4.setBackground(couleur);
			choix4.setForeground(Color.white);
			
			sauvegarde = new JButton();
			sauvegarde.setFont(police);
			sauvegarde.setText("Sauvegarder");
			sauvegarde.setBounds(200,420,120,40);
			sauvegarde.setBackground(couleur);
			sauvegarde.setForeground(Color.white);
			
			String text1 = "ZQSD";
			zqsd = new JRadioButton(text1, unselected);
			zqsd.setLocation(100,200);
			zqsd.setSize(100,40);
			zqsd.setFont(police);
			zqsd.setBackground(couleur);
			zqsd.setForeground(Color.white);
			
			String text2 = "FLECHES";
			fleches = new JRadioButton(text2,selected);
			fleches.setLocation(300,200);
			fleches.setSize(100,40);
			fleches.setFont(police);
			fleches.setBackground(couleur);
			fleches.setForeground(Color.white);
			
			choixtouche = new ButtonGroup();
			choixtouche.add(zqsd);
			choixtouche.add(fleches);
			
			
			JPanel conteneur1 = new JPanel();
			conteneur1.setLayout(null);
			conteneur1.setBounds(0,0,500,500);
			
			
			conteneur1.add(choix1);
			conteneur1.add(choix2);
			conteneur1.add(choix3);
			conteneur1.add(choix4);
			conteneur1.add(sauvegarde);
			
			conteneur1.add(zqsd);
			conteneur1.add(fleches);
			
			this.add(conteneur1);
			JLabel imageFondParametre;
			imageFondParametre = new JLabel(new ImageIcon("./textures/fond_menu.png"));
			imageFondParametre.setLocation(0,0);
			imageFondParametre.setSize(500,500);
			
			
			conteneur1.add(imageFondParametre);
			
		
			choix1.addActionListener(this);
			choix2.addActionListener(this);
			choix3.addActionListener(this);
			choix4.addActionListener(this);
			
			sauvegarde.addActionListener(this);
			
		}
		
		public void actionPerformed (ActionEvent e){
			
			
			
			if (e.getSource()== choix1){
				System.out.println("choix1");
			}
			if (e.getSource()== choix2){
				System.out.println("choix2");
			}
			if (e.getSource()== choix3){
				System.out.println("choix3");
			}
			if (e.getSource()== choix4){
				System.out.println("choix4");
			}
			if (e.getSource()== sauvegarde){
				System.out.println("choix sauvegardé");
				this.setVisible(false);
			}
			
		}
		
		
		
		
	} 
