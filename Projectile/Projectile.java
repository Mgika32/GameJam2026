package Projectile;

import Entity.entity;
import main.GamePanel;
import java.awt.Graphics2D;

public class Projectile extends entity {

    entity user; 
    GamePanel gp;
    public boolean alive = false; 
    int life; 
    int attack;

    public Projectile(GamePanel gp) {
        this.gp = gp;
    }

    public void set(int worldX, int worldY, String direction, boolean alive, entity user) {
        this.worldX = worldX;
        this.worldY = worldY;
        this.direction = direction;
        this.alive = alive; // 'alive' permettra de savoir quand supprimer la balle
        this.user = user;
        this.life = this.maxLife; // Durée de vie avant disparition auto (optionnel)
    }

    public void update() {

        // 1. Vérifier les collisions avec les MURS (Tiles)
        collisionOn = false;
        gp.cChecker.checkTile(this);
        if(collisionOn == true) {
            alive = false; // La balle meurt si elle touche un mur
        }
        
        // 2. Vérifier les collisions avec les OBJETS/ENNEMIS
        // On utilise checkObject mais en mode "non-player" (false)
        int objIndex = gp.cChecker.checkObject(this, false);
        if(objIndex != 999) {
             // Si l'objet touché est solide ou destructible
             if(gp.display[objIndex].collision == true) {
                  alive = false; 
                  // Ici, tu pourras ajouter plus tard : gp.display[objIndex].takeDamage(attack);
             }
        }

        // 3. Déplacement si pas de collision
        if (alive == true) {
            switch (direction) {
                case "up": worldY -= speed; break;
                case "down": worldY += speed; break;
                case "left": worldX -= speed; break;
                case "right": worldX += speed; break;
            }
        }

        // 4. Durée de vie limitée (optionnel : la balle disparaît après un certain temps)
        life--;
        if(life <= 0) {
            alive = false;
        }

        int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
    
        if(monsterIndex != 999) {
            // On a touché un monstre !
            gp.monster[monsterIndex].life -= this.attack; // Le monstre perd de la vie
            this.alive = false; // La balle disparaît
            
            System.out.println("Monstre touché ! Vie restante : " + gp.monster[monsterIndex].life);

            if(gp.monster[monsterIndex].life <= 0) {
                gp.monster[monsterIndex].alive = false; // Le monstre meurt
            }
        }
    }
    
    // Cette méthode sera surchargée par la balle spécifique pour le dessin
    public void draw(Graphics2D g2) {
    }
}