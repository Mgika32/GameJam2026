package Mask;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;


import main.GamePanel;

public class SuperMask {

    public BufferedImage image, image2, image3;
    public String name;
    public boolean collision = false;
    public int worldX, worldY;
    public boolean spawn = true;
    public Rectangle solidArea = new Rectangle(0,0,48,48);
    public int solidAreaDefautlX = 0;
    public int solidAreaDefautlY = 0;
    public int x,y;
    

    public void draw(Graphics2D g2, GamePanel gp) {

        g2.drawImage(image , y*gp.tileSize, x*gp.tileSize, gp.tileSize, gp.tileSize, null);

    }

}
