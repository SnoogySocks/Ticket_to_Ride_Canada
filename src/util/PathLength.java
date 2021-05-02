package util;

/**
 * Class to pass an integer by reference
 * @author Felix
 */

public class PathLength {
    
    private int length;
    
    public PathLength () {
        this.length = 0;
        
    }
    
    public int getLength () {
        return length;
    }
    
    public void incrementLength () {
        ++length;
    }
    
}
