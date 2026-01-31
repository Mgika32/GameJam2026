package Entity;

import Mask.SuperMask;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.GamePanel;
import main.KeyHandler;

public class Player extends entity{

    GamePanel gp;
    KeyHandler keyH;
    public SuperMask[] inventory = new SuperMask[6];

    public final int screenX;
    public final int screenY;

    public boolean multiMaskOn = false;
    public boolean borelMaskOn = false;


    public Player(GamePanel gp, KeyHandler keyH) {

        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

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
                    break;
                case "BorelMask":
                    gp.player1.inventory[1] = gp.Mask[i];
                    gp.player1.borelMaskOn = true;
                    gp.Mask[i] = null;
                    System.out.println("got borel");
                    break;
            }
        }

    }

    public void update() {
        if (keyH.upPressed == true ||keyH.downPressed == true ||keyH.leftPressed == true ||keyH.rightPressed == true ){


        if (keyH.upPressed == true){
            direction = "up";
        }
        else if (keyH.downPressed == true){
            direction = "down";
        }
        else if (keyH.rightPressed == true){
            direction = "right";
        }
        else if (keyH.leftPressed == true){
            direction = "left";
            
        }

        collisionOn = false;
        gp.cChecker.checkTile(this);

        int objIndex = gp.cChecker.checkMask(this, true);
        pickUpMask(objIndex);

        
        switch (gp.currentMap) {

            case 0:
                
                break;
        
            
        }
        
        if (gp.currentMap == 0) {
            gp.eHandler.checkEventMap0();
        }
        
                

        

        if (collisionOn == false) {

            switch (direction) {
                case "up":
                    worldY -= speed;
                    break;
                case "down":
                    worldY += speed;
                    break;
                case "left":
                    worldX -= speed;
                    break;
                case "right":
                    worldX += speed;
                    break;
            
                default:
                    break;
            }

            spriteCounter ++;
            if (spriteCounter > 12) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                }
                else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }}
        else {
            spriteNum = 1;
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
