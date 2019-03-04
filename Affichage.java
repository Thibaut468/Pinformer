package fr.pinformer;

import javax.swing.*;
import java.awt.*;

public class Affichage {

    private JFrame frame;
    private Canvas panel;

    private String titre;
    private int largeur;
    private int hauteur;

    public Affichage(String titre, int largeur, int hauteur) {
        this.titre = titre;
        this.largeur = largeur;
        this.hauteur = hauteur;

        frame = new JFrame(titre);
        frame.setSize(largeur, hauteur);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setIgnoreRepaint(true);
        frame.setResizable(false);
        frame.setVisible(true);

        panel = new Canvas();
        panel.setPreferredSize(new Dimension(largeur, hauteur));
        panel.setMaximumSize(new Dimension(largeur, hauteur));
        panel.setMinimumSize(new Dimension(largeur, hauteur));
        panel.setFocusable(false);

        this.frame.add(panel);
        this.frame.pack();
    }

    public JFrame getFrame(){
        return frame;
    }
    public Canvas getPanel() {
        return this.panel;
    }
}