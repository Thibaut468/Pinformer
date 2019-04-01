import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.util.LinkedList;

public class Jeu implements Runnable, KeyListener {
    private Affichage affichage;
    private int largeur;
    private int hauteur;
    private String titre;

    //Ticks
    private int x = 0;
    private boolean sensDefil = false;
    private int waterX = 0;

    //Start et Timer
    private FenetreVitesse fenetreVitesse;
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

    //Affichage
    private BufferStrategy buff;
    private Graphics g;
    private boolean initialiser = false;

    //Gestion des touches
    private boolean[] keys; //0 HAUT, 1 BAS, 2 GAUCHE, 3 DROITE
    public boolean haut;
    public boolean bas;
    public boolean gauche;
    public boolean droite;
    private int touche;

    //Objets
    LinkedList<Entite> ent;

    //Textures
    public chargementImage textures;

    //Monde
    private Monde monde;

    //Musique

    public Jeu(String titre, int largeur, int hauteur){
        this.largeur = largeur;
        this.hauteur = hauteur;
        this.titre = titre;

        this.keys = new boolean[4];

        String fichier = chargementFichier.chargement("./sauvegardes/param.txt");
        String[] separation = fichier.split("\\s+");
        touche=Integer.parseInt(separation[0]);

        this.textures=new chargementImage();

        this.monde = new Monde("./mondes/monde1.txt", this);

    }

    private void init(){
        affichage = new Affichage(titre, largeur, hauteur);
        this.affichage.getFrame().addKeyListener(this);
    }

    private void tick(){
        //Gestion des fps
        x += 1;

        //On regarde les touches au début
        haut=keys[0];
        bas=keys[1];
        gauche=keys[2];
        droite=keys[3];
		
        //On update le monde
        if(initialiser) monde.tick();
    }

    private void aff(){
        buff = affichage.getPanel().getBufferStrategy();
        if (buff == null) {
            affichage.getPanel().createBufferStrategy(3);
            return;
        }
        g = buff.getDrawGraphics();
        g.clearRect(0, 0, largeur, hauteur);
        if(!dead && !finish) {
            //Dessin
            g.drawImage(textures.backgroundJeu, 0, 0, null);
            if (!sensDefil) {
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
				g.setColor(Color.red);
				g.fillRect(starterX,starterY,10,5);
			}
            //Gère les buffeurs d'affichage
            buff.show();
            g.dispose();
        } if(dead && !finish){
            g.setFont(new Font("Courier", Font.BOLD,70));
            g.setColor(new Color(65, 15, 34));
            g.drawString("GAME OVER",400,720/2);
            buff.show();
            g.dispose();
        } if (finish && !dead){
			g.setFont(new Font("Courier", Font.BOLD,70));
            g.setColor(new Color(65, 15, 34));
            g.drawString("VICTOIRE",400,720/2);
            buff.show();
            g.dispose();
		}
    }

    public void run(){

        init();

        fenetreVitesse = new FenetreVitesse(this);

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
                System.out.println("FPS : " + ticks);
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

    // méthode exécutée à chaque fois qu’une touche unicode est utilisée (donc pas CTRL, SHIFT ou ALT par exemple)
    public void keyTyped(KeyEvent e) {
        //Vide
    }

    public synchronized void start(){
        if(running){
            return;
        }
        running = true;
        t = new Thread(this);
        t.start();
    }

    public synchronized void stop(){
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

    public int getLargeur(){
        return this.largeur;
    }

    public int getHauteur(){
        return this.hauteur;
    }

    public Monde getMonde(){ return this.monde; }

    public void dead(){
        dead=true;
    }
	
	public void finish(){
        finish=true;
    }

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
