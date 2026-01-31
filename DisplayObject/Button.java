package DisplayObject;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Button extends SuperDisplayObject {
    
    public int state = 0; // 0: Inactif, 1: Actif, 2: Appuyé
    public int buttonID;
    
    // On crée trois variables pour stocker nos textures
    public BufferedImage imgInactif, imgActif, imgAppuye;

    public Button(int id) {
        name = "Button";
        buttonID = id;
        collision = false;
        solidArea = new Rectangle(4, 4, 24, 24);
        
        getButtonImages();
    }

    public void getButtonImages() {
        try {
            imgInactif = ImageIO.read(getClass().getResourceAsStream("/res/DisplayObject/PCBlackScreen.png"));
            imgActif = ImageIO.read(getClass().getResourceAsStream("/res/DisplayObject/PCBlueScreen.png"));
            imgAppuye = ImageIO.read(getClass().getResourceAsStream("/res/DisplayObject/PCRedScreen.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void draw(Graphics2D g2, main.GamePanel gp) {
        int screenX = worldX - gp.player1.worldX + gp.player1.screenX;
        int screenY = worldY - gp.player1.worldY + gp.player1.screenY;

        BufferedImage image = null;

        // On choisit l'image à dessiner selon l'état
        switch(state) {
            case 0: image = imgInactif; break;
            case 1: image = imgActif; break;
            case 2: image = imgAppuye; break;
        }
        
        // Dessin du sprite (si l'image est chargée)
        if (image != null) {
            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
        } else {
            // Sécurité : si l'image manque, on dessine quand même un petit carré
            g2.fillRect(screenX + 8, screenY + 8, gp.tileSize - 16, gp.tileSize - 16);
        }
    }
}