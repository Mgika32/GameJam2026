package Monster;

import Entity.entity;
import main.GamePanel;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

public class MON_GreenSlime extends entity {

    GamePanel gp;

    public MON_GreenSlime(GamePanel gp) {
        this.gp = gp;

        name = "Green Slime";
        speed = 1; // Il est lent
        maxLife = 4;
        life = maxLife;
        type = 2; // 0 = player, 1 = npc, 2 = monster

        solidArea = new Rectangle(3, 18, 42, 30); // Hitbox
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }

    public void setAction() {
        // IA : Changement de direction aléatoire toutes les 2 secondes (120 frames)
        actionLockCounter++;

        if(actionLockCounter == 120) {
            Random random = new Random();
            int i = random.nextInt(100) + 1; // Nombre entre 1 et 100

            if(i <= 25) { direction = "up"; }
            if(i > 25 && i <= 50) { direction = "down"; }
            if(i > 50 && i <= 75) { direction = "left"; }
            if(i > 75 && i <= 100) { direction = "right"; }

            actionLockCounter = 0;
        }
    }

    public void update() {
        setAction();

        collisionOn = false;
        gp.cChecker.checkTile(this);
        gp.cChecker.checkObject(this, false);
        
        // Vérifier si le monstre touche le joueur
        boolean contactPlayer = gp.cChecker.checkPlayer(this);

        if(this.type == 2 && contactPlayer == true) {
            if(gp.player1.invincible == false) {
                // Le joueur prend des dégâts
                gp.player1.life -= 1; 
                gp.player1.invincible = true; // Active l'invincibilité temporaire
            }
        }

        // Si pas de collision, on bouge
        if(collisionOn == false) {
            switch(direction) {
                case "up": worldY -= speed; break;
                case "down": worldY += speed; break;
                case "left": worldX -= speed; break;
                case "right": worldX += speed; break;
            }
        }
    }
    
    // Dessin simple (Carré vert) si tu n'as pas d'images
    public void draw(Graphics2D g2) {
        int screenX = worldX - gp.player1.worldX + gp.player1.screenX;
        int screenY = worldY - gp.player1.worldY + gp.player1.screenY;

        g2.setColor(Color.GREEN);
        g2.fillRect(screenX, screenY, gp.tileSize, gp.tileSize);
    }
}