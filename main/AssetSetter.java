package main;

import DisplayObject.*;
import Mask.SuperMask;
import Monster.MON_GreenSlime;

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

        gp.Mask[2] = new Mask.MultiMask();
        gp.Mask[2].worldX = 45 * gp.tileSize;
        gp.Mask[2].worldY = 12 * gp.tileSize;

        gp.Mask[3] = new Mask.BorelMask();
        gp.Mask[3].worldX = 12 * gp.tileSize;
        gp.Mask[3].worldY = 45 * gp.tileSize;
    }
    public void setDisplayObjectMap0() {
        
    }

    public void setMonster() {
        gp.monster[0] = new MON_GreenSlime(gp);
        gp.monster[0].worldX = gp.tileSize * 21;
        gp.monster[0].worldY = gp.tileSize * 2;

        gp.monster[1] = new MON_GreenSlime(gp);
        gp.monster[1].worldX = gp.tileSize * 23;
        gp.monster[1].worldY = gp.tileSize * 3;
    }

    public void setMaskMap1() {
        gp.Mask[0] = new Mask.MultiMask();
        gp.Mask[0].worldX = 41 * gp.tileSize;
        gp.Mask[0].worldY = 48 * gp.tileSize;

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

        gp.display[18] = new Collision();
        gp.display[18].worldX = 21* gp.tileSize;
        gp.display[18].worldY = 18 * gp.tileSize;

        gp.display[19] = new Collision();
        gp.display[19].worldX = 22* gp.tileSize;
        gp.display[19].worldY = 18 * gp.tileSize;

        gp.display[20] = new Collision();
        gp.display[20].worldX = 23* gp.tileSize;
        gp.display[20].worldY = 18 * gp.tileSize;

        gp.display[21] = new Collision();
        gp.display[21].worldX = 24* gp.tileSize;
        gp.display[21].worldY = 18 * gp.tileSize;

        gp.display[22] = new Collision();
        gp.display[22].worldX = 25* gp.tileSize;
        gp.display[22].worldY = 18 * gp.tileSize;

        gp.display[23] = new Collision();
        gp.display[23].worldX = 26* gp.tileSize;
        gp.display[23].worldY = 18 * gp.tileSize;

        gp.display[24] = new Collision();
        gp.display[24].worldX = 27* gp.tileSize;
        gp.display[24].worldY = 18 * gp.tileSize;

        gp.display[25] = new Collision();
        gp.display[25].worldX = 28* gp.tileSize;
        gp.display[25].worldY = 18 * gp.tileSize;

        gp.display[26] = new Collision();
        gp.display[26].worldX = 29* gp.tileSize;
        gp.display[26].worldY = 18 * gp.tileSize;

        gp.display[27] = new Collision();
        gp.display[27].worldX = 21* gp.tileSize;
        gp.display[27].worldY = 21 * gp.tileSize;

        gp.display[28] = new Collision();
        gp.display[28].worldX = 22* gp.tileSize;
        gp.display[28].worldY = 21 * gp.tileSize;

        gp.display[29] = new Collision();
        gp.display[29].worldX = 23* gp.tileSize;
        gp.display[29].worldY = 21 * gp.tileSize;

        gp.display[30] = new Collision();
        gp.display[30].worldX = 24 * gp.tileSize;
        gp.display[30].worldY = 21 * gp.tileSize;

        gp.display[31] = new Collision();
        gp.display[31].worldX = 25* gp.tileSize;
        gp.display[31].worldY = 21 * gp.tileSize;

        gp.display[32] = new Collision();
        gp.display[32].worldX = 26* gp.tileSize;
        gp.display[32].worldY = 21 * gp.tileSize;

        gp.display[33] = new Collision();
        gp.display[33].worldX = 27* gp.tileSize;
        gp.display[33].worldY = 21 * gp.tileSize;

        gp.display[34] = new Collision();
        gp.display[34].worldX = 28* gp.tileSize;
        gp.display[34].worldY = 21 * gp.tileSize;

        gp.display[35] = new Collision();
        gp.display[35].worldX = 29* gp.tileSize;
        gp.display[35].worldY = 21 * gp.tileSize;

        gp.display[36] = new DisplayBridge();
        gp.display[36].worldX = 21* gp.tileSize;
        gp.display[36].worldY = 18 * gp.tileSize;

        gp.display[37] = new DisplayBridge();
        gp.display[37].worldX = 22* gp.tileSize;
        gp.display[37].worldY = 18 * gp.tileSize;

        gp.display[38] = new DisplayBridge();
        gp.display[38].worldX = 23* gp.tileSize;
        gp.display[38].worldY = 18 * gp.tileSize;

        gp.display[39] = new DisplayBridge();
        gp.display[39].worldX = 24* gp.tileSize;
        gp.display[39].worldY = 18 * gp.tileSize;

        gp.display[40] = new DisplayBridge();
        gp.display[40].worldX = 25* gp.tileSize;
        gp.display[40].worldY = 18 * gp.tileSize;

        gp.display[41] = new DisplayBridge();
        gp.display[41].worldX = 26* gp.tileSize;
        gp.display[41].worldY = 18 * gp.tileSize;

        gp.display[42] = new DisplayBridge();
        gp.display[42].worldX = 27* gp.tileSize;
        gp.display[42].worldY = 18 * gp.tileSize;

        gp.display[43] = new DisplayBridge();
        gp.display[43].worldX = 28* gp.tileSize;
        gp.display[43].worldY = 18 * gp.tileSize;

        gp.display[44] = new DisplayBridge();
        gp.display[44].worldX = 29* gp.tileSize;
        gp.display[44].worldY = 18 * gp.tileSize;

        gp.display[45] = new CollisionUnder();
        gp.display[45].worldX = 30 * gp.tileSize;
        gp.display[45].worldY = 19 * gp.tileSize;

        gp.display[46] = new CollisionUnder();
        gp.display[46].worldX = 30 * gp.tileSize;
        gp.display[46].worldY = 20 * gp.tileSize;

        gp.display[47] = new CollisionUnder();
        gp.display[47].worldX = 30* gp.tileSize;
        gp.display[47].worldY = 21 * gp.tileSize;

        gp.display[48] = new CollisionUnder();
        gp.display[48].worldX = 21* gp.tileSize;
        gp.display[48].worldY = 19 * gp.tileSize;

        gp.display[49] = new CollisionUnder();
        gp.display[49].worldX = 21* gp.tileSize;
        gp.display[49].worldY = 20 * gp.tileSize;

        gp.display[50] = new CollisionUnder();
        gp.display[50].worldX = 21* gp.tileSize;
        gp.display[50].worldY = 21 * gp.tileSize;

        gp.display[51] = new Button(0);
        gp.display[51].worldX = 1 * gp.tileSize;
        gp.display[51].worldY = 5 * gp.tileSize;
        ((Button)gp.display[51]).state = 1; // Le premier est BLEU

        gp.display[52] = new Button(1);
        gp.display[52].worldX = 1 * gp.tileSize;
        gp.display[52].worldY = 20 * gp.tileSize;

        gp.display[53] = new Button(2);
        gp.display[53].worldX = 40 * gp.tileSize;
        gp.display[53].worldY = 3 * gp.tileSize;

        gp.display[54] = new Door();
        gp.display[54].name = "Door";
        gp.display[54].collision = true;
        gp.display[54].worldX = 33 * gp.tileSize;
        gp.display[54].worldY = 31 * gp.tileSize;

        gp.display[55] = new Door();
        gp.display[55].name = "Door";
        gp.display[55].collision = true;
        gp.display[55].worldX = 33 * gp.tileSize;
        gp.display[55].worldY = 32 * gp.tileSize;

        gp.display[56] = new Door();
        gp.display[56].name = "Door";
        gp.display[56].collision = true;
        gp.display[56].worldX = 33 * gp.tileSize;
        gp.display[56].worldY = 33 * gp.tileSize;

        gp.display[57] = new Button(3);
        gp.display[57].worldX = 48 * gp.tileSize;
        gp.display[57].worldY = 22 * gp.tileSize;

        gp.display[58] = new Button(4);
        gp.display[58].worldX = 3 * gp.tileSize;
        gp.display[58].worldY = 48 * gp.tileSize;

        gp.display[59] = new Button(5);
        gp.display[59].worldX = 11 * gp.tileSize;
        gp.display[59].worldY = 48 * gp.tileSize;
    
    }
    

    public void setMob() {

    }

    public void setDisplayObjectMap2() {
        
    }

    public void setMaskMap2() {
    }

}
