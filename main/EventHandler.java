package main;

import java.awt.Graphics2D;
import java.awt.Rectangle;

public class EventHandler {
    Rectangle eventRect;
    Rectangle eventRectLarge;
    int eventRectDefaultX, eventRectDefaultY;

    GamePanel gp;
    Graphics2D g2;


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

        if (hit(0, 0, "any", false, false )) {System.out.println("pute");}
        if (hit(24, 6, "any", false, false)&& gp.player.multiMaskOn == false) {gp.player.worldX = 0; gp.player.worldY = 0;}
        if (hit(25, 6, "any", false, false)&& gp.player.multiMaskOn == false) {gp.player.worldX = 0; gp.player.worldY = 0;}
        if (hit(26, 6, "any", false, false)&& gp.player.multiMaskOn == false) {gp.player.worldX = 0; gp.player.worldY = 0;}

    }

    public boolean hit(int eventCol, int eventRow, String reqDirection, boolean isLarge, boolean isHeight) {
        boolean hit = false;

        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;

        Rectangle targetRect = isLarge ? eventRectLarge : eventRect;
        
        targetRect.x = eventCol * gp.tileSize + eventRectDefaultX;
        targetRect.y = eventRow * gp.tileSize + eventRectDefaultY;

        // 3. Vérifier l'intersection
        if (gp.player.solidArea.intersects(targetRect)) {
            if (gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
                hit = true;
            }
        }

        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;
        
        
        return hit;
    }

    public void hack() {
        
    }


    

}
