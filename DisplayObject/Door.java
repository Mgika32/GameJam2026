package DisplayObject;

import java.io.IOException;
import javax.imageio.ImageIO;

public class Door extends SuperDisplayObject {

    public int doorID; // Nouvel identifiant

    // Constructeur par défaut (pour compatibilité)
    public Door() {
        this(-1); // Appelle l'autre constructeur avec un ID par défaut
    }

    // NOUVEAU Constructeur avec ID
    public Door(int id) {
        this.doorID = id;
        
        name = "Door";
        collision = true;
        
        solidArea.x = 0;
        solidArea.y = 0;
        solidArea.width = 32;
        solidArea.height = 32;
        solidAreaDefautlX = solidArea.x;
        solidAreaDefautlY = solidArea.y;

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/DisplayObject/Door.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}