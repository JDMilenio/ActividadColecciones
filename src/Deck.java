import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

// Representa el mazo completo de 52 cartas de poker
public class Deck {
    private final LinkedList<Card> cards;     // cartas disponibles en el mazo
    private final List<Card>       discarded; // cartas ya extraídas

    private static final String[] PALOS  = {"Treboles", "Corazones", "Picas", "Diamantes"};
    private static final String[] VALORES = {"2","3","4","5","6","7","8","9","10","J","Q","K","A"};

    // Inicializa las 52 cartas respetando el color de cada palo
    public Deck() {
        cards     = new LinkedList<>();
        discarded = new ArrayList<>();
        for (String palo : PALOS) {
            String color = (palo.equals("Corazones") || palo.equals("Diamantes")) ? "Rojo" : "Negro";
            for (String valor : VALORES) {
                cards.add(new Card(palo, color, valor));
            }
        }
    }

    public List<Card> getDiscarded() { return discarded; }

    // Mezcla el mazo de forma aleatoria
    public void shuffle() {
        Collections.shuffle(cards);
        System.out.println("Se mezcló el Deck.");
    }

    // Extrae y muestra la primera carta del mazo
    public void head() {
        if (cards.isEmpty()) { System.out.println("El deck está vacío."); return; }
        Card c = cards.removeFirst();
        discarded.add(c);
        System.out.println(c);
        System.out.println("Quedan " + cards.size());
    }

    // Extrae y muestra una carta al azar del mazo
    public void pick() {
        if (cards.isEmpty()) { System.out.println("El deck está vacío."); return; }
        int idx = new Random().nextInt(cards.size());
        Card c = cards.remove(idx);
        discarded.add(c);
        System.out.println(c);
        System.out.println("Quedan " + cards.size());
    }

    // Extrae y muestra las primeras cinco cartas del mazo
    public Card[] hand() {
        int n = Math.min(5, cards.size());
        Card[] hand = new Card[n];
        for (int i = 0; i < n; i++) {
            hand[i] = cards.removeFirst();
            discarded.add(hand[i]);
            System.out.println(hand[i]);
        }
        System.out.println("Quedan " + cards.size());
        return hand;
    }

    public static void main(String[] args) {
        Deck deck = new Deck();

        System.out.println("=== shuffle ===");
        deck.shuffle();

        System.out.println("\n=== head ===");
        deck.head();

        System.out.println("\n=== pick ===");
        deck.pick();

        System.out.println("\n=== hand ===");
        Card[] mano = deck.hand();
        System.out.println("Cartas descartadas: " + deck.getDiscarded().size());
    }
}
