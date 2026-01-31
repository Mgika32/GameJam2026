package Entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import main.GamePanel;

public class entity {

    public GamePanel gp;
    
    // Position et Stats
    public int worldX, worldY;
    public int speed;
    public int maxLife;
    public int life;
    public int type; // 0 = player, 1 = npc, 2 = monster
    public String name;
    
    // Images
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public BufferedImage imageLeft, imageRight, image;
    public String direction = "down";
    
    // Animation
    public int spriteCounter = 0;
    public int spriteNum = 1;
    
    // Collision
    public Rectangle solidArea = new Rectangle(12, 12, 16, 16);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;

    // --- CES VARIABLES MANQUAIENT ET CAUSAIT L'ERREUR ---
    public boolean alive = true;
    public boolean dying = false;
    public boolean invincible = false;
    public int actionLockCounter = 0; // Pour le délai de l'IA
    public int invincibleCounter = 0;
    
    // Constructeur (optionnel selon ton code actuel)
    public entity(GamePanel gp) {
        this.gp = gp;
    }
    // Constructeur vide si besoin
    public entity() {}

    // --- CES MÉTHODES MANQUAIENT DANS LA CLASSE PARENTE ---

    public void setAction() {
        // Cette méthode sera écrasée (Override) par les monstres
    }

    public void update() {
        // L'IA du monstre définit l'action (bouger aléatoirement)
        setAction();

        collisionOn = false;
        gp.cChecker.checkTile(this);
        gp.cChecker.checkObject(this, false);
        gp.cChecker.checkEntity(this, gp.monster); // Collision entre monstres
        boolean contactPlayer = gp.cChecker.checkPlayer(this);

        if(this.type == 2 && contactPlayer == true) {
            if(gp.player1.invincible == false) {
                gp.player1.life -= 1;
                gp.player1.invincible = true;
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

        // Animation du sprite (marche)
        spriteCounter++;
        if(spriteCounter > 12) {
            if(spriteNum == 1) { spriteNum = 2; }
            else if(spriteNum == 2) { spriteNum = 1; }
            spriteCounter = 0;
        }
    }

    public void draw(Graphics2D g2) {
        // Méthode de dessin générique (si le monstre n'a pas sa propre méthode draw)
        // Tu peux laisser vide ou mettre ton switch d'images ici
        
        int screenX = worldX - gp.player1.worldX + gp.player1.screenX;
        int screenY = worldY - gp.player1.worldY + gp.player1.screenY;

        // Si tu as des images chargées dans la classe enfant, tu peux ajouter le code de dessin ici
        // Sinon, le @Override dans MON_GreenSlime prendra le relais
    }
}