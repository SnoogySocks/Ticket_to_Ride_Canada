package controller;

import model.City;
import model.Route;
import model.Ticket;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import util.*;

import java.util.Scanner;

public final class FileImportController {
    
    private static final String routePath = "./files/routes.txt";
    private static final String cityPath = "./files/cities.txt";
    private static final String ticketPath = "./files/tickets.txt";
    
    public static final ArrayList<Route> routes = new ArrayList<>();
    public static final ArrayList<City> cities = new ArrayList<>();
    public static final Stack<Ticket> tickets = new Stack<>();
    
    private static final HashMap<String, City> cityMap = new HashMap<>();
    
    public static void init () {
        
        importCities();
        importRoutes();
        importTickets();
        
        Collections.shuffle(tickets);
        
    }
    
    /**
     * returns a sanitized string name
     * @author Nathan, Felix
     */
    private static String sanitize(String s){
        return s.replace("\n", "").replace("\r", "");
    }
    
    /**
     * @implNote call this before importRoutes()
     * @author Nathan
     */
    private static void importCities() {
        
        try {
            
            Scanner input = new Scanner(new File(cityPath));
            input.useDelimiter(",");
            
            while (input.hasNext()){
                
                String name = sanitize(input.next());
                int x = input.nextInt();
                int y = input.nextInt();
                
                City city = new City(name, new Coordinate(x,y));
                
                cities.add(city);
                cityMap.put(name, city);
                
            }
            
            input.close();
            
        } catch (FileNotFoundException e) {
            System.err.println(e);
        }
        
        
    }
    
    /**
     * @author Felix
     */
    private static void importRoutes() {
        
        try {
            
            Scanner input = new Scanner(new File(routePath));
            input.useDelimiter(",");
            
            while (input.hasNext()) {
                
                String city1 = input.next(), city2 = input.next();
                int length = input.nextInt();
                Color colour = ColorConverter.parseColor(input.next());
                Coordinate completionPoint = new Coordinate(input.nextInt(), input.nextInt());
                boolean isDualRoute = Boolean.parseBoolean(input.next());
                
                routes.add(new Route(cityMap.get(city1), cityMap.get(city2), length, colour, completionPoint, isDualRoute));
                
            }
            
            input.close();
            
        } catch (FileNotFoundException e) {
            System.err.println("Route not found");
        }
        
    }
    
    private static void importTickets() {
        
        try {
            
            Scanner input = new Scanner(new File(ticketPath));
            input.useDelimiter(",");
            
            while (input.hasNext()) {
                
                String firstCity = input.next(), secondCity = input.next();
                int val = input.nextInt();

                Ticket ticket = new Ticket(cityMap.get(firstCity), cityMap.get(secondCity), val, false);
                tickets.push(ticket);
                
                
                
                
            }
            
            input.close();
            
        } catch (FileNotFoundException e) {
            System.err.println("Ticket not found");
        }
        
    }
}