package Monster;

import Entity.entity;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;
import main.GamePanel;

import javax.imageio.ImageIO;

public class MON_GreenSlime extends entity {

    GamePanel gp;

    public MON_GreenSlime(GamePanel gp) {
        this.gp = gp;

        try {
            imageLeft = ImageIO.read(getClass().getResourceAsStream("/res/mobs/fantome1.png")); 
            imageRight = ImageIO.read(getClass().getResourceAsStream("/res/mobs/fantome2.png"));
            image = imageLeft;
        } catch (Exception e) {
            e.printStackTrace();
        }
        name = "Green Slime";
        speed = 3; // 
        maxLife = 3;
        life = maxLife;
        type = 2; // 0 = player, 1 = npc, 2 = monster

        solidArea = new Rectangle(3, 18, 42, 30); // Hitbox
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }

    @Override
    public void setAction() {
        
        actionLockCounter++;

        // On met à jour la direction toutes les 60 frames (1 seconde)
        // Tu peux baisser ce chiffre (ex: 30 ou 40) pour le rendre plus réactif/agressif
        if(actionLockCounter == 60) {

            // 1. Calculer la distance (en nombre de tuiles pour simplifier)
            int xDistance = (worldX - gp.player1.worldX); // Positif si monstre est à droite
            int yDistance = (worldY - gp.player1.worldY); // Positif si monstre est en bas
            
            // On prend la valeur absolue pour savoir "à quel point" il est loin
            int absX = Math.abs(xDistance); // ex: distance de 300 pixels devient 300
            int absY = Math.abs(yDistance);

            // 2. DISTANCE D'AGGRO (ex: 12 tuiles * taille d'une tuile)
            // Si le joueur est à moins de 12 cases, on le chasse !
            if (absX < 12 * gp.tileSize && absY < 12 * gp.tileSize) {
                
                // On choisit l'axe où la distance est la plus grande pour se rapprocher efficacement
                if (absX > absY) {
                    // Le joueur est plus loin horizontalement, on bouge en X
                    if (worldX > gp.player1.worldX) {
                        direction = "left"; // Le monstre est à droite, il va à gauche
                    } else {
                        direction = "right";
                    }
                } else {
                    // Le joueur est plus loin verticalement, on bouge en Y
                    if (worldY > gp.player1.worldY) {
                        direction = "up"; // Le monstre est en bas, il monte
                    } else {
                        direction = "down";
                    }
                }
            } 
            // 3. Si le joueur est trop loin, mouvement aléatoire classique
            else {
                Random random = new Random();
                int i = random.nextInt(100) + 1;

                if(i <= 25) { direction = "up"; }
                if(i > 25 && i <= 50) { direction = "down"; }
                if(i > 50 && i <= 75) { direction = "left"; }
                if(i > 75 && i <= 100) { direction = "right"; }
            }

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
    
    public void draw(Graphics2D g2) {
        int screenX = worldX - gp.player1.worldX + gp.player1.screenX;
        int screenY = worldY - gp.player1.worldY + gp.player1.screenY;

        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }
}