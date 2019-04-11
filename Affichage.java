import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class Affichage {

    private JFrame frame;
    private Canvas panel;
    private JLabel temps;

    //Timer
    private double totalms = 0;
    private double millisecs = 0;
    private int secs = 0;
    private int minutes = 0;

    public Affichage(String titre, int largeur, int hauteur) {

        //On instancie et paramètre la JFrame qui affiche le jeu
        frame = new JFrame(titre);
        frame.setSize(largeur, hauteur);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);

        ImageIcon ico = new ImageIcon(getClass().getResource("/textures/fonds/icone.png"));
        frame.setIconImage(ico.getImage());

        //On utilise un panel de façon à pouvoir utiliser des buffer d'images (propre à la gestion des FPS)
        panel = new Canvas();
        panel.setPreferredSize(new Dimension(largeur, hauteur));
        panel.setMaximumSize(new Dimension(largeur, hauteur));
        panel.setMinimumSize(new Dimension(largeur, hauteur));
        panel.setFocusable(false);

        Font police = new Font(" Arial ", Font.BOLD, 19);

        //On instancie le timer
        temps=new JLabel("     Lancement");
        temps.setFont(police);
        temps.setBounds(largeur-170,10,150,30);

        this.frame.add(temps);
        this.frame.add(panel);
        this.frame.pack();
    }

    public void tickTimer (){ //AFFICHAGE DU TIMER
        double interval = 1000;
        this.millisecs+= interval;
        this.totalms+= interval;
        if(this.millisecs>=1000){
            this.secs++;
            this.millisecs-=1000;
        }
        if(this.secs>=60){
            this.minutes++;
            this.secs-=60;
        }

        String s = "  Temps : ";
        if(this.minutes<10) s+="0";
        s+=this.minutes+":";
        if(this.secs<10) s+="0";
        s+=this.secs;

        this.temps.setText(s);
    }

    /** GETTER AND SETTER **/

    public double getTotalms(){ return totalms; }

    public JFrame getFrame(){
        return frame;
    }

    public Canvas getPanel() {
        return this.panel;
    }
}
