package Entity;

import java.awt.image.BufferedImage;
import java.awt.Rectangle;

public class entity { 

    public int worldX, worldY;
    public int speed;

    
    
    public boolean collisionOn = false;
    
    public int solidAreaDefaultX, solidAreaDefaultY;
    public String name;
    public BufferedImage temp;
    public BufferedImage image, image1, image2,image3,image4,image5,image6,image7,image8;
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public BufferedImage Fup1, Fup2, Fdown1, Fdown2, Fleft1, Fleft2, Fright1, Fright2;
    public String direction; 
    public boolean direction_right;
    public boolean direction_left;
    public int choice;

    public boolean spawn = true;
    public int spriteCounter = 0;
    public int spriteNum = 1;
    public Rectangle solidArea = new Rectangle(0, 0, 32, 32);
    public boolean collisionON = false;
    public int actionLockCounter = 0;
    public String dialogue[] = new String[20];
    int dialogueIndex = 0;

    // stats 
    public int maxLife;
    public int life;
}