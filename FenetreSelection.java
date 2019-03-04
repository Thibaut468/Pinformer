import javax.swing.*;
import java.awt.*;
import java.awt.event.*; 


public class FenetreSelection extends JFrame implements ActionListener {

    private JButton boutonJouer;
    private JButton boutonParam;
    private JButton boutonCredits;
    private JButton boutonQuitter;
    private Jeu jeu;
    private FenetreParametres parametres;
    private JFrame menu;


    public FenetreSelection(){

        this.jeu = new Jeu("Pinformer",400,400);
        menu = new JFrame();
        menu.setTitle("Menu du jeu");
        menu.setSize(500,500);
        menu.setLocation(100,50);
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menu.setResizable(false);

        Font police = new Font(" Arial ",Font.BOLD,18);

        boutonJouer = new JButton();
        boutonJouer.setFont(police);
        boutonJouer.setText("JOUER");
        boutonJouer.setBounds(95,125,300,60);
        boutonJouer.setBackground(Color.black);
        boutonJouer.setForeground(Color.white);

        boutonParam = new JButton();
        boutonParam.setFont(police);
        boutonParam.setText("PARAMETRES");
        boutonParam.setBounds(95,200,300,60);
        boutonParam.setBackground(Color.black);
        boutonParam.setForeground(Color.white);

        boutonCredits = new JButton();
        boutonCredits.setFont(police);
        boutonCredits.setText("CREDITS");
        boutonCredits.setBounds(95,275,300,60);
        boutonCredits.setBackground(Color.black);
        boutonCredits.setForeground(Color.white);

        boutonQuitter = new JButton();
        boutonQuitter.setFont(police);
        boutonQuitter.setText("QUITTER");
        boutonQuitter.setBounds(95,350,300,60);
        boutonQuitter.setBackground(Color.black);
        boutonQuitter.setForeground(Color.white);




        JPanel conteneur1 = new JPanel();
        conteneur1.setLayout(null);
        conteneur1.setBounds(100,125,400,475);
        conteneur1.add(boutonJouer);
        conteneur1.add(boutonParam);
        conteneur1.add(boutonCredits);
        conteneur1.add(boutonQuitter);



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
