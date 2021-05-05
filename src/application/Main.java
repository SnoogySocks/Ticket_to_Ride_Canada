package application;

import controller.TTRController;

/**
 * This is a Java application to play the game Ticket To Ride.
 *
 * "Extra Features"
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
 *  Longest Path:
 *      At the end of the game, players who have tied or achieved the longest path are recorded with the array playersLongestPathLength.
 *      If a player’s value is 0, that means the player does not have the longest path, otherwise the player’s value will be the length of the current longest path.
 *      To calculate the longest path, all the lengths of continuous paths are examined with a depth first search algorithm.
 *      It uses the helper class Path  to keep track of the path’s length so that when it gets passed as an argument in the depth first search function it is passed by reference.
 *      Passing by reference is important because one change in a function changes the variable in every other function; the path's total length is always recorded.
 *      At the end of the function, an arraylist of the winning players is created and returned.
 *
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
