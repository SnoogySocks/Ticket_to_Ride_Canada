package controller;

import model.TrainCard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

/**
/ This class contains methods to manage the train cards including creating the train card deck, dealing the train cards,
/ dealing a single train card to a specific player, flipping over five cards, checking for three rainbow cards showing,
/ and flipping over the next card from the top of the deck
/
/@Cerena
*/

public class TrainCardController {

    public static void createDeck(){

        Stack<Integer> createDeck = new Stack<>();

        ArrayList<Integer> trainCardsArray = new ArrayList<>();

        //Generates 12 of each colour
        for(int i = 1; i < 9; i++){

            for( int twelveCards = 0; twelveCards < 13; twelveCards++){

                trainCardsArray.add(i);
            }

        }

        //Generate 14 rainbow cards and add to array list
        for(int x = 0; x < 15; x++){

            trainCardsArray.add(0);
        }

        //Shuffle array list
        Collections.shuffle(trainCardsArray);

        //Add array list into stack
        createDeck.addAll(trainCardsArray);


    }






}
