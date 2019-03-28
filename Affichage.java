import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Affichage {

    private JFrame frame;
    private Canvas panel;
    private JLabel temps;

    private String titre;
    private int largeur;
    private int hauteur;

    //Timer
    private double millisecs = 0;
    private int secs = 0;
    private int minutes = 0;

    private final double interval = 1000;

    public Affichage(String titre, int largeur, int hauteur) {
        this.titre = titre;
        this.largeur = largeur;
        this.hauteur = hauteur;

        frame = new JFrame(titre);
        frame.setSize(largeur, hauteur);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        //frame.setIgnoreRepaint(true);
        frame.setResizable(false);
        frame.setVisible(true);

        panel = new Canvas();
        panel.setPreferredSize(new Dimension(largeur, hauteur));
        panel.setMaximumSize(new Dimension(largeur, hauteur));
        panel.setMinimumSize(new Dimension(largeur, hauteur));
        panel.setFocusable(false);

        Font police = new Font(" Arial ", Font.BOLD, 19);

        temps=new JLabel("     Lancement");
        temps.setFont(police);
        temps.setBounds(largeur-170,10,150,30);

        this.frame.add(temps);
        this.frame.add(panel);
        this.frame.pack();
    }

    public JFrame getFrame(){
        return frame;
    }
    public Canvas getPanel() {
        return this.panel;
    }

    public void tickTimer (){
        this.millisecs+=this.interval;
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
}
