package DisplayObject;

import java.io.IOException;

import javax.imageio.ImageIO;

public class DisplayBridge extends SuperDisplayObject{


    public DisplayBridge() {
        name = "Bridge";
        x = 8;
        y = 16;
        spawn = true;
         
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/DisplayObject/Bridge.png"));
            
        } catch (IOException e) {
            e.printStackTrace();
        }  
        
    }
}
