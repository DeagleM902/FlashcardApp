import flashcards.Card;
import flashcards.FlashCardDeck;
import javax.swing.*;
import java.awt.*;
//Matthew Deagle, 17 Apr 2024

/**
 * Class that manages the GUI and associates functions to the buttons. 
 * Displays the current flashcard, allows the user to flip to the answer, advance to the next flashcard,
 * and shuffle the deck 
 */
class FlashCardGUI extends JFrame {
    private FlashCardDeck deck;
    private JLabel cardLabel;
    private boolean isQuestionDisplayed = true;

    /**
     * Constucts the GUI instance.
     * @param deck The deck of flashcards.
     */
    public FlashCardGUI(FlashCardDeck deck) {
        this.deck = deck;
        setTitle("Flaschard App");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initUI();
    }

    /**
     * Determines the UI elements and their positions.
     */
    private void initUI() {
        cardLabel = new JLabel("", SwingConstants.CENTER);
        JButton flipButton = new JButton("Flip Card");
        JButton nextButton = new JButton("Next Card");
        JButton shuffleButton = new JButton("Shuffle Cards");

        flipButton.addActionListener(e -> flipCard());
        nextButton.addActionListener(e -> showNextCard());
        shuffleButton.addActionListener(e -> shuffleCards());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1,3));
        buttonPanel.add(flipButton);
        buttonPanel.add(nextButton);
        buttonPanel.add(shuffleButton);

        add(cardLabel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        showCurrentCard();
    }

    /**
     * Displays the current flashcard.
     */
    private void showCurrentCard() {
        Card card = deck.getCurrentCard();
        cardLabel.setText(card.getQuestion());
        isQuestionDisplayed = true;
    }

    /**
     * Allows the user to flip between the question andthe answer associated with the current flashcard.
     */
    private void flipCard() {
        Card card = deck.getCurrentCard();
        //if/else allows the user to switch between question and answer as desired.
        if (isQuestionDisplayed) {
            cardLabel.setText(card.getAnswer());
        } else {
            cardLabel.setText(card.getQuestion());
        }
        isQuestionDisplayed = !isQuestionDisplayed;
    }

    /**
     * Advances to the next flashcard in the deck.
     */
    private void showNextCard() {
        deck.nextCard();
        showCurrentCard();
    }

    /**
     * Shuffles the deck of flashcards.
     */
    private void shuffleCards() {
        deck.shuffleCards();
        showCurrentCard();
    }
}

/**
 * Main class/method to operate the flashcard app.
 */
public class FlashCardApp {
    /**
     * Main method to operate the flashcard app.
     * @param args
     */
    public static void main(String[] args){
        FlashCardDeck deck = new FlashCardDeck();
        deck.addCard(new Card("En", "One"));
        deck.addCard(new Card("To", "Two"));
        deck.addCard(new Card("Tre", "Three"));
        deck.addCard(new Card("Fire", "Four"));
        deck.addCard(new Card("Fem", "Five"));
        deck.addCard(new Card("Seks", "Six"));
        deck.addCard(new Card("Sju", "Seven"));
        deck.addCard(new Card("Åtte", "Eight"));
        deck.addCard(new Card("Ni", "Nine"));
        deck.addCard(new Card("Ti", "Ten"));

        SwingUtilities.invokeLater(() -> {
            new FlashCardGUI(deck).setVisible(true);
        });
    }
}