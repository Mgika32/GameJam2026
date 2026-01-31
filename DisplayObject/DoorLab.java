package DisplayObject;

import java.io.IOException;
import javax.imageio.ImageIO;

public class DoorLab extends SuperDisplayObject {

    public int doorID; 

    public DoorLab(int id) {
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