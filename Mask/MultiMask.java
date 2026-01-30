package Mask;

import java.io.IOException;

import javax.imageio.ImageIO;

public class MultiMask extends SuperMask{


    public MultiMask() {
        name = "MultiMask";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/Mask/MultiMask.png"));
            
        } catch (IOException e) {
            e.printStackTrace();
        }  
    }
}
