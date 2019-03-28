import javax.swing.*;

public class ThreadTimer extends Thread {

    private FenetreVitesse fenetreVitesse;
    private FenetreMonde fenetreMonde;

    public ThreadTimer(FenetreVitesse fv, FenetreMonde fm) {
        this.fenetreVitesse = fv;
        this.fenetreMonde = fm;
    }

    @Override
    public void run() {
        try {
            // pour 20 secondes
            for (int i = 0; i < 10; i++) {
                /*
                on decompte
                la valeur de la variable time et le texte du label afficheT A MODIFIER QUE ICI !!! SINON PROBLEME DACCES DE THREAD EN MEME TEMPS :(
                 */
                fenetreVitesse.time--;
                this.fenetreVitesse.afficheT.setText("Temps restant : " + fenetreVitesse.time);

                if (fenetreVitesse.time > 0) {
                    // on attend une seconde avant de continuer à compter
                    sleep(1000);
                } else {
                    // on disable le bouton clique parce que l'utilisateur dois plus pouvoir cliquer
                    fenetreVitesse.clique.setEnabled(false);

                    // si on est arrive a 0, on indique le nombre de fois que l utilisateur a clique puis on lance le jeu
                    JOptionPane.showMessageDialog(null, "L utilisateur a clique " + fenetreVitesse.timesClicked + " fois en 100 secondes !");

                    fenetreMonde.jeu = new Jeu("Pinformer",1280,720);
                    fenetreMonde.jeuIsRunning = true;
                    fenetreMonde.jeu.start();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

