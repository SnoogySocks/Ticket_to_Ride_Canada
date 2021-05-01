package model;

import java.io.Serializable;

/**
 * @author Nathan Wong
 */
public class Ticket implements Serializable {
    
    private City city1, city2;
    private int val;
    private boolean completed;
    
    public Ticket (City city1, City city2, int val, boolean completed) {
        
        this.city1 = city1;
        this.city2 = city2;
        this.val = val;
        this.completed = completed;
        
    }
    
    public City getCity1 () {
        return city1;
    }
    
    public void setCity1 (City city1) {
        this.city1 = city1;
    }
    
    public City getCity2 () {
        return city2;
    }
    
    public void setCity2 (City city2) {
        this.city2 = city2;
    }
    
    public int getVal () {
        return val;
    }
    
    public void setVal (int val) {
        this.val = val;
    }
    
    public boolean isCompleted () {
        return completed;
    }
    
    public void setCompleted (boolean completed) {
        this.completed = completed;
    }
    
    @Override
    public String toString () {
        return "Ticket{"+
                "city1="+city1.getName()+
                ", city2="+city2.getName()+
                ", val="+val+
                '}';
    }
    
}
