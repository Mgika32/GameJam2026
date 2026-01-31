package main;

import Entity.entity;

public class CollisionChecker {

    GamePanel gp;

    public CollisionChecker(GamePanel gp) {
        this.gp = gp;

    }

    public void checkTile(entity entity) {

        // On définit les bords de la hitbox de l'entité
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        // CORRECTION : -1 pixel pour ne pas toucher la case suivante par erreur
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width - 1; 
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        // CORRECTION : -1 pixel ici aussi
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height - 1; 

        int entityLeftCol = entityLeftWorldX / gp.tileSize;
        int entityRightCol = entityRightWorldX / gp.tileSize;
        int entityTopRow = entityTopWorldY / gp.tileSize;
        int entityBottomRow = entityBottomWorldY / gp.tileSize;

        int tileNum1, tileNum2;

        switch (entity.direction) {
            case "up":
                // On prédit la position future
                entityTopRow = (entityTopWorldY - entity.speed) / gp.tileSize;
                tileNum1 = gp.TileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.TileM.mapTileNum[entityRightCol][entityTopRow];
                if (gp.TileM.tile[tileNum1].collision || gp.TileM.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tileSize;
                tileNum1 = gp.TileM.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gp.TileM.mapTileNum[entityRightCol][entityBottomRow];
                if (gp.TileM.tile[tileNum1].collision || gp.TileM.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileSize;
                tileNum1 = gp.TileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.TileM.mapTileNum[entityLeftCol][entityBottomRow];
                if (gp.TileM.tile[tileNum1].collision || gp.TileM.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.speed) / gp.tileSize;
                tileNum1 = gp.TileM.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = gp.TileM.mapTileNum[entityRightCol][entityBottomRow];
                if (gp.TileM.tile[tileNum1].collision || gp.TileM.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
        }
    }

    public int checkObject(entity entity, boolean player1) {
        int index = 999;

        for (int i = 0; i < gp.display.length; i++) {
            if (gp.display[i] != null) {
                
                // 1. Position actuelle de la zone solide dans le monde
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;

                gp.display[i].solidArea.x = gp.display[i].worldX + gp.display[i].solidArea.x;
                gp.display[i].solidArea.y = gp.display[i].worldY + gp.display[i].solidArea.y;

                // 2. Prédire la position selon la direction
                switch (entity.direction) {
                    case "up":    entity.solidArea.y -= entity.speed; break;
                    case "down":  entity.solidArea.y += entity.speed; break;
                    case "left":  entity.solidArea.x -= entity.speed; break;
                    case "right": entity.solidArea.x += entity.speed; break;
                }

                // 3. Vérifier l'intersection
                if (entity.solidArea.intersects(gp.display[i].solidArea)) {
                    if (gp.display[i].collision == true) {
                        
                        // --- LOGIQUE DES BLOCS CONDITIONNELS ---
                        if (gp.display[i].name.equals("Collision")) {
                            if (gp.under == false) {
                                entity.collisionOn = true;
                            }
                        } 
                        else if (gp.display[i].name.equals("CollisionUnder")) {
                            if (gp.under == true) {
                                entity.collisionOn = true;
                            }
                        } 
                        else {
                            entity.collisionOn = true;
                        }
                    }
                    
                    if (player1) { 
                        index = i; 
                    }
                }

                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                gp.display[i].solidArea.x = gp.display[i].solidAreaDefautlX; 
                gp.display[i].solidArea.y = gp.display[i].solidAreaDefautlY; 
            }
        }
        return index;
    }

    public int checkMask(entity entity, boolean player1) {

        int index = 999;

        for (int i = 0; i < gp.Mask.length; i++) {
            if (gp.Mask[i] != null && gp.Mask[i].spawn == true) {
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;

                gp.Mask[i].solidArea.x = gp.Mask[i].worldX + gp.Mask[i].solidArea.x;
                gp.Mask[i].solidArea.y = gp.Mask[i].worldY + gp.Mask[i].solidArea.y;

                switch (entity.direction) {
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        if (entity.solidArea.intersects(gp.Mask[i].solidArea)){
                            if (gp.Mask[i].collision== true) {
                                entity.collisionOn = true;
                            }
                            if (player1 == true) {
                                index = i;
                            }
                        }
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;
                        if (entity.solidArea.intersects(gp.Mask[i].solidArea)){
                            if (gp.Mask[i].collision== true) {
                                entity.collisionOn = true;
                            }
                            if (player1 == true) {
                                index = i;
                            }
                        }
                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        if (entity.solidArea.intersects(gp.Mask[i].solidArea)){
                            if (gp.Mask[i].collision== true) {
                                entity.collisionOn = true;
                            }
                            if (player1 == true) {
                                index = i;
                            }
                        }
                        break;
                    case "right":
                        entity.solidArea.x += entity.speed;
                        if (entity.solidArea.intersects(gp.Mask[i].solidArea)){
                            if (gp.Mask[i].collision== true) {
                                entity.collisionOn = true;
                            }
                            if (player1 == true) {
                                index = i;
                            }
                        }
                        break;
                    }
                    entity.solidArea.x = entity.solidAreaDefaultX;
                    entity.solidArea.y = entity.solidAreaDefaultY;
                    gp.Mask[i].solidArea.x = gp.Mask[i].solidAreaDefautlX;
                    gp.Mask[i].solidArea.y = gp.Mask[i].solidAreaDefautlY;
            }
        }


        return index;
    }

    public int checkEntity (entity entity, entity[] target){
        int index = 999;

        for (int i = 0; i < target.length; i++) {
            if (target[i] != null) {
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;

                target[i].solidArea.x = target[i].worldX + target[i].solidArea.x;
                target[i].solidArea.y = target[i].worldY + target[i].solidArea.y;

                switch (entity.direction) {
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        if (entity.solidArea.intersects(target[i].solidArea)){
                            entity.collisionOn = true;
                            index = i;
                        }
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;
                        if (entity.solidArea.intersects(target[i].solidArea)){
                            entity.collisionOn = true;
                            index = i;
                        }
                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        if (entity.solidArea.intersects(target[i].solidArea)){
                            entity.collisionOn = true;
                            index = i;
                        }
                        break;
                    case "right":
                        entity.solidArea.x += entity.speed;
                        if (entity.solidArea.intersects(target[i].solidArea)){
                            entity.collisionOn = true;
                            index = i;
                            
                        }
                        break;
                    }
                    entity.solidArea.x = entity.solidAreaDefaultX;
                    entity.solidArea.y = entity.solidAreaDefaultY;
                    target[i].solidArea.x = target[i].solidAreaDefaultX;
                    target[i].solidArea.y = target[i].solidAreaDefaultY;
            }
        }


        return index;
    }

    public void checkPlayer(entity entity) {
        entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;

                gp.player1.solidArea.x = gp.player1.worldX + gp.player1.solidArea.x;
                gp.player1.solidArea.y = gp.player1.worldY + gp.player1.solidArea.y;

                switch (entity.direction) {
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        if (entity.solidArea.intersects(gp.player1.solidArea)){
                            entity.collisionOn = true;
                        }
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;
                        if (entity.solidArea.intersects(gp.player1.solidArea)){
                            entity.collisionOn = true;
                        }
                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        if (entity.solidArea.intersects(gp.player1.solidArea)){
                            entity.collisionOn = true;
                        }
                        break;
                    case "right":
                        entity.solidArea.x += entity.speed;
                        if (entity.solidArea.intersects(gp.player1.solidArea)){
                            entity.collisionOn = true;               
                        }
                        break;
                    }
                    entity.solidArea.x = entity.solidAreaDefaultX;
                    entity.solidArea.y = entity.solidAreaDefaultY;
                    gp.player1.solidArea.x = gp.player1.solidAreaDefaultX;
                    gp.player1.solidArea.y = gp.player1.solidAreaDefaultY;
    } 

}

