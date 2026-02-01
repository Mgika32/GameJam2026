package main;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import DisplayObject.*; // Indispensable pour reconnaître Button et Door

public class EventHandler {
    
    Rectangle eventRect;
    Rectangle eventRectLarge;
    int eventRectDefaultX, eventRectDefaultY;

    GamePanel gp;
    Graphics2D g2;

    // Variables pour le Puzzle de la Map 1
    int currentButtonNeeded = 0; 
    int totalButtons = 6;
    
    public boolean allMobsDeads = false;

    public EventHandler(GamePanel gp){
        this.gp = gp;
        
        eventRect = new Rectangle();
        eventRect.x = 23;
        eventRect.y = 23;
        eventRect.width = gp.tileSize + 5;
        eventRect.height = gp.tileSize + 5;
        
        eventRectLarge = new Rectangle();
        eventRectLarge.x = 23;
        eventRectLarge.y = 23;
        eventRectLarge.width = gp.tileSize * 6;
        eventRectLarge.height = gp.tileSize * 5;
        
        eventRectDefaultX = eventRect.x;
        eventRectDefaultY = eventRect.y;
    }

    // =========================================================================
    //                            INTERACTIONS (BOUTONS / PORTES)
    // =========================================================================

    public void interactButton(int i) {
        // Sécurité : si l'index est invalide ou l'objet vide, on ne fait rien
        if (i == 999 || gp.display[i] == null) return;

        if (gp.display[i] instanceof Button) {
            Button btn = (Button) gp.display[i];

            // --- CAS 1 : INTERRUPTEUR ON/OFF (Map 4) ---
            if (btn.togglable) {
                if (btn.state == 0) {
                    btn.state = 1; // Allumé
                    if (btn.doorToOpen != -1) openDoorById(btn.doorToOpen);
                } else {
                    btn.state = 0; // Éteint
                }
                return; 
            }

            // --- CAS 2 : BOUTON SIMPLE AVEC CIBLE (Map 4) ---
            if (btn.doorToOpen != -1 && btn.state == 0) {
                btn.state = 2; // Rouge définitif
                openDoorById(btn.doorToOpen);
            }

            // --- CAS 3 : PUZZLE SÉQUENTIEL (Map 1) ---
            else if (btn.doorToOpen == -1 && btn.buttonID == currentButtonNeeded && btn.state == 1) {
                btn.state = 2; 
                currentButtonNeeded++; 

                if (currentButtonNeeded == totalButtons) {
                    openPuzzleDoor(); // Ouvre la porte finale du puzzle
                } else {
                    activateNextButton(); // Active le bouton suivant de la séquence
                }
            }
        }
    }

    public void openDoorById(int idToFind) {
        for (int i = 0; i < gp.display.length; i++) {
            if (gp.display[i] instanceof Door) {
                Door door = (Door) gp.display[i];
                if (door.doorID == idToFind) {
                    gp.display[i] = null; // Supprime la porte
                }
            }
        }
    }

    public void openPuzzleDoor() {
        for (int i = 0; i < gp.display.length; i++) {
            // Ouvre les portes qui n'ont pas d'ID spécifique (-1)
            if (gp.display[i] instanceof Door && ((Door)gp.display[i]).doorID == -1) {
                gp.display[i] = null; 
            }
        }
    }

    public void activateNextButton() {
        for (int i = 0; i < gp.display.length; i++) {
            if (gp.display[i] instanceof Button) {
                Button btn = (Button) gp.display[i];
                if (btn.buttonID == currentButtonNeeded && btn.doorToOpen == -1) {
                    btn.state = 1; // Bleu
                }
            }
        }
    }

    // =========================================================================
    //                            DÉTECTION D'EVENTS (MARCHE)
    // =========================================================================

