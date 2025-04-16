import java.util.*;

class CardCollection {
    private Map<String, List<String>> cardMap;

    public CardCollection() {
        cardMap = new HashMap<>();
    }

    public void addCard(String symbol, String cardName) {
        cardMap.computeIfAbsent(symbol, k -> new ArrayList<>()).add(cardName);
    }

    public List<String> getCardsBySymbol(String symbol) {
        return cardMap.getOrDefault(symbol, new ArrayList<>());
    }

    public void displayCards() {
        for (Map.Entry<String, List<String>> entry : cardMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CardCollection collection = new CardCollection();

        // Sample card entries
        collection.addCard("Hearts", "Ace of Hearts");
        collection.addCard("Hearts", "Queen of Hearts");
        collection.addCard("Spades", "King of Spades");
        collection.addCard("Diamonds", "Jack of Diamonds");

        System.out.println("Enter a card symbol to search:");
        String symbol = scanner.nextLine();
        
        List<String> cards = collection.getCardsBySymbol(symbol);
        if (!cards.isEmpty()) {
            System.out.println("Cards found: " + cards);
        } else {
            System.out.println("No cards found for symbol: " + symbol);
        }

        // Display all cards in collection
        System.out.println("\nComplete Card Collection:");
        collection.displayCards();

        scanner.close();
    }
}
