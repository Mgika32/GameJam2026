package Projectile;

import main.GamePanel;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class OBJ_Bullet_Yellow extends Projectile {

    GamePanel gp;

    public OBJ_Bullet_Yellow(GamePanel gp) {
        super(gp);
        this.gp = gp;

        name = "Yellow Bullet";
        speed = 10; // Beaucoup plus rapide que le joueur
        maxLife = 80; // La balle disparaît après 80 frames si elle ne touche rien
        life = maxLife;
        attack = 1; // Dégâts de la balle (pour plus tard)
        alive = false;

        // Taille de la hitbox de la balle (petit carré)
        solidArea = new Rectangle(12, 12, 8, 8);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }

    // On dessine un trait jaune au lieu d'une image
    public void draw(Graphics2D g2) {
        
        int screenX = worldX - gp.player1.worldX + gp.player1.screenX;
        int screenY = worldY - gp.player1.worldY + gp.player1.screenY;

        g2.setColor(Color.RED);
        
        // On dessine un rectangle fin selon la direction pour faire un trait
        switch(direction) {
            case "up":
            case "down":
                 // Trait vertical (plus haut que large)
                 g2.fillRect(screenX + 14, screenY + 8, 4, 16);
                 break;
            case "left":
            case "right":
                 // Trait horizontal (plus large que haut)
                 g2.fillRect(screenX + 8, screenY + 14, 16, 4);
                 break;
        }
    }
}