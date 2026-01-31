package main;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import DisplayObject.Button;

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

    public void checkEventMap0() {

        if (hit(24, 6, "any", false, false)) {switchMap(1, "/res/map/map1.txt",50,50,26,47);}
        if (hit(25, 6, "any", false, false)) {switchMap(1, "/res/map/map1.txt",50,50,26,47);}
        if (hit(26, 6, "any", false, false)) {switchMap(1, "/res/map/map1.txt",50,50,26,47);}

    }

    public void checkEventMap1() {
        if (hit(26, 49, "any", false, false)) {switchMap(0, "/res/map/map0.txt",50,50,25,8);}
        if (hit(27, 49, "any", false, false)) {switchMap(0, "/res/map/map0.txt",50,50,25,8);}
        if (hit(25, 49, "any", false, false)) {switchMap(0, "/res/map/map0.txt",50,50,25,8);}
        if (hit(32, 23, "up", false, false)) {gp.under = false; System.out.println("Under: " + gp.under);}
        if (hit(32, 23, "down", false, false)) {gp.under = true; System.out.println("Under: " + gp.under);}
        if (hit(31, 23, "up", false, false)) {gp.under = false; System.out.println("Under: " + gp.under);}
        if (hit(31, 23, "down", false, false)) {gp.under = true; System.out.println("Under: " + gp.under);}
    }

    public boolean hit(int eventCol, int eventRow, String reqDirection, boolean isLarge, boolean isHeight) {
        boolean hit = false;

        gp.player1.solidArea.x = gp.player1.worldX + gp.player1.solidArea.x;
        gp.player1.solidArea.y = gp.player1.worldY + gp.player1.solidArea.y;

        Rectangle targetRect = isLarge ? eventRectLarge : eventRect;
        
        targetRect.x = eventCol * gp.tileSize + eventRectDefaultX;
        targetRect.y = eventRow * gp.tileSize + eventRectDefaultY;

        // 3. Vérifier l'intersection
        if (gp.player1.solidArea.intersects(targetRect)) {
            if (gp.player1.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
                hit = true;
            }
        }

        gp.player1.solidArea.x = gp.player1.solidAreaDefaultX;
        gp.player1.solidArea.y = gp.player1.solidAreaDefaultY;
        
        
        return hit;
    }

    public void hack() {
        
    }

    public void takeDamage(int damage) {
        gp.player1.life -= damage;
        if (gp.player1.life < 0) {
            gp.player1.life = 0;
        }
        System.out.println("Player life: " + gp.player1.life);
    }

    public void heal(int heal) {
        gp.player1.life += heal;
        if (gp.player1.life > gp.player1.maxLife) {
            gp.player1.life = gp.player1.maxLife;
        }
        System.out.println("Player life: " + gp.player1.life);
    }

    public void switchMap(int mapIndex, String path, int cols, int rows, int spawnX, int spawnY) {
        gp.currentMap = mapIndex;
        
        gp.resetEntities(); 
        
        gp.TileM.loadMap(path, cols, rows);
        
        gp.player1.worldX = spawnX * gp.tileSize;
        gp.player1.worldY = spawnY * gp.tileSize;

        switch (mapIndex) {
            case 0:
                gp.aSetter.setDisplayObjectMap0();
                gp.aSetter.setMaskMap0();
                break;
        
            case 1:
                gp.aSetter.setDisplayObjectMap1();
                gp.aSetter.setMaskMap1();
                gp.player1.speed = 5;
                break;
            default:
                break;
        }
        
}


}
