package verbs;

import core.*;

import java.util.Random;

public class Bless extends Verb {

    public Bless() {
        super("bless", Verb.usage().bare(), "God bless", "b l e s s", "55318", "BLESS",
                "B L E S S", "8 1 3 5 5", "81355", "81ESS", "81E55", "bless");
    }

    @Override
    public void run(final Command command, final Context context) {
        final Player player = context.getPlayer();
        final Random rand = new Random();
        final int n = rand.nextInt(10);
        if (n == 0) {
            System.out.println("You don't know why but you feel better");
        } else if (n == 1 || n == 5) {
            System.out.println("Nothing happens . . .");
        } else if (n == 2 || n == 4) {
            System.out.println("Papa bless.");
        } else if (n == 3 || n == 6) {
            System.out.println("You hear the dark chuckling of evil overlord Booth");
        } else if (n == 3 || n == 6) {
            System.out.println("Good idea blessing is super effective");
        } else if (n == 7 || n == 8) {
            System.out.println("May your goats grow and prosper.");
        } else {
            System.out.println("You hear a very powerful sneeze");
        }
    }
}
