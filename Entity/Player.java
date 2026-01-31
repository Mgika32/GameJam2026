package Entity;

import Mask.SuperMask;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.GamePanel;
import main.KeyHandler;
import Projectile.OBJ_Bullet_Yellow;
import Projectile.Projectile;

public class Player extends entity{

    GamePanel gp;
    KeyHandler keyH;
    public SuperMask[] inventory = new SuperMask[6];

    public int maskCount = 0;

    public final int screenX;
    public final int screenY;

    public int shotAvailableCounter = 0;
    Projectile projectile;

    public boolean multiMaskOn = false;
    public boolean borelMaskOn = false;

    public boolean invincible = false;
    public int invincibleCounter = 0;


    public Player(GamePanel gp, KeyHandler keyH) {

        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

        projectile = new OBJ_Bullet_Yellow(gp);

        setDefaultValues();
        getPlayerImage();


    } 
    public void setDefaultValues() {

        worldX = gp.tileSize * 25;
        worldY = gp.tileSize * 25;
        speed = 4;
        direction = "down";
    }
    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/res/PlayerMove/boy_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/res/PlayerMove/boy_up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/res/PlayerMove/boy_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/res/PlayerMove/boy_down_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/res/PlayerMove/boy_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/res/PlayerMove/boy_left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/res/PlayerMove/boy_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/res/PlayerMove/boy_right_2.png"));
        }catch(IOException e) {
            e.printStackTrace();
        }

    }

    public void pickUpMask (int i) {

        if (i != 999) {
            switch (gp.Mask[i].name) {
                case "MultiMask":
                    gp.player1.inventory[0] = gp.Mask[i];
                    gp.Mask[i] = null;
                    System.out.println("got multi");
                    gp.player1.multiMaskOn = true;
                    maskCount++;
                    break;
                case "BorelMask":
                    gp.player1.inventory[1] = gp.Mask[i];
                    gp.player1.borelMaskOn = true;
                    gp.Mask[i] = null;
                    System.out.println("got borel");
                    maskCount++;
                    break;
            }
        }

    }

    public void update() {
    

        if (keyH.spacePressed == true && shotAvailableCounter == 30) {

            int bulletX = this.worldX;
            int bulletY = this.worldY;

            switch(direction) {
                case "up":    bulletX += 8; bulletY -= 16; break;
                case "down":  bulletX += 8; bulletY += 32; break;
                case "left":  bulletX -= 16; bulletY += 8; break;
                case "right": bulletX += 32; bulletY += 8; break;
            }

            // 2. On active la balle
            projectile.set(bulletX, bulletY, this.direction, true, this);

            // 3. On l'ajoute à la liste du GamePanel
            gp.projectileList.add(projectile);

            // 4. On prépare la prochaine balle (sinon on ajouterait toujours la même référence)
            projectile = new OBJ_Bullet_Yellow(gp);

            // 5. On reset le cooldown
            shotAvailableCounter = 0;

            // Optionnel : Jouer un son de tir ici
        }

        // Gestion du cooldown (attendre 30 frames entre chaque tir)
        if(shotAvailableCounter < 30) {
            shotAvailableCounter++;
        }
        // On ne fait l'update que si une touche est pressée
        if (keyH.upPressed == true || keyH.downPressed == true || 
            keyH.leftPressed == true || keyH.rightPressed == true) {

            // 1. DÉFINIR LA DIRECTION
            if (keyH.upPressed == true) { direction = "up"; }
            else if (keyH.downPressed == true) { direction = "down"; }
            else if (keyH.leftPressed == true) { direction = "left"; }
            else if (keyH.rightPressed == true) { direction = "right"; }

            // 2. VÉRIFIER LES COLLISIONS (Reset à chaque update)
            collisionOn = false;
            
            // Vérifie les tuiles (murs)
            gp.cChecker.checkTile(this);
            
            // Vérifie les objets (boutons, ponts, collisions invisibles)
            int objIndex = gp.cChecker.checkObject(this, true);
            
            // Vérifie les masques
            int maskIndex = gp.cChecker.checkMask(this, true);

            // 3. ACTIONS ET INTERACTIONS
            // Si on touche un masque, on essaie de le ramasser
            pickUpMask(maskIndex);
            
            // Si on touche un objet, on vérifie si c'est un bouton
            if (objIndex != 999) {
                gp.eHandler.interactButton(objIndex);
            }

            // 4. VÉRIFIER LES ÉVÉNEMENTS (Changement de map, etc.)
            if (gp.currentMap == 0) {
                gp.eHandler.checkEventMap0();
            } else if (gp.currentMap == 1) {
                gp.eHandler.checkEventMap1();
            }

            // 5. SI AUCUNE COLLISION, LE JOUEUR PEUT BOUGER
            if (collisionOn == false) {
                switch (direction) {
                    case "up":    worldY -= speed; break;
                    case "down":  worldY += speed; break;
                    case "left":  worldX -= speed; break;
                    case "right": worldX += speed; break;
                }
            }

            // 6. ANIMATION DU SPRITE
            spriteCounter++;
            if (spriteCounter > 12) { // Vitesse de l'animation
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        } else {
            // Si aucune touche n'est pressée, on remet le sprite au repos
            spriteNum = 1;
        }

                // Gestion de l'invincibilité (clignotement ou délai)
        if(invincible == true) {
            invincibleCounter++;
            if(invincibleCounter > 60) { // 1 seconde d'invincibilité
                invincible = false;
                invincibleCounter = 0;
            }
        }
    }    

    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        
        switch(direction) {
            case "up":
                if(spriteNum == 1) {
                    image = up1;
                }
                if (spriteNum == 2) {
                    image = up2;
                }
                break;
            case "down":
                if(spriteNum == 1) {
                    image = down1;
                }
                if(spriteNum == 2) {
                    image = down2;
                }
                break;
            case "left":
                if(spriteNum == 1) {
                    image = left1;
                }
                if(spriteNum == 2) {
                    image = left2;
                }
                break;
            case "right":
                if(spriteNum == 1) {
                    image = right1;
                }
                if(spriteNum == 2) {
                    image = right2;
                }
                break;
        }
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }

}