    public boolean hit(int eventCol, int eventRow, String reqDirection, boolean isLarge, boolean isHeight) {
        boolean hit = false;

        gp.player1.solidArea.x = gp.player1.worldX + gp.player1.solidArea.x;
        gp.player1.solidArea.y = gp.player1.worldY + gp.player1.solidArea.y;

        Rectangle targetRect = isLarge ? eventRectLarge : eventRect;
        targetRect.x = eventCol * gp.tileSize + eventRectDefaultX;
        targetRect.y = eventRow * gp.tileSize + eventRectDefaultY;

        if (gp.player1.solidArea.intersects(targetRect)) {
            if (reqDirection != null && (gp.player1.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any"))) {
                hit = true;
            }
        }

        gp.player1.solidArea.x = gp.player1.solidAreaDefaultX;
        gp.player1.solidArea.y = gp.player1.solidAreaDefaultY;
        
        return hit;
    }

    // --- EVENTS MAP 0 ---
    public void checkEventMap0() {
        if (hit(23, 6, "any", false, false)) {switchMap(1, "/res/map/map1.txt",50,50,26,47);}
        if (hit(24, 6, "any", false, false)) {switchMap(1, "/res/map/map1.txt",50,50,26,47);}
        if (hit(25, 6, "any", false, false)) {switchMap(1, "/res/map/map1.txt",50,50,26,47);}
        if (hit(26, 6, "any", false, false)) {switchMap(1, "/res/map/map1.txt",50,50,26,47);}
        
        if (hit(38, 17, "any", false, false) || hit(38, 18, "any", false, false) || hit(38, 19, "any", false, false)) { 
            switchMap(2, "/res/map/map2.txt", 50, 32, 24, 30); 
        }  
        if (hit(20, 46, "any", false, false)) {switchMap(6, "/res/map/map6.txt",50,50,25,2);}
        if (hit(21, 46, "any", false, false)) {switchMap(6, "/res/map/map6.txt",50,50,25,2);}

        if (hit(9, 17, "any", false, false)) {switchMap(3, "/res/map/map3.txt",50,32,50-21,30);}
        if (hit(9, 18, "any", false, false)) {switchMap(3, "/res/map/map3.txt",50,32,50-21,30);}
        if (hit(9, 19, "any", false, false)) {switchMap(3, "/res/map/map3.txt",50,32,50-21,30);}

        if (hit(39, 36, "any", false, false)) {switchMap(4, "/res/map/map4.txt",67,51,5,48);}

        if (hit(9, 50, "any", false, false)) {switchMap(5, "/res/map/map5.txt",28,32,20,20);}
    }

    // --- EVENTS MAP 1 ---
    public void checkEventMap1() {
        if (hit(26, 49, "any", false, false)) {switchMap(0, "/res/map/map0.txt",50,50,25,8);}
        if (hit(27, 49, "any", false, false)) {switchMap(0, "/res/map/map0.txt",50,50,25,8);}
        if (hit(25, 49, "any", false, false)) {switchMap(0, "/res/map/map0.txt",50,50,25,8);}
        
        if (hit(32, 23, "up", false, false)) {gp.under = false;}
        if (hit(32, 23, "down", false, false)) {gp.under = true;}
        if (hit(31, 23, "up", false, false)) {gp.under = false;}
        if (hit(31, 23, "down", false, false)) {gp.under = true;}
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
        if (hit(30, 31, "any", false, false)) {
            switchMap(2, "/res/map/map2.txt",50,32,39,0);
            for (int i = 0; i < gp.monster.length; i++) { gp.monster[i] = null; }
        }
        if (allMobsDeads == false) {
            checkAllMobsDeads();
            allMobsDeads = true;
        }
        if (hit(31, 31, "any", false, false)) {
            switchMap(2, "/res/map/map2.txt",50,32,39,0);
            for (int i = 0; i < gp.monster.length; i++) { gp.monster[i] = null; }
        }
        if (allMobsDeads == false) {
            checkAllMobsDeads();
            allMobsDeads = true;
        }
        if (hit(29, 31, "any", false, false)) {
            switchMap(2, "/res/map/map2.txt",50,32,39,0);
            for (int i = 0; i < gp.monster.length; i++) { gp.monster[i] = null; }
        }
        if (allMobsDeads == false) {
            checkAllMobsDeads();
            allMobsDeads = true;
        }
    }

    
    public void checkEventMap6() {
        if (hit(-1, 44, "any", false, false)) {switchMap(0, "/res/map/map0.txt",50,70,18,52);}
        if (hit(-1, 45, "any", false, false)) {switchMap(0, "/res/map/map0.txt",50,70,18,52);}
        if (hit(-1, 46, "any", false, false)) {switchMap(0, "/res/map/map0.txt",50,70,18,52);}
    }

    // =========================================================================
    //                            CHANGEMENT DE MAP
    // =========================================================================

    // Note : mapIndex est un double pour gérer "2.1"
    public void switchMap(double mapIndex, String path, int cols, int rows, int spawnX, int spawnY) {
        // On convertit en int pour gp.currentMap si nécessaire, ou on change le type dans GamePanel
        // Ici je le cast en int pour la compatibilité avec currentMap qui doit être un int
        gp.currentMap = (int) mapIndex;
        // Si 2.1 est important, il faudra changer le type de currentMap dans GamePanel
        if(mapIndex == 2.1) gp.currentMap = 21; // Astuce : on utilise 21 pour 2.1

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
            gp.aSetter.setMonsterMap4(); // Charge les fantômes
        }
        else if (mapIndex == 6) {
            gp.aSetter.setDisplayObjectMap6();
            gp.aSetter.setMaskMap6();
        }
    }

    public void checkEventMap3() {
        if (hit(50-27, 31, "any", false, false)) {
            switchMap(0, "/res/map/map0.txt",50,70,13,20);
        }
    }

    public void checkEventMap4() {
        if (hit(3, 50, "any", false, false)) {switchMap(0, "/res/map/map0.txt",50,70,39,36);}
        
    }

    public void checkEventMap5() {
        if (hit(9, 17, "any", false, false)) {switchMap(0, "/res/map/map0.txt",50,32,50-21,30);}
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