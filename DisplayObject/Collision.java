package DisplayObject;

import java.io.IOException;

import javax.imageio.ImageIO;

import java.awt.*;

public class Collision extends SuperDisplayObject{

    public boolean onlyBlocksWhenUnder = true;


    public Collision() {
        solidArea = new Rectangle(16,16,2,2);
        
        name = "Collision";
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
