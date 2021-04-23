package util;

import java.util.*;

/**
 * @author Nathan
 *
 * java.util.Observable was deprecated so here's my implementation
 */
public class Observable {
    
    private HashSet<Observer> observers = new HashSet<>();
    
    public void addObserver(Observer observer) {
        observers.add(observer);
    }
    
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }
    
    public void clearObservers(){
        observers.clear();
    }
    
    public void notifyObservers(EventType event) {
        for (Observer observer : observers) {
            observer.update(this, event);
        }
    }
}
