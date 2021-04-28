package model;

import util.Coordinate;

import javax.swing.*;
import java.util.ArrayList;

/**
 * @author Felix
 */
public class City extends JLabel {
    
    private String name;
    private Coordinate point;


    private ArrayList<Route> routes = new ArrayList<>();

    public City (String name, Coordinate point) {
//        setIcon(new ImageIcon("./images/city.png"));
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

    public ArrayList<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(ArrayList<Route> routes) {
        this.routes = routes;
    }

    /**
     * @author Nathan
     */
    public boolean playerHasConnection(Player player){
        for(Route r : routes){
            if(r.getOwner().equals(player)){
                return true;
            }
        }
        return false;
    }

    /**
     * @author Nathan
     */
    public ArrayList<Route> ownedRoutes(Player player){
        ArrayList<Route> list = new ArrayList<>();

        for(Route r: routes){
            if(r.getOwner().equals(player)){
                list.add(r);
            }
        }

        return list;
    }

    public boolean equals(City other){
        return this.name == other.name;
    }

    @Override
    public String toString () {
        return "City{"+
                "name='"+name+'\''+
                ", point="+point+
                '}';
    }
    
}
