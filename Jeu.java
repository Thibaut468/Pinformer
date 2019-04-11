import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;

//Cette classe instancie et "manage" toutes les parties du jeu

public class Jeu implements Runnable, KeyListener {
    private Affichage affichage;
    private int largeur;
    private int hauteur;
    private String titre;

    private boolean sensDefil = false;
    private int waterX = 0;

    private boolean propulsion = true;

    //Thread
    private boolean running = false;
    private Thread t;
    private boolean dead = false;
    private boolean finish = false;
    
    //Starter
    private boolean starterEnCours = true;
    private int starterX = 0;
    private int starterY = 0;

    private boolean initialiser = false;

    //Gestion des touches
    private boolean[] keys; //0 HAUT, 1 BAS, 2 GAUCHE, 3 DROITE
    public boolean haut;
    public boolean gauche;
    public boolean droite;
    private int touche;

    //Textures
    public chargementImage textures;

    //Monde
    private Monde monde;
    private String[] dataMonde;
    private int niveau;
    private boolean saved = false;

    public Jeu(String titre, int largeur, int hauteur, int niveau, String[] dataMonde){
        this.largeur = largeur;
        this.hauteur = hauteur;
        this.titre = titre;
        this.dataMonde = dataMonde;
        this.niveau = niveau;

        this.keys = new boolean[4]; //on crée un tableau de 4 touches (haut bas gauche droite), activé = true, désactivé = false

        /** RECUPERATION DES PARAMETRES SUR LE FICHIER TEXTE **/
        String fichier = chargementFichier.chargement("./param.txt", false);
        String[] separation = fichier.split("\\s+");
        touche=Integer.parseInt(separation[0]);

        this.textures=new chargementImage(); //On initialise et charge tout les images

        /** CHOIX DU NIVEAU A LANCER **/

        switch (niveau) {
            case 1:
                this.monde = new Monde("./mondes/monde1.txt", this);
                break;
            case 2:
                this.monde = new Monde("./mondes/monde2.txt", this);
                break;
            case 3:
                this.monde = new Monde("./mondes/monde3.txt", this);
                break;
            case 4:
                this.monde = new Monde("./mondes/monde4.txt", this);
                break;
            default:
                this.monde = new Monde("./mondes/monde1.txt", this);
                break;
        }
    }

    private void init(){ //On initialise l'affichage
        affichage = new Affichage(titre, largeur, hauteur);
        this.affichage.getFrame().addKeyListener(this);
    }

    private void tick(){
        //On regarde les touches
        haut=keys[0];
        gauche=keys[2];
        droite=keys[3];
		
        //On update le monde
        if(initialiser) monde.tick();
    }

    private void aff(){ //Cette méthode gère complètement l'affichage de tous les composants
        //Affichage
        BufferStrategy buff = affichage.getPanel().getBufferStrategy();
        if (buff == null) {
            affichage.getPanel().createBufferStrategy(3);
            return;
        }
        Graphics g = buff.getDrawGraphics();
        g.clearRect(0, 0, largeur, hauteur);

        if(!dead && !finish) { //JEU EN COURS
            //Dessin
            g.drawImage(textures.backgroundJeu, 0, 0, null);
            if (!sensDefil) { //Défilement de l'eau sous le niveau avec inversion
                waterX -= 2;
            } else {
                waterX += 2;
            }
            if (waterX <= -1280) sensDefil = !sensDefil;
            if (waterX >= 0) sensDefil = !sensDefil;
            g.drawImage(textures.water, waterX, 656, null);

            monde.aff(g);

            //Affichage hauteur du starter

            if(starterEnCours){
				g.setColor(new Color(255, 39, 73));
				g.fillRect(starterX,starterY,15,15);
			}
            //Gère les buffeurs d'affichage
            buff.show();
            g.dispose();

        } if(dead && !finish){ //PERSONNAGE MORT - FIN DU JEU
            g.setFont(new Font("Courier", Font.BOLD,70));
            g.setColor(new Color(65, 15, 34));
            g.drawString("GAME OVER",400,720/2);
            buff.show();
            g.dispose();
        } if (finish && !dead){ //VICTOIRE - FIN DU JEU
            if(!saved) saveTime(); //On sauvegarde les résultats
			g.setFont(new Font("Courier", Font.BOLD,70));
            g.setColor(new Color(65, 15, 34));
            g.drawString("VICTOIRE !",400,720/2);
            buff.show();
            g.dispose();
		}
    }

