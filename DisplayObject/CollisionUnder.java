package DisplayObject;

import java.io.IOException;

import javax.imageio.ImageIO;

import java.awt.*;

public class CollisionUnder extends SuperDisplayObject{

    public boolean onlyBlocksWhenUnder = true;


    public CollisionUnder() {
        solidArea = new Rectangle(16,16,2,2);
        
        name = "CollisionUnder";
        x = 8;
        y = 16;
        spawn = true;
        collision = true;
         
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/Collision.png"));
            
        } catch (IOException e) {
            e.printStackTrace();
        }  
        
    }
}
