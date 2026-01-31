package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;
import main.GamePanel;
import main.UtilityTool;

    public class TileManager {
        GamePanel gp;
        public Tile[] tile;
        public int mapTileNum[][];
        public String path = "/res/map/map0.txt";

    public TileManager(GamePanel gp) {
        this.gp = gp;

        tile = new Tile[5000];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];

        getTileImage();
        loadMap(path, gp.maxWorldCol, gp.maxWorldRow);
    }

    public void setup(int index, String imageName, boolean collision) {
        UtilityTool uTool = new UtilityTool();

        try {
            tile[index] = new Tile();
            File f = new File("res/tiles/" + imageName + ".png");
            tile[index].image = ImageIO.read(f); //ImageIO.read(getClass().getResourceAsStream("res/tiles/" + imageName + ".png"));
            tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision = collision;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getTileImage() {
        // Terrain
        setup(10, "grass", false);
        setup(20, "path", false);
        setup(25, "BlackGround", false);
        setup(26, "WoodPlanks", false);
        setup(30, "wall", true);
        setup(50, "Collision", true);

        
    }


    public void loadMap(String filePath, int newMaxCol, int newMaxRow) {
    
        // 1. Mettre à jour les dimensions dans GamePanel
        gp.maxWorldCol = newMaxCol;
        gp.maxWorldRow = newMaxRow;

        // 2. Réinitialiser le tableau avec la NOUVELLE taille
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];

        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
                String line = br.readLine();
                if (line == null) break; // Sécurité si le fichier est plus court

                String numbers[] = line.split(" ");
                while (col < gp.maxWorldCol) {
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == gp.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();
        } catch (Exception e) {
            System.out.println("Erreur chargement map : " + e.getMessage());
        }
    }

    
    public void draw(Graphics2D g2) {
        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player1.worldX + gp.player1.screenX;
            int screenY = worldY - gp.player1.worldY + gp.player1.screenY;

            if (worldX + gp.tileSize > gp.player1.worldX - gp.player1.screenX &&
                worldX - gp.tileSize < gp.player1.worldX + gp.player1.screenX &&
                worldY + gp.tileSize > gp.player1.worldY - gp.player1.screenY &&
                worldY - gp.tileSize < gp.player1.worldY + gp.player1.screenY) {

                if (tile[tileNum] != null) {
                    g2.drawImage(tile[tileNum].image, screenX, screenY, null);
                }
            }
            worldCol++;
            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }
    }
}