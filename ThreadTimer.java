//Cette classe gère le second Thread de notre programme, il permet la gestion du temps pour le starter de départ
//Ensuite, sa fonction devient celle du timer sur la carte qui correspond au score du joueur

public class ThreadTimer extends Thread {

    private Jeu jeu;
    private FenetreVitesse fenetreVitesse;

    public ThreadTimer(FenetreVitesse fv, Jeu jeu) {
        this.jeu = jeu;
        this.fenetreVitesse = fv;
    }

    @Override
    public void run() {
        try {
            // pour 20 secondes
            for (int i = 0; i < 5; i++) {
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
                    // on disable le bouton clique parce que l'utilisateur ne doit plus pouvoir cliquer
                    fenetreVitesse.clique.setEnabled(false);

                    // si on est arrive a 0, on indique le nombre de fois que l utilisateur a clique puis on lance le jeu
                    //JOptionPane.showMessageDialog(null, "L utilisateur a clique " + fenetreVitesse.getTimesClicked()+ " fois en 5 secondes !");
                }
            }

            jeu.setInit(true); //On initialise alors le jeu, le monde devient dynamique
            fenetreVitesse.setVisible(false); //On cache cette fenetre
            //ON DONNE LE PULSE
            jeu.getMonde().getStarter().action(jeu.getMonde().getJoueur());
            jeu.setStarterEnCours(false);

            while(!jeu.getInit()){
                //attente pour s'assurer que tout est démarré
            }

            while(!jeu.getDead() && !jeu.getFinish()){ //Tant que le jeu est en cours, on tick le timer d'affichage pour incrément d'une seconde chaque seconde.
                jeu.getAffichage().tickTimer();
                sleep(1000);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

