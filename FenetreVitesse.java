import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FenetreVitesse extends JFrame implements ActionListener {

    public JButton clique;
    public JLabel afficheT;
    private JPanel conteneur1;
    public int time = 10;
    public int  timesClicked = 0;
    public boolean aMisVitesse = false;
    private boolean timerLance = false;
    private FenetreMonde fenetreMonde;

    public FenetreVitesse(FenetreMonde fm) {
        this.fenetreMonde = fm;

        this.setTitle("Cliquer pour donner vitesse initiale");
        this.setSize(500,500);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        Font police = new Font(" Arial ", Font.BOLD, 18);

        clique = new JButton();
        clique.setFont(police);
        clique.setText("Cliquer");
        clique.setBounds(125, 350, 250, 50);
        clique.setBackground(Color.black);
        clique.setForeground(Color.white);
        clique.setFocusPainted(false);
        clique.addActionListener(this);

        afficheT = new JLabel();
        afficheT.setFont(police);
        afficheT.setBounds(125, 150, 250, 50);
        afficheT.setBackground(Color.PINK);

        afficheT.setText("Temps restant : " + time);

        JPanel conteneur1 = new JPanel();
        conteneur1.setLayout(null);
        conteneur1.setBounds(100, 125, 400, 475);
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
                ThreadTimer threadTimer = new ThreadTimer(this, fenetreMonde);
                threadTimer.start();
                timerLance = true;
            } else {
                if (time > 0) {
                    timesClicked++;
                    System.out.println("nombre de cliques : " + timesClicked);
                }
            }
        }
    }
}
