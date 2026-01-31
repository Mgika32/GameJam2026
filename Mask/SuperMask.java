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

        int screenX = worldX - gp.player1.worldX + gp.player1.screenX;
        int screenY = worldY - gp.player1.worldY + gp.player1.screenY;

        if (worldX + gp.tileSize > gp.player1.worldX - gp.player1.screenX &&
            worldX - gp.tileSize < gp.player1.worldX + gp.player1.screenX &&
            worldY + gp.tileSize > gp.player1.worldY - gp.player1.screenY &&
            worldY - gp.tileSize < gp.player1.worldY + gp.player1.screenY) {

            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
        }
    }

}
