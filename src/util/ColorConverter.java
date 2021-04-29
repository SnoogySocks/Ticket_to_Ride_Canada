package util;

import model.RouteColour;

import java.util.HashMap;

/**
 * Utility for string conversion to colour
 * @author Nathan
 */
public final class ColorConverter {
    
    private static HashMap<String, RouteColour> string2ColourConversions = new HashMap<>() {{
        for (RouteColour colour: RouteColour.values()) {
            put(colour.toString(), colour);
        }
    }};
    
    public static RouteColour parseColor (String s){
        return string2ColourConversions.get(s);
    }
    
}
