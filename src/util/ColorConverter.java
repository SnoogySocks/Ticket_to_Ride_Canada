package util;

import java.util.HashMap;
import java.awt.Color;

/**
 * Utility for string conversion to colour
 * @author Nathan
 */
public final class ColorConverter {
    
    private static HashMap<String, Color> string2ColourConversions = new HashMap<>() {{
        
        put("GRAY", Color.GRAY);
        put("RED", Color.RED);
        put("GREEN", Color.GREEN);
        put("BLUE", Color.BLUE);
        put("WHITE", Color.WHITE);
        put("PURPLE", new Color(255, 0, 171)); // hot pink best pink
        put("ORANGE", Color.ORANGE);
        put("YELLOW", Color.YELLOW);
        
    }};
    
    public static Color parseColor (String s){
        return string2ColourConversions.get(s);
    }
    
}
