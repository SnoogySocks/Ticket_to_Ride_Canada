package model;

import util.Coordinate;

import javax.swing.*;

/**
 * @author Felix
 */
public class City extends JLabel {
    
    private String name;
    private Coordinate point;
    
    public City (String name, Coordinate point) {
//        setIcon(new ImageIcon("images/city.png"));
        this.name = name;
        this.point = point;
    }
    
    public String getName () {
        return name;
    }
    
    public void setName (String name) {
        this.name = name;
    }
    
    public Coordinate getPoint () {
        return point;
    }
    
    public void setPoint (Coordinate point) {
        this.point = point;
    }
    
    @Override
    public String toString () {
        return "City{"+
                "name='"+name+'\''+
                ", point="+point+
                '}';
    }
    
}
