package main;

import DisplayObject.SuperDisplayObject;
import Entity.Player;
import Entity.entity;
import Mask.MultiMask;
import Mask.SuperMask;
import Projectile.Projectile;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import javax.swing.JPanel;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable{

    // SCREEN SETTINGS
    final int originalTileSize = 32; //32x32 pixels
    final int scale = 1; 

    public final int tileSize = originalTileSize * scale; //32
    public final int maxScreenCol = 50; // largeur
    public final int maxScreenRow = 30; // longueur
    public final int screenWidth = tileSize * maxScreenCol; // 768 pixels largeur d'écran
    public final int screenHeight = tileSize * maxScreenRow; // 576 pixels longueur d'écran

    // WORLD SETTINGS
    public int maxWorldCol = 50;
    public int maxWorldRow = 70;

    // FPS
    int FPS = 60;

    KeyHandler keyH = new KeyHandler(this);
    Thread gameThread;
    public Player player1 = new Player(this, keyH);

    //INSTANCE
    TileManager TileM = new TileManager(this);
    MultiMask mask0 = new MultiMask();
 
    public SuperMask[] Mask = new SuperMask[10];
    public SuperDisplayObject[] display = new SuperDisplayObject[100];
    public entity monster[] = new entity[20];

    public CollisionChecker cChecker = new CollisionChecker(this);
    public EventHandler eHandler = new EventHandler(this);

    AssetSetter aSetter = new AssetSetter(this);
    public boolean under = true;
    public double currentMap = 0;


    public ArrayList<Projectile> projectileList = new ArrayList<>();


    public GamePanel() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setupGame() {

        aSetter.setMaskMap0();
        aSetter.setDisplayObjectMap0();
        
        
    }

    public void startGameThread() {

        gameThread = new Thread(this);
        gameThread.start();

    }
    public void run(){

        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;


        while (gameThread != null) {
            
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;

            lastTime = currentTime;

            if (delta >= 1) {
                // 1 UPDATE : mettre a jour les informations comme la position du joueur
                update();
                // 2 DRAW : dessiner l'écran avec les informations a jour
                repaint();
                delta--; 

            }
            
            

        }
    }

    public void resetEntities() {
        for (int i = 0; i < display.length; i++) {
            display[i] = null;
        }
        for (int i = 0; i < Mask.length; i++) {
            Mask[i] = null;
        }
        aSetter.setDisplayObjectMap0();
        aSetter.setMaskMap0();
    }

    public void update() {


        player1.update();

        if (currentMap == 0) {
            eHandler.checkEventMap0();
        } 
        else if (currentMap == 1) {
            eHandler.checkEventMap1();
        }  
        else if (currentMap == 2) { 
            eHandler.checkEventMap2();
        }
        else if (currentMap == 21) {
            eHandler.checkEventMap2_1();
        }
        else if (currentMap == 3) {
            eHandler.checkEventMap3();
        }
        else if (currentMap == 4) {
            eHandler.checkEventMap4();
        }
        else if (currentMap == 5) {
            eHandler.checkEventMap5();
        }
        else if (currentMap == 6) {
            eHandler.checkEventMap6();
        }
        for(int i = 0; i < projectileList.size(); i++) {
            if(projectileList.get(i) != null) {
                if(projectileList.get(i).alive == true) {
                    // Si la balle est vivante, on la met à jour (déplacement, collision)
                    projectileList.get(i).update();
                }
                if(projectileList.get(i).alive == false) {
                    // Si elle est morte (a touché un mur), on la supprime de la liste
                    projectileList.remove(i);
                    i--; // Important : on décrémente i car la liste a rétréci
                }
            }
        }

        for(int i = 0; i < monster.length; i++) {
            if(monster[i] != null) {
                if(monster[i].alive == true && monster[i].dying == false) {
                    monster[i].update();
                }
                if(monster[i].alive == false) {
                    monster[i] = null; // On supprime le monstre mort
                }
            }
}
        
    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        TileM.draw(g2);

        
        

        for (int i = 0; i < Mask.length; i++) {
            if (Mask[i] != null ) {
                Mask[i].draw(g2, this);
            }
        }


        if (under == true) {
            player1.draw(g2);

            for (int j = 0; j < display.length; j ++) {
                if (display[j] != null) {
                    display[j].draw(g2, this);
                }
            }
        }
        else {

            for (int j = 0; j < display.length; j ++) {
                if (display[j] != null) {
                    display[j].draw(g2, this);
                }
            }

            player1.draw(g2);
        }

        for(int i = 0; i < projectileList.size(); i++) {
            if(projectileList.get(i) != null && projectileList.get(i).alive == true) {
                projectileList.get(i).draw(g2);
            }
        }

        for(int i = 0; i < monster.length; i++) {
            if(monster[i] != null) {
                monster[i].draw(g2);
            }
    }
        
        // affichage du nombre de mask 
        g2.setFont(g2.getFont().deriveFont(20F));
        g2.setColor(Color.WHITE);
        g2.drawString("X " + player1.maskCount, 3*tileSize, 3*tileSize);

        g2.dispose();

    }
}

