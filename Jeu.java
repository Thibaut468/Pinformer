import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import javax.swing.Timer;

public class Jeu implements Runnable, KeyListener {
    private Affichage affichage;
    private int largeur;
    private int hauteur;
    private String titre;

    //Ticks
    private int x = 0;

    //Thread
    private boolean running = false;
    private Thread t;

    //Affichage
    private BufferStrategy buff;
    private Graphics g;

    //Gestion des touches
    private boolean[] keys; //0 HAUT, 1 BAS, 2 GAUCHE, 3 DROITE
    public boolean haut;
    public boolean bas;
    public boolean gauche;
    public boolean droite;

    //Objets
    private Joueur joueur;

    //Textures
    public chargementImage textures;

    public Jeu(String titre, int largeur, int hauteur){
        this.largeur = largeur;
        this.hauteur = hauteur;
        this.titre = titre;

        this.keys = new boolean[4];

        this.joueur=new Joueur(10,10,32,32,10,5,this);

        this.textures=new chargementImage();
    }

    private void init(){
        affichage = new Affichage(titre, largeur, hauteur);
        this.affichage.getFrame().addKeyListener(this);
    }

    private void tick(){
        //Gestion des fps
        x += 1;

        //Timer
        this.affichage.tickTimer();

        //On regarde les touches au début
        haut=keys[0];
        bas=keys[1];
        gauche=keys[2];
        droite=keys[3];

        //On update les objets
        joueur.tick();
    }

    private void aff(){
        buff = affichage.getPanel().getBufferStrategy();
        if(buff == null){
            affichage.getPanel().createBufferStrategy(3);
            return;
        }
        g = buff.getDrawGraphics();
        g.clearRect(0, 0, largeur, hauteur);

        //Dessin
        joueur.aff(g);

        //Gère les buffeurs d'affichage
        buff.show();
        g.dispose();
    }

    public void run(){

        init();

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

    // méthode exécutée à chaque fois qu’une touche est relâchée
    public void keyReleased(KeyEvent e) {
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
}
