package tile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

import java.awt.Graphics2D;

public class TileManager {
    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];

    public TileManager(GamePanel gp) {
        this.gp = gp;

        tile = new Tile[100];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];

        getTileImage();
        loadMap("/res/map/map3.txt");
    }

    public void setup(int index, String imageName, boolean collision) {
        UtilityTool uTool = new UtilityTool();

        try {
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/" + imageName + ".png"));
            tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision = collision;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getTileImage() {
        // GRASS
        setup(10, "grass00", false);
        setup(11, "grass01", false);

        // WATER
        setup(12, "water00", true);
        setup(13, "water01", true);
        setup(14, "water02", true);
        setup(15, "water03", true);
        setup(16, "water04", true);
        setup(17, "water05", true);
        setup(18, "water06", true);
        setup(19, "water07", true);
        setup(20, "water08", true);
        setup(21, "water09", true);
        setup(22, "water10", true);
        setup(23, "water11", true);
        setup(24, "water12", true);
        setup(25, "water13", true);

        // ROAD
        setup(26, "road00", false);
        setup(27, "road01", false);
        setup(28, "road02", false);
        setup(29, "road03", false);
        setup(30, "road04", false);
        setup(31, "road05", false);
        setup(32, "road06", false);
        setup(33, "road07", false);
        setup(34, "road08", false);
        setup(35, "road09", false);
        setup(36, "road10", false);
        setup(37, "road11", false);
        setup(38, "road12", false);

        // DIVERS
        setup(39, "floor01", false);
        setup(40, "wall", true);

        // TREE
        setup(41, "tree_1", true);
        setup(42, "tree_2", true);
        setup(43, "tree_3", true);
        setup(44, "tree_4", true);

        // HOUSE

        setup(50, "house_1", true);
        setup(51, "house_2", true);
        setup(52, "house_3", true);
        setup(53, "house_4", true);
        setup(54, "house_5", true);
        setup(55, "house_6", true);
        setup(56, "house_7", true);
        setup(57, "house_8", true);
        setup(58, "house_9", true);
        setup(59, "house_10", true);
        setup(60, "house_11", true);
        setup(61, "house_12", true);
        setup(62, "house_13", true);
        setup(63, "house_14", true);
        setup(64, "house_15", true);
        setup(65, "house_16", true);
        setup(66, "house_17", true);
        setup(67, "house_18", true);
        setup(68, "house_19", true);
        setup(69, "house_20", true);
        setup(70, "house_21", true);
        setup(71, "house_22", true);
        setup(72, "house_23", true);
        setup(73, "house_24", true);
        setup(74, "house_25", true);
        setup(75, "house_26", false);
        setup(76, "house_27", false);
        setup(77, "house_28", false);
        setup(78, "house_29", false);
        setup(79, "house_30", false);

    }

    public void loadMap(String path) {
        try {
            InputStream is = getClass().getResourceAsStream(path);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
                String line = br.readLine();
                while (col < gp.maxWorldCol) {
                    String numbers[] = line.split(" ");
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
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            // Rendu optimisé (Culling)
            if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

                if (tile[tileNum] != null) {
                    // On ne met plus de gp.tileSize ici, car l'image est DEJA à la bonne taille !
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