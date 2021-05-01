package util;

import model.CardColour;

import java.util.HashMap;

/**
 * Utility for string conversion to colour
 * @author Nathan
 */
public final class ColourConverter {
    
    private static final HashMap<String, CardColour> string2ColourConversions = new HashMap<>() {{
        
        for (CardColour colour: CardColour.values()) {
            put(colour.toString(), colour);
        }
        
    }};
    
    public static CardColour parseColor (String s) {
        return string2ColourConversions.get(s);
    }
    
}
