package util;

import model.CardColour;

import java.util.HashMap;

/**
 * Utility for string conversion to colour
 * @author Nathan
 */
public final class ColorConverter {
    
    private static final HashMap<String, CardColour> string2ColourConversions = new HashMap<>() {{
        
        for (CardColour colour: CardColour.values()) {
            put(colour.toString(), colour);
        }
        // Gray card colours are considered rainbow, or wild cards
        put("GRAY", CardColour.RAINBOW);
        
    }};
    
    public static CardColour parseColor (String s){
        return string2ColourConversions.get(s);
    }
    
}
