package Mask;

import java.io.IOException;

import javax.imageio.ImageIO;

public class BorelMask extends SuperMask{


    public BorelMask() {
        name = "BorelMask";
        x = 8;
        y = 16;
        spawn = true;
         
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/Mask/BorelMask.png"));
            
        } catch (IOException e) {
            e.printStackTrace();
        }  
        
    }
}
