package util;

import java.util.*;

/**
 * see {@link Observer} for a description of the observer pattern
 *
 * @implNote make sure that when an observable object is destroyed, clearObservers() is also called to prevent memory leaks
 * @author Nathan
 */
public class Observable {
    
    private HashSet<Observer> observers = new HashSet<>();
    private static HashSet<Observer> staticObservers = new HashSet<>();
    
    public static void addStaticObserver(Observer observer) {
        staticObservers.add(observer);
    }
    
    public static void removeStaticObserver(Observer observer) {
        staticObservers.remove(observer);
    }
    
    public static void clearStaticObservers(){
        staticObservers.clear();
    }
    
    public static void notifyStaticObservers (EventType event){
        for (Observer observer : staticObservers) {
            observer.update(null, event);
        }
    }
    
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
