import javax.swing.*;
import java.awt.*; 
import java.awt.event.*;
import java.util.ArrayList;  

	public class FenetreParametres extends JFrame implements ActionListener{
		
		private JButton choix1;
		private JButton choix2;
		private JButton choix3;
		private JButton choix4;
		private JButton choix5;
		private JButton choix6;
		private JButton choix7;
		private JButton choix8;
		private JButton sauvegarde;
		
		private JRadioButton zqsd;
		private JRadioButton fleches;
		boolean unselected = false;
		boolean selected = true;
		private ButtonGroup choixtouche;
		
		private Color couleur = new Color(166, 39, 86);

		private String[] param = new String[2];

		private int choix;
		private int touche; //1 zqsd 2 fleches
		
		public FenetreParametres(){
			this.setTitle("Parametres"); 
			this.setLayout(null); 
			this.setSize(500,500); 
			this.setLocationRelativeTo(null);
			this.setResizable(false); 
			this.setVisible(false);	
			
			Font police1 = new Font(" Arial ",Font.BOLD,9);
			Font police2 = new Font(" Arial ",Font.BOLD,8);

			String fichier = chargementFichier.chargement("./sauvegardes/param.txt");
			String[] separation = fichier.split("\\s+");
			param[0]=separation[0];
			touche = Integer.parseInt(separation[0]);
			System.out.println("touche : "+touche);
			choix = Integer.parseInt(separation[1]);
			param[1]=separation[1];
            System.out.println("choix : "+choix);
			if(touche==1){
				selected = !selected;
				unselected = !unselected;
			}
			
			
			choix1 = new JButton();
			choix1.setFont(police1);
			choix1.setText("Tchatcheur");
			choix1.setBounds(34,186,100,40);
			choix1.setBackground(couleur);
			choix1.setForeground(Color.white);
			choix1.setFocusPainted(false);
			
			choix2 = new JButton();
			choix2.setFont(police1);
			choix2.setText("CdPoulet");
			choix2.setBounds(141,186,100,40);
			choix2.setBackground(couleur);
			choix2.setForeground(Color.white);
			choix2.setFocusPainted(false);
			
			choix3 = new JButton();
			choix3.setFont(police2);
			choix3.setText("Canard'tichaud");
			choix3.setBounds(250,186,100,40);
			choix3.setBackground(couleur);
			choix3.setForeground(Color.white);
			choix3.setFocusPainted(false);

			choix4 = new JButton();
			choix4.setFont(police1);
			choix4.setText("Licorne");
			choix4.setBounds(359,186,100,40);
			choix4.setBackground(couleur);
			choix4.setForeground(Color.white);
			choix4.setFocusPainted(false);
			
			choix5 = new JButton();
			choix5.setFont(police1);
			choix5.setText("Tripadvisor");
			choix5.setBounds(34,348,100,40);
			choix5.setBackground(couleur);
			choix5.setForeground(Color.white);
			choix5.setFocusPainted(false);
			
			choix6 = new JButton();
			choix6.setFont(police2);
			choix6.setText("Le Papa Pingouin");
			choix6.setBounds(141,348,100,40);
			choix6.setBackground(couleur);
			choix6.setForeground(Color.white);
			choix6.setFocusPainted(false);
			
			choix7 = new JButton();
			choix7.setFont(police1);
			choix7.setText("Urhino");
			choix7.setBounds(250,348,100,40);
			choix7.setBackground(couleur);
			choix7.setForeground(Color.white);
			choix7.setFocusPainted(false);
			
			choix8 = new JButton();
			choix8.setFont(police2);
			choix8.setText("Morse");
			choix8.setBounds(359,348,100,40);
			choix8.setBackground(couleur);
			choix8.setForeground(Color.white);
			choix8.setFocusPainted(false);
			
			sauvegarde = new JButton();
			sauvegarde.setFont(police1);
			sauvegarde.setText("Sauvegarder");
			sauvegarde.setBounds(200,420,120,40);
			sauvegarde.setBackground(couleur);
			sauvegarde.setForeground(Color.white);
			sauvegarde.setFocusPainted(false);
			
			String text1 = "ZQSD";
			zqsd = new JRadioButton(text1, unselected);
			zqsd.setLocation(100,125);
			zqsd.setSize(100,40);
			zqsd.setFont(police1);
			zqsd.setBackground(couleur);
			zqsd.setForeground(Color.white);
			zqsd.setFocusPainted(false);
			
			String text2 = "FLECHES";
			fleches = new JRadioButton(text2,selected);
			fleches.setLocation(300,125);
			fleches.setSize(100,40);
			fleches.setFont(police1);
			fleches.setBackground(couleur);
			fleches.setForeground(Color.white);
			fleches.setFocusPainted(false);
			
			choixtouche = new ButtonGroup();
			zqsd.setActionCommand("zqsd");
			fleches.setActionCommand("fleches");
			choixtouche.add(zqsd);
			choixtouche.add(fleches);
			
			
			JPanel conteneur1 = new JPanel();
			conteneur1.setLayout(null);
			conteneur1.setBounds(0,0,500,500);
			
			
			conteneur1.add(choix1);
			conteneur1.add(choix2);
			conteneur1.add(choix3);
			conteneur1.add(choix4);
			conteneur1.add(choix5);
			conteneur1.add(choix6);
			conteneur1.add(choix7);
			conteneur1.add(choix8);
			conteneur1.add(sauvegarde);
			
			conteneur1.add(zqsd);
			conteneur1.add(fleches);
			
			this.add(conteneur1);
			JLabel imageFondParametre;
			imageFondParametre = new JLabel(new ImageIcon("./textures/fond_parametres.png"));
			imageFondParametre.setLocation(0,0);
			imageFondParametre.setSize(500,500);
			
			
			conteneur1.add(imageFondParametre);
			
			
			if(choix==1) choix1.setBackground(Color.green);
			if(choix==2) choix2.setBackground(Color.green);
			if(choix==3) choix3.setBackground(Color.green);
			if(choix==4) choix4.setBackground(Color.green);
			if(choix==5) choix5.setBackground(Color.green);
			if(choix==6) choix6.setBackground(Color.green);
			if(choix==7) choix7.setBackground(Color.green);
			if(choix==8) choix8.setBackground(Color.green);
		
			choix1.addActionListener(this);
			choix2.addActionListener(this);
			choix3.addActionListener(this);
			choix4.addActionListener(this);
			choix5.addActionListener(this);
			choix6.addActionListener(this);
			choix7.addActionListener(this);
			choix8.addActionListener(this);
			
			sauvegarde.addActionListener(this);
			
			
			
		}
		
		public void actionPerformed (ActionEvent e){
			

			if (e.getSource()== choix1){
				System.out.println("choix1");
				param[1]="1";
				boutonMauve();
				choix1.setBackground(Color.green);
				
			}
			if (e.getSource()== choix2){
				System.out.println("choix2");
                param[1]="2";
                boutonMauve();
                choix2.setBackground(Color.green);
			}
			if (e.getSource()== choix3){
				System.out.println("choix3");
                param[1]="3";
                boutonMauve();
                choix3.setBackground(Color.green);
			}
			if (e.getSource()== choix4){
				System.out.println("choix4");
                param[1]="4";
                boutonMauve();
                choix4.setBackground(Color.green);
			}
			if (e.getSource()== choix5){
				System.out.println("choix5");
				param[1]="5";
				boutonMauve();
				choix5.setBackground(Color.green);
			}
			if (e.getSource()== choix6){
				System.out.println("choix6");
				param[1]="6";
				boutonMauve();
				choix6.setBackground(Color.green);
			}
			if (e.getSource()== choix7){
				System.out.println("choix7");
				param[1]="7";
				boutonMauve();
				choix7.setBackground(Color.green);
			}
			if (e.getSource()== choix8){
				System.out.println("choix8");
				param[1]="8";
				boutonMauve();
				choix8.setBackground(Color.green);
			}
			if (e.getSource()== sauvegarde){
				System.out.println("choix sauvegardé");
				if(choixtouche.getSelection().getActionCommand().equals("zqsd")){
					touche = 1;
                    param[0]="1";
				}
				if(choixtouche.getSelection().getActionCommand().equals("fleches")){
					touche = 2;
                    param[0]="2";
				}

				chargementFichier.ecritureParam("./sauvegardes/param.txt", param);
				this.setVisible(false);
			}
			
		}
		public void boutonMauve(){
			choix1.setBackground(couleur);
			choix2.setBackground(couleur);
			choix3.setBackground(couleur);
			choix4.setBackground(couleur);
			choix5.setBackground(couleur);
			choix6.setBackground(couleur);
			choix7.setBackground(couleur);
			choix8.setBackground(couleur);
	}
		
	} 
