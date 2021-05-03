package application;

import controller.TTRController;

/**
 *
 * Nathan:
 *  Observers:
 *     The observer pattern is a commonly used software design pattern particularly in game development.
 *     The basis of this pattern is a system of observers and observable objects.
 *     The observers’ update() method is called whenever an observable object emits an event.
 *     The observer can then run a method in response to any type of event it is subscribed to.
 *     This allow us to decouple classes and minimize dependencies throughout the code.
 *     As a result, changes can be made in a class without significantly changing the implementation of other classes.
 *  Saving/Loading:
 *      Saving and loading is done through the byte (de)serialization of a GameState java object which holds all the fields needed to reset a game at a certain point.
 *      When a game is saved, the state of the game is saved in an object and passed to the Serializer to be converted into bytes and serialized.
 *      When a game is loaded, a file path to the serialized file is specified and the deserializer is called to unpack the bytes into a java object
 *      which can then be used to set the TTRController state.
 *
 * Felix:
 *
 *
 *
 *
 *
 *
 * Cerena:
 *  Highlighting Available Routes:
 *      The way this feature works is that valid routes, which is an array list containing valid routes that a player could take, is fed into a method called “highlightRoutes”.
 *      The method highlightRoutes feeds the coordinates of the cities from the array list “validRoutes” into another method located in the view package called “drawLine”.
 *      The drawLine method takes the coordinates of the two cities it is fed from the highlightRoutes method and uses the graphics class in order to draw a highlighted line between two cities.
 *
 * Group: Felix, Cerena, Nathan
 * Date: May 3, 2021
 * Class: ICS4U
 * Teacher: Mr. Fernandes
 */
public class Main {

    public static void main(String[] args) {

        new TTRController();

    }

}
