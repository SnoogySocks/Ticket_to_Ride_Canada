package controller;

import model.CardColour;
import model.Player;
import model.TrainCard;

import java.util.Collections;
import java.util.Stack;

import util.*;

import javax.swing.*;

/**
 * / This class contains methods to manage the train cards including creating the train card deck, dealing the train cards,
 * / dealing a single train card to a specific player, flipping over five cards, checking for three rainbow cards showing,
 * / and flipping over the next card from the top of the deck
 * /
 * /@Cerena
 */

public class TrainCardController extends Observable {
    
    private int cardsTaken = 0;
    
    public Stack<TrainCard> generateTrainCardDeck () {
        
        Stack<TrainCard> stack = new Stack<>();
        
        CardColour[] values = CardColour.values();
        //Generates 12 of each colour
        for (int i = 1; i<values.length; ++i) {
            if (values[i]!=CardColour.GRAY) {
                for (int j = 0; j<12; j++) {
                    stack.push(new TrainCard(values[i]));
                }
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
                player.addCard(TTRController.trainCardDeck.pop());
            }
        }
        
    }
    
    public void giveShownCard (int index) {
        
        TrainCard card = TTRController.shownCards.get(index);
        
        if (card==null) {
            return;
        }
        
        //If the rainbow card is selected from the shownCards then player only receives 1 rainbow card
        if (card.getColour()==CardColour.RAINBOW) {
            cardsTaken = 0;
            notifyStaticObservers(EventType.LOCK_CONTROLS);
        } else {

            TTRController.getCurrentPlayer().addCard(card);
            replaceTakenTrainCard(index);
            cardsTaken++;

            notifyStaticObservers(EventType.CARD_TAKEN);

            if (cardsTaken == 2) {
                cardsTaken = 0;
                notifyStaticObservers(EventType.LOCK_CONTROLS);
            }
        }
    }
    
    public void giveDeckCard () {
        
        if (!TTRController.trainCardDeck.isEmpty()) {
            
            TrainCard card = TTRController.trainCardDeck.pop();
            JOptionPane.showMessageDialog(TTRController.frame,
                    "You got a "+card.getColour().toString()+" card!",
                    "Alert", JOptionPane.INFORMATION_MESSAGE);
            
            TTRController.getCurrentPlayer().addCard(card);
            cardsTaken++;
            notifyStaticObservers(EventType.CARD_TAKEN);
            
        }

        if (cardsTaken>=2) {
            cardsTaken = 0;
            notifyStaticObservers(EventType.LOCK_CONTROLS);
        }
        
    }
    
    public void flipFiveCards () {
        TTRController.shownCards.clear();
        for (int i = 0; i<Math.min(TTRController.trainCardDeck.size(), 5); i++) {
            TTRController.shownCards.add(TTRController.trainCardDeck.pop());
        }
        
        System.out.println("[TC CONTROLLER] Flipped Five Cards");
        notifyObservers(EventType.UPDATE_SHOWN_CARDS);
    }
    
    public boolean checkForThreeRainbowCards () {
        
        int rainbowCardCounter = 0;
        for (TrainCard card : TTRController.shownCards) {
            if (card.getColour()==CardColour.RAINBOW){
                rainbowCardCounter++;
            }
        }
        
        if (rainbowCardCounter==3) {
            TTRController.trainCardDiscards.addAll(TTRController.shownCards);
            flipFiveCards();
            return true;
        }
        return false;
        
    }
    
    public void replaceTakenTrainCard (int index) {
        
        //TODO if the stack is empty we switch to the discard pile
        if (TTRController.trainCardDeck.isEmpty()) {
            TTRController.trainCardDeck = (Stack<TrainCard>) TTRController.trainCardDiscards.clone();
            TTRController.trainCardDiscards.clear();
        }
        
        if (!TTRController.trainCardDeck.isEmpty()) {
            
            TTRController.shownCards.set(index, TTRController.trainCardDeck.pop());
            if (checkForThreeRainbowCards()) {
                JOptionPane.showMessageDialog(TTRController.frame,
                        "Three rainbow cards were found! Redrawing...",
                        "Alert", JOptionPane.INFORMATION_MESSAGE);
                cardsTaken = 0;
            }
            notifyObservers(EventType.UPDATE_SHOWN_CARDS);
            
        } else {
            
            // if there are no cards left set the button to be invisible
            TTRController.frame.getCardPanel().getTrainButtons()[index].setEnabled(false);
            TTRController.frame.getCardPanel().getTrainButtons()[index].setIcon(null);
            TTRController.frame.getCardPanel().getTrainButtons()[index].setText("No available cards");
            
        }
        
    }
    
}
