package main;

import java.awt.Graphics2D;
import java.awt.Rectangle;

public class EventHandler {
    Rectangle eventRect;
    Rectangle eventRectLarge;
    int eventRectDefaultX, eventRectDefaultY;

    GamePanel gp;
    Graphics2D g2;

    public boolean pitDone = false;
    public boolean leverDone = false;
    public boolean healDone = false;
    public boolean tombFlower = false;
    public boolean H1 = false;
    boolean enter = false;

    public EventHandler(GamePanel gp){

        this.gp = gp;
        eventRect = new Rectangle();
        eventRect.x = 23;
        eventRect.y = 23;
        eventRect.width = gp.tileSize+5;
        eventRect.height = gp.tileSize+5;
        eventRectLarge = new Rectangle();
        eventRectLarge.x = 23;
        eventRectLarge.y = 23;
        eventRectLarge.width = gp.tileSize* 6;
        eventRectLarge.height = gp.tileSize* 5;
        eventRectDefaultX = eventRect.x;
        eventRectDefaultY = eventRect.y;
    }

    public void checkEvent() {

        if (hit(8, 16, "any", false, false)) {System.out.println("pute");}
    }

    

    

    


    

    

    public boolean hit(int eventCol, int eventRow, String reqDirection, boolean isLarge, boolean isHeight) {

        boolean hit = false;

        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
        eventRectLarge.x = eventCol*gp.tileSize + eventRectDefaultX;
        eventRectLarge.y = eventRow*gp.tileSize + eventRectDefaultY;
        eventRect.x = eventCol*gp.tileSize + eventRectDefaultX;
        eventRect.y = eventRow*gp.tileSize + eventRectDefaultY;


        if (isLarge == false) {
            if (gp.player.solidArea.intersects(eventRect)) {
                if(gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
                    hit = true;
                    if (gp.keyH.enterPressed == true) {
                        enter = true;
                    }
                }
            }
        }
        if( isLarge == true) {
            if (gp.player.solidArea.intersects(eventRectLarge)) {
                if(gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
                    hit = true;
                    if (gp.keyH.enterPressed == true) {
                        enter = true;
                    }
                }
            }
        }
        gp.player.solidArea.x = gp.player.solidAreaDefautlX;
        gp.player.solidArea.y = gp.player.solidAreaDefautlY;
        eventRect.x = eventRectDefaultX;
        eventRect.y = eventRectDefaultY;

        return hit;  
    }

    public void hack() {
        
    }


    

}
