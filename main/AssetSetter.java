package main;

import DisplayObject.*;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setMaskMap0() {
        gp.Mask[0] = new Mask.MultiMask();
        gp.Mask[0].worldX = 10 * gp.tileSize;
        gp.Mask[0].worldY = 7 * gp.tileSize;

        gp.Mask[1] = new Mask.BorelMask();
        gp.Mask[1].worldX = 25 * gp.tileSize;
        gp.Mask[1].worldY = 4 * gp.tileSize;
    }
    public void setDisplayObjectMap0() {
        gp.display[0] = new DisplayBridge();
        gp.display[0].worldX = 25* gp.tileSize;
        gp.display[0].worldY = 12 * gp.tileSize;

    }

    public void setMaskMap1() {
        gp.Mask[0] = new Mask.BorelMask();
        gp.Mask[0].worldX = 15 * gp.tileSize;
        gp.Mask[0].worldY = 45 * gp.tileSize;

        gp.Mask[1] = null;
    }
    public void setDisplayObjectMap1() {
        
        gp.display[0] = new DisplayBridge();
        gp.display[0].worldX = 21* gp.tileSize;
        gp.display[0].worldY = 19 * gp.tileSize;

        gp.display[1] = new DisplayBridge();
        gp.display[1].worldX = 22* gp.tileSize;
        gp.display[1].worldY = 19 * gp.tileSize;

        gp.display[2] = new DisplayBridge();
        gp.display[2].worldX = 23* gp.tileSize;
        gp.display[2].worldY = 19 * gp.tileSize;

        gp.display[3] = new DisplayBridge();
        gp.display[3].worldX = 24* gp.tileSize;
        gp.display[3].worldY = 19 * gp.tileSize;

        gp.display[4] = new DisplayBridge();
        gp.display[4].worldX = 25* gp.tileSize;
        gp.display[4].worldY = 19 * gp.tileSize;

        gp.display[5] = new DisplayBridge();
        gp.display[5].worldX = 26* gp.tileSize;
        gp.display[5].worldY = 19 * gp.tileSize;

        gp.display[6] = new DisplayBridge();
        gp.display[6].worldX = 27* gp.tileSize;
        gp.display[6].worldY = 19 * gp.tileSize;

        gp.display[7] = new DisplayBridge();
        gp.display[7].worldX = 28* gp.tileSize;
        gp.display[7].worldY = 19 * gp.tileSize;

        gp.display[8] = new DisplayBridge();
        gp.display[8].worldX = 29* gp.tileSize;
        gp.display[8].worldY = 19 * gp.tileSize;

        gp.display[9] = new DisplayBridge();
        gp.display[9].worldX = 21* gp.tileSize;
        gp.display[9].worldY = 20 * gp.tileSize;

        gp.display[10] = new DisplayBridge();
        gp.display[10].worldX = 22* gp.tileSize;
        gp.display[10].worldY = 20 * gp.tileSize;

        gp.display[11] = new DisplayBridge();
        gp.display[11].worldX = 23* gp.tileSize;
        gp.display[11].worldY = 20 * gp.tileSize;

        gp.display[12] = new DisplayBridge();
        gp.display[12].worldX = 24* gp.tileSize;
        gp.display[12].worldY = 20 * gp.tileSize;

        gp.display[13] = new DisplayBridge();
        gp.display[13].worldX = 25* gp.tileSize;
        gp.display[13].worldY = 20 * gp.tileSize;

        gp.display[14] = new DisplayBridge();
        gp.display[14].worldX = 26* gp.tileSize;
        gp.display[14].worldY = 20 * gp.tileSize;

        gp.display[15] = new DisplayBridge();
        gp.display[15].worldX = 27* gp.tileSize;
        gp.display[15].worldY = 20 * gp.tileSize;

        gp.display[16] = new DisplayBridge();
        gp.display[16].worldX = 28* gp.tileSize;
        gp.display[16].worldY = 20 * gp.tileSize;

        gp.display[17] = new DisplayBridge();
        gp.display[17].worldX = 29* gp.tileSize;
        gp.display[17].worldY = 20 * gp.tileSize;

    }

    public void setMob() {

    }

}
