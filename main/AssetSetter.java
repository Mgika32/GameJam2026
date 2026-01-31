package main;

import DisplayObject.*;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setMask() {
        gp.Mask[0] = new Mask.MultiMask();
        gp.Mask[0].worldX = 10 * gp.tileSize;
        gp.Mask[0].worldY = 7 * gp.tileSize;

        gp.Mask[1] = new Mask.BorelMask();
        gp.Mask[1].worldX = 25 * gp.tileSize;
        gp.Mask[1].worldY = 4 * gp.tileSize;
    }
    public void setDisplayObject() {
        gp.display[0] = new DisplayBridge();
        gp.display[0].worldX = 25* gp.tileSize;
        gp.display[0].worldY = 12 * gp.tileSize;


    }

    public void setMob() {

    }

}
