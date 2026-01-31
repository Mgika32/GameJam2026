package DisplayObject;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class boutonPorte extends SuperDisplayObject {
    
    public int state = 0; // 0: Noir, 1: Bleu, 2: Rouge
    public int buttonID;
    public int doorToOpen; // ID de la porte à ouvrir (-1 si c'est pour un puzzle)
    
    public BufferedImage imgInactif, imgActif, imgAppuye;

    // Constructeur modifié
    public boutonPorte(int id, int targetDoor) {
        name = "boutonPorte";
        buttonID = id;
        this.doorToOpen = targetDoor;
        
        collision = false; // On marche dessus
        solidArea = new Rectangle(4, 4, 24, 24);
        
        getButtonImages();
    }

    public void getButtonImages() {
        try {
            imgInactif = ImageIO.read(getClass().getResourceAsStream("/res/DisplayObject/button_black.png"));
            imgActif = ImageIO.read(getClass().getResourceAsStream("/res/DisplayObject/button_blue.png"));
            imgAppuye = ImageIO.read(getClass().getResourceAsStream("/res/DisplayObject/button_red.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void draw(Graphics2D g2, main.GamePanel gp) {
        int screenX = worldX - gp.player1.worldX + gp.player1.screenX;
        int screenY = worldY - gp.player1.worldY + gp.player1.screenY;

        BufferedImage image = null;

        switch(state) {
            case 0: image = imgInactif; break;
            case 1: image = imgActif; break;
            case 2: image = imgAppuye; break;
        }
        
        if (image != null) {
            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
        } else {
            g2.fillRect(screenX + 8, screenY + 8, gp.tileSize - 16, gp.tileSize - 16);
        }
    }
}