    private void saveTime(){ //Si il y a victoire, sauvegarde du temps dans un fichier texte pour affichage du score et débloquage des niveaux
        saved=true;
        double totalms = affichage.getTotalms();
        String[] lignes = new String[4];
        System.arraycopy(dataMonde, 0, lignes, 0, lignes.length);

        if(totalms<Integer.parseInt(lignes[niveau-1]) || Integer.parseInt(lignes[niveau-1])==0) lignes[niveau-1]=Integer.toString((int)totalms);

        chargementFichier.ecritureParam("./mondes.txt", lignes);
    }

    public void run(){ //Cette méthode gère totalement la fluidité du jeu à un CAP de 60 FPS - Game Loop inspiré de game loop très connus trouvable sur des tutos JAVA

        init();

        //Start et Timer
        FenetreVitesse fenetreVitesse = new FenetreVitesse(this);

        //Gère l'affichage a une fréquence fixe de 60 FPS
        int fps = 60;
        double timePerTick = 1000000000 / fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long timer = 0;
        int ticks = 0;

        while(running){
            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            timer += now - lastTime;
            lastTime = now;

            if(delta >= 1){
                tick();
                aff();
                ticks++;
                delta--;
            }

            if(timer >= 1000000000){
                //System.out.println("FPS : " + ticks);
                ticks = 0;
                timer = 0;
            }
        }

        stop();

    }

    // méthode exécutée à chaque fois qu’une touche est enfoncée
    public void keyPressed(KeyEvent e) {
        if(touche==1){
            switch(e.getKeyCode()){
                case KeyEvent.VK_Z:
                    keys[0]=true;
                    break;
                case KeyEvent.VK_S:
                    keys[1]=true;
                    break;
                case KeyEvent.VK_Q:
                    keys[2]=true;
                    break;
                case KeyEvent.VK_D:
                    keys[3]=true;
                    break;
            }
        } else {
            switch(e.getKeyCode()){
                case KeyEvent.VK_UP:
                    keys[0]=true;
                    break;
                case KeyEvent.VK_DOWN:
                    keys[1]=true;
                    break;
                case KeyEvent.VK_LEFT:
                    keys[2]=true;
                    break;
                case KeyEvent.VK_RIGHT:
                    keys[3]=true;
                    break;
            }
        }
    }

    // méthode exécutée à chaque fois qu’une touche est relâchée
    public void keyReleased(KeyEvent e) {
        if(touche==1){
            switch(e.getKeyCode()){
                case KeyEvent.VK_Z:
                    keys[0]=false;
                    break;
                case KeyEvent.VK_S:
                    keys[1]=false;
                    break;
                case KeyEvent.VK_Q:
                    keys[2]=false;
                    break;
                case KeyEvent.VK_D:
                    keys[3]=false;
                    break;
            }
        } else {
            switch(e.getKeyCode()){
                case KeyEvent.VK_UP:
                    keys[0]=false;
                    break;
                case KeyEvent.VK_DOWN:
                    keys[1]=false;
                    break;
                case KeyEvent.VK_LEFT:
                    keys[2]=false;
                    break;
                case KeyEvent.VK_RIGHT:
                    keys[3]=false;
                    break;
            }
        }
    }

    public void keyTyped(KeyEvent e) {
        //Vide
    }

    public synchronized void start(){ //LANCEMENT DU THREAD - Synchronized pour éviter des multi thread desssus
        if(running){
            return;
        }
        running = true;
        t = new Thread(this);
        t.start();
    }

    private synchronized void stop(){ //FERMETURE DU THREAD
        if(!running) {
            return;
        }
        running = false;
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public void dead(){
        dead=true;
    } //GAME OVER
	
	public void finish(){
        finish=true;
    } //VICTOIRE

    /** GETTERS AND SETTERS **/

    public Monde getMonde(){ return this.monde; }

    public boolean getDead(){ return dead; }

    public boolean getFinish(){ return finish; }

    public Affichage getAffichage(){ return affichage; }

    public boolean getInit() { return initialiser;}

    public void setInit(boolean init) { this.initialiser = init; }

    public boolean getPropulsion(){ return this.propulsion; }

    public void setPropulsion(boolean p){ this.propulsion = p; }
    
    public void setStarterEnCours(boolean a) { this.starterEnCours = a; }
    
    public void setStarterX(int starterX){ this.starterX = starterX; }
    
    public void setStarterY(int starterY){ this.starterY = starterY; }

}
