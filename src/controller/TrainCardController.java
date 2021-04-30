package controller;

import model.CardColour;
import model.Player;
import model.TrainCard;

import java.util.Collections;
import java.util.Stack;

import util.*;

/**
 * / This class contains methods to manage the train cards including creating the train card deck, dealing the train cards,
 * / dealing a single train card to a specific player, flipping over five cards, checking for three rainbow cards showing,
 * / and flipping over the next card from the top of the deck
 * /
 * /@Cerena
 */

public class TrainCardController extends Observable {
    
    public Stack<TrainCard> generateTrainCardDeck () {
        
        Stack<TrainCard> stack = new Stack<>();
        
        CardColour[] values = CardColour.values();
        //Generates 12 of each colour
        for (int i = 1; i<values.length; ++i) {
            for (int j = 0; j < 12; j++) {
                stack.push(new TrainCard(values[i]));
            }
        }
        
        //Generate 14 rainbow cards and add to stack
        for (int x = 0; x<14; x++) {
            stack.push(new TrainCard(CardColour.RAINBOW));
        }
        
        //Shuffle array list and return it
        Collections.shuffle(stack);
        return stack;
        
    }
    
    public void dealTrainCards () {
        
        for (Player player : TTRController.players) {
            for (int i = 0; i<4; i++) {
                player.addTrainCard(TTRController.trainCardDeck.pop());
            }
        }
        
    }

    public void giveShownCard(int index){
        TrainCard card = TTRController.shownCards.get(index);
        TTRController.getCurrentPlayer().addTrainCard(card);
        TTRController.shownCards.remove(card);
        replaceTakenTrainCard();
    }
    
    public void dealSingleTrainCards (Player player) {
        player.addTrainCard(TTRController.trainCardDeck.pop());
    }
    
    public void flipFiveCards () {
        TTRController.shownCards.clear();
        for(int i = 0 ; i < 5; i++){
            TTRController.shownCards.add(TTRController.trainCardDeck.pop());
        }
        
        System.out.println("[TC CONTROLLER] Flipped Five Cards");
        notifyObservers(EventType.UPDATE_SHOWN_CARDS);
    }
    
    public void checkForThreeRainbowCards () {
        /*
         setup an integer counter
         
         Loop through the five shown cards
            if the card is rainbow increment the counter
            
         check if counter is greater or less than 3
            do stuff yay
         
         */

        int rainbowCardCounter = Collections.frequency(TTRController.shownCards, 0);

        if(rainbowCardCounter == 0){
            flipFiveCards();
        }



    }
    
    public void replaceTakenTrainCard() {
        //TODO if the stack is empty we switch to the discard pile

        TTRController.shownCards.add(TTRController.trainCardDeck.pop());
        notifyObservers(EventType.UPDATE_SHOWN_CARDS);
    }
    
    
    
}
