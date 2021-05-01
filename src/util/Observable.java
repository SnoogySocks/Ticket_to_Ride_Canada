package util;

import java.util.*;

/**
 * see {@link Observer} for a description of the observer pattern <br/>
 *
 *
 * @implNote make sure that when an observable object is destroyed, clearObservers() is also called to prevent memory leaks
 * @author Nathan
 */
public class Observable {

    // Observers receive events from an instance of a class
    private HashSet<Observer> observers = new HashSet<>();

    //Static observers are observers that receive events from the static class itself
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

    /**
     * Emit an event that all static observers of this class will receive
     */
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

    /**
     * Emit an event that all observers of this class will receive
     */
    public void notifyObservers(EventType event) {
        for (Observer observer : observers) {
            observer.update(this, event);
        }
    }
    
}
