package main;


public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setMask() {
        gp.Mask[0] = new Mask.MultiMask();
        gp.Mask[0].worldX = 10 * gp.tileSize;
        gp.Mask[0].worldY = 7 * gp.tileSize;
    }
    public void setNPC() {
        


    }

    public void setMob() {

    }

}
