import javax.imageio.ImageIO;
import java.io.File;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;


public class FenetreSelection extends JFrame implements ActionListener {

    private JButton boutonJouer;
    private JButton boutonParam;
    private JButton boutonCredits;
    private JButton boutonQuitter;
    private Jeu jeu;
    private FenetreParametres parametres;
    private JFrame menu;
    private Color couleur = new Color(166, 39, 86);

    public FenetreSelection(){

        this.jeu = new Jeu("Pinformer",1280,720);
        menu = new JFrame();
        menu.setTitle("Menu du jeu");
        menu.setSize(500,500);
        menu.setLocationRelativeTo(null);
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menu.setResizable(false);

        Font police = new Font(" Arial ",Font.BOLD,18);

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
		
		/**/
		JLabel imageFond;
		imageFond = new JLabel(new ImageIcon("./textures/fond_menu.png"));
		imageFond.setLocation(0,0);
		imageFond.setSize(500,500);
		conteneur1.add(imageFond);
		/**/
		
        menu.add(conteneur1);

        boutonJouer.addActionListener(this);
        boutonParam.addActionListener(this);
        boutonCredits.addActionListener(this);
        boutonQuitter.addActionListener(this);

        parametres = new FenetreParametres();

        menu.setVisible(true);

    }

    public void actionPerformed (ActionEvent e){

        if (e.getSource()== boutonJouer){
            jeu.start();
        }
        if (e.getSource()== boutonParam){
            parametres.setVisible(true);
        }
        if (e.getSource()==boutonCredits){
            JOptionPane.showMessageDialog(this,"A MODIFIER");
        }
        if (e.getSource()==boutonQuitter){
            JOptionPane.showMessageDialog(this,"A MODIFIER");
        }
    }
}
