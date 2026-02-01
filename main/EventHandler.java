package main;

import DisplayObject.Button;
import DisplayObject.boutonPorte;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class EventHandler {
    Rectangle eventRect;
    Rectangle eventRectLarge;
    int eventRectDefaultX, eventRectDefaultY;

    GamePanel gp;
    Graphics2D g2;

    public boolean allMobsDeads = false;


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

        if (hit(23, 6, "any", false, false)) {switchMap(1, "/res/map/map1.txt",50,50,26,47);}
        if (hit(24, 6, "any", false, false)) {switchMap(1, "/res/map/map1.txt",50,50,26,47);}
        if (hit(25, 6, "any", false, false)) {switchMap(1, "/res/map/map1.txt",50,50,26,47);}
        if (hit(26, 6, "any", false, false)) {switchMap(1, "/res/map/map1.txt",50,50,26,47);}
        if (hit(38, 17, "any", false, false) || hit(38, 18, "any", false, false) || hit(38, 19, "any", false, false)) { 
            switchMap(2, "/res/map/map2.txt", 50, 32, 24, 30);}  

        if (hit(9, 17, "any", false, false)) {switchMap(3, "/res/map/map3.txt",50,32,50-21,30);}
        if (hit(9, 18, "any", false, false)) {switchMap(3, "/res/map/map3.txt",50,32,50-21,30);}
        if (hit(9, 19, "any", false, false)) {switchMap(3, "/res/map/map3.txt",50,32,50-21,30);}

        if (hit(39, 37, "any", false, false)) {switchMap(4, "/res/map/map4.txt",67,51,2,50);}
        if (hit(39, 36, "any", false, false)) {switchMap(4, "/res/map/map4.txt",67,51,3,50);}
        if (hit(39, 35, "any", false, false)) {switchMap(4, "/res/map/map4.txt",67,51,3,50);}
        
        if (hit(20, 46, "any", false, false)) {switchMap(6, "/res/map/map6.txt",50,50,25,2);}
        if (hit(21, 46, "any", false, false)) {switchMap(6, "/res/map/map6.txt",50,50,25,2);}

    }


    public void checkEventMap2() {
        if (hit(38, -2, "any", false, false)) {switchMap(2.1, "/res/map/map2_1.txt",50,32,31,31);gp.aSetter.setMonster();}
        if (hit(37, -2, "any", false, false)) {switchMap(2.1, "/res/map/map2_1.txt",50,32,31,31);gp.aSetter.setMonster();}
        if (hit(39, -2, "any", false, false)) {switchMap(2.1, "/res/map/map2_1.txt",50,32,31,31);gp.aSetter.setMonster();}
        if (hit(23, 30, "any", false, false) || hit(22, 30, "any", false, false) || hit(24, 30, "any", false, false)) { 
            switchMap(0, "/res/map/map0.txt", 50, 70, 38, 19); 
        }  
        if (hit(24, 30, "any", false, false) || hit(22, 30, "any", false, false) || hit(24, 30, "any", false, false)) { 
            switchMap(0, "/res/map/map0.txt", 50, 70, 38, 19); 
        }
    }

    public void checkEventMap2_1() {
        if (hit(30, 31, "any", false, false)) {switchMap(2, "/res/map/map2.txt",50,32,39,0);
            for (int i = 0; i < gp.monster.length; i++) {
                gp.monster[i] = null;
            }
        }
        if (allMobsDeads == false) {
            checkAllMobsDeads();
            allMobsDeads = true;
        }
        if (hit(31, 31, "any", false, false)) {switchMap(2, "/res/map/map2.txt",50,32,39,0);
            for (int i = 0; i < gp.monster.length; i++) {
                gp.monster[i] = null;
            }
        }
        if (allMobsDeads == false) {
            checkAllMobsDeads();
            allMobsDeads = true;
        }
        if (hit(29, 31, "any", false, false)) {switchMap(2, "/res/map/map2.txt",50,32,39,0);
            for (int i = 0; i < gp.monster.length; i++) {
                gp.monster[i] = null;
            }
        }
        if (allMobsDeads == false) {
            checkAllMobsDeads();
            allMobsDeads = true;
        }
    }
    public void checkEventMap3() {
    }
    public void checkEventMap4() {

        if (hit(7, 50, "any", false, false)) {switchMap(1, "/res/map/map0.txt",50,50,39,36);}

    }

    

    public void checkEventMap6() {
        if (hit(-1, 44, "any", false, false)) {switchMap(0, "/res/map/map0.txt",50,70,18,52);}
        if (hit(-1, 45, "any", false, false)) {switchMap(0, "/res/map/map0.txt",50,70,18,52);}
        if (hit(-1, 46, "any", false, false)) {switchMap(0, "/res/map/map0.txt",50,70,18,52);}
    }
        
