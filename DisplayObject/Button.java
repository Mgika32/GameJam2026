package DisplayObject;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Button extends SuperDisplayObject {
    
    public int state = 0; // 0: Noir, 1: Bleu, 2: Rouge
    public int buttonID;  // L'ordre dans la chaîne (0, 1, 2...)

    public Button(int id) {
        name = "Button";
        buttonID = id;
        collision = false; // On peut marcher dessus
        solidArea = new Rectangle(4, 4, 24, 24);
    }

    // On remplace le dessin de l'image par un rectangle de couleur
    public void draw(Graphics2D g2, main.GamePanel gp) {
        int screenX = worldX - gp.player1.worldX + gp.player1.screenX;
        int screenY = worldY - gp.player1.worldY + gp.player1.screenY;

        switch(state) {
            case 0: g2.setColor(Color.BLACK); break;
            case 1: g2.setColor(Color.BLUE); break;
            case 2: g2.setColor(Color.RED); break;
        }
        
        g2.fillRect(screenX + 4, screenY + 4, gp.tileSize - 8, gp.tileSize - 8);
    }
}