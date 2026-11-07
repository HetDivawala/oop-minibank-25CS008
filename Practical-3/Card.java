public class Card {

    String rank;
    String suite;

    Card(String rank, String suite) {
        this.rank = rank.substring(0, 1).toUpperCase() + rank.substring(1);
        this.suite = suite.substring(0, 1).toUpperCase() + suite.substring(1);
    }

    @Override
    public String toString() {
        return rank + " of " + suite;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;

        Card card = (Card) o;

        if (java.util.Objects.equals(rank, card.rank) &&
            java.util.Objects.equals(suite, card.suite))
            return true;
        else
            return false;
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(rank + suite);
    }
}

class Driver {
    public static void main(String[] args) {

        final int MAX = 2;

        Card[] cards = new Card[MAX];

        String rank = "";
        String suite = "";
        boolean duplicateFlag = false;

        java.util.Scanner sc = new java.util.Scanner(System.in);

        for (int i = 0; i < MAX; ++i) {

            rank = sc.next();
            suite = sc.next();

            for (int j = 0; j < i; j++) {

                if (java.util.Objects.equals(cards[j].rank, rank) &&
                    java.util.Objects.equals(cards[j].suite, suite)) {

                    System.out.println("Duplicate found: " + cards[j]);
                    duplicateFlag = true;
                    break;
                }
            }

            if (!duplicateFlag)
                cards[i] = new Card(rank, suite);
        }

        sc.close();
    }
}