public void openDoorById(int idToFind) {
    for (int i = 0; i < gp.display.length; i++) {
        if (gp.display[i] instanceof DisplayObject.DoorLab) {
            DisplayObject.DoorLab door = (DisplayObject.DoorLab) gp.display[i];
            
            if (door.doorID == idToFind) {
                gp.display[i] = null;
                System.out.println("PORTE OUVERTE : ID " + idToFind);
            }
        }
    }
}

    // Remplace ta méthode interactButton par celle-ci
    public void interactButtonMap4(int i) {
        
        // Sécurité anti-crash
        if (i == 999 || gp.display[i] == null) return;

        if (gp.display[i] instanceof boutonPorte) {
            boutonPorte btn = (boutonPorte) gp.display[i];

            // --- TYPE 1 : Bouton Simple (Liaison ID) ---
            // Si le bouton a une cible (différent de -1) et n'est pas déjà appuyé
            if (btn.doorToOpen != -1 && btn.state == 0) {
                btn.state = 2; // Passe au rouge
                openDoorById(btn.doorToOpen); // Ouvre la porte associée
            }

            // --- TYPE 2 : Bouton Puzzle (Séquence) ---
            // Si le bouton n'a pas de cible (-1), c'est l'ancien système de puzzle
            else if (btn.doorToOpen == -1 && btn.buttonID == currentButtonNeeded && btn.state == 1) {
                btn.state = 2; 
                currentButtonNeeded++; 

                if (currentButtonNeeded == totalButtons) {
                    openPuzzleDoor(); // Ouvre la porte du puzzle final
                } else {
                    activateNextButton();
                }
            }
        }
    }

    // Renomme ton ancienne méthode openDoor en openPuzzleDoor pour éviter les conflits
    public void openPuzzleDoor() {
        System.out.println("Puzzle fini !");
        for (int i = 0; i < gp.display.length; i++) {
            // Cette méthode cherche juste une porte nommée "Door" sans ID spécifique
            if (gp.display[i] != null && gp.display[i].name.equals("DoorLab") && !(gp.display[i] instanceof DisplayObject.DoorLab)) {
                gp.display[i] = null; 
            }
            // Si tu veux qu'elle ouvre aussi les OBJ_Door sans ID, adapte ici
        }
    }
    

    int currentButtonNeeded = 0; 
    int totalButtons = 6;        
    

    public void interactButton(int i) {
        // On vérifie si l'objet touché est bien un bouton
        if (gp.display[i] instanceof Button) {
            Button btn = (Button) gp.display[i];

            if (btn.buttonID == currentButtonNeeded && btn.state == 1) {
                btn.state = 2; // Il devient rouge
                currentButtonNeeded++; // On attend le suivant

                // Est-ce que c'était le dernier ?
                if (currentButtonNeeded == totalButtons) {
                    openDoor();
                } else {
                    // On active le bouton suivant (il devient bleu)
                    activateNextButton();
                }
            }
        }
    }

    public void activateNextButton() {
        for (int i = 0; i < gp.display.length; i++) {
            if (gp.display[i] instanceof Button) {
                Button btn = (Button) gp.display[i];
                if (btn.buttonID == currentButtonNeeded) {
                    btn.state = 1;
                }
            }
        }
    }

    

    public void openDoor() {
        for (int i = 0; i < gp.display.length; i++) {
            if (gp.display[i] != null && gp.display[i].name.equals("Door")) {
                gp.display[i] = null; 
            }
        }
    }

    public void checkEventMap1() {
        if (hit(26, 49, "any", false, false)) {switchMap(0, "/res/map/map0.txt",50,50,25,8);}
        if (hit(27, 49, "any", false, false)) {switchMap(0, "/res/map/map0.txt",50,50,25,8);}
        if (hit(25, 49, "any", false, false)) {switchMap(0, "/res/map/map0.txt",50,50,25,8);}
        if (hit(32, 23, "up", false, false)) {gp.under = false; }
        if (hit(32, 23, "down", false, false)) {gp.under = true;}
        if (hit(31, 23, "up", false, false)) {gp.under = false; }
        if (hit(31, 23, "down", false, false)) {gp.under = true;}
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

    

    public void switchMap(double mapIndex, String path, int cols, int rows, int spawnX, int spawnY) {
        gp.currentMap = mapIndex;
        
        gp.resetEntities(); 
        
        gp.TileM.loadMap(path, cols, rows);
        
        gp.player1.worldX = spawnX * gp.tileSize;
        gp.player1.worldY = spawnY * gp.tileSize;



        if (mapIndex == 0) {
            gp.aSetter.setDisplayObjectMap0();
            gp.aSetter.setMaskMap0();
        }
        
        else if (mapIndex == 1) {
            gp.aSetter.setDisplayObjectMap1();
            gp.aSetter.setMaskMap1();
            gp.player1.speed = 5;
        }
        else if (mapIndex == 2) {
            
            gp.aSetter.setDisplayObjectMap2();
            gp.aSetter.setMaskMap2();
        }
        else if (mapIndex == 2.1) {
            gp.aSetter.setDisplayObjectMap2_1();
            gp.aSetter.setMaskMap2_1();
        }
        else if (mapIndex == 3) {
            gp.aSetter.setDisplayObjectMap3();
            gp.aSetter.setMaskMap3();
        }
        else if (mapIndex == 4) {
            gp.aSetter.setDisplayObjectMap4();
            gp.aSetter.setMaskMap4();
        }
        else if (mapIndex == 6) {
            gp.aSetter.setDisplayObjectMap6();
            gp.aSetter.setMaskMap6();
        }
        
}



    public void checkEventMap5() {
        
    }

    public void checkAllMobsDeads() {
        boolean allDead = true;
        for (int i = 0; i < gp.monster.length; i++) {
            if (gp.monster[i] != null) {
                allDead = false;
                break;
            }
        }
        if (allDead) {
            spawnBorelMask();
        }
    }

    public void spawnBorelMask() {
        for (int i = 0; i < gp.Mask.length; i++) {
            if (gp.Mask[i] == null) {
                gp.Mask[i] = new Mask.BorelMask();
                gp.Mask[i].worldX = 40 * gp.tileSize;
                gp.Mask[i].worldY = 10 * gp.tileSize;
                break;
            }
        }
    }

}
