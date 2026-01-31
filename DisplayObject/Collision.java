package DisplayObject;

import java.io.IOException;

import javax.imageio.ImageIO;

public class CollisionBlock extends SuperDisplayObject{


    public CollisionBlock() {
        name = "Collision";
        x = 8;
        y = 16;
        spawn = true;
         
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/DisplayObject/Collision.png"));
            
        } catch (IOException e) {
            e.printStackTrace();
        }  
        
    }
}
