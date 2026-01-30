package main;

import Entity.Player;
import Mask.MultiMask;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
    public final int maxWorldCol = 150;
    public final int maxWorldRow = 111;

    // FPS
    int FPS = 60;

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    Player player = new Player(this, keyH);

    //INSTANCE
    TileManager TileM = new TileManager(this);
    MultiMask mask0 = new MultiMask();


    public GamePanel() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
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

    public void update() {

        
    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        TileM.draw(g2);

        player.draw(g2);
        

        if (mask0.spawn == true) {
            mask0.draw(g2, this);
        }
        

        g2.dispose();

    }
}

